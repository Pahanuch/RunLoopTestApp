package com.runloopetestapp.paul.runlooptestapp.data.service

import io.reactivex.Single
import me.toptas.rssconverter.RssFeed

import retrofit2.http.GET
import retrofit2.http.Url

interface FeedService {

    @GET
    fun getRss(@Url url: String): Single<RssFeed>
}