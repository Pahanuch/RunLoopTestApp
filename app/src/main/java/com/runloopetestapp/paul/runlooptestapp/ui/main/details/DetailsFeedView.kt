package com.runloopetestapp.paul.runlooptestapp.ui.main.details

import com.runloopetestapp.paul.runlooptestapp.ui.base.BaseView
import me.toptas.rssconverter.RssItem

interface DetailsFeedView : BaseView{

    fun showRssItem(rssItem: RssItem)

}