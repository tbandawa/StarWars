# <b>Star Wars</b>
Browse Films, People, Planets, Species, Starships and Vehicles from Star Wars API ([SWAPI](https://swapi.dev/)).

#### App Architecture.
* A single-activity architecture, using the Navigation component to manage fragment operations.
* Pattern Model-View-ViewModel (MVVM) facilitating separation of development of the graphical user interface.
* Modular app architecture allows being developed features in isolation, independently from other features.

#### Modules:
* <b>app</b> - the main entry point of the application.
* <b>design</b> - holds the app theme and colors.
* <b>commons</b> - contains shared libraries and code.
* <b>data</b> - contains models, repository, API(data source) and data manipulation util code.
* <b>home</b> - root resources view.
* <b>resources</b> - collection of resources view.
* <b>resource</b> - resource detail view.
* <b>search</b> - search resources.

#### App Specs
* Minimum SDK 21
* [Kotlin](https://kotlinlang.org/)
* Android Architecture Components (LiveData, Lifecycle, ViewModel, Navigation Component, ConstraintLayout)
* [Hilt](https://dagger.dev/hilt/) for dependency injection.
* [Retrofit](https://square.github.io/retrofit) for fetching data from SWAPI.
* [Timber](https://github.com/JakeWharton/timber) for logging events.
