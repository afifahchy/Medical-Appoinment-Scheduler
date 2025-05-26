import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class MedicalAppointment {

    static List<Doctor> doctors = new ArrayList<>();
    static List<Appointment> appointments = new ArrayList<>();

    public static void main(String[] args) {
        doctors.add(new Doctor("Fazal Karim", 50, "Male", "Pediatrician", "10:00 AM - 12:00 PM"));
        doctors.add(new Doctor("Kajol Rekha", 42, "Female", "Gynaecologist", "01:00 PM - 03:00 PM"));
        SwingUtilities.invokeLater(MedicalAppointment::loginFrame);
    }

    public static void loginFrame() {
        JFrame frame = new JFrame("Aristrocare Hospital and Diagnostic Center - Login");
        frame.setSize(350, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(3, 2));

        JTextField usernameField = new JTextField();
        JPasswordField passwordField = new JPasswordField();
        JButton loginBtn = new JButton("Login");

        frame.add(new JLabel("Username:"));
        frame.add(usernameField);
        frame.add(new JLabel("Password:"));
        frame.add(passwordField);
        frame.add(new JLabel(""));
        frame.add(loginBtn);

        loginBtn.addActionListener(e -> {
            String user = usernameField.getText();
            String pass = new String(passwordField.getPassword());

            if (user.equals("admin") && pass.equals("1234")) {
                frame.dispose();
                doctorListFrame();
            } else {
                JOptionPane.showMessageDialog(frame, "Invalid credentials.");
            }
        });

        frame.setVisible(true);
    }

    public static void doctorListFrame() {
        JFrame frame = new JFrame("Available Doctors - Aristrocare Hospital");
        frame.setSize(400, 300);
        frame.setLayout(new BorderLayout());

        JTextArea area = new JTextArea();
        area.setEditable(false);
        for (Doctor doc : doctors) {
            area.append(doc.toString() + "\n");
        }

        JButton nextBtn = new JButton("Book Appointment");
        nextBtn.addActionListener(e -> {
            frame.dispose();
            appointmentFrame();
        });

        frame.add(new JScrollPane(area), BorderLayout.CENTER);
        frame.add(nextBtn, BorderLayout.SOUTH);
        frame.setVisible(true);
    }

    public static void appointmentFrame() {
        JFrame frame = new JFrame("Book an Appointment");
        frame.setSize(400, 300);
        frame.setLayout(new GridLayout(7, 2));

        JTextField nameField = new JTextField();
        JTextField ageField = new JTextField();
        JTextField genderField = new JTextField();
        JTextField dobField = new JTextField();
        JComboBox<String> doctorBox = new JComboBox<>();

        for (Doctor d : doctors) {
            doctorBox.addItem(d.toString());
        }

        JButton submitBtn = new JButton("Submit");

        frame.add(new JLabel("Patient Name:"));
        frame.add(nameField);
        frame.add(new JLabel("Age:"));
        frame.add(ageField);
        frame.add(new JLabel("Gender:"));
        frame.add(genderField);
        frame.add(new JLabel("Date of Birth (DD-MM-YYYY):"));
        frame.add(dobField);
        frame.add(new JLabel("Select Doctor:"));
        frame.add(doctorBox);
        frame.add(new JLabel(""));
        frame.add(submitBtn);

        submitBtn.addActionListener(e -> {
            try {
                String name = nameField.getText();
                int age = Integer.parseInt(ageField.getText());
                String gender = genderField.getText();
                String dob = dobField.getText();
                Doctor selectedDoctor = doctors.get(doctorBox.getSelectedIndex());

                Patient patient = new Patient(name, age, gender, dob);
                Appointment appointment = new Appointment(selectedDoctor, patient);
                appointments.add(appointment);

                JOptionPane.showMessageDialog(frame, "Appointment booked successfully!");
                frame.dispose();
                summaryFrame();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Invalid input. Please check all fields.");
            }
        });

        frame.setVisible(true);
    }

    public static void summaryFrame() {
        JFrame frame = new JFrame("Appointment Summary");
        frame.setSize(400, 300);
        frame.setLayout(new BorderLayout());

        JTextArea area = new JTextArea();
        area.setEditable(false);

        if (appointments.isEmpty()) {
            area.setText("No appointments made yet.");
        } else {
            for (Appointment a : appointments) {
                area.append(a.toString() + "\n");
            }
        }

        JButton backBtn = new JButton("Book Another Appointment");
        backBtn.addActionListener(e -> {
            frame.dispose();
            appointmentFrame();
        });

        frame.add(new JScrollPane(area), BorderLayout.CENTER);
        frame.add(backBtn, BorderLayout.SOUTH);
        frame.setVisible(true);
    }
}
