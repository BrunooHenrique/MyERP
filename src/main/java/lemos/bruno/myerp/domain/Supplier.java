package lemos.bruno.myerp.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Table(name = "supplier")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Supplier {
    @Id
    @GeneratedValue
    private UUID id;
    // Id gerado automaticamente


    private String name;
    //nome do fornecedor
    private String cnpj;
    //cnpj do fornecedor
    private String address;
    //endereço do fornecedor
    private String phone;
    //telefone do fornecedor



}
