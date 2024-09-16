package com.chris.fetch.repository;

import com.chris.fetch.model.Dog;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface DogsRepository extends Repository<Dog, Long> {

    List<Dog> findAll();

    @Query(value = "SELECT * FROM Dog WHERE id = (SELECT floor(random() * count(id)) FROM Dog)", nativeQuery = true)
    Dog findRandom();
}
