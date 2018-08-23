package com.runloopetestapp.paul.runlooptestapp.ui.main.fragments.rssfeeds

import com.arellomobile.mvp.viewstate.strategy.SingleStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.runloopetestapp.paul.runlooptestapp.ui.base.BaseView
import me.toptas.rssconverter.RssItem

interface FeedFragmentView : BaseView {

    @StateStrategyType(SingleStateStrategy::class)
    fun showRssBusinessFeeds(rssFeeds: List<RssItem>)

    @StateStrategyType(SingleStateStrategy::class)
    fun showRssEntertainmentFeeds(rssFeeds: List<RssItem>)

    @StateStrategyType(SingleStateStrategy::class)
    fun showRssEnvironmentFeeds(rssFeeds: List<RssItem>)

}