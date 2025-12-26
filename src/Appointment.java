import java.time.LocalDateTime;

public class Appointment {
    private int appointmentId;
    private Patient patient;
    private Doctor doctor;
    private LocalDateTime dateTime;
    private String status; // "Scheduled", "Completed", "Cancelled"
    private double bonusPerAppointment; // bonus for this appointment

    // Constructor with parameters
    public Appointment(int appointmentId, Patient patient, Doctor doctor,
                       LocalDateTime dateTime, String status, double bonusPerAppointment) {
        this.appointmentId = appointmentId;
        this.bonusPerAppointment = bonusPerAppointment;

        if (patient.isMinor()) {
            System.out.println("Error: Minor patients cannot book an appointment!");
            this.patient = patient;
            this.doctor = doctor;
            this.dateTime = dateTime;
            this.status = "Cancelled";
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
        this.patient = new Patient();
        this.doctor = new Doctor();
        this.dateTime = LocalDateTime.now();
        this.status = "Scheduled";
        this.bonusPerAppointment = 0;
    }

    // Getters
    public int getAppointmentId() { return appointmentId; }
    public Patient getPatient() { return patient; }
    public Doctor getDoctor() { return doctor; }
    public LocalDateTime getDateTime() { return dateTime; }
    public String getStatus() { return status; }
    public double getBonusPerAppointment() { return bonusPerAppointment; }

    // Setters
    public void setAppointmentId(int appointmentId) { this.appointmentId = appointmentId; }

    public void setPatient(Patient patient) {
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

    // Methods
    public void reschedule(LocalDateTime newDateTime) {
        if (!status.equalsIgnoreCase("Cancelled")) {
            this.dateTime = newDateTime;
        }
    }

    public void cancel() {
        this.status = "Cancelled";
    }

    // COMPLETE appointment and add bonus to doctor
    public void complete() {
        if (!status.equalsIgnoreCase("Cancelled")) {
            this.status = "Completed";
            if (doctor != null && bonusPerAppointment > 0) {
                doctor.addAppointmentBonus(bonusPerAppointment);
            }
        }
    }

    public boolean isScheduled() {
        return status.equalsIgnoreCase("Scheduled");
    }

    @Override
    public String toString() {
        return "Appointment{ID=" + appointmentId +
                ", patient='" + patient.getFullName() + '\'' +
                ", doctor='" + doctor.getName() + '\'' +
                ", dateTime=" + dateTime +
                ", status='" + status + '\'' +
                ", bonus=$" + bonusPerAppointment +
                '}';
    }
}
