package br.com.amitta.real_state.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.amitta.real_state.entities.RealState;
import br.com.amitta.real_state.repositories.RealStateRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/realstates")
public class RealStateController {

    @Autowired
    private RealStateRepository repository;

    @GetMapping
    public List<RealState> getAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<RealState> getById(@PathVariable UUID id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<RealState> create(@Valid @RequestBody RealState realState) {
        RealState saved = repository.save(realState);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RealState> update(@PathVariable UUID id, @Valid @RequestBody RealState realState) {
        return repository.findById(id)
                .map(existing -> {
                    realState.setId(id);
                    return ResponseEntity.ok(repository.save(realState));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        return repository.findById(id)
                .map(existing -> {
                    repository.deleteById(id);
                    return ResponseEntity.noContent().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}