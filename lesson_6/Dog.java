package geekbrains.java.dubovik;

public class Dog extends Animals {
    private String name;
    private String breed;
    static int count;

    public Dog(String name, String breed) {
        super(name);
        this.breed = breed;
        count++;
    }

    public String getName() {
        return name;
    }

    public String getBreed() {
        return breed;
    }

    @Override
    public void run(int distance) {
        if (distance > 500) {
            System.out.println("Собака не может пробежать более 500 метров");
        }
        else super.run(distance);
    }

    @Override
    public void swim(int distance) {
        if (distance > 10) {
            System.out.println("Собака не может проплыть более 10 метров");
        }
        else super.swim(distance);
    }
}
