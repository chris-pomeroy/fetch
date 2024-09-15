package com.chris.fetch.repository;

import com.chris.fetch.model.Dog;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface DogsRepository extends Repository<Dog, Long> {

    List<Dog> findAll();
}
