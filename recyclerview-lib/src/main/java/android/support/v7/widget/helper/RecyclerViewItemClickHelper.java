package android.support.v7.widget.helper;

import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

/**
 * author THC
 * Title: RecyclerViewItemClickHelper
 * Package com.jcodecraeer.xrecyclerview
 * Description:
 * date 2016/9/5 16:56
 */
public abstract class RecyclerViewItemClickHelper implements RecyclerView.OnItemTouchListener {

    private GestureDetectorCompat mGestureDetector;
    private RecyclerView recyclerView;

    public RecyclerViewItemClickHelper(RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
        mGestureDetector = new GestureDetectorCompat(recyclerView.getContext(), new ItemTouchHelperGestureListener());
    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        mGestureDetector.onTouchEvent(e);
        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {
        mGestureDetector.onTouchEvent(e);
    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
    }

    private class ItemTouchHelperGestureListener extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
            if (child != null) {
                RecyclerView.ViewHolder vh = recyclerView.getChildViewHolder(child);
                onItemClick(vh);
            }
            return true;
        }

        @Override
        public boolean onDown(MotionEvent e) {
//            View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
//            if (child != null) {
//                RecyclerView.ViewHolder vh = recyclerView.getChildViewHolder(child);
//                vh.itemView.setClickable(true);
//            }
            return super.onDown(e);

        }

        @Override
        public void onLongPress(MotionEvent e) {
            View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
            if (child != null) {
                RecyclerView.ViewHolder vh = recyclerView.getChildViewHolder(child);
                onItemLongClick(vh);
            }
        }

    }

    protected abstract void onItemClick(RecyclerView.ViewHolder viewHolder);

    protected abstract void onItemLongClick(RecyclerView.ViewHolder viewHolder);
}
