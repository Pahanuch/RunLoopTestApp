package com.runloopetestapp.paul.runlooptestapp.ui.main.fragments.info

import android.annotation.SuppressLint
import com.arellomobile.mvp.InjectViewState
import com.runloopetestapp.paul.runlooptestapp.data.api.addSchedulers
import com.runloopetestapp.paul.runlooptestapp.data.repository.FeedRepositoryProvider
import com.runloopetestapp.paul.runlooptestapp.ui.base.BasePresenter
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

@InjectViewState
class InfoFragmentPresenter : BasePresenter<InfoFragmentView>() {

    private var dateTimeDispose : Disposable? = null

    override fun onDestroy() {
        if (dateTimeDispose != null) dateTimeDispose!!.dispose()
    }

    @SuppressLint("SimpleDateFormat")
    fun updateDateTime() {
        dateTimeDispose = Observable.interval(1000L, TimeUnit.MILLISECONDS)
                .timeInterval()
                .addSchedulers()
                .subscribe {
                    val s = SimpleDateFormat("dd MMM yyyy HH:mm:ss")
                    val format = s.format(Date())
                    viewState.updateDateTime(format)
                }
    }

    fun getRssItem() {
        val rssItem = FeedRepositoryProvider.instance
                .getRssItem()

        rssItem.title?.let {
            viewState.showLastRssTitle(rssItem.title)
        }
    }

}