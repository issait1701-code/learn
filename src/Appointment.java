import java.time.LocalDateTime;

public class Appointment {
    // Unique ID for the appointment
    private int appointmentId;
    // Patient assigned to this appointment
    private Patient patient;
    // Doctor assigned to this appointment
    private Doctor doctor;
    // Date and time of the appointment
    private LocalDateTime dateTime;
    // Current status: "Scheduled", "Completed", "Cancelled"
    private String status;
    // Bonus amount given to doctor when appointment is completed
    private double bonusPerAppointment;

    // Constructor with parameters
    public Appointment(int appointmentId, Patient patient, Doctor doctor,
                       LocalDateTime dateTime, String status, double bonusPerAppointment) {
        this.appointmentId = appointmentId;
        this.bonusPerAppointment = bonusPerAppointment;

        // Validation: minors cannot book appointments
        if (patient.isMinor()) {
            System.out.println("Error: Minor patients cannot book an appointment!");
            this.patient = patient;
            this.doctor = doctor;
            this.dateTime = dateTime;
            this.status = "Cancelled"; // automatically cancelled
        } else {
            this.patient = patient;
            this.doctor = doctor;
            this.dateTime = dateTime;
            this.status = status;
        }
    }

    // Default constructor
    public Appointment() {
        this.appointmentId = 0;
        this.patient = new Patient();   // default patient
        this.doctor = new Doctor();     // default doctor
        this.dateTime = LocalDateTime.now();
        this.status = "Scheduled";
        this.bonusPerAppointment = 0;
    }

    // Getters: controlled access to private fields
    public int getAppointmentId() { return appointmentId; }
    public Patient getPatient() { return patient; }
    public Doctor getDoctor() { return doctor; }
    public LocalDateTime getDateTime() { return dateTime; }
    public String getStatus() { return status; }
    public double getBonusPerAppointment() { return bonusPerAppointment; }

    // Setters with validation
    public void setAppointmentId(int appointmentId) { this.appointmentId = appointmentId; }

    public void setPatient(Patient patient) {
        // Prevent assigning minors to appointments
        if (patient.isMinor()) {
            System.out.println("Error: Minor patients cannot be assigned to appointment!");
        } else {
            this.patient = patient;
        }
    }

    public void setDoctor(Doctor doctor) { this.doctor = doctor; }
    public void setDateTime(LocalDateTime dateTime) { this.dateTime = dateTime; }
    public void setStatus(String status) { this.status = status; }
    public void setBonusPerAppointment(double bonus) { this.bonusPerAppointment = bonus; }

    // Method to reschedule appointment
    public void reschedule(LocalDateTime newDateTime) {
        if (!status.equalsIgnoreCase("Cancelled")) {
            this.dateTime = newDateTime;
        }
    }

    // Method to cancel appointment
    public void cancel() {
        this.status = "Cancelled";
    }

    // Method to complete appointment and add bonus to doctor
    public void complete() {
        if (!status.equalsIgnoreCase("Cancelled")) {
            this.status = "Completed";
            if (doctor != null && bonusPerAppointment > 0) {
                doctor.addAppointmentBonus(bonusPerAppointment);
            }
        }
    }

    // Helper method: check if appointment is still scheduled
    public boolean isScheduled() {
        return status.equalsIgnoreCase("Scheduled");
    }

    // toString: readable representation of appointment
    @Override
    public String toString() {
        return "Appointment{ID=" + appointmentId +
                ", patient='" + patient.getFullName() + '\'' +
                ", doctor='" + doctor.getFullName() + '\'' +
                ", dateTime=" + dateTime +
                ", status='" + status + '\'' +
                ", bonus=$" + bonusPerAppointment +
                '}';
    }
}