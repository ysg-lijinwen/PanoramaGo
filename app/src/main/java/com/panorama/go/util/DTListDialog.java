package com.panorama.go.util;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.panorama.go.R;

import java.util.Arrays;
import java.util.List;


public class DTListDialog extends DTDialog {

	public DTListDialog(Context context) {
		super(context);
		setGravity(Gravity.BOTTOM);
	}

	public static interface OnItemClickListener {
		void onItemClick(DTListDialog dialog, View view, int position);
	}

	private ListAdapter mAdapter;

	private OnItemClickListener mItemClickListener;

	private List<String> mItemTitles;

	private String mFrameTitle; // 列表的标�?

	public DTListDialog setTitle(String title) {
		this.mFrameTitle = title;
		return this;
	}

	public DTListDialog setTitle(int title) {
		return setTitle(getContext().getString(title));
	}

	public DTListDialog setItems(String[] items, OnItemClickListener listener) {
		return setItems(Arrays.asList(items), listener);
	}

	public DTListDialog setItems(List<String> items, OnItemClickListener listener) {
		mItemTitles = items;
		mItemClickListener = listener;
		return this;
	}

	public DTListDialog setAdapter(ListAdapter adapter, OnItemClickListener listener) {
		this.mAdapter = adapter;
		mItemClickListener = listener;
		return this;
	}

	@Override
	public void show() {
		View view = View.inflate(getContext(), R.layout.layout_list_dialog, null);
		ListView lv = ViewUtil.findViewById(view, R.id.lv);
		TextView tvFrameTitle = ViewUtil.findViewById(view, R.id.tv_frame_title);
//		View viewSeparator = ViewUtil.findViewById(view, R.id.view_separator);
		ListAdapter adapter = null;
		if (mAdapter != null) {
			adapter = mAdapter;
		} else {
			adapter = new DefaultDialogAdapter(getContext(), 0, mItemTitles);
		}
		lv.setAdapter(adapter);
		if (mFrameTitle != null) {
			tvFrameTitle.setVisibility(View.VISIBLE);
//			viewSeparator.setVisibility(View.VISIBLE);
			tvFrameTitle.setText(mFrameTitle);
		} else {
			tvFrameTitle.setVisibility(View.GONE);
//			viewSeparator.setVisibility(View.GONE);
		}
		lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				if (mItemClickListener != null) {
					mItemClickListener.onItemClick(DTListDialog.this, view, position);
				}
			}

		});
		setView(view);
		setWidthPercentage(1f);
		super.show();
	}

	public static class DefaultDialogAdapter extends ArrayAdapter<String> {

		public DefaultDialogAdapter(Context context, int textViewResourceId, List<String> objects) {
			super(context, textViewResourceId, objects);
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder holder = null;
			if (convertView == null) {
				convertView = View.inflate(getContext(), R.layout.item_list_dialog, null);
				holder = new ViewHolder();
				holder.tvTitle = ViewUtil.findViewById(convertView, R.id.tv_title);
				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}
			String title = getItem(position);
			holder.tvTitle.setText(title);
			return convertView;
		}

	}

	private static class ViewHolder {
		TextView tvTitle;
	}

}
