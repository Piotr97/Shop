package pl.netpaper.shop;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableBatchProcessing
@EnableSwagger2
@EnableCaching
@EnableJpaAuditing //włącza auditing na klasach DAO
@SpringBootApplication
public class ShopApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShopApplication.class, args);
	}

}

//stworzyc plik csv
//sprobowac przez restapi wrzucania pliku batchowego
