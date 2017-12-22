package com.handmark.pulltorefresh.library.internal;

import android.content.Context;
import android.util.Log;

import com.handmark.pulltorefresh.library.ILoadingLayout;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.R;

public class Utils {

	static final String LOG_TAG = "PullToRefresh";

	public static void warnDeprecation(String depreacted, String replacement) {
		Log.w(LOG_TAG, "You're using the deprecated " + depreacted + " attr, please switch over to " + replacement);
	}

	public static void setRefreshLayout(Context context, ILoadingLayout loadingLayout) {
		loadingLayout.setPullLabel(context.getString(R.string.is_pull_down_refresh));
		loadingLayout.setReleaseLabel(context.getString(R.string.is_refresh_start));
		loadingLayout.setRefreshingLabel(context.getString(R.string.is_refreshing));
		loadingLayout.setLoadingDrawable(context.getResources().getDrawable(R.drawable.iconfont_downgrey));
	}

	public static void setLoadingLayout(Context context, ILoadingLayout loadingLayout) {
		loadingLayout.setPullLabel(context.getString(R.string.is_pull_down_load));
		loadingLayout.setReleaseLabel(context.getString(R.string.is_pull_load_more));
		loadingLayout.setRefreshingLabel(context.getString(R.string.is_loading));
		loadingLayout.setLoadingDrawable(context.getResources().getDrawable(R.drawable.iconfont_downgrey));
	}

	public static void setFinishLayout(Context context, ILoadingLayout loadingLayout) {
		loadingLayout.setReleaseLabel(context.getString(R.string.is_loaded_all));
		loadingLayout.setPullLabel(context.getString(R.string.is_loaded_all));
		loadingLayout.setRefreshingLabel(context.getString(R.string.is_loaded_all));
		loadingLayout.setLoadingDrawable(null);
	}

	public static void setCustomLayout(Context context, ILoadingLayout loadingLayout, int img, String... data) {
		loadingLayout.setReleaseLabel(data[0]);
		loadingLayout.setPullLabel(data[1]);
		loadingLayout.setRefreshingLabel(data[2]);
		loadingLayout.setLoadingDrawable(context.getResources().getDrawable(img));
	}

	public static void initPullToRefreshText(Context context, PullToRefreshBase listView) {
		// 设置下拉刷新的文字
		ILoadingLayout refreshLayout = listView.getLoadingLayoutProxy(true, false);
		setRefreshLayout(context, refreshLayout);
		// 设置上拉加载的文字
		ILoadingLayout loadingLayout = listView.getLoadingLayoutProxy(false, true);
		setLoadingLayout(context, loadingLayout);
	}

	public static void setFinishLayout(Context context, PullToRefreshBase listView) {
		ILoadingLayout loadingLayoutProxy = listView.getLoadingLayoutProxy(false, true);
		setFinishLayout(context, loadingLayoutProxy);
	}

}
