package pl.netpaper.shop.generator.strategy;

import lombok.Getter;
import pl.netpaper.shop.generator.model.FileType;

public abstract class GeneratorStrategy {
    @Getter
    private FileType fileType;

    public GeneratorStrategy(FileType fileType) {
        this.fileType = fileType;
    }

    public abstract byte[] generateFile();
}
