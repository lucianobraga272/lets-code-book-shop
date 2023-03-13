package com.luciano.books.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

import com.luciano.books.dto.BookDTO;
import com.luciano.books.dto.CategoryDTO;
import com.luciano.books.dto.FactoryEntity;
import com.luciano.books.exception.NotFoundException;
import com.luciano.books.dto.FactoryDTO;
import com.luciano.books.model.Book;
import com.luciano.books.model.Category;
import com.luciano.books.service.BookService;
import com.luciano.books.service.CategoryService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/livro")
@AllArgsConstructor
public class BookController {

    private BookService bookService;
    private CategoryService categoryService;
    private FactoryEntity factoryEntity;

    @GetMapping
    public ResponseEntity<List<BookDTO>> getAll() {
        List<BookDTO> list = bookService.getAll().get().stream()
                .map(FactoryDTO::entityToDTO)
                .collect(Collectors.toList());

        return ResponseEntity.ok().header("List-size", Integer.toString(list.size())).body(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDTO> get(@PathVariable int id) {
        Optional<Book> book = bookService.get(id);
        System.out.println(book.get());
        if (!book.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new BookDTO());
        }
        BookDTO bookDTO = FactoryDTO.entityToDTO(book.get());
        return ResponseEntity.ok().body(bookDTO);
    }

    @GetMapping("/bycategory/{category}")
    public ResponseEntity<List<BookDTO>> get(@PathVariable("category") String categoryName) {
        Optional<Category> category = categoryService.getByName(categoryName);

        if(!category.isPresent()){
            throw new NotFoundException("Categoria " + categoryName + " não encontrada!");
        }
        List<BookDTO> bookDTOs = bookService.findByCategory(category.get()).stream()
                .map(FactoryDTO::entityToDTO)
                .collect(Collectors.toList());
        ;

        return ResponseEntity.ok().body(bookDTOs);
    }

    @PostMapping
    public ResponseEntity<BookDTO> create(@RequestBody BookDTO bookDTO) {
        Book book = factoryEntity.dtoToEntity(bookDTO);
        if (!categoryService.getByName(bookDTO.getCategory()).isPresent()) {
            throw new NotFoundException("Categoria não encontrada!");
            // return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new BookDTO());
        }
        bookService.save(book);
        return ResponseEntity.ok().body(FactoryDTO.entityToDTO(book));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookDTO> update(@PathVariable("id") int id, @RequestBody BookDTO bookDTO) {
        Optional<Book> bookOptional = bookService.get(id);
        if (!categoryService.getByName(bookDTO.getCategory()).isPresent()) {
            throw new NotFoundException("Categoria não encontrada!");
            // return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new BookDTO());
        }
        if (!bookOptional.isPresent()) {
            throw new NotFoundException("Livro não encontrado!");
            // return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new BookDTO());
        }

        Book book = factoryEntity.dtoToEntity(bookDTO);
        BookDTO bookDTO2 = FactoryDTO.entityToDTO(bookService.update(id, book));

        return ResponseEntity.ok().body(bookDTO2);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable int id) {
        Optional<Book> book = bookService.get(id);

        if (!book.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(false);
        }

        return ResponseEntity.ok().body(bookService.delete(id));
    }

    @GetMapping("/nome")
    public ResponseEntity<BookDTO> getByName(@RequestParam String name) {

        Optional<Book> book = bookService.getByName(name);

        if (!book.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new BookDTO());
        }
        BookDTO bookDTO = FactoryDTO.entityToDTO(book.get());

        return ResponseEntity.ok().body(bookDTO);
    }
}
