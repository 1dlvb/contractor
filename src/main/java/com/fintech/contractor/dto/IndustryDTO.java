package com.fintech.contractor.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO for representing an industry.
 * Contains basic information about an industry entity.
 * @author Matushkin Anton
 * @see com.fintech.contractor.model.Industry
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class IndustryDTO {

    @Schema(description = "The ID of the industry.", example = "2")
    private Long id;
    @Schema(description = "The name of the industry.", example = "Автомобилестроение")
    private String name;

}
