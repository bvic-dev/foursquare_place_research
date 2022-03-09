<h1 align="center">Welcome to Foursquare Place Research 👋</h1>
<p>
  <a href="#" target="_blank">
    <img alt="License: MIT" src="https://img.shields.io/badge/License-MIT-yellow.svg" />
  </a>
</p>

## Description
A simple Android MVVM project with [compose](https://developer.android.com/jetpack/compose).
It use [Foursquare API](https://developer.foursquare.com/docs) and a local room.

## Requirements
Adding your [maps API](https://developers.google.com/maps/documentation/javascript/get-api-key) and [foursquare authorization](https://developer.foursquare.com/docs/places-api-getting-started) in local.properties

**`local.properties`**
```properties
MAPS_API_KEY=XXXXXXXX
FOURSQUARE_AUTHORIZATION_KEY=XXXXXXXX
```

## Library Used
- [Material Compose](https://developer.android.com/reference/kotlin/androidx/compose/material/package-summary) Material design and [icons](https://developer.android.com/reference/kotlin/androidx/compose/material/icons/package-summary) for compose
- [Jetpack Compose](https://developer.android.com/jetpack/compose) With [navigation](https://developer.android.com/jetpack/compose/navigation), [activity](https://developer.android.com/reference/kotlin/androidx/activity/compose/package-summary) and [ViewModel](https://developer.android.com/jetpack/compose/libraries#viewmodel) for Compose. Use it for creating the ui and navigate.
- [Coil Compose](https://coil-kt.github.io/coil/compose/) for image loading
- [Maps Compose](https://github.com/googlemaps/android-maps-compose) components for the Maps SDK
- [Coroutine](https://kotlinlang.org/docs/coroutines-overview.html) for writing and use asynchronous, non-blocking code
- [Retrofit](https://square.github.io/retrofit/) a type-safe HTTP client REST for Android
- [Logging Interceptor](https://github.com/square/okhttp/tree/master/okhttp-logging-interceptor) an OkHttp interceptor which logs HTTP request and response data.
- [Room](https://developer.android.com/training/data-storage/room) for saving data in a local database
- [Hilt](https://developer.android.com/training/dependency-injection/hilt-android) for Dependency injection

## Author

👤 **HIkira**

* Github: [@HIkira](https://github.com/HIkira)

## Show your support

Give a ⭐️ if this project helped you!
