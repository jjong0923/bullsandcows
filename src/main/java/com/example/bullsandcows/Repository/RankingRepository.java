package com.example.bullsandcows.Repository;

import com.example.bullsandcows.Entity.Ranking;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;
import java.util.List;

public interface RankingRepository  extends CrudRepository<Ranking, Long> {
    @Override
    ArrayList<Ranking> findAll();

    List<Ranking> findAllByOrderByTryCountAsc();
}
