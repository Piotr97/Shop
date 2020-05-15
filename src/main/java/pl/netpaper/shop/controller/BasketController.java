package pl.netpaper.shop.controller;

import lombok.RequiredArgsConstructor;
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

    @PostMapping("/{userId}")
    public void saveBasketEntry(@RequestBody ProductDto productDto, @PathVariable Long userId) {
        Product product = productMapper.productDtoToProduct(productDto);
        basketService.save(product, userId);
    }
}
