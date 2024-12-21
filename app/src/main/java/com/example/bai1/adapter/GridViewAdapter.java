package com.example.bai1.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bai1.R;

public class GridViewAdapter extends BaseAdapter {
    /**
     * @return
     */
    private Context context;
    private String[] tenLogo;
    private int[] hinhLogo;

    public GridViewAdapter(Context context, String[] tenLogo, int[] hinhLogo) {
        this.context = context;
        this.tenLogo = tenLogo;
        this.hinhLogo = hinhLogo;
    }

    @Override
    public int getCount() {
        return tenLogo.length;
    }

    /**
     * @param i
     * @return
     */
    @Override
    public Object getItem(int i) {
        return tenLogo[i];
    }

    /**
     * @param i
     * @return
     */
    @Override
    public long getItemId(int i) {
        return i;
    }

    /**
     * @param i
     * @param convertView
     * @param viewGroup
     * @return
     */
    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.gridview_row, viewGroup, false);
        }

        TextView textView = convertView.findViewById(R.id.textView);
        ImageView imgView = convertView.findViewById(R.id.imgView);

        textView.setText(tenLogo[i]);
        imgView.setImageResource(hinhLogo[i]);
        return convertView;
    }

}
