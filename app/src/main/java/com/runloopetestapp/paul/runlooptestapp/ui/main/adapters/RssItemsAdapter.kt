package com.runloopetestapp.paul.runlooptestapp.ui.main.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.runloopetestapp.paul.runlooptestapp.R
import android.widget.RelativeLayout
import me.toptas.rssconverter.RssItem
import android.view.LayoutInflater
import android.widget.ImageView
import com.squareup.picasso.Picasso
import java.util.ArrayList

/**
 * Adapter for listing [RssItem]
 */
internal class RssItemsAdapter(private val mContext: Context) : RecyclerView.Adapter<RssItemsAdapter.ViewHolder>() {

    private val BUSINESS_TYPE = 0
    private val ENTERTAINMENT_TYPE = 1
    private val ENVIRONMENT_TYPE = 2

    private val mItemsBusiness : ArrayList<RssItem> = arrayListOf()
    private val mItemsEntertainment : ArrayList<RssItem> = arrayListOf()
    private val mItemsEnvironment : ArrayList<RssItem> = arrayListOf()

    private var mListener: OnItemClickListener? = null

    fun setListener(listener: OnItemClickListener) {
        this.mListener = listener
    }

    fun setItemsBusiness(items: List<RssItem>) {
        mItemsBusiness.clear()
        mItemsEntertainment.clear()
        mItemsEnvironment.clear()
        mItemsBusiness.addAll(items)
        notifyDataSetChanged()
    }

    fun setItemsEntertainment(items: List<RssItem>) {
        mItemsBusiness.clear()
        mItemsEntertainment.clear()
        mItemsEntertainment.addAll(items)
        notifyDataSetChanged()
    }

    fun setItemsEnvironment(items: List<RssItem>) {
        mItemsBusiness.clear()
        mItemsEnvironment.clear()
        mItemsEnvironment.addAll(items)
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RssItemsAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_rss_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: RssItemsAdapter.ViewHolder, position: Int) {
        if (mItemsBusiness.size <= position && mItemsEntertainment.size + mItemsEnvironment.size <= position) {
            return
        }

        val item : RssItem?
        if (getItemViewType(position) == BUSINESS_TYPE) {
            item = mItemsBusiness.get(position)
        } else if (getItemViewType(position) == ENTERTAINMENT_TYPE) {
            item = mItemsEntertainment.get(position)
        } else {
            item = mItemsEnvironment.get(position-mItemsEntertainment.size)
        }

        holder.textTitle!!.setText(item.getTitle())
        holder.textPubDate!!.setText(item.getPublishDate())

        if (item.getImage() != null) {
            Picasso.get().load(item.getImage()).fit()
                    .centerCrop()
                    .into(holder.imageThumb)
        }
        holder.itemView.setOnClickListener { if (mListener != null) mListener!!.onItemSelected(item) }
        holder.itemView.tag = item
    }

    override fun getItemCount(): Int {
        return mItemsBusiness.size + mItemsEntertainment.size + mItemsEnvironment.size
    }

    override fun getItemViewType(position: Int): Int {
        return if (position < mItemsBusiness.size) BUSINESS_TYPE else if (position < mItemsEntertainment.size) ENTERTAINMENT_TYPE else ENVIRONMENT_TYPE
    }

    internal interface OnItemClickListener {
        fun onItemSelected(rssItem: RssItem)
    }

    internal class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var textTitle: TextView? = itemView.findViewById(R.id.tvTitle)
        var textPubDate: TextView? = itemView.findViewById(R.id.tvPubDate)
        var imageThumb: ImageView? = itemView.findViewById(R.id.ivThumb)
        var llTextContainer: RelativeLayout? = itemView.findViewById(R.id.llTextContainer)

    }
}