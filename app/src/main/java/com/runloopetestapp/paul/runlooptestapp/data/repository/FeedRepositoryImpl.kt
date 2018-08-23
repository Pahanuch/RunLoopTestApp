package com.runloopetestapp.paul.runlooptestapp.data.repository

import com.runloopetestapp.paul.runlooptestapp.data.api.ApiFactory
import com.runloopetestapp.paul.runlooptestapp.data.service.FeedService
import io.reactivex.Single
import me.toptas.rssconverter.RssFeed
import me.toptas.rssconverter.RssItem

class FeedRepositoryImpl : FeedRepository {

    private var mRssItem : RssItem? = null

    override fun getBussinessFeeds(): Single<RssFeed> {
        return ApiFactory.getRetrofit(FeedService::class.java)
                .getRss("businessNews")
    }

    override fun getEnvironmentFeeds(): Single<RssFeed> {
        return ApiFactory.getRetrofit(FeedService::class.java)
                .getRss("environment")
    }

    override fun getEntertainmentFeeds(): Single<RssFeed> {
        return ApiFactory.getRetrofit(FeedService::class.java)
                .getRss("entertainment")
    }

    override fun setRssItem(rssItem: RssItem){
        mRssItem = rssItem
    }

    override fun getRssItem(): RssItem {
        return mRssItem ?: RssItem()
    }
}
