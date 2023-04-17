package com.geekbrains.spring_data1.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

// заготовка на обработку ошибки
@AllArgsConstructor
@Data
public class FieldValidationError {

    private List<String> errorFieldsMessage;



}
