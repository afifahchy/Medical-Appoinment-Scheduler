public class Doctor extends Person {
    String specialization;
    String time;

    public Doctor(String name, int age, String gender, String specialization, String time) {
        super(name, age, gender);
        this.specialization = specialization;
        this.time = time;
    }

    public String toString() {
        return "Dr. " + name + " - " + specialization + " - Time: " + time;
    }
}
