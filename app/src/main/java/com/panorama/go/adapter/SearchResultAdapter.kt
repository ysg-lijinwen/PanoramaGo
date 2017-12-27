package com.panorama.go.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.panorama.base.BaseRecyclerAdapter
import com.panorama.go.R
import com.panorama.go.bean.ListBean
import com.panorama.go.util.inflate
import com.panorama.go.util.loadImage
import kotlinx.android.synthetic.main.item_home_info.view.*

/**
 * Description:搜索结果列表
 * Created by Kevin.Li on 2017-12-25.
 */
class SearchResultAdapter(ctx: Context, list: List<ListBean>) : BaseRecyclerAdapter<ListBean, SearchResultAdapter.ItemViewHolder>(ctx, list) {
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ItemViewHolder {
        return ItemViewHolder(parent?.inflate(R.layout.item_home_info)!!)
    }

    override fun onBindViewHolder(holder: ItemViewHolder?, position: Int) {
        super.onBindViewHolder(holder, position)
        with(holder!!) {
            val info = itemList[position]
            itemView.imvHomeInfo.loadImage(info.image)
        }
    }

    class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view)
}