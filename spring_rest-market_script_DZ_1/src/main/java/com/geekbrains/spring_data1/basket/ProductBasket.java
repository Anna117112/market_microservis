package com.geekbrains.spring_data1.basket;

import com.geekbrains.spring_data1.dto.ProductDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
@Data
@AllArgsConstructor
@Component
public class ProductBasket {

    public List<ProductDto> getListBasket() {
        return listBasket;
    }

    public void setListBasket(List<ProductDto> listBasket) {
        this.listBasket = listBasket;
    }

    private List<ProductDto> listBasket;

@PostConstruct
public void init(){
    listBasket = new ArrayList<>();
}
    public void addProduct(ProductDto productDto){
        listBasket.add(productDto);

    }

    public ProductBasket showBasket(){
    return this;
    }

    public void delete(ProductDto productDto){
    listBasket.remove(productDto);
    }
    public ProductBasket(){

    }

}
