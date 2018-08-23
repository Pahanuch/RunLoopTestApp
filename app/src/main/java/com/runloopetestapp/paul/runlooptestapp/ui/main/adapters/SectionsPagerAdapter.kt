package com.runloopetestapp.paul.runlooptestapp.ui.main.adapters

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.runloopetestapp.paul.runlooptestapp.ui.main.fragments.rssfeeds.FeedFragment
import com.runloopetestapp.paul.runlooptestapp.ui.main.fragments.info.InfoFragment

class SectionsPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment? {
        when(position){
            0-> return InfoFragment.newInstance()
            1-> return FeedFragment.newInstance()
            else -> return null
        }
    }

    override fun getCount(): Int {
        return 2
    }
}