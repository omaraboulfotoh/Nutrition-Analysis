package com.example.nutritionanalysis.di

import com.example.nutritionanalysis.network.FormRemoteDataSource
import com.example.nutritionanalysis.network.usecase.UseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class UseCasesModule {

    @Provides
    fun providesUseCases(remote: FormRemoteDataSource): UseCases {
        return UseCases(remote)
    }

    @Provides
    fun providesGetCurrenciesUseCase(useCases: UseCases): UseCases.GetNutritionDetailsUseCase {
        return useCases.getNutritionDetails
    }
}
