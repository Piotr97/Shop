package pl.netpaper.shop.procesor.export;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;
import pl.netpaper.shop.model.csv.ProductCsv;
import pl.netpaper.shop.model.dao.Product;

@Component
public class DbProcessor implements ItemProcessor<Product,ProductCsv> {
    @Override
    public ProductCsv process(Product product) throws Exception {
        return ProductCsv.builder()
                .description(product.getDescription())
                .name(product.getName())
                .price(product.getPrice())
                .quantity(product.getQuantity())
                .build();
    }
}
