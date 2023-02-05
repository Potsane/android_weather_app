## ANDROID WEATHER APP (XML)
This is a simple Weather App that uses the OpenWeatherMap and the Google Places APIs.
It is a demonstation of how to use and implement modern Android development tools, Room, MVVM, Livedata & Kotlin

## GETTING STARTED
Open the app module `build.gradle` file, navigatio to the `defaultConfig` section and enter your Googple Places & OpenWeatherMap API keys
```
   buildConfigField 'String', 'WEATHER_API_KEY', "replace with valid openweather-map key"
   buildConfigField 'String', 'PLACES_API_KEY', "replace with valid Google places key"
```

## API
```
https://api.openweathermap.org
```

## TOOLS
- [Kotlin coroutines](https://kotlinlang.org/docs/coroutines-overview.html) 
- [Retrofit](https://square.github.io/retrofit/) 
- [Dagger](https://dagger.dev/) 
- [ROOM](https://developer.android.com/training/data-storage/room)
- [Google Places](https://developers.google.com/maps/documentation/places/android-sdk/overview)
- [Moshi](https://square.github.io/moshi/1.x/moshi/)

## OUTPUTS

<img src="https://user-images.githubusercontent.com/7416651/216812542-86fd7823-8985-4850-8b22-3afa6ec1826a.jpeg" width="200">&emsp;
<img src="https://user-images.githubusercontent.com/7416651/216812549-e359b4ab-be46-46de-8b7c-b4abc84f3bbe.jpeg" width="200">&emsp;
<img src="https://user-images.githubusercontent.com/7416651/216812546-917ae9e0-d191-4962-9e3b-3856a757997a.jpeg" width="200">

## License
```
Copyright 2021 Potsane Mohale

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    https://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```

 
