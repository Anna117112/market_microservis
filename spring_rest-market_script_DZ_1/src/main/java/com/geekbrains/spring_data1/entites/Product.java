package com.geekbrains.spring_data1.entites;

import jdk.jfr.DataAmount;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Table(name = "products")
// подключили зависимость lombok  и можно убрать гетеры и сетеры поставив @Data и конструктор по
// умолчанию пустой @NoArgsConstructor
@Data
@NoArgsConstructor
// конструктор со всеми полями
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public long id;

    @Column(name = "name")
    public String name;

    @Column(name = "cost")
    public Integer cost;


    public void setId(Long aLong) {
    }
}
