package pl.netpaper.shop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.netpaper.shop.mapper.ProductMapper;
import pl.netpaper.shop.model.dao.Product;
import pl.netpaper.shop.model.dto.ProductDto;
import pl.netpaper.shop.service.ProductService;


@RestController
@RequiredArgsConstructor
@RequestMapping("api/product")
public class ProductController {

    private final ProductService productService;
    private final ProductMapper productMapper;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ProductDto addProduct(@RequestBody ProductDto productDto){
        Product product = productService.save(productMapper.productDtoToProduct(productDto));
        return productMapper.productToProductDto(product);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ProductDto updateProduct(@PathVariable Long id){
        Product productById = productService.productById(id);
        return productMapper.productToProductDto(productById);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public void deleteProduct(@PathVariable Long id){
        productService.delete(id);
    }


    @GetMapping
    public Page<ProductDto> getPage(@RequestParam int page,  @RequestParam int size){
        Page<Product> productPage = productService.page(PageRequest.of(page, size));
        return productPage.map(productMapper::productToProductDto);
    }

}
// hazelcast
//zrobic controller Basket,
// sprawdzic co to hystrix(cyrcle braker (odcinacz obwodow), √
// ribon(load balancing)) √
//docker file i compose
//sonarqube √
//zad dom controller
// mapper dokonczyc
//sprobowac orderby