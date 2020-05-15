package pl.netpaper.shop.mapper.impl;

import org.springframework.stereotype.Component;
import pl.netpaper.shop.mapper.ProductMapper;
import pl.netpaper.shop.model.dao.Product;
import pl.netpaper.shop.model.dto.ProductDto;


import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductMapperImpl implements ProductMapper {
    @Override
    public Product productDtoToProduct(ProductDto productDto) {
        return Product.builder()
                .id(productDto.getId())
                .name(productDto.getName())
                .price(productDto.getPrice())
                .description(productDto.getDescription())
                .quantity(productDto.getQuantity())
                .build();
    }

    @Override
    public ProductDto productToProductDto(Product product) {
        return ProductDto.builder()
                .id(product.getId())
                .name(product.getName())
                .quantity(product.getQuantity())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();
    }

    @Override
    public List<ProductDto> productListToProductListDto(List<Product> products) {
        return products.stream()
                .map(this::productToProductDto)
                .collect(Collectors.toList());
    }
}
