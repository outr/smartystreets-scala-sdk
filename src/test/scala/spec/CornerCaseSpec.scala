package spec

import com.smartystreets.api.{CityState, SmartyStreets, USStreetQuery, ZipStatus, ZipQuery}
import org.scalatest.{AsyncWordSpec, Matchers}
import profig.Profig

class CornerCaseSpec extends AsyncWordSpec with Matchers {
  private lazy val ss = SmartyStreets()

  "Corner Cases" should {
    "initialize properly" in {
      Profig.loadDefaults()

      ss should not be null
    }

    "properly support a zip query" in {
      ss.zip.us(ZipQuery(city = Some("Tucson"), state = Some("Arizona"))).map { zips =>
        zips.length should be(1)
      }
    }
    "properly support a bad city and state" in {
      ss.zip.us(city = Some("Glasgow"), state = Some("Scotland")).map { zip =>
        zip.status should be(ZipStatus.InvalidState)
        zip.reason should be(Some("Invalid state name or abbreviation."))
        zip.city_states should be(Nil)
        zip.zipcodes should be(Nil)
      }
    }
    "properly support a bad city for state" in {
      ss.zip.us(city = Some("Glasgow"), state = Some("Oklahoma")).map { zip =>
        zip.status should be(ZipStatus.InvalidCity)
        zip.reason should be(Some("Invalid city for the given state."))
        zip.city_states should be(Nil)
        zip.zipcodes should be(Nil)
      }
    }
    "properly support a city and zip" in {
      ss.zip.us(city = Some("Stilwell"), zipcode = Some("74960")).map { zip =>
        zip.status should be(ZipStatus.Success)
        zip.reason should be(None)
        zip.city_states should be(List(CityState("Stilwell", "OK", "Oklahoma", mailable_city = true)))
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
        address.delivery_line_1 should be("1600 Amphitheatre Pkwy")
        address.delivery_line_2 should be(None)
        address.components.city_name should be(Some("Mountain View"))
        address.components.state_abbreviation should be(Some("CA"))
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
        address.delivery_line_1 should be("1600 Amphitheatre Pkwy")
        address.delivery_line_2 should be(None)
        address.components.city_name should be(Some("Mountain View"))
        address.components.state_abbreviation should be(Some("CA"))
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
