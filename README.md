# smartystreets-scala-sdk
Scala SDK for SmartyStreets (https://smartystreets.com)

## SBT Configuration

```
libraryDependencies += "com.outr" %% "smartystreets-scala-sdk" % "1.0.6"
```

## Initialization

Create an instance of `SmartyStreets`:

```scala
val ss = new SmartyStreets(authId = "", authToken = "")
```

Note: it is usually not ideal to inline details like this into your code as it can change and for security reasons.

This project relies on [Profig](https://github.com/outr/profig) to allow configuration to be defined as:
 * Command-line arguments (`-smartystreets.authId=abc123 -smartystreets.authToken=abc123` and loaded with `Profig.merge(args)`)
 * Configuration JSON (stored in `config.json` and loaded with `Profig.loadDefaults()`):
    ```json
   {
     "smartystreets": {
       "authId": "abc123",
       "authToken": "abc123"
     }
   }
    ```
 * Environment Variables (`SMARTY_STREETS_AUTH_ID=abc123` and `SMARTY_STREETS_AUTH_TOKEN=abc123`)
 
 If any of those external configuration options are available simply instantiate `SmartyStreets` with default arguments:
 
 ```scala
 val ss = new SmartyStreets()
 ```
 
## US Address Lookup

Single address lookup is as simple as:

```scala
val validated: Future[List[StreetAddress]] = ss.streets.us(street = Some("345 Spear Street San Francisco, CA"))
```

## US Zip Lookup

Single ZIP lookup is as simple as:

```scala
val validated: Future[List[Zip]] = ss.zip.us(zipcode = Some("84101"))
```

## Limitations

Currently, no support has been added for international validations or autocompletion.