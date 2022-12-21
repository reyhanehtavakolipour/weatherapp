package rtp.reyhanehtpour.weatherapp.data



/**
 * A generic class that holds a value with its loading status.
 * @param <T>
 */
sealed class Result<out R> {

    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val error: RequestError) : Result<Nothing>()
    object Loading : Result<Nothing>()
    object EmptyResult: Result<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[exception=${error.message}]"
            is EmptyResult -> "Empty Result"
            Loading -> "Loading"
        }
    }
}



data class RequestError(
    val code: Int?,
    val message: String
)


/**
 * `true` if [Result] is of type [Success] & holds non-null [Success.data].
 */
val Result<*>.succeeded
    get() = this is Result.Success && data != null

val Result<*>.failed
    get() = this is Result.Error

val Result<*>.isEmpty
    get() = this is Result.EmptyResult


val <T> Result<T>.data: T
    get() = (this as Result.Success).data

val <T> Result<T>.errorMessage: String
    get() = (this as Result.Error).error.message

