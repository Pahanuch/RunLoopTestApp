package com.runloopetestapp.paul.runlooptestapp.data.repository

object FeedRepositoryProvider {
    val instance: FeedRepository by lazy { FeedRepositoryImpl() }
}