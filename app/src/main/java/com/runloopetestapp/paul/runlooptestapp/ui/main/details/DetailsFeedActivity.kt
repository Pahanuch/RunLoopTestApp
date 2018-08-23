package com.runloopetestapp.paul.runlooptestapp.ui.main.details

import android.os.Bundle
import android.text.Html
import com.arellomobile.mvp.presenter.InjectPresenter
import com.runloopetestapp.paul.runlooptestapp.R
import com.runloopetestapp.paul.runlooptestapp.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_feed_details.*
import android.os.Build
import android.text.Spanned
import me.toptas.rssconverter.RssItem


class DetailsFeedActivity : BaseActivity(), DetailsFeedView {

    @InjectPresenter
    lateinit var presenter: DetailsFeedPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feed_details)
        setSupportActionBar(toolbar)

        presenter.getRssItem()

    }

    override fun showRssItem(rssItem: RssItem) {
        toolbar.title = rssItem.title
        tvDescription.text = fromHtml(rssItem.description)
    }

    @SuppressWarnings("deprecation")
    fun fromHtml(html: String): Spanned {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY)
        } else {
            Html.fromHtml(html)
        }
    }
}
