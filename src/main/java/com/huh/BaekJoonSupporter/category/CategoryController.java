package com.huh.BaekJoonSupporter.category;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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
}
