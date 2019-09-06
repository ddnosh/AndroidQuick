package com.androidwind.androidquick.demo.view.expandablelistview;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.androidwind.androidquick.demo.R;

import java.util.List;



/**
 * @author  ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public class AnimatedListAdapter extends AnimatedExpandableListView.AnimatedExpandableListAdapter {
    private Context context;
    private List<GroupItem> list;

    public AnimatedListAdapter(Context context, List<GroupItem> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return list.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return list.size();
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return list.get(groupPosition).childList.get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GroupHolder holder;
        if (convertView == null) {
            holder = new GroupHolder();
            convertView = View.inflate(context, R.layout.item_adapter_expandable_parent, null);
            holder.item_parent = (TextView) convertView.findViewById(R.id.item_parent);
            convertView.setTag(holder);
        } else {
            holder = (GroupHolder) convertView.getTag();
        }
        holder.item_parent.setText(list.get(groupPosition).title);
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    @Override
    public int getRealChildrenCount(int groupPosition) {
        return list.get(groupPosition).childList.size();
    }

    @Override
    public View getRealChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildHolder holder;
        if (convertView == null) {
            holder = new ChildHolder();
            convertView = View.inflate(context, R.layout.item_adapter_expandable_child, null);
            holder.item_child = (TextView) convertView.findViewById(R.id.item_child);
            convertView.setTag(holder);
        } else {
            holder = (ChildHolder) convertView.getTag();
        }
        holder.item_child.setText(list.get(groupPosition).childList.get(childPosition));
        return convertView;
    }

    private static class ChildHolder {
        TextView item_child;
    }

    private static class GroupHolder {
        TextView item_parent;
    }

}
