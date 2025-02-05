package lemos.bruno.myerp.domain.controllers;


import lemos.bruno.myerp.domain.dtos.CustomerDto;
import lemos.bruno.myerp.domain.model.Customer;
import lemos.bruno.myerp.repositories.CustomerRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/customers")
public class CustomerController {


    @Autowired
    CustomerRepository customerRepository;


    @GetMapping
    public ResponseEntity getAll(){
        List<Customer> customerList = customerRepository.findAll();
        return ResponseEntity.status(HttpStatus.OK)
                .body(customerList);
    }
    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable(value = "id") UUID id) {
        Optional customer = customerRepository.findById(id);

        if (customer.isEmpty()) { //Id inexistente
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Customer not found");
        }
        //Id válido
        return ResponseEntity.status(HttpStatus.FOUND)
                .body(customer.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteById(@PathVariable(value= "id") UUID id) {
        Optional<Customer> customer = customerRepository.findById(id);

        if (customer.isEmpty()) {
            //id de cliente inexistente
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Customer not found");
        }
        //Id do cliente existe
        customerRepository.delete(customer.get());

        return ResponseEntity.status(HttpStatus.OK)
                .body("Customer '"+ customer.get().getName() +"' deleted");
    }

    @PostMapping
    public ResponseEntity create(@RequestBody CustomerDto dto) {

        var customer = new Customer();
        BeanUtils.copyProperties(dto, customer);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(customerRepository.save(customer));
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable(value = "id") UUID id, @RequestBody CustomerDto dto) {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);

        if (optionalCustomer.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Customer not found");
        }

        // Obtém a entidade existente
        Customer customerModel = optionalCustomer.get();

        // Atualiza apenas os campos necessários (evitando ID e campos não editáveis)
        if (dto.name()!= null)
            customerModel.setName(dto.name());

        if (dto.cpf()!= null)
            customerModel.setCpf(dto.cpf());

        if (dto.address()!= null)
            customerModel.setAddress(dto.address());

        if (dto.phone()!= null)
            customerModel.setPhone(dto.phone());

        return ResponseEntity.ok(customerRepository.save(customerModel));
    }
}

