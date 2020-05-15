package pl.netpaper.shop.model.elastic;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.Id;
import java.util.List;

@Document(indexName = "basket") // tworzy index w elasticu
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder

public class Basket {
    @Id
    private String id;
    private Long userId;
    private List<ProductBasket> products;

}
