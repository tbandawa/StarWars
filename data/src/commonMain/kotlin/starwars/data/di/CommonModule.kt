package starwars.data.di

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module
import starwars.data.repo.StarWarsRepo
import starwars.data.api.StarWarsApi
import starwars.data.viewmodel.ResourceViewModel
import starwars.data.viewmodel.ResourcesViewModel
import starwars.data.viewmodel.RootViewModel
import starwars.data.viewmodel.SearchViewModel

private val apiModule = module {
    single { StarWarsApi() }
}

private val repoModule = module {
    single { StarWarsRepo(get()) }
}

private val viewModelModule = module {
    single { RootViewModel(get()) }
    single { ResourcesViewModel(get()) }
    single { SearchViewModel(get()) }
    single { ResourceViewModel(get()) }
}

fun initKoin(appDeclaration: KoinAppDeclaration) = startKoin {
    appDeclaration()
    modules(
        apiModule,
        repoModule,
        viewModelModule
    )
}

fun initKoin(){
    startKoin {
        modules(
            apiModule,
            repoModule,
            viewModelModule
        )
    }
}

class DataHelper: KoinComponent {
    val rootViewModel: RootViewModel by inject()
    val resourcesViewModel: ResourcesViewModel by inject()
    val resourceViewModel: ResourceViewModel by inject()
    val searchViewModel: SearchViewModel by inject()
}