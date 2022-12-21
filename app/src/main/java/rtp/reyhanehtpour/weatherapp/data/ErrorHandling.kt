package rtp.reyhanehtpour.weatherapp.data

import rtp.reyhanehtpour.weatherapp.*


object ErrorHandling {

    private const val ERROR_TAG = "ERROR_TAG"

    fun Result.Error.getErrorModel(tag: String): Result.Error{
        println("$ERROR_TAG, class= $tag," +
                " message= ${this.error.message}," +
                " code= ${this.error.code}")
        val error= when{
            this.error.code == 400 -> RequestError(code = 400, message = ERROR_400)
            this.error.code == 401 -> RequestError(code = 401, message = ERROR_401)
            this.error.code == 403 -> RequestError(code = 403, message = ERROR_403)
            this.error.code == 500 -> RequestError(code = 500, message = ERROR_500)
            this.error.code == 404 -> RequestError(code = 404, message = ERROR_404)
            this.error.message.contains(UNABLE_RESOLVE_HOST) -> RequestError(code = null, message = NO_INTERNET)
            else -> RequestError(code = null, message = TRY_AGAIN_LATER)
        }
        return Result.Error(error)
    }

}