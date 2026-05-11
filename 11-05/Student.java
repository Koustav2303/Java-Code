public class Student {
    String name;
    int id;

    Student() { this.name = "Unknown"; }
    Student(String name, int id) { 
        this.name = name; 
        this.id = id; 
    }

    void display() { System.out.println(name + " - " + id); }

    public static void main(String[] args) {
        new Student().display();
        new Student("Koustav", 101).display();
    }
}