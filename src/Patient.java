import java.time.LocalDate;

public class Patient {
    private String iin;        // Individual Identification Number (12 digits)
    private String fullName;
    private int age;
    private String bloodType;

    // Constructor with parameters
    public Patient(String iin, String fullName, String bloodType) {
        setIin(iin); // validate IIN
        this.fullName = fullName;
        this.bloodType = bloodType;
        this.age = calculateAgeFromIIN();
    }

    // Default constructor
    public Patient() {
        this.iin = "000000000000";
        this.fullName = "Unknown Patient";
        this.bloodType = "Unknown";
        this.age = 0;
    }

    // Getters
    public String getIin() { return iin; }
    public String getFullName() { return fullName; }
    public int getAge() { return age; }
    public String getBloodType() { return bloodType; }

    // Setters
    public void setIin(String iin) {
        if (iin != null && iin.matches("\\d{12}")) {
            this.iin = iin;
            this.age = calculateAgeFromIIN(); // recalculate age
        } else {
            System.out.println("Error: IIN must contain 12 digits!");
        }
    }

    public void setFullName(String fullName) { this.fullName = fullName; }
    public void setBloodType(String bloodType) { this.bloodType = bloodType; }
    public void setAge(int age) {
        if (age >= 0) this.age = age;
        else System.out.println("Error: Age cannot be negative!");
    }

    // Check if minor
    public boolean isMinor() { return age < 18; }

    // Blood type info: compatibility and special notes
    public String getBloodTypeInfo() {
        switch (bloodType.toUpperCase()) {
            case "A+":
                return "A+ can donate to A+, AB+; receives from A+, A-, O+, O-";
            case "A-":
                return "A- can donate to A+, A-, AB+, AB-; receives from A-, O-";
            case "B+":
                return "B+ can donate to B+, AB+; receives from B+, B-, O+, O-";
            case "B-":
                return "B- can donate to B+, B-, AB+, AB-; receives from B-, O-";
            case "AB+":
                return "AB+ is universal recipient; can donate only to AB+";
            case "AB-":
                return "AB- can donate to AB+, AB-; receives from AB-, A-, B-, O-";
            case "O+":
                return "O+ can donate to A+, B+, AB+, O+; receives from O+, O-";
            case "O-":
                return "O- is universal donor; receives only from O-";
            default:
                return "Unknown blood type";
        }
    }

    // Calculate age from IIN (YYMMDDxxxxxx)
    public int calculateAgeFromIIN() {
        if (iin == null || iin.length() < 6) return 0;

        int year = Integer.parseInt(iin.substring(0, 2));
        int month = Integer.parseInt(iin.substring(2, 4));
        int day = Integer.parseInt(iin.substring(4, 6));

        // Determine century
        if (year <= LocalDate.now().getYear() % 100) {
            year += 2000;
        } else {
            year += 1900;
        }

        LocalDate birthDate = LocalDate.of(year, month, day);
        LocalDate today = LocalDate.now();

        int calculatedAge = today.getYear() - birthDate.getYear();
        if (today.getDayOfYear() < birthDate.getDayOfYear()) {
            calculatedAge--; // birthday not yet occurred this year
        }

        this.age = calculatedAge;
        return calculatedAge;
    }

    @Override
    public String toString() {
        return "Patient{IIN='" + iin + "', Name='" + fullName + "', Age=" + age +
                ", BloodType='" + bloodType + "'}";
    }
}
