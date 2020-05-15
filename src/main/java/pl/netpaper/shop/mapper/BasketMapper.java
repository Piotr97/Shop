package pl.netpaper.shop.mapper;

import pl.netpaper.shop.model.dto.BasketDto;
import pl.netpaper.shop.model.elastic.Basket;

public interface BasketMapper {

    Basket basketDtoToBasket(BasketDto basketDto);

    BasketDto basketToBasketDto(Basket basket);
}
