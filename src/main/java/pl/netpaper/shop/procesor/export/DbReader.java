package pl.netpaper.shop.procesor.export;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.stereotype.Component;
import pl.netpaper.shop.model.dao.Product;

import javax.sql.DataSource;

@RequiredArgsConstructor
@Component
public class DbReader {

    private final DataSource dataSource;

    public JdbcCursorItemReader<Product> reader() {
        JdbcCursorItemReader<Product> jdbcCursorItemReader = new JdbcCursorItemReader<>();
        jdbcCursorItemReader.setDataSource(dataSource);
        jdbcCursorItemReader.setSql("select * from Product");
        jdbcCursorItemReader.setRowMapper((resultSet, rowNum) -> {
            Product product = new Product();
            product.setId(resultSet.getLong("id"));
            product.setName(resultSet.getString("name"));
            product.setPrice(resultSet.getBigDecimal("price"));
            product.setQuantity(resultSet.getDouble("quantity"));
            return product;
        });
        return jdbcCursorItemReader;
    }
}
