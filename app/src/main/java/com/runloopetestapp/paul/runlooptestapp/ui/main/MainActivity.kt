package com.runloopetestapp.paul.runlooptestapp.ui.main

import android.support.design.widget.TabLayout
import android.os.Bundle
import com.arellomobile.mvp.presenter.InjectPresenter
import com.runloopetestapp.paul.runlooptestapp.R
import com.runloopetestapp.paul.runlooptestapp.ui.base.MainBarActivity
import com.runloopetestapp.paul.runlooptestapp.ui.main.adapters.SectionsPagerAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : MainBarActivity(), MainView {

    private var mSectionsPagerAdapter: SectionsPagerAdapter? = null

    @InjectPresenter
    lateinit var presenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)

        mSectionsPagerAdapter = SectionsPagerAdapter(supportFragmentManager)

        container.adapter = mSectionsPagerAdapter

        container.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabs))
        tabs.addOnTabSelectedListener(TabLayout.ViewPagerOnTabSelectedListener(container))

    }

}
