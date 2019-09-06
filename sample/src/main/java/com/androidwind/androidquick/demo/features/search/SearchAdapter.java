package com.androidwind.androidquick.demo.features.search;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.androidwind.annotation.annotation.TagInfo;

import java.util.List;

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public class SearchAdapter extends ArrayAdapter<TagInfo> {
    private Context mContext;
    private int mResourceId;
    private ViewHolder holder;

    public SearchAdapter(Context context, int textViewResourceId, List<TagInfo> tagInfos) {
        super(context, textViewResourceId, tagInfos);
        this.mContext = context;
        this.mResourceId = textViewResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TagInfo tagInfo = getItem(position);
        if (null == convertView){
            holder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(mResourceId, null);
            holder.text1 = convertView.findViewById(android.R.id.text1);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder)convertView.getTag();
        }

        holder.text1.setText(tagInfo.getDescription());

        return convertView;
    }

    class ViewHolder{
        TextView text1;
    }
}
