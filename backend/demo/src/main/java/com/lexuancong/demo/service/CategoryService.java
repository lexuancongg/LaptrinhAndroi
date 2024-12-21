package com.lexuancong.demo.service;

import com.lexuancong.demo.model.Category;
import com.lexuancong.demo.model.CategoryType;
import com.lexuancong.demo.model.CustomerCategory;
import com.lexuancong.demo.repository.CategoryRepository;
import com.lexuancong.demo.repository.CategoryTypeRepository;
import com.lexuancong.demo.repository.CustomerCategoryRepository;
import com.lexuancong.demo.viewmodel.CategoryPostVm;
import com.lexuancong.demo.viewmodel.CategoryVm;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryTypeRepository categoryTypeRepository;
    private final CustomerCategoryRepository customerCategoryRepository;

    public CategoryService(CategoryRepository categoryRepository, CategoryTypeRepository categoryTypeRepository, CustomerCategoryRepository customerCategoryRepository) {
        this.categoryRepository = categoryRepository;
        this.categoryTypeRepository = categoryTypeRepository;
        this.customerCategoryRepository = customerCategoryRepository;
    }

    public List<CategoryVm> getCategoryByIdTab(String typeCategory){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String customerId = authentication.getName();
        CategoryType categoryType = categoryTypeRepository.findById(typeCategory)
                .orElse(null);
        List<CustomerCategory> customerCategoryList  = customerCategoryRepository.findAllByCustomerId(customerId);
        List<Category> categories = customerCategoryList.stream().map(customerCategory ->
                customerCategory.getCategory()).toList();
        return  categories.stream().filter(category -> category.getCategoryType().getType().equals(typeCategory))
                .map(CategoryVm::fromModel).toList();


    }

    public CategoryVm create(CategoryPostVm categoryPostVm){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String customerId = authentication.getName();
        Category category = new Category();
        System.out.println(categoryPostVm.getClass());

        CategoryType categoryType = categoryTypeRepository.findById(categoryPostVm.type())
                .orElse(null);
        if(categoryType!=null){
            category.setCategoryType(categoryType);
            category.setName(categoryPostVm.name());
            category.setDescription(categoryPostVm.description());
        }
        category = categoryRepository.save(category);
        CustomerCategory customerCategory = new CustomerCategory();
        customerCategory.setCustomerId(customerId);
        customerCategory.setCategory(category);
        customerCategoryRepository.save(customerCategory);
        return CategoryVm.fromModel(category);
    }
}
