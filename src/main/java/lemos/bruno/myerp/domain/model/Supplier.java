package lemos.bruno.myerp.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
