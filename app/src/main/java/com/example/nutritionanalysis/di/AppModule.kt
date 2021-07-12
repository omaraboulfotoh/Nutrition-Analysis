package com.example.nutritionanalysis.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module(
    includes = [
        NetworkModule::class,
        RepositoryModule::class,
        UseCasesModule::class
    ]
)
object AppModule {

    @Singleton
    @Provides
    fun provideCoroutineDispatchers() = AppCoroutineDispatchers(
        io = Dispatchers.IO,
        computation = Dispatchers.Default,
        main = Dispatchers.Main
    )

    @Provides
    @IODispatcher
    fun providesIODispatcher(dispatchers: AppCoroutineDispatchers): CoroutineDispatcher {
        return dispatchers.io
    }

    @Provides
    @MainDispatcher
    fun providesMainDispatcher(dispatchers: AppCoroutineDispatchers): CoroutineDispatcher {
        return dispatchers.main
    }
}
