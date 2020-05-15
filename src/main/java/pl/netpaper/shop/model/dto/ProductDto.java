package pl.netpaper.shop.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)

public class ProductDto {
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private Double quantity;
    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;
}
