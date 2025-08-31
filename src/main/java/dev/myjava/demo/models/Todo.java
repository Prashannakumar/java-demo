package dev.myjava.demo.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Data
/**
 * @Data has both getter and setter, if we need only one means, add it as needed
 * these are from lombok
 */
//@Getter
//@Setter

/**
 * instead of adding constructor manually we can use these below annotations as needed
 * these are from lombok
 */
//@AllArgsConstructor
//@NoArgsConstructor
public class Todo {
    @Id
    @GeneratedValue
    Long id;
    @NotBlank @NotNull
    @Schema(name = "title", example = "Complete Spring Boot")
    String title;
    @NotBlank(message = "Description Cannot be blank")
//    @Size(min = 5, max = 50)
//    @Pattern(regexp = "^[0-9]{10}$")
//    @Min() @Max()
    String description;
    Boolean isCompleted;

    @Email
    String email;
}
