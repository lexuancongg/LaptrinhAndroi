package com.example.bai1.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.bai1.R;
import com.example.bai1.viewmodel.TransactionVm;

import java.text.DecimalFormat;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.TransactionViewHolder> {

    private List<TransactionVm> transactions;

    public TransactionAdapter(List<TransactionVm> transactions) {
        this.transactions = transactions;
    }

    public void updateData(List<TransactionVm> newTransactions) {
        transactions.clear();
        transactions.addAll(newTransactions);
        notifyDataSetChanged();
    }

    @Override
    public TransactionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_transaction, parent, false);
        return new TransactionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TransactionViewHolder holder, int position) {
        TransactionVm transaction = transactions.get(position);

        // Định dạng số tiền dưới dạng tiền tệ (VND)
        DecimalFormat formatter = new DecimalFormat("#,###.##");
        String formattedAmount = formatter.format(transaction.getAmount()) + " VND";

        // Gán giá trị đã định dạng vào TextView
        holder.amountTextView.setText(formattedAmount);

        // Gán các thông tin khác
        holder.descriptionTextView.setText("note: " + transaction.getNote());
        String dateTime = transaction.getDateTime();
        ZonedDateTime zonedDateTime = ZonedDateTime.parse(dateTime);
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy"); // Định dạng ngày tháng năm
        String formattedDate = zonedDateTime.format(timeFormatter);

        holder.dateTextView.setText("Date: " + formattedDate); // Hiển thị ngày tháng năm
        holder.category.setText(transaction.getCategoryName());


        // sự kiện khi nhấn vào các phần tử
        holder.itemView.setOnClickListener(v -> {
            System.out.println(transaction.getId());
        });
    }


    @Override
    public int getItemCount() {
        return transactions.size();
    }

    public static class TransactionViewHolder extends RecyclerView.ViewHolder {
        TextView amountTextView;
        TextView descriptionTextView;
        TextView dateTextView;
        TextView category;


        public TransactionViewHolder(View itemView) {
            super(itemView);
            amountTextView = itemView.findViewById(R.id.amount_text);
            descriptionTextView = itemView.findViewById(R.id.description_text);
            dateTextView = itemView.findViewById(R.id.date_text);
            category= itemView.findViewById(R.id.name_category);
        }
    }
}
