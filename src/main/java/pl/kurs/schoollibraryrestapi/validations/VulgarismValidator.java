package pl.kurs.schoollibraryrestapi.validations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Value;
import pl.kurs.schoollibraryrestapi.services.PathBuilderService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class VulgarismValidator implements ConstraintValidator<NonVulgar, String> {

    @Value("${vulgarisms}")
    private List<String> vulgarWords;

    @Override
    public void initialize(NonVulgar constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
        PathBuilderService pathBuilderService = new PathBuilderService();
        String filePath = pathBuilderService.createPath();
        try {
            String content = Files.readString(Path.of(filePath));
            vulgarWords = Arrays.asList(content.split(","));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        vulgarWords = vulgarWords.stream()
                .map(String::toLowerCase)
                .map(String::trim)
                .collect(Collectors.toList());
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        s = Optional.ofNullable(s)
                .map(String::toLowerCase)
                .map(String::trim)
                .orElse("");
        return !vulgarWords.contains(s);
    }
}
