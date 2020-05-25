package pl.netpaper.shop.procesor;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;
import pl.netpaper.shop.model.dao.Product;
import pl.netpaper.shop.repository.ProductRepository;

import java.util.List;

@RequiredArgsConstructor
@Component
public class ProductWriter implements ItemWriter<Product> {

    private final ProductRepository productRepository;

    @Override
    public void write(List<? extends Product> list) throws Exception {
        productRepository.saveAll(list);
    }
}
