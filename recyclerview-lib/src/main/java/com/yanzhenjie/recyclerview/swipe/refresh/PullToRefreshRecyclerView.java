package com.yanzhenjie.recyclerview.swipe.refresh;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.util.SparseArrayCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.handmark.pulltorefresh.library.PullToRefreshBase;


/**
 * create by THC
 * 2016-9-7 09:44:29
 */
public class PullToRefreshRecyclerView extends PullToRefreshBase<RecyclerView1> {
    static final String RECYCLER_BOUND = "recycler_bound";
    private static final int BASE_ITEM_TYPE_HEADER = 100000;
    private static final int BASE_ITEM_TYPE_FOOTER = 200000;

    private SparseArrayCompat<View> mHeaderViews = new SparseArrayCompat<>();
    private SparseArrayCompat<View> mFootViews = new SparseArrayCompat<>();

    private View mEmptyView;
    private HeaderAndFooterWrapper mWrapAdapter;
    private RecyclerView.Adapter mAdapter;
    private final RecyclerView.AdapterDataObserver mDataObserver = new DataObserver();

    public PullToRefreshRecyclerView(Context context) {
        super(context);
    }

    public PullToRefreshRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);

    }

    public PullToRefreshRecyclerView(Context context, Mode mode) {
        super(context, mode);
    }

    public PullToRefreshRecyclerView(Context context, Mode mode, AnimationStyle animStyle) {
        super(context, mode, animStyle);
    }


    @Override
    protected void onRefreshing(boolean doScroll) {
        super.onRefreshing(doScroll);
    }

    @Override
    public Orientation getPullToRefreshScrollDirection() {
        return Orientation.VERTICAL;
    }

    @Override
    protected RecyclerView1 createRefreshableView(Context context, AttributeSet attrs) {
        RecyclerView1 recyclerView = new RecyclerView1(context, attrs);
        LinearLayoutManager manager = new LinearLayoutManager(context);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        return recyclerView;
    }

    @Override
    protected boolean isReadyForPullEnd() {
//        View lastView = getRefreshableView().getChildAt(getRefreshableView().getChildCount() - 1);
//        int position = getRefreshableView().getChildAdapterPosition(lastView);
//        if (getRefreshableView().getAdapter() != null) {
//            if (position == getRefreshableView().getAdapter().getItemCount() - 1) {
//                if (lastView == null) {
//                    return false;
//                } else {
//                    return getRefreshableView().getBottom() - getRefreshableView().getPaddingBottom() == lastView.getBottom();
//                }
//            }
//        }
//        return false;
        return !ViewCompat.canScrollVertically(getRefreshableView(), 1);
    }

    @Override
    protected boolean isReadyForPullStart() {
        View firstView = getRefreshableView().getChildAt(0);
        if (null != mEmptyView) {
            if (mEmptyView.getVisibility() == View.VISIBLE) {
                return true;
            }
        }
        int position = getRefreshableView().getChildAdapterPosition(firstView);
        return position == 0 && getRefreshableView().getLayoutManager().getDecoratedTop(firstView) == 0;
//        return !ViewCompat.canScrollVertically(getRefreshableView(), -1);

    }

    /**
     * 设置recyclerView的EmptyView
     *
     * @param newEmptyView 空视图
     */
    public final void setEmptyView(View newEmptyView) {
        FrameLayout refreshableViewWrapper = getRefreshableViewWrapper();
        if (null != newEmptyView) {
            newEmptyView.setClickable(true);
            ViewParent newEmptyViewParent = newEmptyView.getParent();
            if (null != newEmptyViewParent && newEmptyViewParent instanceof ViewGroup) {
                ((ViewGroup) newEmptyViewParent).removeView(newEmptyView);
            }

            FrameLayout.LayoutParams lp = convertEmptyViewLayoutParams(newEmptyView.getLayoutParams());
            if (null != lp) {
                refreshableViewWrapper.addView(newEmptyView, lp);
            } else {
                refreshableViewWrapper.addView(newEmptyView);
            }
        }
        mEmptyView = newEmptyView;
    }

    /**
     * 默认不调用这个方法的话是LinearLayoutManager垂直方向
     *
     * @param layoutManager 设置recyclerView的LayoutManager
     */
    public void setLayoutManager(RecyclerView.LayoutManager layoutManager) {
        getRefreshableView().setLayoutManager(layoutManager);
    }

    public RecyclerView.LayoutManager getLayoutManager() {
        return getRefreshableView().getLayoutManager();
    }

    private static FrameLayout.LayoutParams convertEmptyViewLayoutParams(ViewGroup.LayoutParams lp) {
        FrameLayout.LayoutParams newLp = null;

        if (null != lp) {
            newLp = new FrameLayout.LayoutParams(lp);

            if (lp instanceof LinearLayout.LayoutParams) {
                newLp.gravity = ((LinearLayout.LayoutParams) lp).gravity;
            } else {
                newLp.gravity = Gravity.CENTER;
            }
        }

        return newLp;
    }

    public RecyclerView.Adapter getAdapter() {
        return mAdapter;
    }

    @Override
    protected void onPtrSaveInstanceState(Bundle saveState) {
        super.onPtrSaveInstanceState(saveState);
        getRefreshableView().setBundleData(saveState);
    }

    @Override
    protected void onPtrRestoreInstanceState(Bundle savedInstanceState) {
        super.onPtrRestoreInstanceState(savedInstanceState);
    }

    /**
     * Set a new adapter to provide child views on demand.
     * When adapter is changed, all existing views are recycled back to the pool. If the pool has only one adapter, it will be cleared.
     *
     * @param adapter The new adapter to set, or null to set no adapter
     */
    public void setAdapter(RecyclerView.Adapter adapter) {
        if (null != mAdapter) {
            mAdapter.unregisterAdapterDataObserver(mDataObserver);
        }
        mAdapter = adapter;
        mWrapAdapter = new HeaderAndFooterWrapper(mHeaderViews, mFootViews, adapter);
        getRefreshableView().setAdapter(mWrapAdapter);
        adapter.registerAdapterDataObserver(mDataObserver);
        mDataObserver.onChanged();
    }

    public void setNoAdapter(RecyclerView.Adapter adapter) {
        if (null != mAdapter) {
            mAdapter.unregisterAdapterDataObserver(mDataObserver);
        }
        mAdapter = adapter;
        getRefreshableView().setAdapter(mAdapter);
        mAdapter.registerAdapterDataObserver(mDataObserver);
        mDataObserver.onChanged();
    }

    public void setMenuAdapter(RecyclerView.Adapter adapter) {
        if (null != mAdapter) {
            mAdapter.unregisterAdapterDataObserver(mDataObserver);
        }
        mAdapter = adapter;
        getRefreshableView().setAdapter(mAdapter);
        adapter.registerAdapterDataObserver(mDataObserver);
        mDataObserver.onChanged();
    }

    public void setOnItemClickListener(BaseRecyclerAdapter.OnItemClickListener onItemClickListener) {
        if (null != mAdapter) {
            if (mAdapter instanceof BaseRecyclerAdapter) {
                ((BaseRecyclerAdapter) mAdapter).setOnItemClickListener(onItemClickListener);
            } else {
                throw new RuntimeException("the adapter you set must be the child class of BaseRecyclerAdapter!");
            }
        } else {
            throw new RuntimeException("you may not set adapter before setOnItemClickListener");
        }
    }

    public void setOnItemLongClickListener(BaseRecyclerAdapter.OnItemLongClickListener onItemLongClickListener) {
        if (mAdapter != null) {
            if (mAdapter instanceof BaseRecyclerAdapter) {
                ((BaseRecyclerAdapter) mAdapter).setOnItemLongClickListener(onItemLongClickListener);
            } else {
                throw new RuntimeException("the adapter you set must be the child class of BaseRecyclerAdapter!");
            }
        } else {
            throw new RuntimeException("you may not set adapter before setOnItemLongClickListener");
        }
    }

    public void addHeaderView(View view) {
        mHeaderViews.put(mHeaderViews.size() + BASE_ITEM_TYPE_HEADER, view);
    }

    public int removeHeaderView(View view){
        int key = mHeaderViews.indexOfValue(view);
        return key;
//        mHeaderViews.remove(key);
    }

    public void addFootView(View view) {
        mFootViews.put(mFootViews.size() + BASE_ITEM_TYPE_FOOTER, view);
    }

    public int getHeadersCount() {
        return mHeaderViews.size();
    }

    public int getFootersCount() {
        return mFootViews.size();
    }

    public void addItemDecoration(RecyclerView.ItemDecoration decoration) {
        getRefreshableView().addItemDecoration(decoration);
    }

    private class DataObserver extends RecyclerView.AdapterDataObserver {
        @Override
        public void onChanged() {
            super.onChanged();
            RecyclerView.Adapter<?> adapter = getRefreshableView().getAdapter();
            if (adapter != null && mEmptyView != null) {
                if (adapter.getItemCount() == 0) {
                    mEmptyView.setVisibility(View.VISIBLE);
                } else {
                    mEmptyView.setVisibility(View.GONE);
                }
            }
            if (mWrapAdapter != null) {
                mWrapAdapter.notifyDataSetChanged();
            }
        }

        @Override
        public void onItemRangeInserted(int positionStart, int itemCount) {
            mWrapAdapter.notifyItemRangeInserted(positionStart, itemCount);
        }

        @Override
        public void onItemRangeChanged(int positionStart, int itemCount) {
            mWrapAdapter.notifyItemRangeChanged(positionStart, itemCount);
        }

        @Override
        public void onItemRangeChanged(int positionStart, int itemCount, Object payload) {
            mWrapAdapter.notifyItemRangeChanged(positionStart, itemCount, payload);
        }

        @Override
        public void onItemRangeRemoved(int positionStart, int itemCount) {
            mWrapAdapter.notifyItemRangeRemoved(positionStart, itemCount);
        }

        @Override
        public void onItemRangeMoved(int fromPosition, int toPosition, int itemCount) {
            mWrapAdapter.notifyItemMoved(fromPosition, toPosition);
        }
    }

    private int findMax(int[] lastPositions) {
        int max = lastPositions[0];
        for (int value : lastPositions) {
            if (value > max) {
                max = value;
            }
        }
        return max;
    }

}


