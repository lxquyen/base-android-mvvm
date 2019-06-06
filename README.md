# base-android-mvvm-without-databinding

Sample app contains implementation of Clean architecture for Android using Kotlin, RxJava2, Dagger2, Room and MVVM pattern

<img width="1327" alt="Screen Shot 2019-06-06 at 5 19 24 PM" src="https://user-images.githubusercontent.com/21165754/59025871-52a0ba00-887f-11e9-8185-ad5ba8f4ab99.png">

## The app has following packages:
* common
  * di
  * extensions
  * glide
* data
  * mapper
  * repository
  * source
    * remote
    * local
* domain
  * model
  * repository
  * usecase
* presentation
  * activity
  * adapter
  * fragment
  * viewModel
  * widget
  
## Libraries used on the sample project:
  * RxJava/RxAndroid 2
  * Dagger 2
  * Gson
  * OkHttp3: okhttp/logging-intercepter
  * Retrofit 2
  * Glide
  * ButterKnife
  * Timber
  * SQLite - Room
  * Lifecycle
