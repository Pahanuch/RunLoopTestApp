package com.runloopetestapp.paul.runlooptestapp.ui.main.details

import com.arellomobile.mvp.InjectViewState
import com.runloopetestapp.paul.runlooptestapp.data.repository.FeedRepositoryProvider
import com.runloopetestapp.paul.runlooptestapp.ui.base.BasePresenter

@InjectViewState
class DetailsFeedPresenter : BasePresenter<DetailsFeedView>() {

    fun getRssItem() {
        val rssItem = FeedRepositoryProvider.instance
                .getRssItem()
        viewState.showRssItem(rssItem)
    }

}