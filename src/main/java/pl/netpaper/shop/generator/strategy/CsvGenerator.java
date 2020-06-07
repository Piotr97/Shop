package pl.netpaper.shop.generator.strategy;

import org.springframework.stereotype.Component;
import pl.netpaper.shop.generator.model.FileType;

@Component
public class CsvGenerator extends GeneratorStrategy {

    public CsvGenerator() {
        super(FileType.CSV);
    }

    @Override
    public byte[] generateFile() {
        System.out.println("CSV");
        return new byte[0];
    }
}
