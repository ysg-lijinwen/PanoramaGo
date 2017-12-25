package com.panorama.go.fragment

import android.content.Context
import android.support.v4.content.ContextCompat
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bigkoo.convenientbanner.ConvenientBanner
import com.bigkoo.convenientbanner.holder.Holder
import com.handmark.pulltorefresh.library.PullToRefreshBase
import com.panorama.base.MvpBaseFragment2
import com.panorama.base.util.DensityUtils
import com.panorama.base.view.EmptyViewBase
import com.panorama.base.view.recydivider.ItemDecorations
import com.panorama.go.R
import com.panorama.go.adapter.InformationAdapter
import com.panorama.go.bean.ListBean
import com.panorama.go.presenter.HomePresenter
import com.panorama.go.util.finishRequest
import com.panorama.go.util.loadImage
import com.panorama.go.util.showToast
import com.panorama.go.view.IViewHome
import com.yanzhenjie.recyclerview.swipe.refresh.RecyclerView1
import kotlinx.android.synthetic.main.fragment_home.view.*
import org.jetbrains.anko.layoutInflater

/**
 * Description:首页
 * Created by Kevin.Li on 2017-12-08.
 */
class HomeFragment : MvpBaseFragment2<IViewHome, HomePresenter>(), IViewHome {

    private lateinit var convenientBanner: ConvenientBanner<ListBean>
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
        convenientBanner = activity.layoutInflater.inflate(R.layout.header_view_home_carousel, getActivity()!!.findViewById<View>(android.R.id.content) as ViewGroup, false) as ConvenientBanner<ListBean>
        rootView.rv_home.addHeaderView(convenientBanner)
//
        rootView.rv_home.setEmptyView(EmptyViewBase(context))
        val layoutManager = GridLayoutManager(context, 2)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        rootView.rv_home.layoutManager = layoutManager
//        rootView.rv_home.addItemDecoration(GridItemDecoration.Builder().type().create())
        rootView.rv_home.addItemDecoration(ItemDecorations.grid(context).type(0, R.drawable.recycler_item_divider).create())
        rootView.rv_home.adapter = informationAdapter

        (0 until 10).forEach { i ->
            val infoBean = ListBean(i.toString(), "https://image.baidu.com/search/detail?ct=503316480&z=0&ipn=d&word=%E5%9B%BE%E7%89%87%E6%90%9C%E7%B4%A2&step_word=&hs=0&pn=2&spn=0&di=5929304920&pi=0&rn=1&tn=baiduimagedetail&is=0%2C0&istype=0&ie=utf-8&oe=utf-8&in=&cl=2&lm=-1&st=undefined&cs=1852919458%2C1916079413&os=49179960%2C1670120420&simid=3446001284%2C335456999&adpicid=0&lpn=0&ln=1985&fr=&fmq=1514201484008_R&fm=&ic=undefined&s=undefined&se=&sme=&tab=0&width=undefined&height=undefined&face=undefined&ist=&jit=&cg=&bdtype=0&oriquery=&objurl=http%3A%2F%2Fm.qqzhi.com%2Fupload%2Fimg_4_1122690826D3914639914_23.jpg&fromurl=ippr_z2C%24qAzdH3FAzdH3F4_z%26e3Bqqzit_z%26e3Bv54AzdH3Fpw2-%25Eb%25bA%25AD%25Eb%25lc%25BE%25Ec%25lB%25BE%25E0%25bl%25b0_z%26e3Bip4s&gsm=0&rpstart=0&rpnum=0", "https://www.baidu.com", "0", "test")
            infoList.add(infoBean)
        }

        fillHomeInfo(infoList)
    }

    override fun onResume() {
        super.onResume()
        convenientBanner.stopTurning()
        fillCarousel(infoList)
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
            convenientBanner.setPageIndicator(intArrayOf(R.drawable.bg_oval_white, R.drawable.bg_oval_orange))
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

    override fun fillHomeInfo(homeInfos: MutableList<ListBean>) {
        this.infoList.addAll(homeInfos)
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
        rootView.rv_home.setOnRefreshListener(object : PullToRefreshBase.OnRefreshListener2<RecyclerView1> {
            override fun onPullDownToRefresh(refreshView: PullToRefreshBase<RecyclerView1>?, curPageNo: Int) {
//                mPresenter?.getHomeData()
//                reqParamsGetNews.pageNo = "1"
//                mPresenter?.getHotNews(reqParamsGetNews)
                rootView.rv_home.finishRequest(true)
            }

            override fun onPullUpToRefresh(refreshView: PullToRefreshBase<RecyclerView1>?, curPageNo: Int) {
//                reqParamsGetNews.pageNo = curPageNo.toString()
//                mPresenter?.getHotNews(reqParamsGetNews)
                rootView.rv_home.finishRequest(true)
            }
        })

        informationAdapter.setOnItemClickListener {
            //            WebViewActivity.startWebViewActivity(context, newsList[it.adapterPosition - 4].url, newsList[it.adapterPosition - 4].title)
            mContext.showToast("点击资讯")
        }
    }
}