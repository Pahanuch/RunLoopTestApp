package com.runloopetestapp.paul.runlooptestapp.ui.main.fragments.rssfeeds

import com.arellomobile.mvp.InjectViewState
import com.runloopetestapp.paul.runlooptestapp.data.api.addSchedulers
import com.runloopetestapp.paul.runlooptestapp.data.repository.FeedRepositoryProvider
import com.runloopetestapp.paul.runlooptestapp.ui.base.BasePresenter
import io.reactivex.disposables.Disposable
import me.toptas.rssconverter.RssItem
import java.util.concurrent.TimeUnit

@InjectViewState
class FeedFragmentPresenter : BasePresenter<FeedFragmentView>() {

    private var bussinessDispose : Disposable? = null
    private var environmentDispose : Disposable? = null
    private var entertainmentDispose : Disposable? = null

    override fun onDestroy() {
        dispose()
    }

    fun loadBussinessFeeds() {

        bussinessDispose = FeedRepositoryProvider.instance
                .getBussinessFeeds()
                .addSchedulers()
                .doFinally { viewState.hideLoading() }
                .repeatWhen { completed -> completed.delay(5, TimeUnit.SECONDS) }
                .subscribe({
                        viewState.showRssBusinessFeeds(it.items)
                    }, this::handleError)
    }

    fun loadEntertainmentFeeds() {

        entertainmentDispose = FeedRepositoryProvider.instance
                .getEntertainmentFeeds()
                .addSchedulers()
                .doFinally { viewState.hideLoading() }
                .repeatWhen { completed -> completed.delay(5, TimeUnit.SECONDS) }
                .subscribe({
                    viewState.showRssEntertainmentFeeds(it.items)
                }, this::handleError)
    }

    fun loadEnvironmentFeeds() {

        environmentDispose = FeedRepositoryProvider.instance
                .getEnvironmentFeeds()
                .addSchedulers()
                .doFinally { viewState.hideLoading() }
                .repeatWhen { completed -> completed.delay(5, TimeUnit.SECONDS) }
                .subscribe({
                    viewState.showRssEnvironmentFeeds(it.items)
                }, this::handleError)

    }

    fun setRssFeed(rssItem: RssItem){
        FeedRepositoryProvider.instance
                .setRssItem(rssItem = rssItem)
    }

    fun dispose(){
        if (bussinessDispose != null) bussinessDispose!!.dispose()
        if (environmentDispose != null) environmentDispose!!.dispose()
        if (entertainmentDispose != null) entertainmentDispose!!.dispose()
    }

}