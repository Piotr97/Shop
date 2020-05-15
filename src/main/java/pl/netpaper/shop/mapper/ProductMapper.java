package pl.netpaper.shop.mapper;

import pl.netpaper.shop.model.dao.Product;
import pl.netpaper.shop.model.dto.ProductDto;

import java.util.List;

public interface ProductMapper {

    Product productDtoToProduct(ProductDto productDto);

    ProductDto productToProductDto(Product product);

    List<ProductDto> productListToProductListDto(List<Product> products);

}
