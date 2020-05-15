package pl.netpaper.shop.service;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pl.netpaper.shop.model.dao.Product;

public interface ProductService {

    @CachePut(cacheNames = "product", key = "#result.id") //funkcja ktora zawsze wywoluje metode i wstawia zwrocony obiekt z metody do cache
    Product save(Product product);

    @Cacheable(cacheNames = "product", key = "#id") // wywoluje metody tylko wtedy kiedy w cache nie ma klucza
    Product productById(Long id);

    @CacheEvict(cacheNames = "product", key = "#id") // usuwa z chache po kluczu
    void delete(Long id);

    Page<Product> page(Pageable pageable);
}

