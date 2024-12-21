package com.example.bai1.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.bai1.R;
import com.example.bai1.viewmodel.CategoryAtYearAmountVm;

import java.util.List;

public class CategoryAdapterAtYear extends RecyclerView.Adapter<CategoryAdapterAtYear.CategoryViewHolder> {

    private final List<CategoryAtYearAmountVm> categoryList;

    public CategoryAdapterAtYear(List<CategoryAtYearAmountVm> categoryList) {
        this.categoryList = categoryList;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category, parent, false);
        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        CategoryAtYearAmountVm category = categoryList.get(position);
        holder.nameTextView.setText(category.getCategoryName());
        holder.amountTextView.setText(String.format("%,.0f VND", category.getAmount()));
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    public static class CategoryViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView, amountTextView;

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.text_category_name);
            amountTextView = itemView.findViewById(R.id.text_total_amount);
        }
    }
}
