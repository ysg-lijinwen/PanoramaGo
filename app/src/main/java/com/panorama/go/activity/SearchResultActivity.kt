package com.panorama.go.activity

import com.handmark.pulltorefresh.library.PullToRefreshBase
import com.panorama.base.MvpBaseActivity
import com.panorama.base.view.recydivider.ItemDecorations
import com.panorama.go.R
import com.panorama.go.adapter.SearchResultAdapter
import com.panorama.go.bean.ListBean
import com.panorama.go.presenter.SearchResultPresenter
import com.panorama.go.util.finishRequest
import com.panorama.go.util.hideSoftInputFromWindow
import com.panorama.go.util.showToast
import com.panorama.go.util.singleClick
import com.panorama.go.view.IViewSearchResult
import com.yanzhenjie.recyclerview.swipe.refresh.RecyclerView1
import kotlinx.android.synthetic.main.activity_search_result.*
import kotlinx.android.synthetic.main.v_search.*

/**
 * 搜索结果
 */
class SearchResultActivity : MvpBaseActivity<IViewSearchResult, SearchResultPresenter>(), IViewSearchResult {
    private lateinit var searchResultAdapter: SearchResultAdapter
    private val searchResultList = mutableListOf<ListBean>()

    override fun setRootView() {
        super.setRootView()
        setContentView(R.layout.activity_search_result)
    }

    override fun initData() {
        super.initData()
        searchResultAdapter = SearchResultAdapter(context, searchResultList)
    }

    override fun initWidget() {
        super.initWidget()
        rvSearchResult.addItemDecoration(ItemDecorations.vertical(context).type(0, R.drawable.recycler_item_divider_white).create())
        rvSearchResult.adapter = searchResultAdapter

        (0 until 10).forEach { i ->
            val infoBean = ListBean(i.toString(), "http://img.taopic.com/uploads/allimg/121019/234917-121019231H258.jpg", "https://www.baidu.com", "0", "test")
            searchResultList.add(infoBean)
        }
    }

    override fun initListener() {
        super.initListener()
        etSearchKey.setOnEditorActionListener { _, _, _ ->
            val key = etSearchKey.text.toString()
            if (key.isEmpty()) {
                showToast("请输入搜索关键字")
                return@setOnEditorActionListener true
            }
            toSearch(key)
            true
        }
        tvTopBarSearch.singleClick {
            val key = etSearchKey.text.toString()
            if (key.isEmpty()) {
                return@singleClick
            }
//            toSearch(key)
        }
        searchResultAdapter.setOnItemClickListener { holder ->
            //            val url = "https://www.baidu.com/?tn=57095150_2_oem_dg"
            val url = searchResultList[holder.adapterPosition].url
//            activity.startActivity<PanoramaGoActivity>("url" to url) // TODO模拟器上暂时不支持，临时屏蔽
        }
        rvSearchResult.setOnRefreshListener(object : PullToRefreshBase.OnRefreshListener2<RecyclerView1> {
            override fun onPullDownToRefresh(refreshView: PullToRefreshBase<RecyclerView1>?, curPageNo: Int) {
                rvSearchResult.finishRequest(true)
            }

            override fun onPullUpToRefresh(refreshView: PullToRefreshBase<RecyclerView1>?, curPageNo: Int) {
                rvSearchResult.finishRequest(true)
            }
        })
    }

    private fun toSearch(key: String) {
        etSearchKey.hideSoftInputFromWindow()
        TODO("发起搜索")
    }

    override fun setPresenter() {
        mPresenter = SearchResultPresenter(context)
    }

    override fun fillSearchResult(list: MutableList<ListBean>) {
        searchResultList.addAll(list)
        searchResultAdapter.notifyDataSetChanged()
    }

    override fun onDestroy() {
        super.onDestroy()
        etSearchKey.hideSoftInputFromWindow()
    }
}
