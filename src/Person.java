public class Person {
    // Protected fields: accessible in child classes but hidden from outside
    protected String iin;
    protected String fullName;
    protected int age;

    // Constructor with parameters
    public Person(String iin, String fullName, int age) {
        setIin(iin);          // validation inside setter
        setFullName(fullName);
        setAge(age);
    }

    // Default constructor
    public Person() {
        this.iin = "000000000000";     // default IIN
        this.fullName = "Unknown Person"; // default name
        this.age = 0;                  // default age
    }

    // Getters: allow controlled access to private/protected fields
    public String getIin() { return iin; }
    public String getFullName() { return fullName; }
    public int getAge() { return age; }

    // Setters with validation logic
    public void setIin(String iin) {
        // IIN must be exactly 12 digits
        if (iin != null && iin.matches("\\d{12}")) {
            this.iin = iin;
        } else {
            System.out.println("Error: IIN must contain exactly 12 digits!");
            this.iin = "000000000000"; // fallback value
        }
    }

    public void setFullName(String fullName) {
        // Name cannot be empty
        if (fullName != null && !fullName.isEmpty()) {
            this.fullName = fullName;
        } else {
            System.out.println("Error: Name cannot be empty!");
            this.fullName = "Unknown Person";
        }
    }

    public void setAge(int age) {
        // Age must be non-negative
        if (age >= 0) {
            this.age = age;
        } else {
            System.out.println("Error: Age cannot be negative!");
            this.age = 0;
        }
    }

    // Extra method for demonstration
    public void introduce() {
        System.out.println("Hello, my name is " + fullName + " and I am " + age + " years old.");
    }

    // Base method for polymorphism: will be overridden in child classes
    public void work() {
        System.out.println(fullName + " is a person with no specific role.");
    }

    // toString method: returns object info in readable format
    @Override
    public String toString() {
        return "Person{IIN='" + iin + "', Name='" + fullName + "', Age=" + age + "}";
    }
}