public class Patient extends Person {
    String dob;

    public Patient(String name, int age, String gender, String dob) {
        super(name, age, gender);
        this.dob = dob;
    }

    public String toString() {
        return name + " (DOB: " + dob + ")";
    }
}
