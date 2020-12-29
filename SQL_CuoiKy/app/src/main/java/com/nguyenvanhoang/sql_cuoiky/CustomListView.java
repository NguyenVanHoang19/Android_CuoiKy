package com.nguyenvanhoang.sql_cuoiky;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class CustomListView extends BaseAdapter {
    Context context;
    List<Sach> sachList;

    public CustomListView(Context context, List<Sach> sachList) {
        this.context = context;
        this.sachList = sachList;
    }

    @Override
    public int getCount() {
        return sachList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if(view == null){
            view = LayoutInflater.from(context).inflate(R.layout.custom_listview,viewGroup,false);
            viewHolder = new ViewHolder();
            viewHolder.tvMa = (TextView) view.findViewById(R.id.tvMa);
            viewHolder.tvTen = (TextView) view.findViewById(R.id.tvTen);
            viewHolder.tvTacGia = (TextView) view.findViewById(R.id.tvTacGia);
            viewHolder.tvTuaSach = (TextView) view.findViewById(R.id.tvTuaSach);
            view.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder)view.getTag();
        }
        Sach sach = sachList.get(i);
        viewHolder.tvMa.setText(sach.getMaSach()+ "");
        viewHolder.tvTen.setText(sach.getTenSach());
        viewHolder.tvTacGia.setText(sach.getTenTacGia());
        viewHolder.tvTuaSach.setText(sach.getMaTuaSach() + "");
        return view;
    }
    private class ViewHolder {
        TextView tvMa,tvTen,tvTacGia,tvTuaSach;
    }
}
