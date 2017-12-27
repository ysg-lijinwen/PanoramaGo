package com.panorama.go.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.panorama.base.BaseRecyclerAdapter
import com.panorama.go.R
import com.panorama.go.util.inflate
import kotlinx.android.synthetic.main.item_hot_key.view.*

/**
 * Description:热门词条
 * Created by Kevin.Li on 2017-12-25.
 */
class HotKeyAdapter(ctx: Context, list: List<String>) : BaseRecyclerAdapter<String, HotKeyAdapter.ItemViewHolder>(ctx, list) {
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ItemViewHolder {
        return ItemViewHolder(parent?.inflate(R.layout.item_hot_key)!!)
    }

    override fun onBindViewHolder(holder: ItemViewHolder?, position: Int) {
        super.onBindViewHolder(holder, position)
        with(holder!!) {
            itemView.tvHotKey.text = itemList[position]
        }
    }

    class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view)
}