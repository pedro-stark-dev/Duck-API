package com.stark.duckapi.service;

import com.stark.duckapi.model.DuckModel;
import com.stark.duckapi.repository.DuckRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DuckManagerService {
    private final DuckRepository repository;

    public DuckManagerService(DuckRepository repository) {
        this.repository = repository;
    }

    public List<DuckModel> getDucks() {
        return repository.findAll();
    }

    public Optional<DuckModel> getDucksByNickname(String nickname) {
        return repository.findByNickname(nickname);
    }

    public DuckModel create(DuckModel duckModel) {
        return repository.save(duckModel);
    }

    public DuckModel update(String nickname, DuckModel duckModel) {
        return repository.findByNickname(nickname)
                .map(existing -> {
                    existing.setName(duckModel.getName());
                    existing.setYears(duckModel.getYears());
                    existing.setJob(duckModel.getJob());
                    return repository.save(existing);
                }).orElse(null);
    }

    public boolean delete(String nickname) {
        return repository.deleteByNickname(nickname);
    }
}
