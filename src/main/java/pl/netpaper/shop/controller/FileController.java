package pl.netpaper.shop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.netpaper.shop.generator.GeneratorFactory;
import pl.netpaper.shop.generator.model.FileType;

@RestController
@RequestMapping("/api/file")
@RequiredArgsConstructor
public class FileController {

    private final GeneratorFactory generatorFactory;

    @GetMapping
    public void testFactory(@RequestParam FileType fileType) {
        generatorFactory.getByKey(fileType).generateFile();
    }
}
