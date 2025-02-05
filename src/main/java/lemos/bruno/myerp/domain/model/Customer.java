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

@Table(name = "customer")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    @Id
    @GeneratedValue
    private UUID id;
    // Id gerado automaticamente

    private String name;
    //nome do cliente
    private String cpf;
    //cpf do cliente
    private String address;
    //endereço do cliente
    private String phone;
    //telefone do cliente


}
