package br.com.leonardopiel.instivotesting.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.Period;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.leonardopiel.instivotesting.model.Admission;

@ExtendWith(MockitoExtension.class)
public class AdmissionServiceTest {
    @InjectMocks
    private AdmissionService admissionService;

    @Test
    public void testCalculateAdmissionDetails() {
        // Dados de entrada
        LocalDate admissionDate = LocalDate.of(2020, 1, 1);
        BigDecimal grossSalary = new BigDecimal("10000");

        // Executa o método a ser testado
        Admission admission = admissionService.calculateAdmissionDetails(admissionDate, grossSalary);

        // Verifica os resultados
        assertEquals(5, admission.getYears()); // Supondo que o teste seja executado em 2025
        BigDecimal exptected = new BigDecimal("3500").setScale(2);
        assertEquals(exptected, admission.getThirtyFivePercent().setScale(2));
    }

    @Test
    public void testCalculateAdmissionDetailsWithFutureDate() {
        // Dados de entrada
        LocalDate futureDate = LocalDate.now().plusDays(1);
        BigDecimal grossSalary = new BigDecimal("5000");

        // Executa o método e verifica se lança exceção
        IllegalArgumentException exception = org.junit.jupiter.api.Assertions
                .assertThrows(IllegalArgumentException.class, () -> {
                    admissionService.calculateAdmissionDetails(futureDate, grossSalary);
                });

        assertEquals("A data de admissão não pode ser no futuro.", exception.getMessage());
    }

    @Test
    public void testCalculateAdmissionDetailsWithNegativeSalary() {
        // Dados de entrada
        LocalDate admissionDate = LocalDate.of(2020, 1, 1);
        BigDecimal negativeSalary = new BigDecimal("-1000");

        // Executa o método e verifica se lança exceção
        IllegalArgumentException exception = org.junit.jupiter.api.Assertions
                .assertThrows(IllegalArgumentException.class, () -> {
                    admissionService.calculateAdmissionDetails(admissionDate, negativeSalary);
                });

        assertEquals("O salário bruto não pode ser negativo.", exception.getMessage());
    }

    @Test
    public void testCalculateAdmissionDetailsSalaryPrecision() {
        // Dados de entrada
        LocalDate admissionDate = LocalDate.of(2021, 5, 15);
        BigDecimal grossSalary = new BigDecimal("12345.67");

        // Executa o método
        Admission admission = admissionService.calculateAdmissionDetails(admissionDate, grossSalary);

        // Verifica o cálculo de 35%
        BigDecimal expected = grossSalary.multiply(new BigDecimal("0.35")).setScale(2, RoundingMode.HALF_UP);
        assertEquals(expected, admission.getThirtyFivePercent().setScale(2, RoundingMode.HALF_UP));
    }

    @Test
    public void testCalculateAdmissionDetailsWithPastDate() {
        // Dados de entrada
        LocalDate admissionDate = LocalDate.of(2000, 1, 1);
        BigDecimal grossSalary = new BigDecimal("7000");

        // Executa o método
        Admission admission = admissionService.calculateAdmissionDetails(admissionDate, grossSalary);

        // Verifica os resultados
        Period period = Period.between(admissionDate, LocalDate.now());
        assertEquals(period.getYears(), admission.getYears());
        assertEquals(period.getMonths(), admission.getMonths());
        assertEquals(period.getDays(), admission.getDays());
    }
}
