package com.chris.fetch.component;

import com.chris.fetch.model.Dog;
import com.chris.fetch.repository.DogsRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Random;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class GenerateProfiles implements CommandLineRunner {

    private final ObjectMapper mapper;
    private final DogsRepository dogsRepository;

    @Override
    public void run(String... args) throws Exception {
        Set<String> currentSavedUrls = dogsRepository.findAllUrls();
        List<String> names = readFile("names.json");
        Random random = new Random();
        readFile("urls.json").stream()
                .filter(url -> !currentSavedUrls.contains(url))
                .map(url -> Dog.builder()
                        .url(url)
                        .name(names.get(random.nextInt(names.size())))
                        .age(1 + random.nextInt(10))
                        .build())
                .forEach(dogsRepository::save);
    }

    private List<String> readFile(String filename) throws IOException {
        InputStream urlInputStream = new ClassPathResource("data/" + filename).getInputStream();
        return mapper.readValue(urlInputStream, new TypeReference<>() {});
    }
}