package com.andreribeiro.moedasdigitais.util

sealed class Resource<T> {
    class Loading<T> : Resource<T>()
    class Sucess<T>(val data: T) : Resource<T>()
    class Error<T>(val throwable: Throwable) : Resource<T>()
}
