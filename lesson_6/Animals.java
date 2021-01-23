package geekbrains.java.dubovik;

public class Animals {
    private String name;
    static int count;

    public Animals(String name) {
        this.name = name;
        count++;
    }

    public String getName() {
        return name;
    }

    public void animalInfo() {
        System.out.printf("Животное с кличкой: %s\n", name);
    }
    
    public void run(int distance) {
        System.out.printf("Животное пробежало %d метров\n", distance);
    }

    public void swim(int distance) {
        System.out.printf("Животное проплыло %d метров\n", distance);
    }
}
