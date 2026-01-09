import java.time.LocalDate;
import java.time.Period;

public class Doctor extends Person {
    // Doctor-specific fields
    private String specialization;
    private int experienceYears;
    private double totalBonus; // total bonus for appointments

    // Constructor with parameters
    public Doctor(String iin, String fullName, String specialization, int experienceYears) {
        super(iin, fullName, 0); // call parent constructor (super)
        this.specialization = specialization;
        setExperienceYears(experienceYears);
        this.totalBonus = 0; // initialize bonus
    }

    // Default constructor
    public Doctor() {
        super("000000000000", "Unknown Doctor", 0);
        this.specialization = "General";
        this.experienceYears = 0;
        this.totalBonus = 0;
    }

    // Getters
    public String getSpecialization() { return specialization; }
    public int getExperienceYears() { return experienceYears; }
    public double getTotalBonus() { return totalBonus; }

    // Setters
    public void setSpecialization(String specialization) { this.specialization = specialization; }
    public void setExperienceYears(int experienceYears) {
        if (experienceYears >= 0) this.experienceYears = experienceYears;
        else System.out.println("Error: Experience cannot be negative!");
    }

    // Calculate age based on IIN (inherited field from Person)
    @Override
    public int getAge() {
        int year = Integer.parseInt(iin.substring(0, 2));
        int month = Integer.parseInt(iin.substring(2, 4));
        int day = Integer.parseInt(iin.substring(4, 6));

        if (year <= LocalDate.now().getYear() % 100) year += 2000;
        else year += 1900;

        LocalDate birthDate = LocalDate.of(year, month, day);
        return Period.between(birthDate, LocalDate.now()).getYears();
    }

    // Methods specific to Doctor
    public boolean isExperienced() { return experienceYears >= 5; }
    public boolean canPerformSurgery() {
        return specialization.equalsIgnoreCase("surgeon") && experienceYears >= 3;
    }
    public boolean isEligibleForRetirement() {
        return getAge() >= 65;
    }
    public int yearsUntilRetirement() {
        int retirementAge = 65;
        int remaining = retirementAge - getAge();
        return remaining > 0 ? remaining : 0;
    }

    // Bonus methods
    public void addAppointmentBonus(double amount) {
        if (amount > 0) totalBonus += amount; // add bonus to doctor
    }

    public String getBonusInfo() {
        return fullName + " total bonus: $" + totalBonus;
    }

    // Overridden method from Person (polymorphism demo)
    @Override
    public void work() {
        System.out.println(fullName + " is treating patients as a " + specialization + ".");
    }

    // toString method
    @Override
    public String toString() {
        return "Doctor{IIN='" + iin + "', Name='" + fullName +
                "', Specialization='" + specialization +
                "', ExperienceYears=" + experienceYears +
                ", Age=" + getAge() +
                ", TotalBonus=$" + totalBonus + "}";
    }
}