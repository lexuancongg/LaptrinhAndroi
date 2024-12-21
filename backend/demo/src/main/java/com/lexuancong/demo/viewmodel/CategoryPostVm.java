package com.lexuancong.demo.viewmodel;

import com.lexuancong.demo.model.Category;

public record CategoryPostVm (
         String name,
         String description,
         String type
){
    public Category toModel(){
        Category category = new Category();
        category.setName(this.name);
        category.setDescription(this.description);
        return category;
    }

}
