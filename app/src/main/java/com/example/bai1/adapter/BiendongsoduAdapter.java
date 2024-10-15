package com.example.bai1.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.bai1.R;
import com.example.bai1.activity.DailyFfuctuations;
import com.example.bai1.dto.Biendongsodu;

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

        // Lấy đối tượng Biendongsodu tại vị trí
        Biendongsodu biendongsodu = getItem(position);

        if(biendongsodu.getThang()!=0){
            convertView.setOnClickListener(v -> {
                // Tạo một Intent để chuyển sang ChiTietActivity
                Intent intent = new Intent(getContext(), DailyFfuctuations.class);

                // Truyền dữ liệu qua Intent
                intent.putExtra("thang", biendongsodu.getThang());
                intent.putExtra("money", biendongsodu.getMoney());

                // Bắt đầu ChiTietActivity
                getContext().startActivity(intent);
            });
        }

        // Cập nhật các TextView với dữ liệu của Biendongsodu
        TextView textThang = convertView.findViewById(R.id.textThang);
        TextView textMoney = convertView.findViewById(R.id.textMoney);
        int money = biendongsodu.getMoney();


        if (biendongsodu != null) {
            textThang.setText(biendongsodu.getThang()!=0 ? "tháng: " + biendongsodu.getThang(): "ngày "+biendongsodu.getNgay());
            textMoney.setText(biendongsodu.getMoney() > 0? "+ " + biendongsodu.getMoney() : "- "+biendongsodu.getMoney());
        }
        if(money >0){
            textMoney.setTextColor(Color.GREEN);
        }else{
            textMoney.setTextColor(Color.RED);
        }

        return convertView;
    }
}