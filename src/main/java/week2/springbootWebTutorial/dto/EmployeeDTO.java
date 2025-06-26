package week2.springbootWebTutorial.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.*;
import week2.springbootWebTutorial.annotations.EmployeeRoleValidation;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class EmployeeDTO {

   private Long id;

   @NotBlank
   @Size(min = 3, max = 10)
   private String name;

   @NotBlank
   @Email
   private String email;

   @NotNull
   @Max(value = 80)
   @Min(value = 18)
   private Integer age;

   //    @Pattern(regexp = "^(USER||ADMIN)$")
   @EmployeeRoleValidation
   private String role;

   @NotNull
   @Positive
   @Digits(integer = 6,fraction = 2)
   @DecimalMax(value = "100000.99")
   @DecimalMin(value = "100.50")
   private Double salary;

   @PastOrPresent
   private LocalDate dateOfJoining;

   @AssertTrue
   @JsonProperty("isActive")
   private Boolean isActive;

}
