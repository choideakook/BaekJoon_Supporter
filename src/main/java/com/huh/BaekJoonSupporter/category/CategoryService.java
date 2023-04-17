package com.huh.BaekJoonSupporter.category;

import com.huh.BaekJoonSupporter.DataNotFoundException;
import com.huh.BaekJoonSupporter.board.Board;
import com.huh.BaekJoonSupporter.board.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final BoardService boardService;

    //-- create --//
    @Transactional
    public Long create(String name, String about) {
        Category category = Category.createCategory(name, about);
        categoryRepository.save(category);
        return category.getId();
    }

    //-- find by id --//
    public Category getCategory(Long id) {
        Optional<Category> byId = categoryRepository.findById(id);

        if (byId.isPresent())
            return byId.get();

        throw new DataNotFoundException("존재하지 않는 카테고리입니다.");
    }

    //-- find by name --//
    public Category getCategory(String name) {
        Optional<Category> byName = categoryRepository.findByName(name);

        if (byName.isPresent())
            return byName.get();

        throw new DataNotFoundException("존재하지 않는 카테고리입니다.");
    }

    //-- find all categories --//
    public List<Category> getCategoryAll() {
        return categoryRepository.findAll();
    }

    //-- modify --//
    @Transactional
    public Long modify(Category category, String name, String about) {
        Category modifiedCategory = category.modifyCategory(name, about);
        categoryRepository.save(modifiedCategory);
        return modifiedCategory.getId();
    }

    //-- delete --//
    @Transactional
    public void delete(Long categoryId) {
        Category category = this.getCategory(categoryId);
        List<Board> boards = category.getBoards();

        for (Board board : boards)
            boardService.delete(board.getId());

        categoryRepository.delete(category);

    }
}
