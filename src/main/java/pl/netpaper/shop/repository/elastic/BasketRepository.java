package pl.netpaper.shop.repository.elastic;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import pl.netpaper.shop.model.elastic.Basket;
import java.util.Optional;

public interface BasketRepository extends ElasticsearchRepository<Basket, String> {
    Optional<Basket> findByUserId(Long id);
}
