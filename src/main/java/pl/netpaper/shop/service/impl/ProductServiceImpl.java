package pl.netpaper.shop.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.netpaper.shop.model.dao.Product;
import pl.netpaper.shop.repository.ProductRepository;
import pl.netpaper.shop.service.ProductService;

import javax.persistence.EntityNotFoundException;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product productById(Long id) {
        log.info("product none in cache with id: {} " , id);
            return productRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Product with id " + id + " not exist"));
        }

    @Override
    public void delete(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public Page<Product> page(Pageable pageable) {
        return productRepository.findAll(pageable);
    }
}
