package com.stark.duckapi.controller;

import com.stark.duckapi.model.DuckModel;
import com.stark.duckapi.service.DuckManagerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ducks")
public class DuckManagerController {
    private final DuckManagerService service;

    public DuckManagerController(DuckManagerService service) {
        this.service = service;
    }

    @GetMapping
    public List<DuckModel> getAll() {
        return service.getDucks();
    }

    @GetMapping("/{nickname}")
    public ResponseEntity<DuckModel> getByNickname(@PathVariable String nickname) {
        return service.getDucksByNickname(nickname)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public DuckModel create(@RequestBody DuckModel duck) {
        return service.create(duck);
    }

    @PutMapping("/{nickname}")
    public ResponseEntity<DuckModel> update(@PathVariable String nickname, @RequestBody DuckModel duck) {
        DuckModel updated = service.update(nickname, duck);
        if (updated == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{nickname}")
    public ResponseEntity<Void> delete(@PathVariable String nickname) {
        boolean deleted = service.delete(nickname);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
