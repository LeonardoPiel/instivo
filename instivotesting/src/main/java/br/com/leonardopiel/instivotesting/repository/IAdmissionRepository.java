package br.com.leonardopiel.instivotesting.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.leonardopiel.instivotesting.model.Admission;

public interface IAdmissionRepository extends MongoRepository<Admission, String> {
    List<Admission> findByAdmissionDateBetween(LocalDate start, LocalDate end);
}