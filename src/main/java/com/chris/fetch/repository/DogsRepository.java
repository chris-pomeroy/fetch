package com.chris.fetch.repository;

import com.chris.fetch.model.Dog;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Set;

public interface DogsRepository extends Repository<Dog, Long> {

    List<Dog> findAll();

    @Query("SELECT d.url FROM Dog d")
    Set<String> findAllUrls();

    void save(Dog dog);
}
