package lemos.bruno.myerp.domain.dtos;

import java.math.BigDecimal;

public record ProductDto(String name, String description, BigDecimal price, Double stock) {


}
