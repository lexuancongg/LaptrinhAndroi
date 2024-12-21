package com.lexuancong.demo.viewmodel;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

public record TransactionPostVm (Long  selectedCategoryId, String note, BigDecimal amount, ZonedDateTime dateTime,String type){
}
