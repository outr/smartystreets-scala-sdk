package spec

import com.smartystreets.api.{CityState, SmartyStreets, USStreetQuery, ZipStatus}
import org.scalatest.{AsyncWordSpec, Matchers}
import profig.Profig

class CornerCaseSpec extends AsyncWordSpec with Matchers {
  private lazy val ss = new SmartyStreets()

  "Corner Cases" should {
    "initialize properly" in {
      Profig.loadDefaults()

      ss should not be null
    }
    "properly support a bad city and state" in {
      ss.zip.us(city = Some("Glasgow"), state = Some("Scotland")).map { zip =>
        zip.status should be(ZipStatus.InvalidState)
        zip.reason should be(Some("Invalid state name or abbreviation."))
        zip.cityStates should be(Nil)
        zip.zipcodes should be(Nil)
      }
    }
    "properly support a bad city for state" in {
      ss.zip.us(city = Some("Glasgow"), state = Some("Oklahoma")).map { zip =>
        zip.status should be(ZipStatus.InvalidCity)
        zip.reason should be(Some("Invalid city for the given state."))
        zip.cityStates should be(Nil)
        zip.zipcodes should be(Nil)
      }
    }
    "properly support a city and zip" in {
      ss.zip.us(city = Some("Stilwell"), zipcode = Some("74960")).map { zip =>
        zip.status should be(ZipStatus.Success)
        zip.reason should be(None)
        zip.cityStates should be(List(CityState("Stilwell", "OK", "Oklahoma", mailableCity = true)))
      }
    }
    "properly return for a valid address" in {
      ss.streets.us(
        street = Some("1600 Amphitheatre Parkway"),
        city = Some("Mountain View"),
        state = Some("California"),
        zip = Some("94043")
      ).map { addresses =>
        addresses.length should be(1)
        val address = addresses.head
        address.deliveryLine_1 should be("1600 Amphitheatre Pkwy")
        address.deliveryLine_2 should be(None)
        address.components.cityName should be(Some("Mountain View"))
        address.components.stateAbbreviation should be(Some("CA"))
      }
    }
    "properly support a bad city in address" in {
      ss.streets.us(
        street = Some("1600 Amphitheatre Parkway"),
        city = Some("Glasgow"),
        state = Some("California")
      ).map { addresses =>
        addresses.length should be(0)
      }
    }
    "properly normalize a batch address" in {
      ss.streets.us(USStreetQuery(
        street = Some("1600 Amphitheatre Parkway"),
        city = Some("Mountain View"),
        state = Some("California"),
        zip = Some("94043")
      )).map { addresses =>
        addresses.length should be(1)
        val address = addresses.head
        address.deliveryLine_1 should be("1600 Amphitheatre Pkwy")
        address.deliveryLine_2 should be(None)
        address.components.cityName should be(Some("Mountain View"))
        address.components.stateAbbreviation should be(Some("CA"))
      }
    }
    "properly handle a bad address" in {
      //59 s -w on zion rd, stilwell, ok 74960, united states
      ss.streets.us(
        street = Some("59 S -W On Zion Rd"),
        city = Some("Stilwell"),
        state = Some("OK"),
        zip = Some("74960")
      ).map { addresses =>
        addresses.length should be(0)
      }
    }
  }
}