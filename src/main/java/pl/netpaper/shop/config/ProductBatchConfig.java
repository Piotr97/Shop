package pl.netpaper.shop.config;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import pl.netpaper.shop.model.csv.ProductCsv;
import pl.netpaper.shop.model.dao.Product;
import pl.netpaper.shop.procesor.imports.ProductProcessor;
import pl.netpaper.shop.procesor.imports.ProductReader;
import pl.netpaper.shop.procesor.imports.ProductWriter;

@Configuration
@RequiredArgsConstructor

public class ProductBatchConfig {

    private final ProductProcessor productProcessor;
    private final ProductReader productReader;
    private final ProductWriter productWriter;
    private final JobBuilderFactory jobBuilderFactory; //tworzy joba w ktorm mam stepa
    private final StepBuilderFactory stepBuilderFactory; // tworzy klase step // step - klasa w ktorej podaje readera,processora i writera

    private TaskExecutor taskExecutor() {
        SimpleAsyncTaskExecutor simpleAsyncTaskExecutor = new SimpleAsyncTaskExecutor("thread"); // odpowiada za przetwarzanie pliku w wilu watkach
        simpleAsyncTaskExecutor.setConcurrencyLimit(5);
        return simpleAsyncTaskExecutor;
    }

    private Step stepProductCsv(String fileName){
        return stepBuilderFactory.get("csvProcess")
                .<ProductCsv, Product>chunk(50)
                .reader(productReader.itemReader(fileName))
                .processor(productProcessor)
                .writer(productWriter)
                .taskExecutor(taskExecutor())
                .build();
    }

    public Job csvProductJob(String fileName){
        return jobBuilderFactory.get("csvJob")
                .incrementer(new RunIdIncrementer())
                .flow(stepProductCsv(fileName))
                .end()
                .build();
    }
}
