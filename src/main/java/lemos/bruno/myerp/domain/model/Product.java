package lemos.bruno.myerp.domain.model;

import jakarta.persistence.*;

import java.util.UUID;

@Table(name = "product")
@Entity(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    // Id gerado automaticamente


    private String name;
    //nome do produto
    private String description;
    //descrição do produto
    private Double price;
    //preço do produto
    private Double stock;
    //quantidade no estoque, double por poder ser valor fracionado


    public UUID getId() {
        return id;
    }

    public double getStock() {
        return stock;
    }

    public void setStock(Double stock) {
        this.stock = stock;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void updateStock(Double amount){
        this.setStock(this.getStock() + amount);
    }
}
