package pl.netpaper.shop.generator.strategy;

import org.springframework.stereotype.Component;
import pl.netpaper.shop.generator.model.FileType;

@Component
public class JsonGenerator extends GeneratorStrategy {

    public JsonGenerator() {
        super(FileType.JSON);
    }

    @Override
    public byte[] generateFile() {
        System.out.println("JSON");
        return new byte[0];
    }
}
