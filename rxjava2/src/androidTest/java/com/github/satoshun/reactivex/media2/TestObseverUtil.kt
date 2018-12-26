package com.github.satoshun.reactivex.media2

import io.reactivex.observers.TestObserver

fun <T> TestObserver<T>.firstWithAwait() = awaitCount(1).first()!!
fun <T> TestObserver<T>.first() = values()[0]!!
