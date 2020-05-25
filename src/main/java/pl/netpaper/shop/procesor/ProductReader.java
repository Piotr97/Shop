package pl.netpaper.shop.procesor;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Component;
import pl.netpaper.shop.model.csv.ProductCsv;

@Component
public class ProductReader {

    @StepScope
    public ItemReader<ProductCsv> itemReader(String filepath) {
        BeanWrapperFieldSetMapper<ProductCsv> beanWrapperFieldSetMapper = new BeanWrapperFieldSetMapper<>();
        beanWrapperFieldSetMapper.setTargetType(ProductCsv.class);
        return new FlatFileItemReaderBuilder<ProductCsv>()
                .name("productReader")
                .linesToSkip(1)
                .resource(new FileSystemResource(filepath))
                .delimited()
                .names("name", "quantity", "price", "description")
                .fieldSetMapper(beanWrapperFieldSetMapper)
                .build();
    }
}
