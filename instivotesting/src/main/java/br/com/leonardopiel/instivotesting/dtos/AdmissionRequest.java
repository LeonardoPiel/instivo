package br.com.leonardopiel.instivotesting.dtos;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdmissionRequest {
    @NotNull(message = "A data de admissão é obrigatória")
    @PastOrPresent(message = "A data de admissão deve ser no passado ou presente")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate admissionDate;

    @NotNull(message = "O salário bruto é obrigatório")
    @Positive(message = "O salário bruto deve ser um valor positivo")
    private BigDecimal grossSalary;
}