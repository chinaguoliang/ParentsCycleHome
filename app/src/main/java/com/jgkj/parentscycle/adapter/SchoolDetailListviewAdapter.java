package com.jgkj.parentscycle.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.jgkj.parentscycle.R;
import com.jgkj.parentscycle.bean.CommonListItemInfo;

import java.util.List;

/**
 * Created by chen on 16/7/24.
 */
public class SchoolDetailListviewAdapter extends BaseAdapter {
    private List<CommonListItemInfo>  contentData;
    private Context mContext;
    public SchoolDetailListviewAdapter(Context context, List<CommonListItemInfo> data){
        contentData = data;
        mContext = context;
    }

    @Override
    public int getCount() {
        return contentData.size();
    }

    @Override
    public Object getItem(int position) {
        return contentData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MineViewHolder holder;
        if (convertView == null) {
            LayoutInflater mInflater = (LayoutInflater) mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            holder = new MineViewHolder();
            convertView = mInflater.inflate(R.layout.school_detail_activity_listview_item, null);
            convertView.setTag(holder);
            holder.contentDescTv = (TextView)convertView.findViewById(R.id.school_detail_activity_listview_item_name_tv);
            holder.titleTv = (TextView)convertView.findViewById(R.id.school_detail_activity_listview_item_content_tv);
            holder.timeTv = (TextView)convertView.findViewById(R.id.school_detail_activity_listview_item_time_tv);
        } else {
            holder = (MineViewHolder) convertView.getTag();
        }

        CommonListItemInfo clii = contentData.get(position);
        holder.contentDescTv.setText(clii.getCriticstext());
        holder.titleTv.setText(clii.getCritics());
        holder.timeTv.setText(clii.getUpdatetime());
        return convertView;
    }

    class MineViewHolder {
        TextView contentDescTv;	// 消息未读条数
        TextView titleTv;
        TextView timeTv;
    }
}
