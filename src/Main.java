import tasks.One;

public class Main {
    public static void main(String[] args) {
        if(args[0].equals("one") && args[1] != null){
            One one = new One();
            one.run(args[1]);
        }
    }
}