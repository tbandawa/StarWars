package starwars.data.di

import org.koin.core.component.KoinComponent
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module
import starwars.data.StarWarsSDK
import starwars.data.api.StarWarsApi
import starwars.data.viewmodel.StarWarsViewModel

fun initKoin(appDeclaration: KoinAppDeclaration) = startKoin {
    appDeclaration()
    modules(
        apiModule,
        sdkModule,
        viewModule
    )
}

private val apiModule = module {
    single { StarWarsApi() }
}

private val sdkModule = module {
    factory { StarWarsSDK(get()) }
}

private val viewModule = module {
    single { StarWarsViewModel(get()) }
}

@Suppress("unused")
object KotlinDependencies : KoinComponent {
    fun getStarWarsViewModel() = getKoin().get<StarWarsViewModel>()
}

fun initKoin() = initKoin {}