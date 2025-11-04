package com.stark.duckapi.service;

import com.stark.duckapi.model.DuckModel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DuckManagerService {
    private final List<DuckModel> ducks = new ArrayList<>();

    public List<DuckModel> getDucks() {
        return ducks;
    }

    public Optional<DuckModel> getDucksByNickname(String nickname) {
        return ducks.stream()
                .filter(d -> d.getNickname().equals(nickname))
                .findAny();
    }

    public DuckModel create(DuckModel duckModel) {
        ducks.add(duckModel);
        return duckModel;
    }

    public DuckModel update(String nickname, DuckModel duckModel) {
        Optional<DuckModel> epr = getDucksByNickname(nickname);
        if (epr.isPresent()) {
            DuckModel exists = epr.get();
            exists.setName(duckModel.getName());
            exists.setJob(duckModel.getJob());
            exists.setYears(duckModel.getYears());
            return exists;
        }
        return null;
    }

    public boolean delete(String nickname) {
        return ducks.removeIf(d -> d.getNickname().equals(nickname));
    }
}
