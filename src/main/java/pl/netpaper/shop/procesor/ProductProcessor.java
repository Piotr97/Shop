package pl.netpaper.shop.procesor;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;
import pl.netpaper.shop.model.csv.ProductCsv;
import pl.netpaper.shop.model.dao.Product;

@Component
public class ProductProcessor implements ItemProcessor<ProductCsv, Product> {

    @Override
    public Product process(ProductCsv productCsv) throws Exception {
        return Product.builder()
                .name(productCsv.getName())
                .description(productCsv.getDescription())
                .price(productCsv.getPrice())
                .quantity(productCsv.getQuantity())
                .build();
    }
}
