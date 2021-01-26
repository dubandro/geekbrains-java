package geekbrains.java.dubovik;

public class Cat {
    private String name;
    private int appetite;
    private boolean fullness = false;

    public Cat(String name, int appetite) {
        this.name = name;
        this.appetite = appetite;
    }

    public void eat(Plate p) {
        String state;
        if (p.decreaseFood(appetite)) {
            state = " наелся досыта ;)";
            fullness = true;
        }
        else state = " остался голодным :(";
        System.out.println("Кот " + name + state);
    }

    public boolean isFullness() {
        return fullness;
    }
}
