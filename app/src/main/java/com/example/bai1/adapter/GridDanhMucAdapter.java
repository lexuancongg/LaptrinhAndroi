package com.example.bai1.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.example.bai1.R;
import com.example.bai1.viewmodel.CategoryVm;

import java.util.ArrayList;

public class GridDanhMucAdapter extends BaseAdapter {
    private final Context context;
    private final ArrayList<CategoryVm> items; // Thay đổi từ String sang Category
    private int selectedPosition = -1;

    public GridDanhMucAdapter(Context context, ArrayList<CategoryVm> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position); // Trả về đối tượng Category tại vị trí
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.grid_item_text_only, parent, false);
        }

        TextView textView = convertView.findViewById(R.id.tv_item_name);

        // Lấy đối tượng Category tại vị trí
        CategoryVm category = items.get(position);
        textView.setText(category.getName()); // Hiển thị tên danh mục

        // Thay đổi màu nền và màu chữ dựa trên trạng thái chọn
        if (position == selectedPosition) {
            textView.setBackground(ContextCompat.getDrawable(context, R.drawable.bg_item_selected));
            textView.setTextColor(ContextCompat.getColor(context, android.R.color.white));
        } else {
            textView.setBackground(ContextCompat.getDrawable(context, R.drawable.bg_item_default));
            textView.setTextColor(ContextCompat.getColor(context, android.R.color.black));
        }

        return convertView;
    }

    public void setSelectedPosition(int position) {
        selectedPosition = position;
        notifyDataSetChanged();
    }
}
