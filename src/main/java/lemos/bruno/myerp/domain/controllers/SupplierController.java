package lemos.bruno.myerp.domain.controllers;



import lemos.bruno.myerp.domain.dtos.SupplierDto;
import lemos.bruno.myerp.domain.model.Supplier;

import lemos.bruno.myerp.repositories.SupplierRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/suppliers")
public class SupplierController
{


    @Autowired
    SupplierRepository supplierRepository;


    @GetMapping
    public ResponseEntity getAll(){
        List<Supplier> supplierList = supplierRepository.findAll();
        return ResponseEntity.status(HttpStatus.OK)
                .body(supplierList);
    }
    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable(value = "id") UUID id) {
        Optional supplier = supplierRepository.findById(id);

        if (supplier.isEmpty()) { //Id inexistente
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Supplier not found");
        }
        //Id válido
        return ResponseEntity.status(HttpStatus.FOUND)
                .body(supplier.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteById(@PathVariable(value= "id") UUID id) {
        Optional<Supplier> supplier = supplierRepository.findById(id);

        if (supplier.isEmpty()) {
            //id de fornecedor inexistente
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Supplier not found");
        }
        //Id do fornecedor existe
        supplierRepository.delete(supplier.get());

        return ResponseEntity.status(HttpStatus.OK)
                .body("Supplier '"+ supplier.get().getName() +"' deleted");
    }

    @PostMapping
    public ResponseEntity create(@RequestBody SupplierDto dto) {

        var supplier = new Supplier();
        BeanUtils.copyProperties(dto, supplier);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(supplierRepository.save(supplier));
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable(value = "id") UUID id, @RequestBody SupplierDto dto) {
        Optional<Supplier> optionalSupplier = supplierRepository.findById(id);

        if (optionalSupplier.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Supplier not found");
        }

        // Obtém a entidade existente
        Supplier supplierModel = optionalSupplier.get();

        // Atualiza apenas os campos necessários (evitando ID e campos não editáveis)
        if (dto.name()!= null)
            supplierModel.setName(dto.name());

        if (dto.cnpj()!= null)
            supplierModel.setCnpj(dto.cnpj());

        if (dto.address()!= null)
            supplierModel.setAddress(dto.address());

        if (dto.phone()!= null)
            supplierModel.setPhone(dto.phone());

        return ResponseEntity.ok(supplierRepository.save(supplierModel));
    }
}

