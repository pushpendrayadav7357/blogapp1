package com.example.demo.payloads;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto {
    private int catId;
    @NotNull
    @NotBlank(message = "Please enter category title")
    private String catTitle;
    @NotNull
    @NotBlank(message = "Please enter category description")
    private String catDescription;
}
