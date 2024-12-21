package com.lexuancong.demo.service;

import com.lexuancong.demo.model.Category;
import com.lexuancong.demo.model.Transaction;
import com.lexuancong.demo.model.TransactionType;
import com.lexuancong.demo.repository.CategoryRepository;
import com.lexuancong.demo.repository.TransactionRepository;
import com.lexuancong.demo.repository.TransactionTypeRepository;
import com.lexuancong.demo.viewmodel.TransactionGetRequest;
import com.lexuancong.demo.viewmodel.TransactionPostVm;
import com.lexuancong.demo.viewmodel.TransactionVm;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final TransactionTypeRepository transactionTypeRepository;
    private final CategoryRepository categoryRepository;



    public TransactionService(TransactionRepository transactionRepository, TransactionTypeRepository transactionTypeRepository, CategoryRepository categoryRepository) {
        this.transactionRepository = transactionRepository;
        this.transactionTypeRepository = transactionTypeRepository;
        this.categoryRepository = categoryRepository;
    }

    public TransactionVm create(TransactionPostVm transactionPostVm){
        System.out.println("thiss iss request "+ transactionPostVm.selectedCategoryId());
        TransactionType transactionType =  transactionTypeRepository.findById(transactionPostVm.type())
                .orElse(null);
        Category category = categoryRepository.findById(transactionPostVm.selectedCategoryId())
                .orElse(null);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String customerId = authentication.getName();

        Transaction transaction = new Transaction();
        transaction.setTransactionType(transactionType);
        transaction.setDescription(transactionPostVm.note());
        transaction.setCustomerId(customerId);
        transaction.setAmount(transactionPostVm.amount());
        if(transactionPostVm.dateTime()!=null){
            transaction.setCreatedOn(transactionPostVm.dateTime());
        }
        transaction.setCategoryId(category);
        transaction = this.transactionRepository.save(transaction);


        ZonedDateTime dataTime = transactionPostVm.dateTime();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");  // d/M để không có leading zero
        String formattedDate = dataTime.format(formatter);

        return TransactionVm.fromModel(transaction,formattedDate);



    }

    public List<TransactionVm> getTransactions(String fromDate, String toDate) {
        // Lấy customerId từ Security Context
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String customerId = authentication.getName();

        try {
            // Chuyển đổi chuỗi ngày thành ZonedDateTime
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
            ZonedDateTime startDate = LocalDate.parse(fromDate, formatter).atStartOfDay(ZoneId.systemDefault());
            ZonedDateTime endDate = LocalDate.parse(toDate, formatter).atStartOfDay(ZoneId.systemDefault()).plusDays(1).minusNanos(1);

            // Truy vấn cơ sở dữ liệu
            List<Transaction> transactions = transactionRepository.findTransactionsByCustomerIdAndDateRange(customerId, startDate, endDate);

            // Chuyển đổi sang TransactionVm nếu cần thiết
            return transactions.stream()
                    .map(transaction -> TransactionVm.fromModel(transaction,transaction.getCreatedOn().toString()))
                    .toList();

        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Ngày tháng không hợp lệ. Vui lòng sử dụng định dạng d/M/yyyy.");
        }
    }


}
