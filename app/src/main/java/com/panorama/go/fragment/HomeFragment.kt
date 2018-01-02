package com.panorama.go.fragment

import android.content.Context
import android.content.Intent
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.bigkoo.convenientbanner.ConvenientBanner
import com.bigkoo.convenientbanner.holder.Holder
import com.handmark.pulltorefresh.library.PullToRefreshBase
import com.panorama.base.MvpBaseFragment2
import com.panorama.base.util.DensityUtils
import com.panorama.base.view.EmptyViewBase
import com.panorama.base.view.recydivider.ItemDecorations
import com.panorama.go.R
import com.panorama.go.activity.SearchActivity
import com.panorama.go.adapter.InformationAdapter
import com.panorama.go.bean.ListBean
import com.panorama.go.presenter.HomePresenter
import com.panorama.go.util.finishRequest
import com.panorama.go.util.loadImage
import com.panorama.go.util.showToast
import com.panorama.go.util.singleClick
import com.panorama.go.view.IViewHome
import com.yanzhenjie.recyclerview.swipe.refresh.RecyclerView1
import kotlinx.android.synthetic.main.fragment_home.view.*
import org.jetbrains.anko.find
import org.jetbrains.anko.layoutInflater

/**
 * Description:首页
 * Created by Kevin.Li on 2017-12-08.
 */
class HomeFragment : MvpBaseFragment2<IViewHome, HomePresenter>(), IViewHome {

    private lateinit var convenientBanner: ConvenientBanner<ListBean>
    private lateinit var homeSearch: LinearLayout
    private lateinit var informationAdapter: InformationAdapter
    private val infoList = mutableListOf<ListBean>()

    override fun setPresenter() {
        mPresenter = HomePresenter(mContext)
    }

    override fun initRootViewResource(): Int {
        return R.layout.fragment_home
    }

    companion object {
        fun newInstance(): HomeFragment {
            return HomeFragment()
        }
    }

    override fun initData() {
        super.initData()
        informationAdapter = InformationAdapter(mContext, infoList)
    }

    override fun initWidget() {
        super.initWidget()
        rootView.find<TextView>(R.id.tvLeft).text = "玉溪"
        val bannerView = activity.layoutInflater.inflate(R.layout.header_view_home_carousel, getActivity()!!.find<FrameLayout>(android.R.id.content) as ViewGroup, false)
        homeSearch = bannerView.find(R.id.llHomeSearch)
        convenientBanner = bannerView.find<View>(R.id.convenientBanner) as ConvenientBanner<ListBean>
        rootView.rvHome.addHeaderView(bannerView)
        val loPageTurningPoint: LinearLayout = convenientBanner.findViewById<LinearLayout>(R.id.loPageTurningPoint) as LinearLayout
//        loPageTurningPoint.
        val params = loPageTurningPoint.layoutParams as RelativeLayout.LayoutParams
        val marginValue = DensityUtils.dip2px(mContext, 10F)
        params.setMargins(marginValue, marginValue, marginValue, DensityUtils.dip2px(mContext, 45F))
        loPageTurningPoint.layoutParams = params
        convenientBanner.stopTurning()
//
        rootView.rvHome.setEmptyView(EmptyViewBase(context))
        val layoutManager = LinearLayoutManager(context)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        rootView.rvHome.layoutManager = layoutManager
        rootView.rvHome.addItemDecoration(ItemDecorations.grid(context).type(0, R.drawable.recycler_item_divider).create())
        rootView.rvHome.adapter = informationAdapter

        (0 until 10).forEach { i ->
            val infoBean = ListBean(i.toString(), "http://img.taopic.com/uploads/allimg/121019/234917-121019231H258.jpg", "https://www.baidu.com", "0", "test")
            infoList.add(infoBean)
        }

        fillHomeInfo(infoList)
        fillCarousel(infoList)
    }

    override fun onResume() {
        super.onResume()
        convenientBanner.stopTurning()
    }

    override fun onPause() {
        super.onPause()
        convenientBanner.startTurning(5000)
    }

    override fun fillCarousel(carouselList: MutableList<ListBean>) {
        if (carouselList.isEmpty()) {
            val layoutParams = convenientBanner.layoutParams
            layoutParams.height = 0
            convenientBanner.layoutParams = layoutParams
            return
        }
        val params = convenientBanner.layoutParams
        params.height = (DensityUtils.getScreenW(context) * 5.0f / 10f).toInt()
        convenientBanner.layoutParams = params

        if (carouselList.size > 1) {
            convenientBanner.isCanLoop = true
            convenientBanner.startTurning(4000)
            convenientBanner.setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)
            convenientBanner.setPageIndicator(intArrayOf(R.drawable.bg_oval_white, R.drawable.bg_oval_gray))
        } else {
            convenientBanner.isCanLoop = false
            convenientBanner.setPageIndicator(intArrayOf(R.color.c_transparent, R.color.c_transparent))
        }

        convenientBanner.setPages({ CarouselViewHolder() }, carouselList)
                .setOnItemClickListener { position ->
                    //                    WebViewActivity.startWebViewActivity(mContext, carouselList[position].url, carouselList[position].title)
                    mContext.showToast("点击广告" + position)
                }
    }

    override fun fillHomeInfo(homeInfo: MutableList<ListBean>) {
        this.infoList.addAll(homeInfo)
        informationAdapter.notifyDataSetChanged()
    }

    private inner class CarouselViewHolder : Holder<ListBean> {
        private var imageView: ImageView? = null

        override fun createView(context: Context): View {
            imageView = ImageView(context)
            imageView!!.scaleType = ImageView.ScaleType.CENTER_CROP
            imageView!!.setBackgroundColor(ContextCompat.getColor(context, R.color.c_main_white_bg))
            return imageView as ImageView
        }

        override fun UpdateUI(context: Context, position: Int, data: ListBean) {
            imageView?.loadImage(data.image)
        }
    }

    override fun initListener() {
        super.initListener()
        rootView.rvHome.setOnRefreshListener(object : PullToRefreshBase.OnRefreshListener2<RecyclerView1> {
            override fun onPullDownToRefresh(refreshView: PullToRefreshBase<RecyclerView1>?, curPageNo: Int) {
//                mPresenter?.getHomeData()
//                reqParamsGetNews.pageNo = "1"
//                mPresenter?.getHotNews(reqParamsGetNews)
                rootView.rvHome.finishRequest(true)
            }

            override fun onPullUpToRefresh(refreshView: PullToRefreshBase<RecyclerView1>?, curPageNo: Int) {
//                reqParamsGetNews.pageNo = curPageNo.toString()
//                mPresenter?.getHotNews(reqParamsGetNews)
                rootView.rvHome.finishRequest(true)
            }
        })

        informationAdapter.setOnItemClickListener {
            //            WebViewActivity.startWebViewActivity(context, newsList[it.adapterPosition - 4].url, newsList[it.adapterPosition - 4].title)
            mContext.showToast("点击资讯")
        }
        homeSearch.singleClick {
            startActivity(Intent(mContext, SearchActivity::class.java))
        }
    }
}