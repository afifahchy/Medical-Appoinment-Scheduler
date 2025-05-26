public class Appointment {
    Doctor doctor;
    Patient patient;

    public Appointment(Doctor doctor, Patient patient) {
        this.doctor = doctor;
        this.patient = patient;
    }

    public String toString() {
        return patient + " has an appointment with " + doctor;
    }
}
