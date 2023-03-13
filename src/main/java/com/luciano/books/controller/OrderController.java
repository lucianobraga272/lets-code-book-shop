package com.luciano.books.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RestController;

import com.luciano.books.dto.OrderDTO;
import com.luciano.books.exception.NotFoundException;
import com.luciano.books.dto.FactoryEntity;
import com.luciano.books.dto.FactoryDTO;
import com.luciano.books.model.Book;
import com.luciano.books.model.Order;
import com.luciano.books.service.BookService;
import com.luciano.books.service.CustomerService;
import com.luciano.books.service.OrderService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/venda")
@AllArgsConstructor
public class OrderController {

    private OrderService orderService;
    private CustomerService customerService;
    private BookService bookService;

    private FactoryEntity factoryEntity;

    @GetMapping
    public ResponseEntity<List<OrderDTO>> getAll() {
        List<OrderDTO> list = orderService.getAll().get().stream()
                .map(FactoryDTO::entityToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok().header("List-size", Integer.toString(list.size())).body(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDTO> get(@PathVariable int id) {
        Optional<Order> order = orderService.get(id);

        if (!order.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new OrderDTO());
        }
        OrderDTO orderDTO = FactoryDTO.entityToDTO(order.get());

        return ResponseEntity.ok().body(orderDTO);
    }

    @PostMapping
    public ResponseEntity<OrderDTO> create(@RequestBody OrderDTO orderDTO) {
        Order order = factoryEntity.dtoToEntity(orderDTO);
        
        order = orderService.save(order);
        return ResponseEntity.ok().body(FactoryDTO.entityToDTO(order));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable int id, @RequestBody OrderDTO orderDTO) {
        Optional<Order> orderOptional = orderService.get(id);
        if (!orderOptional.isPresent()) {
            throw new NotFoundException("Venda não encontrada!");
            // return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new OrderDTO());
        }
        Order order = factoryEntity.dtoToEntity(orderDTO);
        
        order = orderService.update(id, order);
        return ResponseEntity.ok().body(FactoryDTO.entityToDTO(order));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable int id) {
        Optional<Order> order = orderService.get(id);
        if (!order.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new OrderDTO());
        }
        return ResponseEntity.ok().body(orderService.delete(id));
    }
}
