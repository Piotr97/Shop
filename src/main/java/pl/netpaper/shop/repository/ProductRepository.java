package pl.netpaper.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.netpaper.shop.model.dao.Product;

public interface ProductRepository extends JpaRepository<Product,Long> {
}
