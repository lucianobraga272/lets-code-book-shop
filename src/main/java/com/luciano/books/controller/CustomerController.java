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

import com.luciano.books.dto.CustomerDTO;
import com.luciano.books.dto.FactoryEntity;
import com.luciano.books.dto.FactoryDTO;
import com.luciano.books.model.Customer;
import com.luciano.books.service.CustomerService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/cliente")
@AllArgsConstructor
public class CustomerController {

    private CustomerService customerService;
    private FactoryEntity factoryEntity;

    @GetMapping
    public ResponseEntity<List<CustomerDTO>> getAll() {
        List<CustomerDTO> list = customerService.getAll().get().stream()
                .map(FactoryDTO::entityToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok().header("List-size", Integer.toString(list.size())).body(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDTO> get(@PathVariable int id) {
        Optional<Customer> customer = customerService.get(id);

        if (!customer.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new CustomerDTO());
        }
        CustomerDTO customerDTO = FactoryDTO.entityToDTO(customer.get());

        return ResponseEntity.ok().body(customerDTO);
    }

    @PostMapping
    public ResponseEntity<CustomerDTO> create(@RequestBody CustomerDTO customerDTO) {
        Customer customer = factoryEntity.dtoToEntity(customerDTO);
        customerService.save(customer);
        return ResponseEntity.ok().body(FactoryDTO.entityToDTO(customer));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerDTO> update(@PathVariable int id, @RequestBody Customer customer) {
        Optional<Customer> customerOptional = customerService.get(id);
        if (!customerOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new CustomerDTO());
        }
        CustomerDTO customerDTO = FactoryDTO.entityToDTO(customerService.update(id, customer));
        return ResponseEntity.ok().body(customerDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable int id) {
        Optional<Customer> customerOptional = customerService.get(id);
        if (!customerOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(false);
        }
        return ResponseEntity.ok().body(customerService.delete(id));
    }

    @GetMapping("/nome")
    public ResponseEntity<CustomerDTO> getByName(@RequestParam String name) {

        Optional<Customer> customer = customerService.getByName(name);

        if (!customer.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new CustomerDTO());
        }
        CustomerDTO customerDTO = FactoryDTO.entityToDTO(customer.get());

        return ResponseEntity.ok().body(customerDTO);
    }

    @GetMapping("/cpf")
    public ResponseEntity<CustomerDTO> getByCpf(@RequestParam String cpf) {

        Optional<Customer> customer = customerService.getByCpf(cpf);

        if (!customer.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new CustomerDTO());
        }
        CustomerDTO customerDTO = FactoryDTO.entityToDTO(customer.get());

        return ResponseEntity.ok().body(customerDTO);
    }
}
