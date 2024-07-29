public class Sparrow extends Bird implements Flyable {
    @Override
    public void fly() {
        System.out.println("Flying");
    }

    @Override
    public void makeSound() {
        System.out.println("Chirp chirp");
    }
}
