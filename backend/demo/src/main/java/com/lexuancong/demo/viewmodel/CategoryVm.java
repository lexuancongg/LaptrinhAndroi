package com.lexuancong.demo.viewmodel;

import com.lexuancong.demo.model.Category;

public record CategoryVm(
        Long id,
        String name
) {
    public static CategoryVm fromModel(Category category){
        return new CategoryVm(category.getId(),category.getName());
    }
}
