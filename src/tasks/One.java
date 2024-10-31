package tasks;

import java.nio.file.Path;

public class One {
    private Path path;

    public One(String path){
        this.path = Path.of(path);
    }

    public void run(){
        System.out.println("Hello world");
    }
}
