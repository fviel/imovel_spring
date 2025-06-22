package br.com.amitta.real_state.dtos;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RealStateDTO {

    @NotBlank
    private String address;

    @NotNull
    private Double price;

    @Size(max = 255)
    private String description;
}
