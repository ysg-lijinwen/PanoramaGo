package com.panorama.go.activity

import com.panorama.base.MvpBaseActivity
import com.panorama.base.view.EmptyViewBase
import com.panorama.base.view.recydivider.ItemDecorations
import com.panorama.go.R
import com.panorama.go.adapter.HotKeyAdapter
import com.panorama.go.presenter.SearchPresenter
import com.panorama.go.util.hideSoftInputFromWindow
import com.panorama.go.util.showToast
import com.panorama.go.util.singleClick
import com.panorama.go.util.visible
import com.panorama.go.view.IViewSearch
import kotlinx.android.synthetic.main.activity_search.*
import kotlinx.android.synthetic.main.v_search_top.*
import org.jetbrains.anko.startActivity

/**
 * 搜索
 */
class SearchActivity : MvpBaseActivity<IViewSearch, SearchPresenter>(), IViewSearch {
    private lateinit var hotKeyAdapter: HotKeyAdapter
    private val hotKeyList = mutableListOf<String>()
    private lateinit var emptyView: EmptyViewBase
    override fun setRootView() {
        super.setRootView()
        setContentView(R.layout.activity_search)
    }

    override fun initData() {
        super.initData()
        hotKeyAdapter = HotKeyAdapter(context, hotKeyList)
    }

    override fun initWidget() {
        super.initWidget()
        emptyView = EmptyViewBase(context)
        emptyView.setEmptyText("暂无记录")
        rvHotKey.addItemDecoration(ItemDecorations.vertical(context).type(0, R.drawable.recycler_item_divider).create())
        if (hotKeyList.firstOrNull() == null) {
//            rvHotKey.visible(false)
            rvHotKey.setEmptyView(emptyView)
        }
        rvHotKey.adapter = hotKeyAdapter

        (0 until 10).forEach { i ->
            hotKeyList.add("hotKey" + i)
        }
        rvHotKey.visible(true)
        fillHotKey(hotKeyList)
    }

    override fun initListener() {
        super.initListener()
        etSearchKey.setOnEditorActionListener { _, _, _ ->
            val key = etSearchKey.text.toString()
            if (key.isEmpty()) {
                return@setOnEditorActionListener true
            }
            toSearch(key)
            true
        }
        tvTopBarSearch.singleClick {
            val key = etSearchKey.text.toString()
            if (key.isEmpty()) {
                showToast("请输入搜索关键字")
                return@singleClick
            }
            toSearch(key)
        }
        hotKeyAdapter.setOnItemClickListener { holder -> toSearch(hotKeyList[holder!!.adapterPosition]) }
    }

    private fun toSearch(key: String) {
        etSearchKey.hideSoftInputFromWindow()
        activity.startActivity<SearchResultActivity>("key" to key)
    }

    override fun setPresenter() {
        mPresenter = SearchPresenter(context)
    }

    override fun fillHistory(list: MutableList<String>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun fillHotKey(list: MutableList<String>) {
        hotKeyList.addAll(list)
        hotKeyAdapter.notifyDataSetChanged()
    }

    override fun onDestroy() {
        super.onDestroy()
        etSearchKey.hideSoftInputFromWindow()
    }
}
