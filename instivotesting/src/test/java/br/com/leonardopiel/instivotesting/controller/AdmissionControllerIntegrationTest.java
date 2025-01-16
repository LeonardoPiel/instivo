package br.com.leonardopiel.instivotesting.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import br.com.leonardopiel.instivotesting.model.Admission;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AdmissionControllerIntegrationTest {
    @Autowired
    private TestRestTemplate restTemplate;

    //@Test
    // public void testSaveAdmission() {
    //     // Dados de entrada
    //     Admission admission = new Admission();
    //     admission.setAdmissionDate(LocalDate.of(2020, 1, 1));
    //     admission.setGrossSalary(new BigDecimal("1000"));

    //     // Executa a requisição POST
    //     ResponseEntity<Admission> response = restTemplate.postForEntity("/api/admissions", admission, Admission.class);

    //     // Verifica os resultados
    //     BigDecimal expected = new BigDecimal("350").setScale(2);
    //     BigDecimal actual = response.getBody().getThirtyFivePercent().setScale(2);
    //     assertEquals(200, response.getStatusCode().value());
    //     assertEquals(expected, actual);
    // }

    // @Test
    // public void testSaveAdmissionWithInvalidData() {
    //     // Dados de entrada inválidos
    //     Admission admission = new Admission();
    //     admission.setAdmissionDate(LocalDate.of(2025, 1, 1)); // Data no futuro
    //     admission.setGrossSalary(new BigDecimal("-1000")); // Salário negativo

    //     // Executa a requisição POST
    //     ResponseEntity<String> response = restTemplate.postForEntity("/api/admissions", admission, String.class);

    //     // Verifica os resultados
    //     assertEquals(400, response.getStatusCode().value());
    //     assertTrue(response.getBody().contains("Validation failed"));
    // }
}
