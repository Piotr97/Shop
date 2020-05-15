package pl.netpaper.shop.service;


import pl.netpaper.shop.model.dao.Product;
import pl.netpaper.shop.model.elastic.Basket;

public interface BasketService {

    void save(Product product, Long userId);

    Basket showBasket(Long userId);

    void delete(Long productId, Long userId);
}
