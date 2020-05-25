package pl.netpaper.shop.procesor.export;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import pl.netpaper.shop.model.csv.ProductCsv;

@Component

public class CsvWriter {

    /*public FlatFileItemWriter<ProductCsv> write() {
        FlatFileItemWriter<ProductCsv> flatFileItemWriter = new FlatFileItemWriter<>();
        flatFileItemWriter.setResource(new ClassPathResource("product.csv"));
        flatFileItemWriter.setLineAggregator(product -> {
        });
    }*/
}