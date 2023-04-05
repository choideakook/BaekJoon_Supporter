package com.huh.BaekJoonSupporter.category;

import com.huh.BaekJoonSupporter.category.form.CategoryForm;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    //-- 카테고리 목록 --//
    @GetMapping("/list")
    public String showList(Model model) {
        List<Category> categories = categoryService.getCategoryAll();
        model.addAttribute("categories", categories);
        return "/category/list";
    }

    //-- 카테고리 생성 폼 --//
    @GetMapping("create")
//    @PreAuthorize("isAuthenticated()")
    public String showCreate(CategoryForm categoryForm) {
        return "/category/create";
    }

    //-- 카테고리 생성 처리 --//
    @PostMapping("create")
//    @PreAuthorize("isAuthenticated()")
    public String showCreate(CategoryForm categoryForm,
                             BindingResult bindingResult,
                             Principal principal) {
        categoryService.create(categoryForm.getName(), categoryForm.getAbout());
        return "redirect:/category/list";
    }
}
