package com.stark.duckapi.controller;

import com.stark.duckapi.model.DuckModel;
import com.stark.duckapi.service.DuckManagerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ducks")
public class DuckManagerController {
    private final DuckManagerService duckManagerService;
    public DuckManagerController(DuckManagerService service){
        this.duckManagerService = service;
    }
    @GetMapping
    public List<DuckModel> getAll(){
        return duckManagerService.getDucks();
    }
    @GetMapping("/nickname")
    public ResponseEntity<DuckModel> getByNickName(@PathVariable String nickname){
        return duckManagerService.getDucksByNickname(nickname)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @PostMapping
    public DuckModel create(@RequestBody DuckModel duck) {
        return duckManagerService.create(duck);
    }

    @PutMapping("/{nickname}")
    public ResponseEntity<DuckModel> update(@PathVariable String nickname, @RequestBody DuckModel duck) {
        DuckModel updated = duckManagerService.update(nickname, duck);
        if (updated == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{nickname}")
    public ResponseEntity<Void> delete(@PathVariable String nickname) {
        boolean deleted = duckManagerService.delete(nickname);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
