package com.example.bai1.api;

import com.example.bai1.viewmodel.CategoryAtYearAmountVm;
import com.example.bai1.viewmodel.CategoryPostVm;
import com.example.bai1.viewmodel.CategoryVm;
import com.example.bai1.viewmodel.LaiLoVm;
import com.example.bai1.viewmodel.TransactionGetVm;
import com.example.bai1.viewmodel.TransactionPostVm;
import com.example.bai1.viewmodel.TransactionVm;

import java.math.BigDecimal;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {
    @POST("/category/create")  // Đảm bảo rằng URL này là đúng với API của bạn
    Call<CategoryVm> createCategory(
            @Header("Authorization") String token,
            @Body CategoryPostVm categoryPostVm
            );  // Gửi token trong header
    @GET("/category/categories/{id}")
    Call<List<CategoryVm>> getCategoryVmByTab(
            @Header("Authorization") String token,
            @Path("id") String tabId
    );
    @GET("/category/categories")
    Call<List<CategoryVm>> getAllCAtegory(
            @Header("Authorization") String token
    );

    @POST("/transaction/create")
    Call<TransactionVm> createTransaction(
            @Header("Authorization") String token,
            @Body TransactionPostVm transactionPostVm
    );

    @GET("/transaction/list-transactions")
    Call<List<TransactionVm>> getTransactions(
            @Header("Authorization") String token,
            @Query("fromDate") String fromDate,
            @Query("toDate") String toDate,
            @Query("fromMoney") BigDecimal fromMoney,
            @Query("toMoney") BigDecimal toMoney
            );


    @GET("/transaction/list-expense")
    Call<List<TransactionGetVm>> getExpensesByDate(
            @Header("Authorization") String token,
            @Query("fromDate") String fromDate,
            @Query("type") String type

    );

    @DELETE("/category/delete/{id}")
    Call<Void> deleteCategory(
            @Header("Authorization") String token,
            @Path("id") Long id
    );

    @PUT("/category/edit/{id}")
    Call<Void> editCategory(
            @Header("Authorization") String token,
            @Path("id") Long id,
            @Query("name") String name
    );

    @GET("/transaction/lailo")
    Call<LaiLoVm> getBaoCaoLaiLo(
            @Header("Authorization") String token,
            @Query("date") String date

    );


    @GET("/transaction/baocaotheonam")
    Call<List<CategoryAtYearAmountVm>> baocaodanhmuctheonam(
            @Header("Authorization") String token,
            @Query("year") int year,
            @Query("title") String title
    );





}
