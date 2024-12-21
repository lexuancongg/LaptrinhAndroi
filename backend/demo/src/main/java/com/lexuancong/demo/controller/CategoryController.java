package com.lexuancong.demo.controller;

import com.lexuancong.demo.service.CategoryService;
import com.lexuancong.demo.viewmodel.CategoryPostVm;
import com.lexuancong.demo.viewmodel.CategoryVm;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/categories/{id}")
    public ResponseEntity<List<CategoryVm>> getCategoryByIdTab(@PathVariable String id){
        return ResponseEntity.ok(categoryService.getCategoryByIdTab(id));

    }

    @PostMapping("/create")
    public ResponseEntity<CategoryVm> create(@RequestBody CategoryPostVm categoryPostVm){
        return ResponseEntity.ok(categoryService.create(categoryPostVm));

    }


}
