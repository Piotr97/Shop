package pl.netpaper.shop.model.csv;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder

public class ProductCsv {

    private String name;
    private Double quantity;
    private BigDecimal price;
    private String description;

}
