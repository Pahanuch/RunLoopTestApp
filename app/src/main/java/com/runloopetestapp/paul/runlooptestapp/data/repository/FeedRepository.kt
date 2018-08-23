package com.runloopetestapp.paul.runlooptestapp.data.repository

import io.reactivex.Single
import me.toptas.rssconverter.RssFeed
import me.toptas.rssconverter.RssItem

interface FeedRepository{

    fun getBussinessFeeds() : Single<RssFeed>

    fun getEnvironmentFeeds() : Single<RssFeed>

    fun getEntertainmentFeeds() : Single<RssFeed>

    fun setRssItem(rssItem : RssItem)

    fun getRssItem() : RssItem
}