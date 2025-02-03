package lemos.bruno.myerp.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Table(name = "product")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue
    private UUID id;
    // Id gerado automaticamente


    private String name;
    //nome do produto
    private String description;
    //descrição do produto
    private double price;
    //preço do produto
    private double stock;
    //quantidade no estoque, double por poder ser valor fracionado




}
