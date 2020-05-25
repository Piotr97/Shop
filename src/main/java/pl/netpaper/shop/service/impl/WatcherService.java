package pl.netpaper.shop.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.stereotype.Service;
import pl.netpaper.shop.config.ProductBatchConfig;

import java.io.IOException;
import java.nio.file.*;

@Service
@RequiredArgsConstructor
public class WatcherService implements Runnable {

    private final JobLauncher jobLauncher; //klasa do uruchamiania batchowego przetwarzania
    private final ProductBatchConfig productBatchConfig;

    @Override
    public void run() {
        try {
            WatchService watchService = FileSystems.getDefault().newWatchService();
            Path path = Paths.get("/Users/piotrszewczyk/Desktop/JavaKurs");
            path.register(watchService, StandardWatchEventKinds.ENTRY_CREATE);  //sprawdza czy plik/folder zmienil nazwe
            WatchKey watchKey;
            while ((watchKey = watchService.take()) != null) {
                watchKey.pollEvents().forEach(event -> {
                    Path pathFile = Paths.get(path.toString(), event.context().toString()); //event.context().toString() - zwraca nazwe pliku stworzonych w folderze
                    if (Files.isRegularFile(pathFile)){
                        JobParameters jobParameters = new JobParametersBuilder().addString("JobID", String.valueOf(System.currentTimeMillis())).toJobParameters(); // tworzy parametry do joba batch
                        try {
                            jobLauncher.run(productBatchConfig.csvProductJob(pathFile.toString()),jobParameters);
                        } catch (JobExecutionAlreadyRunningException | JobRestartException | JobInstanceAlreadyCompleteException | JobParametersInvalidException e) {
                            e.printStackTrace();
                        }
                    }
                });
                watchKey.reset();
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
