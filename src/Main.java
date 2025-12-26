import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Hospital Management System ===\n");

        // 1. Create patients
        Patient p1 = new Patient("060115431265", "Aruzhan T.", "A+"); // adult
        Patient p2 = new Patient("910715864521", "Nursultan K.", "O-"); // adult
        Patient p3 = new Patient("150715431265", "Dana S.", "B+"); // minor

        // Patient created via setters
        Patient p4 = new Patient(); // default
        p4.setIin("051215431265");
        p4.setFullName("Alik N.");
        p4.setBloodType("AB+");
        p4.setAge(p4.calculateAgeFromIIN()); // calculate age from IIN

        // 2. Create doctors
        Doctor d1 = new Doctor("850403238546", "Dr. Alimov", "Surgeon", 7);
        Doctor d2 = new Doctor("930117551791", "Dr. Sadykova", "Therapist", 2);

        // 3. Display patients
        System.out.println(" --- PATIENTS --- ");
        System.out.println(p1);
        System.out.println(p2);
        System.out.println(p3);
        System.out.println(p4);
        System.out.println();

        // 4. Display doctors
        System.out.println(" --- DOCTORS --- ");
        System.out.println(d1);
        System.out.println(d2);
        System.out.println();

        // 5. Create appointments (bonus $50 per appointment)
        System.out.println(" --- CREATING APPOINTMENTS ---");
        Appointment a1 = new Appointment(4001, p1, d1, LocalDateTime.of(2026, 1, 5, 10, 30), "Scheduled", 50);
        Appointment a2 = new Appointment(4002, p2, d2, LocalDateTime.now().plusDays(2), "Scheduled", 50);
        Appointment a3 = new Appointment(4003, p3, d1, LocalDateTime.now().plusDays(1), "Scheduled", 50); // minor -> Cancelled
        Appointment a4 = new Appointment(4004, p4, d2, LocalDateTime.now().plusDays(1), "Scheduled", 50); // via setters

        System.out.println();

        // 6. Display appointments (minor patients handled)
        System.out.println(" --- APPOINTMENTS --- ");
        printAppointment(a1);
        printAppointment(a2);
        printAppointment(a3); // minor message appears here
        printAppointment(a4);
        System.out.println();

        // 7. Reschedule example: move a4 by 3 days later
        System.out.println(" --- RESCHEDULE APPOINTMENT 4004 --- ");
        System.out.println("Before reschedule: " + a4);
        a4.reschedule(a4.getDateTime().plusDays(3));
        System.out.println("After reschedule: " + a4);
        System.out.println();

        // 8. Complete appointment a1 -> bonus added to doctor
        System.out.println(" --- COMPLETE APPOINTMENT 4001 --- ");
        a1.complete();
        System.out.println(a1);
        System.out.println("Doctor bonus after completion: " + d1.getTotalBonus());
        System.out.println();

        // 9. Cancel appointment a2
        System.out.println(" --- CANCEL APPOINTMENT 4002 --- ");
        a2.cancel();
        System.out.println(a2);
        System.out.println();

        // 10. Attempt to reschedule cancelled appointment a2
        System.out.println(" --- ATTEMPT RESCHEDULE CANCELLED APPOINTMENT 4002 --- ");
        a2.reschedule(a2.getDateTime().plusDays(5)); // will not change
        System.out.println(a2);
        System.out.println();

        // 11. Final summary
        System.out.println(" --- FINAL STATE --- ");
        System.out.println("Patients:");
        System.out.println(p1 + " | Blood info: " + p1.getBloodTypeInfo());
        System.out.println(p2 + " | Blood info: " + p2.getBloodTypeInfo());
        System.out.println(p3 + " | Blood info: " + p3.getBloodTypeInfo());
        System.out.println(p4 + " | Blood info: " + p4.getBloodTypeInfo());
        System.out.println();

        System.out.println("Doctors:");
        System.out.println(d1 + " | Total bonus: $" + d1.getTotalBonus());
        System.out.println(d2 + " | Total bonus: $" + d2.getTotalBonus());
        System.out.println();

        System.out.println("Appointments:");
        printAppointment(a1);
        printAppointment(a2);
        printAppointment(a3);
        printAppointment(a4);

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
