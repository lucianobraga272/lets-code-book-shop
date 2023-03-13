package com.luciano.books.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.luciano.books.dto.CategoryDTO;
import com.luciano.books.dto.FactoryDTO;
import com.luciano.books.model.Category;
import com.luciano.books.service.CategoryService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/categoria")
@AllArgsConstructor
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<CategoryDTO>> getAll() {
        List<CategoryDTO> list = categoryService.getAll().get().stream()
                .map(FactoryDTO::entityToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok().header("List-size", Integer.toString(list.size())).body(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> get(@PathVariable int id) {
        Optional<Category> category = categoryService.get(id);

        if (!category.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new CategoryDTO());
        }
        CategoryDTO categoryDTO = FactoryDTO.entityToDTO(category.get());

        return ResponseEntity.ok().body(categoryDTO);
    }

    @GetMapping("/nome")
    public ResponseEntity<CategoryDTO> getByName(@RequestParam String name) {

        Optional<Category> category = categoryService.getByName(name);

        if (!category.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new CategoryDTO());
        }
        CategoryDTO categoryDTO = FactoryDTO.entityToDTO(category.get());

        return ResponseEntity.ok().body(categoryDTO);
    }

    @PostMapping
    public ResponseEntity<CategoryDTO> create(@RequestBody Category category) {
        return ResponseEntity.ok().body(FactoryDTO.entityToDTO(categoryService.save(category)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryDTO> update(@PathVariable int id, @RequestBody Category category) {
        Optional<Category> categoryOptional = categoryService.get(id);

        if (!categoryOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new CategoryDTO());
        }

        CategoryDTO categoryDTO = FactoryDTO.entityToDTO(categoryService.update(id, category));

        return ResponseEntity.ok().body(categoryDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable int id) {
        Optional<Category> categoryOptional = categoryService.get(id);

        if (!categoryOptional.isPresent()) {
            // throw new NotFoundException("Categoria não encontrada!");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(false);
        }
        return ResponseEntity.ok().body(categoryService.delete(id));
    }
}