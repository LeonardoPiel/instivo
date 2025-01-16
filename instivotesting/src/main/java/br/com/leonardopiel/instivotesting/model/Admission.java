package br.com.leonardopiel.instivotesting.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Document(collection = "admissions")
@Getter
@Setter
public class Admission {
    @Id
    private String id;
    private LocalDate admissionDate;
    private BigDecimal grossSalary;
    private BigDecimal thirtyFivePercent;
    private int years;
    private int months;
    private int days;
}
