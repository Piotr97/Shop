package pl.netpaper.shop.generator.strategy;

import org.springframework.stereotype.Component;
import pl.netpaper.shop.generator.model.FileType;

@Component
public class PdfGenerator extends GeneratorStrategy {

    public PdfGenerator() {
        super(FileType.PDF);
    }

    @Override
    public byte[] generateFile() {
        System.out.println("PDF");
        return new byte[0];
    }
}
