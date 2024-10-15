package com.example.bt_nhom.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.bt_nhom.R;
import com.example.bt_nhom.activity.DailyFluctuationsActivity;
import com.example.bt_nhom.dto.Biendongsodu;

public class BiendongsoduAdapter extends ArrayAdapter<Biendongsodu> {
    public BiendongsoduAdapter(Context context, Biendongsodu[] biendongsodus) {
        super(context, 0, biendongsodus);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Kiểm tra nếu view đang sử dụng đã tồn tại không, nếu không thì tạo mới
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_biendongsodu, parent, false);
        }
        TextView textThang = convertView.findViewById(R.id.textThang);
        TextView textMoney = convertView.findViewById(R.id.textMoney);
        // Lấy đối tượng Biendongsodu tại vị trí
        Biendongsodu biendongsodu = getItem(position);
        if(biendongsodu.getThang()!=0){
            convertView.setOnClickListener(v -> {
                // get context : trả về context adapter : activy đang hiển thị
                Intent intent = new Intent(getContext(), DailyFluctuationsActivity.class);

                // Truyền dữ liệu qua Intent
                intent.putExtra("thang", biendongsodu.getThang());
                intent.putExtra("money", biendongsodu.getMoney());

                // Bắt đầu ChiTietActivity
                getContext().startActivity(intent);
            });

        }

        if (biendongsodu != null) {
            textThang.setText(biendongsodu.getNgay() == 0 ? "Tháng: " + biendongsodu.getThang() : "ngày " + biendongsodu.getNgay());
            textMoney.setText(biendongsodu.getMoney() > 0 ?   "+ " + biendongsodu.getMoney() : "- " + biendongsodu.getMoney());
        }
        int money = biendongsodu.getMoney();
        if(money > 0){
            textMoney.setTextColor(Color.GREEN);
        }else{
            textMoney.setTextColor(Color.RED);
        }


        return convertView;
    }
}