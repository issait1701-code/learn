import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Person> people = new ArrayList<>();
        ArrayList<Appointment> appointments = new ArrayList<>();

        // Preloaded demo data
        Patient p1 = new Patient("060115431265", "Aruzhan T.", "A+"); // adult
        Patient p2 = new Patient("910715864521", "Nursultan K.", "O-"); // adult
        Patient p3 = new Patient("150715431265", "Dana S.", "B+"); // minor
        Patient p4 = new Patient(); // default + setters
        p4.setIin("051215431265");
        p4.setFullName("Alik N.");
        p4.setBloodType("AB+");
        p4.setAge(p4.calculateAgeFromIIN());

        Doctor d1 = new Doctor("850403238546", "Dr. Alimov", "Surgeon", 7);
        Doctor d2 = new Doctor("930117551791", "Dr. Sadykova", "Therapist", 2);

        people.add(p1);
        people.add(p2);
        people.add(p3);
        people.add(p4);
        people.add(d1);
        people.add(d2);

        int choice;
        do {
            System.out.println("\n=== MENU ===");
            System.out.println("1. View All People");
            System.out.println("2. Demonstrate Polymorphism");
            System.out.println("3. Create Appointment");
            System.out.println("4. View Appointments");
            System.out.println("5. Test Validation (wrong IIN)");
            System.out.println("0. Exit");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.println(" --- PEOPLE --- ");
                    for (Person person : people) {
                        System.out.println(person);
                    }
                    break;

                case 2:
                    System.out.println(" --- POLYMORPHISM DEMO --- ");
                    for (Person person : people) {
                        person.work(); // overridden in Patient and Doctor
                        person.introduce(); // inherited from Person
                        if (person instanceof Doctor) {
                            Doctor doc = (Doctor) person; // downcasting
                            System.out.println("Doctor specialization: " + doc.getSpecialization());
                        }
                        if (person instanceof Patient) {
                            Patient pat = (Patient) person;
                            System.out.println("Blood info: " + pat.getBloodTypeInfo());
                        }
                        System.out.println();
                    }
                    break;

                case 3:
                    System.out.println(" --- CREATE APPOINTMENT --- ");
                    Appointment a1 = new Appointment(4001, p1, d1,
                            LocalDateTime.now().plusDays(1), "Scheduled", 50);
                    Appointment a2 = new Appointment(4002, p3, d2,
                            LocalDateTime.now().plusDays(2), "Scheduled", 50); // minor -> cancelled
                    appointments.add(a1);
                    appointments.add(a2);
                    System.out.println("Appointments created!");
                    break;

                case 4:
                    System.out.println(" --- APPOINTMENTS --- ");
                    for (Appointment a : appointments) {
                        printAppointment(a);
                    }
                    break;

                case 5:
                    System.out.println(" --- VALIDATION TEST --- ");
                    Patient invalid = new Patient();
                    invalid.setIin("123"); // wrong IIN -> validation error
                    System.out.println(invalid);
                    break;
            }
        } while (choice != 0);

        System.out.println("\n=== Program Complete ===");
    }

    // Method to print appointment with minor check
    public static void printAppointment(Appointment a) {
        if (a.getPatient().isMinor() && a.getStatus().equalsIgnoreCase("Cancelled")) {
            System.out.println("Error: Minor patient cannot book an appointment! -> " + a);
        } else {
            System.out.println(a);
        }
    }
}