package pl.netpaper.shop.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import pl.netpaper.shop.model.elastic.ProductBasket;

import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
//@JsonInclude(JsonInclude.Include.NON_NULL)


public class BasketDto {

    private String id;
    private List<ProductBasket> products;
}
