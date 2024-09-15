package com.chris.fetch.component;

import com.chris.fetch.model.Dog;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Random;

@Component
@RequiredArgsConstructor
public class GenerateProfiles implements CommandLineRunner {

    private final ObjectMapper mapper;

    @Override
    public void run(String... args) throws Exception {
        List<String> names = readFile("names.json");
        Random random = new Random();
        readFile("urls.json").stream()
                .map(url -> Dog.builder()
                        .url(url)
                        .name(names.get(random.nextInt(names.size())))
                        .age(1 + random.nextInt(10))
                        .build())
                .forEach(this::createInsertStatement);
    }

    @SneakyThrows
    private void createInsertStatement(Dog dog) {
        String insert = "INSERT INTO dog (url, name, age) VALUES ('%s', '%s', %d);%n"
                .formatted(dog.getUrl(), dog.getName(), dog.getAge());
        Path output = Path.of(new FileSystemResource("src/main/resources/data/inserts.sql").getFile().getAbsolutePath());
        Files.writeString(output, insert, StandardOpenOption.APPEND);
    }

    private List<String> readFile(String filename) throws IOException {
        InputStream urlInputStream = new ClassPathResource("data/" + filename).getInputStream();
        return mapper.readValue(urlInputStream, new TypeReference<>() {});
    }
}