package com.example.bai1.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.bai1.R;
import java.util.List;

public class DanhMucAdapter extends RecyclerView.Adapter<DanhMucAdapter.DanhMucViewHolder> {

    private List<String> danhMucList;
    private Context context;
    private OnItemClickListener onItemClickListener;

    public DanhMucAdapter(Context context, List<String> danhMucList, OnItemClickListener onItemClickListener) {
        this.context = context;
        this.danhMucList = danhMucList;
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public DanhMucViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_danh_muc, parent, false);
        return new DanhMucViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DanhMucViewHolder holder, int position) {
        String danhMuc = danhMucList.get(position);
        holder.itemText.setText(danhMuc);

        // Sự kiện click sửa
        holder.editButton.setOnClickListener(v -> onItemClickListener.onEditClick(position));

        // Sự kiện click xóa
        holder.deleteButton.setOnClickListener(v -> onItemClickListener.onDeleteClick(position));
    }

    @Override
    public int getItemCount() {
        return danhMucList.size();
    }

    public static class DanhMucViewHolder extends RecyclerView.ViewHolder {
        TextView itemText;
        ImageView editButton;
        ImageView deleteButton;

        public DanhMucViewHolder(View itemView) {
            super(itemView);
            itemText = itemView.findViewById(R.id.itemText);
            editButton = itemView.findViewById(R.id.editButton);
            deleteButton = itemView.findViewById(R.id.deleteButton);
        }
    }

    // Interface để xử lý sự kiện sửa và xóa
    public interface OnItemClickListener {
        void onEditClick(int position);
        void onDeleteClick(int position);
    }
}
