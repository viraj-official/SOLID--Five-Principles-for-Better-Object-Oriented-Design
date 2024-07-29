### SOLID: Five Principles for Better Object-Oriented Design

Creating reliable, easy-to-maintain software is a top priority for developers. SOLID principles provide a clear path to achieving this. SOLID stands for five key principles that improve object-oriented design:

1.  **Single Responsibility Principle** — Each class should have one job.
2.  **Open/Closed Principle** — Classes should be open for extension but closed for modification.
3.  **Liskov Substitution Principle** — Objects of a superclass should be replaceable with objects of a subclass.
4.  **Interface Segregation Principle** — No client should be forced to depend on methods it does not use.
5.  **Dependency Inversion Principle** — Depend on abstractions, not on concrete classes.

Following these principles makes your code easier to understand, maintain, and extend.

#### **[1] Single Responsibility Principle**

The Single Responsibility Principle (SRP) states that a class should have only one reason to change, meaning it should have only one job or responsibility. This principle helps in maintaining a clean and manageable codebase.

**Wrong Way:**

Imagine a class that handles both user authentication and user data management. This class does too much, violating SRP.

public class UserService {  
    // User authentication  
  
    public boolean authenticateUser(String username, String password) {  
        // Authentication logic  
        return true; // simplified for example  
    }  
  
    // User data management  
    public void addUser(String username, String password) {  
        // Add user logic  
    }  
  
    public void deleteUser(String username) {  
        // Delete user logic  
    }  
}

In this example, the `UserService` class is responsible for both authenticating users and managing user data. This makes the class difficult to maintain and extend because changes to authentication logic might impact user management and vice versa.

**Right Way:**

We can refactor the code by separating the responsibilities into two distinct classes: `AuthenticationService` and `UserManagementService`.

public class AuthenticationService {  
    public boolean authenticateUser(String username, String password)  
    {  
        // Authentication logic  
        return true; // simplified for example  
    }  
}  
  
public class UserManagementService   
{  
    public void addUser(String username, String password)  
    {  
        System.out.println("User " + username + " added.");  
    }  
  
    public void deleteUser(String username)   
    {  
        // Delete user logic  
        System.out.println("User " + username + " deleted.");  
    }  
}

In this refactored example, each class has a single responsibility. The `AuthenticationService` class is solely responsible for user authentication, while the `UserManagementService` class handles user data management. This separation makes the code more modular, easier to understand, and simpler to maintain.

#### [2] Open/Closed Principle

The Open/Closed Principle (OCP) states that software entities (classes, modules, functions, etc.) should be open for extension but closed for modification. This means you should be able to add new functionality to a class without changing its existing code.

**Wrong Way:**

Suppose we have a class that calculates the area of different shapes. Each time we add a new shape, we modify the existing class.

public class AreaCalculator {  
    public double calculateArea(Object shape) {  
        double area = 0;  
        if (shape instanceof Circle) {  
            Circle circle = (Circle) shape;  
            area = Math.PI * circle.getRadius() * circle.getRadius();  
        } else if (shape instanceof Rectangle) {  
            Rectangle rectangle = (Rectangle) shape;  
            area = rectangle.getLength() * rectangle.getWidth();  
        }  
        // Add more shape types here in the future  
        return area;  
    }  
}  
  
class Circle {  
    private double radius;  
    // Constructor, getters, and setters  
}  
  
class Rectangle {  
    private double length;  
    private double width;  
    // Constructor, getters, and setters  
}

In this example, every time a new shape is added, the `AreaCalculator` class must be modified, violating the Open/Closed Principle.

**Right Way:**

We can refactor the code to adhere to the Open/Closed Principle by using polymorphism. Each shape will have its own way of calculating its area.

public abstract class Shape {  
    public abstract double calculateArea();  
}  
  
public class Circle extends Shape {  
    private double radius;  
  
    public Circle(double radius) {  
        this.radius = radius;  
    }  
  
    @Override  
    public double calculateArea() {  
        return Math.PI * radius * radius;  
    }  
}  
  
public class Rectangle extends Shape {  
    private double length;  
    private double width;  
  
    public Rectangle(double length, double width) {  
        this.length = length;  
        this.width = width;  
    }  
  
    @Override  
    public double calculateArea() {  
        return length * width;  
    }  
}

In this refactored example, the `AreaCalculator` class remains unchanged when new shapes are added. Each shape class (`Circle`, `Rectangle`, etc.) is responsible for its own area calculation, making it easy to extend the system by simply adding new shape classes without modifying existing code. This adheres to the Open/Closed Principle, ensuring the code is both extensible and maintainable.

#### [3] Liskov Substitution Principle

The Liskov Substitution Principle (LSP) states that objects of a superclass should be replaceable with objects of a subclass without affecting the functionality of the program. This principle ensures that a subclass can stand in for its superclass without altering the desired behavior.

**Wrong Way:**

Consider a base class `Bird` and a derived class `Penguin`. If the `Bird` class has a method `fly()`, it can create issues for the `Penguin` class since penguins cannot fly.

public class Bird {  
    public void fly() {  
        System.out.println("Flying");  
    }  
}  
  
public class Penguin extends Bird {  
    @Override  
    public void fly() {  
        throw new UnsupportedOperationException("Penguins can't fly");  
    }  
}  
  
public class Main {  
    public static void main(String[] args) {  
        Bird bird = new Penguin();  
        bird.fly(); // This will throw an exception  
    }  
}

In this example, substituting `Penguin` for `Bird` violates the Liskov Substitution Principle because `Penguin` cannot fulfill the `fly()` behavior expected of a `Bird`.

**Right Way:**

To adhere to the Liskov Substitution Principle, we can refactor the code by separating the flying behavior into an interface and having only the birds that can fly implement this interface.

// Flyable.java  
public interface Flyable {  
    void fly();  
}  
  
// Bird.java  
public abstract class Bird {  
    public abstract void makeSound();  
}  
  
// Sparrow.java  
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
  
// Penguin.java  
public class Penguin extends Bird {  
    @Override  
    public void makeSound() {  
        System.out.println("Quack quack");  
    }  
}  
  
// Main.java  
public class Main {  
    public static void main(String[] args) {  
        Bird sparrow = new Sparrow();  
        Bird penguin = new Penguin();  
  
        sparrow.makeSound(); // Outputs: Chirp chirp  
        penguin.makeSound(); // Outputs: Quack quack  
  
        if (sparrow instanceof Flyable) {  
            ((Flyable) sparrow).fly(); // Outputs: Flying  
        }  
  
        if (penguin instanceof Flyable) {  
            ((Flyable) penguin).fly();  
        } else {  
            System.out.println("Penguins can't fly"); // Outputs: Penguins can't fly  
        }  
    }  
}

In this refactored example, we introduce a `Flyable` interface for the flying behavior. The `Sparrow` class implements the `Flyable` interface, while the `Penguin` class does not. This way, we ensure that substituting one bird type for another does not violate the expected behaviors, adhering to the Liskov Substitution Principle.

#### [4] Interface Segregation Principle

The Interface Segregation Principle (ISP) states that a class should not be forced to implement interfaces it does not use. This principle emphasizes that clients should not be forced to depend on methods they do not use, promoting the creation of small, specific interfaces rather than large, general ones.

**Wrong Way:**

Suppose we have a general interface `Worker` with multiple methods, including ones that not all implementing classes need.

public interface Worker {  
    void work();  
    void eat();  
}  
  
public class HumanWorker implements Worker {  
    @Override  
    public void work() {  
        System.out.println("Working");  
    }  
  
    @Override  
    public void eat() {  
        System.out.println("Eating");  
    }  
}  
  
public class RobotWorker implements Worker {  
    @Override  
    public void work() {  
        System.out.println("Working");  
    }  
  
    @Override  
    public void eat() {  
        throw new UnsupportedOperationException("Robots don't eat");  
    }  
}

In this example, the `RobotWorker` class is forced to implement the `eat` method, which it does not use. This violates the Interface Segregation Principle because `RobotWorker` should not be required to implement functionality that does not apply to it.

**Right Way:**

We can refactor the code by splitting the `Worker` interface into more specific interfaces.

public interface Workable {  
    void work();  
}  
  
public interface Eatable {  
    void eat();  
}  
  
public class HumanWorker implements Workable, Eatable {  
    @Override  
    public void work() {  
        System.out.println("Working");  
    }  
    @Override  
    public void eat() {  
        System.out.println("Eating");  
    }  
}  
  
public class RobotWorker implements Workable {  
    @Override  
    public void work() {  
        System.out.println("Working");  
    }  
}

In this refactored example, we introduce specific interfaces `Workable` and `Eatable`. The `HumanWorker` class implements both interfaces, while the `RobotWorker` class only implements the `Workable` interface. This way, we ensure that substituting one worker type for another does not force the implementation of unnecessary methods, adhering to the Interface Segregation Principle.

#### [5] Dependency Inversion Principle

The Dependency Inversion Principle (DIP) states that high-level modules should not depend on low-level modules. Both should depend on abstractions (e.g., interfaces). Additionally, abstractions should not depend on details. Details should depend on abstractions. This principle helps to decouple software components, making the system more modular, flexible, and easier to maintain.

**Wrong Way:**

Suppose we have a `LightSwitch` class that directly depends on a `LightBulb` class.

public class LightBulb {  
    public void turnOn() {  
        System.out.println("LightBulb turned on");  
    }  
    public void turnOff() {  
        System.out.println("LightBulb turned off");  
    }  
}  
  
public class LightSwitch {  
    private LightBulb lightBulb;  
    public LightSwitch() {  
        this.lightBulb = new LightBulb();  
    }  
    public void switchOn() {  
        lightBulb.turnOn();  
    }  
    public void switchOff() {  
        lightBulb.turnOff();  
    }  
}

In this example, the `LightSwitch` class depends directly on the `LightBulb` class, making it difficult to change the implementation of the light without modifying the `LightSwitch` class. This violates the Dependency Inversion Principle.

**Right Way:**

We can refactor the code by introducing an abstraction (interface) that both the high-level and low-level modules depend on.

// Switchable.java  
public interface Switchable {  
    void turnOn();  
    void turnOff();  
}  
  
// LightBulb.java  
public class LightBulb implements Switchable {  
    @Override  
    public void turnOn() {  
        System.out.println("LightBulb turned on");  
    }  
    @Override  
    public void turnOff() {  
        System.out.println("LightBulb turned off");  
    }  
}  
  
// LightSwitch.java  
public class LightSwitch {  
    private Switchable device;  
    public LightSwitch(Switchable device) {  
        this.device = device;  
    }  
    public void switchOn() {  
        device.turnOn();  
    }  
    public void switchOff() {  
        device.turnOff();  
    }  
}

In this refactored example, we introduce the `Switchable` interface, which abstracts the behavior of turning a device on and off. The `LightBulb` class implements this interface, and the `LightSwitch` class depends on the `Switchable` interface rather than the concrete `LightBulb` class. This way, we can easily swap out the `LightBulb` with any other device that implements the `Switchable` interface, adhering to the Dependency Inversion Principle.

### Conclusion

The SOLID principles are essential guidelines for designing high-quality, maintainable, and scalable object-oriented software. By adhering to these principles, developers can create systems that are easier to understand, extend, and maintain. The Single Responsibility Principle (SRP) ensures that each class has a single responsibility, promoting clarity and reducing complexity. The Open/Closed Principle (OCP) allows for extension without modification, enhancing flexibility. The Liskov Substitution Principle (LSP) ensures that subclasses can replace their base classes without affecting the system’s behavior. The Interface Segregation Principle (ISP) advocates for small, specific interfaces, preventing the implementation of unnecessary methods. Finally, the Dependency Inversion Principle (DIP) decouples high-level and low-level modules through abstractions, improving modularity and flexibility. Embracing the SOLID principles leads to robust, adaptable, and maintainable software, ultimately contributing to the success of software projects.

### Reference

GitHub repo : [https://github.com/viraj-official/SOLID--Five-Principles-for-Better-Object-Oriented-Design](https://github.com/viraj-official/SOLID--Five-Principles-for-Better-Object-Oriented-Design)

### Contact

For any questions or suggestions, feel free to reach out:

-   Email: [virajofficialinfo@gmail.com](http://virajofficialinfo@gmail.com/)
-   GitHub: [github.com/viraj-official](https://github.com/viraj-official)
-   Linkedin: [Linkedin/virajofficial](https://www.linkedin.com/in/virajofficial/)
