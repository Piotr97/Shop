package pl.netpaper.shop.mapper.impl;

import org.springframework.stereotype.Component;
import pl.netpaper.shop.mapper.BasketMapper;
import pl.netpaper.shop.model.dto.BasketDto;
import pl.netpaper.shop.model.elastic.Basket;


@Component
public class BasketMapperImpl implements BasketMapper {
    @Override
    public Basket basketDtoToBasket(BasketDto basketDto) {
        return Basket.builder()
                .id(basketDto.getId())
                .products(basketDto.getProducts())
                .build();
    }

    @Override
    public BasketDto basketToBasketDto(Basket basket) {
        return BasketDto.builder()
                .id(basket.getId())
                .products(basket.getProducts())
                .build();
    }
}


