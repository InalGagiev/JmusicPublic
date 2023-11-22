package ru.djt.jmusic.repo;

import org.springframework.data.repository.CrudRepository;
import ru.djt.jmusic.entity.Chart;

public interface ChartRepository extends CrudRepository<Chart, Long>{
}