package pl.netpaper.shop.generator;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.netpaper.shop.generator.model.FileType;
import pl.netpaper.shop.generator.strategy.GeneratorStrategy;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class GeneratorFactory {

    private final List<GeneratorStrategy> generatorStrategies;

    private Map<FileType, GeneratorStrategy> strategyMap;

    @PostConstruct //adnotacja dla funkcji ,która uruchamia się po konstruktorze
    public void init() {
        strategyMap = generatorStrategies.stream()
                .collect(Collectors.toMap(GeneratorStrategy::getFileType, generatorStrategy -> generatorStrategy));
    }

    public GeneratorStrategy getByKey(FileType fileType) {
        return strategyMap.get(fileType);
    }


}
