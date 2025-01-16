package br.com.leonardopiel.instivotesting.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.leonardopiel.instivotesting.model.Admission;
import br.com.leonardopiel.instivotesting.repository.IAdmissionRepository;

@Service
public class AdmissionService {
    @Autowired
    private IAdmissionRepository admissionRepository;

    public Admission calculateAdmissionDetails(LocalDate admissionDate, BigDecimal grossSalary) {
        if (admissionDate.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("A data de admissão não pode ser no futuro.");
        }
        if (grossSalary.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("O salário bruto não pode ser negativo.");
        }
        // Cálculo dos dias, meses e anos
        LocalDate today = LocalDate.now();
        Period period = Period.between(admissionDate, today);

        // Cálculo de 35% do salário bruto
        BigDecimal thirtyFivePercent = grossSalary.multiply(new BigDecimal("0.35"));

        // Criar e retornar o objeto
        Admission Admission = new Admission();
        Admission.setAdmissionDate(admissionDate);
        Admission.setGrossSalary(grossSalary);
        Admission.setThirtyFivePercent(thirtyFivePercent);
        Admission.setYears(period.getYears());
        Admission.setMonths(period.getMonths());
        Admission.setDays(period.getDays());

        return Admission;
    }

    public Admission saveAdmission(Admission Admission) {
        return admissionRepository.save(Admission);
    }

    public Page<Admission> getAllAdmissions(Pageable pageable) {
        return admissionRepository.findAll(pageable);
    }

    public Optional<Admission> GetById(String id) {
        return admissionRepository.findById(id);
    }
}
