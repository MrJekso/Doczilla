package tasks;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class One {

    public void run(String path){
        try {
            List<Path> allFilePaths = Files.walk(Paths.get(path))
                    .filter(Files::isRegularFile)
                    .collect(Collectors.toList());
            sortFiles(allFilePaths);
        }catch(IOException ex){
            ex.printStackTrace();
        }
    }
    private void sortFiles(List<Path> listPaths){
        listPaths = listPaths
                .stream()
                .sorted()
                .collect(Collectors.toList());

        listPaths.forEach(p -> System.out.println(p.getFileName()));
    }
}
