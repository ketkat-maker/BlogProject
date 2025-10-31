package com.katkat_maker.Blog.Domain.Dtos;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Builder
//@Data
@AllArgsConstructor
@NoArgsConstructor
public class createTagRequest {
    @NotNull(message = "Tag list cannot be null")
    @NotEmpty(message = "Tag list cannot be empty")
    @Size(max = 10, message = "Maximum {max} tags allowed")
    private List<@Size(min = 2, max = 30, message = "Tag name must be between {min} and {max} characters")
    @Pattern(regexp = "^[\\w\\s-]+$", message = "Tag name can only contain letters, numbers, spaces, and hyphens")
            String> names;

    public List<

            String> getNames() {
        return names;
    }

    public void setNames(List<

            String> names) {
        this.names = names;
    }
}
