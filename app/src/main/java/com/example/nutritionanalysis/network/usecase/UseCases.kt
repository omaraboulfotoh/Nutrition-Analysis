package com.example.nutritionanalysis.network.usecase

import com.example.nutritionanalysis.network.FormRemoteDataSource
import com.example.nutritionanalysis.network.Result
import com.example.nutritionanalysis.network.response.NutritionDetailsResponse
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class UseCases(
    repository: FormRemoteDataSource,
    coroutineContext: CoroutineContext = Dispatchers.IO
) {
    private var job: Job = SupervisorJob()
    private val scope = CoroutineScope(coroutineContext) + job


    class GetNutritionDetailsUseCase internal constructor(
        private val repository: FormRemoteDataSource,
        override val scope: CoroutineScope
    ) : UseCase<GetNutritionDetailsUseCase.Params, NutritionDetailsResponse>() {

        override suspend fun doWork(params: Params): Result<NutritionDetailsResponse> {
            return repository.getNutritionDetails(params.ingr)
        }

        data class Params(val ingr: MutableList<String>)
    }


    val getNutritionDetails: GetNutritionDetailsUseCase by lazy {
        GetNutritionDetailsUseCase(repository, scope)
    }
}
