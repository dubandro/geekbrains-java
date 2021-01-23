package geekbrains.java.dubovik;

public class Main {

    public static void main(String[] args) {
        Cat catOne = new Cat("Ржавый", "рыжий");
        catOne.animalInfo();
        catOne.run(100);
        catOne.swim(100);
        Cat catTwo = new Cat("Беляш", "белый");
        catTwo.animalInfo();

        Dog dogOne = new Dog("Рич", "ретривер");
        dogOne.animalInfo();
        System.out.println("Порода собаки: " + dogOne.getBreed());
        dogOne.run(200);
        dogOne.swim(20);
        dogOne.swim(10);

        System.out.println("Количество животных: " + Animals.count);
        System.out.println("Количество котов: " + Cat.count);
        System.out.println("Количество собак: " + Dog.count);
    }
}
