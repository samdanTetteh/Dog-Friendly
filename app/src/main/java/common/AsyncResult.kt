package common

import android.util.Log
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single

sealed class AsyncResult<out T> {
    class Loading<out T> : AsyncResult<T>() {
        override fun equals(other: Any?) = other is Loading<*>
        override fun hashCode() = "Loading".hashCode()
    }

    data class Success<out T>(val data: T? = null) : AsyncResult<T>()
    data class Error(val error: Throwable) : AsyncResult<Nothing>() {
        override fun equals(other: Any?): Boolean {
            if (other !is Error) return false

            val otherError = other.error
            return error::class == otherError::class &&
                    error.message == otherError.message &&
                    error.stackTrace[0] == otherError.stackTrace[0]
        }

        override fun hashCode(): Int = arrayOf(error::class, error.message, error.stackTrace[0]).contentHashCode()
    }

    override fun toString(): String {
        return when (this) {
            is Loading -> "Loading"
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[exception=$error]"
        }
    }
}

fun <T> Observable<T>.mapToAsyncResult(): Observable<AsyncResult<T>> {

    return this.map<AsyncResult<T>> { data -> AsyncResult.Success(data) }
        .onErrorReturn { e ->
            AsyncResult.Error(e).also {
                log(e)
            }
        }
        .startWith(AsyncResult.Loading())
}

fun Completable.mapToAsyncResult(): Observable<AsyncResult<Nothing>> {
    return this.andThen(Single.just<AsyncResult<Nothing>>(AsyncResult.Success()))
        .onErrorReturn { e -> AsyncResult.Error(e).also { log(e) } }
        .toObservable()
        .startWith(AsyncResult.Loading())
}

fun <T> Single<T>.mapToAsyncResult(): Observable<AsyncResult<T>> {
    return this.map<AsyncResult<T>> { data -> AsyncResult.Success(data) }
        .onErrorReturn { e -> AsyncResult.Error(e).also { log(e) } }
        .toObservable()
        .startWith(AsyncResult.Loading())
}

private fun log(e: Throwable) {
    Log.e(AsyncResult::class.java.canonicalName, "$e", e)
}