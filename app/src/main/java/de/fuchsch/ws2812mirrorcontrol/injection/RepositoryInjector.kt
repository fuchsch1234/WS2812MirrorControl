package de.fuchsch.ws2812mirrorcontrol.injection

import dagger.Component
import de.fuchsch.ws2812mirrorcontrol.model.Repository
import javax.inject.Singleton

@Singleton
@Component(modules = [(RepositoryModule::class)])
interface RepositoryInjector {

    fun inject(repository: Repository)

    @Component.Builder
    interface Builder {

        fun build(): RepositoryInjector

        fun repositoryModule(repositoryModule: RepositoryModule): Builder

    }
}