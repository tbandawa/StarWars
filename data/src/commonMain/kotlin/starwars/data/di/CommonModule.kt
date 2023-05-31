package starwars.data.di

import org.koin.core.component.KoinComponent
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module
import starwars.data.repo.StarWarsRepo
import starwars.data.api.StarWarsApi
import starwars.data.viewmodel.ResourceViewModel
import starwars.data.viewmodel.ResourcesViewModel
import starwars.data.viewmodel.RootViewModel
import starwars.data.viewmodel.SearchViewModel

fun initKoin(appDeclaration: KoinAppDeclaration) = startKoin {
    appDeclaration()
    modules(
        apiModule,
        repoModule,
        viewModule
    )
}

private val apiModule = module {
    single { StarWarsApi() }
}

private val repoModule = module {
    factory { StarWarsRepo(get()) }
}

private val viewModule = module {
    single { RootViewModel(get()) }
    single { ResourcesViewModel(get()) }
    single { SearchViewModel(get()) }
    single { ResourceViewModel(get()) }
}

@Suppress("unused")
object KotlinDependencies : KoinComponent {
    fun getRootViewModel() = getKoin().get<RootViewModel>()
    fun getResourcesViewModel() = getKoin().get<ResourcesViewModel>()
    fun getSearchViewModel() = getKoin().get<SearchViewModel>()
    fun getResourceViewModel() = getKoin().get<ResourceViewModel>()
}

fun initKoin() = initKoin {}