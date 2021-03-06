package pl.netpaper.shop.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import pl.netpaper.shop.model.dao.Product;
import pl.netpaper.shop.model.dao.User;
import pl.netpaper.shop.model.elastic.Basket;
import pl.netpaper.shop.model.elastic.ProductBasket;
import pl.netpaper.shop.repository.ProductRepository;
import pl.netpaper.shop.repository.UserRepository;
import pl.netpaper.shop.repository.elastic.BasketRepository;
import pl.netpaper.shop.service.BasketService;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BasketServiceImpl implements BasketService {

    private final BasketRepository basketRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    @Override
    public void save(Product product) {
        Basket basket = new Basket();
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<Product> optionalProduct = productRepository.findById(product.getId());
        Optional<User> optionalUser = userRepository.findByEmail(email);
        Optional<Basket> optionalBasket = basketRepository.findByUserEmail(email);
        if (optionalProduct.isPresent() && optionalUser.isPresent()) {
            Product productDb = optionalProduct.get();
            ProductBasket productBasket = new ProductBasket();
            productBasket.setName(product.getName());
            productBasket.setPrice(product.getPrice());
            productBasket.setId(product.getId());
            if (product.getQuantity() <= productDb.getQuantity()) {
                productBasket.setQuantity(product.getQuantity());
            } else {
                productBasket.setQuantity(productDb.getQuantity());
            }
            if (optionalBasket.isPresent()) {
                basket = optionalBasket.get();
                basket.getProducts().removeIf(pDb -> pDb.getId().equals(product.getId()));
                basket.getProducts().add(productBasket);
            } else {
                basket.setUserEmail(email);
                List<ProductBasket> productBasketsList = new ArrayList<>();
                basket.setProducts(productBasketsList);
            }
            basketRepository.save(basket);
        } else {
            throw new EntityNotFoundException();
        }
    }

    //todo przetestowac metode czy dziala

    @Override
    public Basket showBasket() {
        return basketRepository.findByUserEmail(SecurityContextHolder.getContext().getAuthentication().getName())
                .orElseThrow(() -> new RuntimeException("basket doesn't exist"));
    }

    @Override
    public void delete(Long productId) {
        Optional<Basket> optionalBasket = basketRepository.findByUserEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        optionalBasket.ifPresent(basket -> {
            basket.getProducts().removeIf(productBasket -> productBasket.getId().equals(productId));
            basketRepository.save(basket);
        });
    }
}




