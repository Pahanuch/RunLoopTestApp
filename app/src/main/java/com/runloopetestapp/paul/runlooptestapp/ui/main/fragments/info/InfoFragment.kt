package com.runloopetestapp.paul.runlooptestapp.ui.main.fragments.info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.presenter.InjectPresenter
import com.runloopetestapp.paul.runlooptestapp.R
import com.runloopetestapp.paul.runlooptestapp.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_info.*

class InfoFragment : BaseFragment(), InfoFragmentView {

    @InjectPresenter
    lateinit var presenter: InfoFragmentPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_info, container, false)

        presenter.updateDateTime()

        return rootView
    }

    override fun onResume() {
        super.onResume()
        presenter.getRssItem()
    }

    override fun showLastRssTitle(rssTitle: String) {
        tvLastRssTitle.text = rssTitle
    }

    override fun updateDateTime(dateTime : String) {
        tvDateTime.text = dateTime
    }

    companion object {

        fun newInstance(): InfoFragment {
            val fragment = InfoFragment()
            return fragment
        }
    }
}
