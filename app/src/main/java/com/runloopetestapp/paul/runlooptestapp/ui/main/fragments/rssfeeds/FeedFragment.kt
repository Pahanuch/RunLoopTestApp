package com.runloopetestapp.paul.runlooptestapp.ui.main.fragments.rssfeeds

import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.presenter.InjectPresenter
import com.runloopetestapp.paul.runlooptestapp.ui.base.BaseFragment
import com.runloopetestapp.paul.runlooptestapp.ui.main.adapters.RssItemsAdapter
import com.runloopetestapp.paul.runlooptestapp.ui.main.details.DetailsFeedActivity
import me.toptas.rssconverter.RssItem
import org.jetbrains.anko.support.v4.startActivity
import android.widget.ArrayAdapter
import com.runloopetestapp.paul.runlooptestapp.R
import kotlinx.android.synthetic.main.fragment_feed.*
import kotlinx.android.synthetic.main.fragment_feed.view.*
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener



class FeedFragment : BaseFragment(), FeedFragmentView, SwipeRefreshLayout.OnRefreshListener, RssItemsAdapter.OnItemClickListener {

    @InjectPresenter
    lateinit var presenter: FeedFragmentPresenter

    private lateinit var adapter: RssItemsAdapter

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        swRefresh.setOnRefreshListener(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_feed, container, false)

        adapter = RssItemsAdapter(context)
        adapter.setListener(this)
        rootView.recyclerView.adapter = adapter
        rootView.recyclerView.layoutManager = LinearLayoutManager(context)

        rootView.spinnerNews.setOnItemSelectedListener(object : OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View,
                                        position: Int, id: Long) {

                presenter.dispose()

                when(position){
                    0->  presenter.loadBussinessFeeds()
                    1->  {
                        presenter.loadEntertainmentFeeds()
                        presenter.loadEnvironmentFeeds()
                    }
                }
            }

            override fun onNothingSelected(arg0: AdapterView<*>) {}
        })

        return rootView
    }

    override fun onRefresh() {

        presenter.dispose()

        val position =  spinnerNews.selectedItemPosition
        when(position){
            0->  presenter.loadBussinessFeeds()
            1->  {
                presenter.loadEnvironmentFeeds()
                presenter.loadEntertainmentFeeds()
            }
        }
    }

    override fun onItemSelected(rssItem: RssItem) {
        presenter.setRssFeed(rssItem)
        startActivity<DetailsFeedActivity>()
    }

    override fun showRssBusinessFeeds(rssFeeds: List<RssItem>) {
        adapter.setItemsBusiness(rssFeeds)
    }

    override fun showRssEntertainmentFeeds(rssFeeds: List<RssItem>) {
        adapter.setItemsEntertainment(rssFeeds)
    }

    override fun showRssEnvironmentFeeds(rssFeeds: List<RssItem>) {
        adapter.setItemsEnvironment(rssFeeds)
    }

    override fun showLoading() {
        swRefresh.setRefreshing(true)
    }

    override fun hideLoading() {
        swRefresh.setRefreshing(false)
    }

    companion object {

        fun newInstance(): FeedFragment {
            val fragment = FeedFragment()
            return fragment
        }
    }
}