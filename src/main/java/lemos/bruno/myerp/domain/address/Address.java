package lemos.bruno.myerp.domain.address;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.UUID;

@Table(name = "address")
@Entity
public class Address {
    @Id
    @GeneratedValue
    private UUID id;
    // Id gerado automaticamente

    private String country;
    private String state;
    private String city;
    private String street;
    private String number;
    private String zipCode;
}
