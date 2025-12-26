import java.time.LocalDate;
import java.time.Period;

public class Doctor {
    private String iin;
    private String name;
    private String specialization;
    private int experienceYears;
    private double totalBonus; // total bonus for appointments

    // Constructor with parameters
    public Doctor(String iin, String name, String specialization, int experienceYears) {
        setIin(iin);
        this.name = name;
        this.specialization = specialization;
        this.experienceYears = experienceYears;
        this.totalBonus = 0; // initialize bonus
    }

    // Default constructor
    public Doctor() {
        this.iin = "000000000000";
        this.name = "Unknown Doctor";
        this.specialization = "General";
        this.experienceYears = 0;
        this.totalBonus = 0;
    }

    // Getters
    public String getIin() { return iin; }
    public String getName() { return name; }
    public String getSpecialization() { return specialization; }
    public int getExperienceYears() { return experienceYears; }
    public double getTotalBonus() { return totalBonus; }

    // Calculate age based on IIN
    public int getAge() {
        int year = Integer.parseInt(iin.substring(0, 2));
        int month = Integer.parseInt(iin.substring(2, 4));
        int day = Integer.parseInt(iin.substring(4, 6));

        if (year <= LocalDate.now().getYear() % 100) year += 2000;
        else year += 1900;

        LocalDate birthDate = LocalDate.of(year, month, day);
        return Period.between(birthDate, LocalDate.now()).getYears();
    }

    // Setters
    public void setIin(String iin) {
        if (iin.matches("\\d{12}")) this.iin = iin;
        else System.out.println("Error: IIN must contain exactly 12 digits!");
    }
    public void setName(String name) { this.name = name; }
    public void setSpecialization(String specialization) { this.specialization = specialization; }
    public void setExperienceYears(int experienceYears) {
        if (experienceYears >= 0) this.experienceYears = experienceYears;
        else System.out.println("Experience cannot be negative!");
    }

    // Methods
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
        if (amount > 0) totalBonus += amount; // начисление премии
    }

    public String getBonusInfo() {
        return name + " total bonus: $" + totalBonus;
    }

    @Override
    public String toString() {
        return "Doctor{iin='" + iin + "', name='" + name +
                "', specialization='" + specialization +
                "', experienceYears=" + experienceYears +
                ", age=" + getAge() +
                ", totalBonus=$" + totalBonus + "}";
    }
}
