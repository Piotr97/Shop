package pl.netpaper.shop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.netpaper.shop.mapper.ProductMapper;
import pl.netpaper.shop.model.dao.Product;
import pl.netpaper.shop.model.dto.ProductDto;
import pl.netpaper.shop.service.BasketService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/basket")
public class BasketController {

    public final ProductMapper productMapper;
    public final BasketService basketService;

    @PreAuthorize("isAuthenticated()") //kazdy zalogowany uzytk ma dostep
    @PostMapping("/{userId}")
    public void saveBasketEntry(@RequestBody ProductDto productDto) {
        Product product = productMapper.productDtoToProduct(productDto);
        basketService.save(product);
    }
}
