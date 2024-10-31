package tasks;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class One {
    public void run(String path){
        try {
            List<Path> allFilePaths = Files.walk(Paths.get(path)).filter(Files::isRegularFile).collect(Collectors.toList());
            sortFiles(allFilePaths);
        }catch(IOException ex){
            ex.printStackTrace();
        }
    }

    private void sortFiles(List<Path> listPaths){
        Directory.list = listPaths.stream().sorted().collect(Collectors.toList());
        List<Directory> arr = new ArrayList<>();
        listPaths.stream().forEach(pathVal -> {
            Directory d = new Directory(pathVal);
            arr.add(d);
        });
        arr.forEach(e -> e.setMass());
        List<Directory> d = arr.stream().filter(e -> e.mass > 0).sorted(Directory::getMass).collect(Collectors.toList());
        d = d.stream().sorted(Collections.reverseOrder(Directory::getMass)).collect(Collectors.toList());
        d.reversed().stream().forEach(w-> System.out.println(w.toString()));
    }
}

class Directory{
    public static List<Path> list;
    public Path value;
    public List<String> children;
    public Integer mass;

    public Directory(Path p){
        value = p;
        children = new ArrayList<>();
        mass = Integer.MAX_VALUE;
        adddictionSetList();
    }
    public int getMass(Directory directory){
        return  directory.mass;
    }

    public void setMass(){
        if(this.children.size() == 0){
            mass = 0;
        }else{
            children.forEach(strPath -> {
                list.forEach(pathEl -> {
                    String s = strPath.split("require ‘")[1].replace("’","");
                    if(pathEl.toString().contains(s)){
                        if(mass > list.indexOf(pathEl) + 1){
                            mass = list.indexOf(pathEl) + 1;
                        }
                    }
                });
            });
        }
    }

    public void adddictionSetList(){
        try {
            Files.readAllLines(value).forEach(str -> {
                if(str.contains("require")){
                    this.children.add(str);
                }
            });
        }catch (IOException ex){
            ex.printStackTrace();
        }
        sortList();
    }

    public void sortList() {
        children = children.stream().sorted().collect(Collectors.toList());
    }

    @Override
    public String toString(){
        return "#########\n" + value + "\n" + children.toString() + "\n" + "Size: " + mass + "\n";
    }
}