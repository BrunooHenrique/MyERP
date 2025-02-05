package lemos.bruno.myerp.domain.controllers;


import lemos.bruno.myerp.domain.dtos.ProductDto;
import lemos.bruno.myerp.domain.model.Product;
import lemos.bruno.myerp.repositories.ProductRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/products")
public class ProductController {



    @Autowired
    ProductRepository repository;


    @GetMapping
    public ResponseEntity getAll() {
        List<Product> listProucts = repository.findAll();
        return ResponseEntity.status(HttpStatus.OK)
                .body(listProucts);
    }

    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable(value = "id") UUID id) {
        Optional product = repository.findById(id);

        if (product.isEmpty()) { //Id inexistente
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Product not found");
        }
        //Id válido
        return ResponseEntity.status(HttpStatus.FOUND)
                .body(product.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteById(@PathVariable(value = "id") UUID id) {
        Optional<Product> product = repository.findById(id);

        if (product.isEmpty()) { //Id inexistente
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Product not found");
        }
        //Id válido
        repository.delete(product.get());

        return ResponseEntity.status(HttpStatus.OK)
                .body("Product deleted");
    }



    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable(value = "id") UUID id, @RequestBody ProductDto dto) {
        Optional<Product> productOptional = repository.findById(id);

        if (productOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Product not found");
        }

        // Obtém a entidade existente
        Product productModel = productOptional.get();

        // Atualiza apenas os campos necessários (evitando ID e campos não editáveis)
        if (dto.name()!= null)
            productModel.setName(dto.name());

        if (dto.price()!= null)
            productModel.setPrice(dto.price());

        if (dto.description()!= null)
            productModel.setDescription(dto.description());

        if (dto.stock()!= null)
            productModel.setStock(dto.stock());

        return ResponseEntity.ok(repository.save(productModel));
    }


    @PutMapping("/add/{id}")
    public ResponseEntity addOrRemoveStock(@PathVariable(value = "id") UUID id, @RequestBody Double d) {
        Optional<Product> productOptional = repository.findById(id);

        if (productOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Product not found");
        }

        // Obtém a entidade existente
        Product productModel = productOptional.get();

        productModel.updateStock(d);

        return ResponseEntity.ok(repository.save(productModel));
    }


    @PostMapping
    public ResponseEntity save(@RequestBody ProductDto dto) {

        var product = new Product();
        BeanUtils.copyProperties(dto, product);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(repository.save(product));
    }

}
