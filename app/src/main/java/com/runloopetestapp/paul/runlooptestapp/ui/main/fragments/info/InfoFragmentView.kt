package com.runloopetestapp.paul.runlooptestapp.ui.main.fragments.info

import com.runloopetestapp.paul.runlooptestapp.ui.base.BaseView

interface InfoFragmentView : BaseView {

    fun updateDateTime(dateTime : String)

    fun showLastRssTitle(rssTitle : String)

}