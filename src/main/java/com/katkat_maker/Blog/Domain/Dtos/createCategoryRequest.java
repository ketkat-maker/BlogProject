package com.katkat_maker.Blog.Domain.Dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class createCategoryRequest {
    @NotBlank(message = "Category name is required")
    @Size(min=2,max=50,message = "Category name must be between  {min}  and  {max} characters")
    @Pattern(regexp = "^[\\w\\s-]+$",message = "Category name only contain letters , numbers , spaces and hyphen ")
    private String names;

    public String getNames() {
        return names;
    }
    public void setNames(String names) {
        this.names = names;
    }

}
