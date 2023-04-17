package com.geekbrains.spring_data1.aop;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// создаем обект для зиписи инфо о времени работы методоы
@Data
@NoArgsConstructor
// конструктор со всеми аргументами
@AllArgsConstructor
public class InfoAspect {


    public String title;
    public long time;









}
