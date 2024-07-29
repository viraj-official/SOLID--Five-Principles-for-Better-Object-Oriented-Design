public class main
{
    public static void main(String[] args)
    {
        // [1] Single Responsibility Principle
        System.out.println("\n[1] Single Responsibility Principle");

        AuthenticationService authService = new AuthenticationService();
        UserManagementService userService = new UserManagementService();

        // Example usage
        boolean isAuthenticated = authService.authenticateUser("user1", "password1");
        if (isAuthenticated) {
            System.out.println("User authenticated successfully.");
            userService.addUser("user1", "password1");
            userService.deleteUser("user1");
        } else {
            System.out.println("Authentication failed.");
        }

        // [2] Open/Closed Principle
        System.out.println("\n[2] Open/Closed Principle");
        Circle circle = new Circle(5);
        Rectangle rectangle = new Rectangle(4, 6);

        System.out.println("Circle Area: " + circle.calculateArea());
        System.out.println("Rectangle Area: " + rectangle.calculateArea());

        // [3] Liskov Substitution Principle
        System.out.println("\n[3] Liskov Substitution Principle");
        Bird sparrow = new Sparrow();
        Bird penguin = new Penguin();

        sparrow.makeSound();
        penguin.makeSound();

        if (sparrow instanceof Flyable) {
            ((Flyable) sparrow).fly();
        }

        if (penguin instanceof Flyable) {
            ((Flyable) penguin).fly();
        } else {
            System.out.println("Penguins can't fly");
        }

        // [4] Interface Segregation Principle
        System.out.println("\n[4] Interface Segregation Principle");
        Workable humanWorker = new HumanWorker();
        Workable robotWorker = new RobotWorker();
        Eatable humanEater = (Eatable) humanWorker;

        humanWorker.work();
        robotWorker.work();
        humanEater.eat();

        // HumanWorker h1 = new HumanWorker();
        // h1.work();
        // h1.eat();

        // [5] Dependency Inversion Principle
        System.out.println("\n[5] Dependency Inversion Principle");

        Switchable lightBulb = new LightBulb();
        LightSwitch lightSwitch = new LightSwitch(lightBulb);

        lightSwitch.switchOn();
        lightSwitch.switchOff();
    }
}


