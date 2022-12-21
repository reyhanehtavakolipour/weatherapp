package rtp.reyhanehtpour.weatherapp.domain.usecases

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import rtp.reyhanehtpour.weatherapp.data.ErrorHandling.getErrorModel
import rtp.reyhanehtpour.weatherapp.data.RequestError
import rtp.reyhanehtpour.weatherapp.data.Result

/**
 * Executes business logic synchronously or asynchronously using Coroutines.
 */
abstract class UseCase<in P, R, Type:RequestType>(private val coroutineDispatcher: CoroutineDispatcher) {

    private val TAG = "UseCase"

    /** Executes the use case asynchronously and returns a [Result].
     *
     * @return a [Result].
     *
     * @param parameters the input parameters to run the use case with
     */
    suspend operator fun invoke(parameters: P, type: Type): Result<R> {
        return try {
            // Moving all use case's executions to the injected dispatcher
            // In production code, this is usually the Default dispatcher (background thread)
            // In tests, this becomes a TestCoroutineDispatcher
            withContext(coroutineDispatcher) {
                if (type == RequestType.REMOTE_REQUEST){
                    remoteExecute(parameters)
                }else{
                   localExecute(parameters)
                }
            }
        } catch (e: Exception) {
            withContext(coroutineDispatcher){
                Result.Error(
                    RequestError(code = null,
                    message = e.localizedMessage.toString())
                ).getErrorModel(TAG)
            }
        }
    }

    /**
     * Override this to set the code to be executed.
     * remote and local requests could have been called in a single execution functions,
     * if kotlin flows were been used instead of livedata, both requests could have been done inside a
     * single execution function.
     */
    @Throws(RuntimeException::class)
     abstract suspend fun remoteExecute(parameters: P): Result<R>


    @Throws(RuntimeException::class)
    abstract suspend fun localExecute(parameters: P): Result<R>
}

enum class RequestType{
    REMOTE_REQUEST,
    LOCAL_REQUEST
}