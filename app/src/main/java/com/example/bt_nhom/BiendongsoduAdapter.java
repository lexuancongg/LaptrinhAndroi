package com.example.bt_nhom;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

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

        // Cập nhật các TextView với dữ liệu của Biendongsodu
        TextView textThang = convertView.findViewById(R.id.textThang);
        TextView textMoney = convertView.findViewById(R.id.textMoney);

        if (biendongsodu != null) {
            textThang.setText("Tháng: " + biendongsodu.getThang());
            textMoney.setText("Tiền: " + biendongsodu.getMoney());
        }

        return convertView;
    }
}