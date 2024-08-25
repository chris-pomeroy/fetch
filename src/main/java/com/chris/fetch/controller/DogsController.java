package com.chris.fetch.controller;

import com.chris.fetch.model.Dog;
import com.chris.fetch.repository.DogsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class DogsController {

    private final DogsRepository repository;

    @GetMapping("dogs")
    public List<Dog> getDogs() {
        return repository.findAll();
    }
}
