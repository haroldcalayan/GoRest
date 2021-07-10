# GoRest

This is a mini Android Application written in Kotlin for a day. The purpose of this is to just showcase the latest technologies in Android development. This is app consuming APIs from https://gorest.co.in/.

## Installation
Clone this repository and import into **Android Studio**
```bash
git clone git@github.com:haroldcalayan/GoRest.git
```

## Configuration
### Keystores:
Create `app/keystore.gradle` with the following info:
```gradle
ext.signing = [
    storePassword   : "...",
    keyAlias        : "...",
    keyPassword     : "..."
]
```
And place both keystores under `app/keystores/` directory:
- `playstore.keystore`
- `stage.keystore`


## Build variants
Use the Android Studio *Build Variants* button to choose between **debug**, **staging** and **production**


## Generating signed APK
From Android Studio:
1. ***Build*** menu
2. ***Generate Signed APK...***
3. Fill in the keystore information *(you only need to do this once manually and then let Android Studio remember it)*

## Tech Stacks / Tools Used
* Kotlin
* MVVM
* LiveData
* Databinding
* Repository Pattern
* Hilt
* Coroutines
* Room
* Retrofit
* OkHttp
* Moshi
* Navigation Components
* AndroidX
* Binding Adapters
* UI (Google Material, ConstraintLayout, CoordinatorLayout, CollapsingToolbarLayout)
* Glide
* Timber
* Kotlin Coding Conventions - https://kotlinlang.org/docs/reference/coding-conventions.html
* Android Naming Standards - <WHAT>_<WHERE>_<DESCRIPTION>_<SIZE> ex. activity_main, edittext_signin_username
* Git Commit Standards - https://chris.beams.io/posts/git-commit/
* Git Commit Emoji - https://gist.github.com/parmentf/035de27d6ed1dce0b36a
* Implemented proper handling of secret keys
* Implemented proper handling of keystores
* Implement caching and supported offline scenario
* Supported both phone and tablet

## Maintainers
This project is mantained by:
* [Harold Calayan](https://github.com/haroldcalayan)


## Contributing

1. Fork it
2. Create your feature branch (git checkout -b my-new-feature)
3. Commit your changes (git commit -m 'Add some feature')
4. Run the linter (ruby lint.rb').
5. Push your branch (git push origin my-new-feature)
6. Create a new Pull Request