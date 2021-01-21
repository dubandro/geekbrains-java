package geekbrains.java.dubovik;

public class Main {

    public static void main(String[] args) {

        Person[] persArray = new Person[5];

        persArray[0] = new Person("Morozov", "CEO", "Morozov@email.ru", "79112223344", 200000, 44);
        persArray[1] = new Person("Egorov", "CFO", "Egorov@email.ru", "79112223345", 175000, 36);
        persArray[2] = new Person("Nikolaev", "CMO", "Nikolaev@email.ru", "79112223346", 150000, 42);
        persArray[3] = new Person("Smirnov", "CIO", "Smirnov@email.ru", "79112223347", 150000, 34);
        persArray[4] = new Person("Nikitin", "COO", "Nikitin@email.ru", "79112223348", 100000, 40);

        for (Person person : persArray) {
            if (person.getAge() >= 40) {
                person.printPerson();
            }
        }
    }
}