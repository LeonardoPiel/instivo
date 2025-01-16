package br.com.leonardopiel.instivotesting.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.leonardopiel.instivotesting.dtos.AdmissionRequest;
import br.com.leonardopiel.instivotesting.model.Admission;
import br.com.leonardopiel.instivotesting.service.AdmissionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/admission")
public class AdmissionController {

    @Autowired
    private AdmissionService admissionService;

    @GetMapping
    public ResponseEntity<Page<Admission>> getAllAdmissions(
        @Parameter(description = "Número da página (inicia em 0)", example = "0") 
        @RequestParam(defaultValue = "0") int page,

        @Parameter(description = "Quantidade de elementos por página", example = "10") 
        @RequestParam(defaultValue = "10") int size,

        @Parameter(description = "Campo de ordenação", example = "admissionDate") 
        @RequestParam(defaultValue = "admissionDate") String sortBy,

        @Parameter(description = "Direção de ordenação (asc ou desc)", example = "asc") 
        @RequestParam(defaultValue = "asc") String sortDir
    ) {
        Pageable pageable = PageRequest.of(
            page,
            size,
            sortDir.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending()
        );
        Page<Admission> admissions = admissionService.getAllAdmissions(pageable);
        return ResponseEntity.ok(admissions);
    }

    @Operation(summary = "Retornar cálculo de admissão", description = "Retorna dados da requisição de acordo com os parâmetros de entrada")
    @PostMapping("/calculate")
    public ResponseEntity<Admission> calculateAdmissionDetails(
            @Valid @RequestBody AdmissionRequest request) {
        Admission admission = admissionService.calculateAdmissionDetails(
                request.getAdmissionDate(), request.getGrossSalary());
        return ResponseEntity.ok(admission);
    }

    @Operation(summary = "Inserir nova admissão", description = "Insere uma nova admissão de acordo com o JSON de exemplo.")
    @PostMapping
    public ResponseEntity<Admission> saveAdmission(@Valid @RequestBody AdmissionRequest request) {
        Admission admission = admissionService.calculateAdmissionDetails(
                request.getAdmissionDate(), request.getGrossSalary());
        Admission savedAdmission = admissionService.saveAdmission(admission);
        return ResponseEntity.ok(savedAdmission);
    }

    @Operation(summary = "Retornar admissão por Identificador único", description = "Retorna a admissão passada por como parte da URL na variável Id")
    @GetMapping("/{id}")
    public ResponseEntity<Admission> GetById(@PathVariable String id) {
        Optional<Admission> admission = admissionService.GetById(id);
        return admission.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}