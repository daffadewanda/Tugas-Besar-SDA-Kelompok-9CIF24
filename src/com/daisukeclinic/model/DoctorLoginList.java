package com.daisukeclinic.model;

import com.daisukeclinic.structure.*;

import java.io.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class DoctorLoginList {
    private String filePath;

    static class Doctor {
        int id;
        String name;
        String specialty;
        LocalTime startTime;
        LocalTime endTime;
        boolean isLoggedIn;
        AppointmentQueue appointmentQueue;

        Doctor(int id, String name, String specialty, LocalTime startTime, LocalTime endTime) {
            this.id = id;
            this.name = name;
            this.specialty = specialty;
            this.startTime = startTime;
            this.endTime = endTime;
            this.isLoggedIn = false;
            this.appointmentQueue = new AppointmentQueue();
        }

        public void login() {
            this.isLoggedIn = true;
        }

        public void logout() {
            this.isLoggedIn = false;
        }

        public String toString() {
            return "ID: " + id + ", Name: " + name + ", Specialty: " + specialty + 
                   ", Schedule: " + startTime + "-" + endTime + ", Status: " + 
                   (isLoggedIn ? "Logged In" : "Logged Out");
        }
    }

    public static class DoctorLinkedList {
        static class Node {
            Doctor doctor;
            Node next;

            Node(Doctor doctor) {
                this.doctor = doctor;
                this.next = null;
            }
        }

        private Node head = null;
        private static final String DEFAULT_FILE_PATH = "doctors.txt";

        // Method untuk menangani pilihan menu
        public void handleChoice(int choice, Scanner scanner, String filePath, PatientLinkedList patientList) {
            switch (choice) {
                case 4:
                    addDoctor(scanner, filePath);
                    break;
                case 5:
                    loginDoctor(scanner, filePath);
                    break;
                case 6:
                    logoutDoctor(scanner);
                    break;
                case 7:
                    viewLastLoggedIn();
                    break;
                case 8:
                    viewAllLoggedIn();
                    break;
                case 9:
                    scheduleAppointment(scanner, patientList);
                    break;
                case 10:
                    processNextAppointment(scanner);
                    break;
                case 11:
                    viewDoctorAppointments(scanner);
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
        //Method untuk menambahkan dokter baru 
        public void addDoctor(Scanner scanner) {
            addDoctor(scanner, DEFAULT_FILE_PATH);
        }

        public void addDoctor(Scanner scanner, String filePath) {
            System.out.print("Enter Doctor ID: ");
            String idInput = scanner.nextLine().trim(); 

            // Validate ID input
            if (idInput.isEmpty() || !idInput.matches("\\d+")) {
                System.out.println("Invalid ID. Must be a positive number.");
                return;
            }

            int id = Integer.parseInt(idInput);
            if (id <= 0) {
                System.out.println("Doctor ID must be positive.");
                return;
            }

            // Check if ID already exists (hanya cek ID duplikat)
            if (isDoctorIdExist(id, filePath)) {
                System.out.println("Doctor ID already exists.");
                return;
            }

            // Get doctor details
            System.out.print("Enter Doctor Name: ");
            String name = scanner.nextLine().trim();
            System.out.print("Enter Specialty: ");
            String specialty = scanner.nextLine().trim();

            // Validate name and specialty
            if (name.isEmpty() || specialty.isEmpty()) {
                System.out.println("Name and Specialty cannot be empty.");
                return;
            }

            if (name.contains(",") || specialty.contains(",")) {
                System.out.println("Name and Specialty cannot contain commas.");
                return;
            }

            // Get time schedule
            System.out.print("Enter Start Time (HH:mm): ");
            String startTimeStr = scanner.nextLine().trim();
            System.out.print("Enter End Time (HH:mm): ");
            String endTimeStr = scanner.nextLine().trim();

            // Validate time format
            if (!isValidTimeFormat(startTimeStr) || !isValidTimeFormat(endTimeStr)) {
                System.out.println("Invalid time format. Use HH:mm (e.g., 09:00, 17:30).");
                return;
            }

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
            try {
                LocalTime startTime = LocalTime.parse(startTimeStr, formatter);
                LocalTime endTime = LocalTime.parse(endTimeStr, formatter);

                // Validate time logic
                if (!startTime.isBefore(endTime)) {
                    System.out.println("Start time must be before end time.");
                    return;
                }

                // ===== REMOVED SCHEDULE CONFLICT CHECK =====
                // Multiple doctors can now work at the same time
                // if (hasScheduleConflict(startTime, endTime, filePath, id)) {
                //     System.out.println("Schedule conflict with existing doctor. Please choose different hours.");
                //     return;
                // }

                // Optional: Show info about other doctors at similar times
                showDoctorsAtSimilarTime(startTime, endTime, filePath);

                // Create directory if doesn't exist
                File file = new File(filePath);
                File parentDir = file.getParentFile();
                if (parentDir != null && !parentDir.exists()) {
                    if (!parentDir.mkdirs()) {
                        System.err.println("Failed to create directory: " + parentDir.getAbsolutePath());
                        return;
                    }
                }

                // Write to file
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
                    writer.write(id + "," + name + "," + specialty + "," + startTime + "," + endTime);
                    writer.newLine();
                    System.out.println("Doctor added successfully: ID " + id + ", Name " + name);
                    System.out.println("Schedule: " + startTime + " - " + endTime);
                }

            } catch (DateTimeParseException e) {
                System.out.println("Invalid time format. Use HH:mm format (e.g., 09:00).");
            } catch (IOException e) {
                System.err.println("Error writing to file: " + e.getMessage());
            }
        }

        // Optional: Method untuk menampilkan dokter lain di jam serupa (informatif saja)
        private void showDoctorsAtSimilarTime(LocalTime newStartTime, LocalTime newEndTime, String filePath) {
            File file = new File(filePath);
            if (!file.exists()) {
                return;
            }

            System.out.println("\n--- Other doctors working at similar times ---");
            boolean foundSimilar = false;

            try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    line = line.trim();
                    if (line.isEmpty()) continue;
                    
                    String[] parts = line.split(",");
                    if (parts.length >= 5) {
                        try {
                            int existingId = Integer.parseInt(parts[0].trim());
                            String existingName = parts[1].trim();
                            String existingSpecialty = parts[2].trim();
                            LocalTime existingStart = LocalTime.parse(parts[3].trim());
                            LocalTime existingEnd = LocalTime.parse(parts[4].trim());
                            
                            // Check for any overlap (just for information)
                            if (newStartTime.isBefore(existingEnd) && newEndTime.isAfter(existingStart)) {
                                System.out.println("--> Dr. " + existingName + " (ID: " + existingId + 
                                                ") - " + existingSpecialty + 
                                                " (" + existingStart + "-" + existingEnd + ")");
                                foundSimilar = true;
                            }
                            
                        } catch (NumberFormatException | DateTimeParseException e) {
                            continue;
                        }
                    }
                }
            } catch (IOException e) {
                // Silently ignore read errors for this informational feature
            }
            
            if (!foundSimilar) {
                System.out.println("No other doctors scheduled at overlapping times.");
            }
            System.out.println("===============================================\n");
        }

        //Method untuk mengecek apakah ID dokter sudah ada dengan error handling 
        public boolean isDoctorIdExist(int id) {
            return isDoctorIdExist(id, DEFAULT_FILE_PATH);
        }

        public boolean isDoctorIdExist(int id, String filePath) {
            File file = new File(filePath);
            if (!file.exists()) {
                return false;
            }

            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                int lineNumber = 0;
                while ((line = reader.readLine()) != null) {
                    lineNumber++;
                    line = line.trim(); 
                    if (line.isEmpty()) continue; 
                    
                    String[] parts = line.split(",");
                    if (parts.length >= 1) {
                        try {
                            int existingId = Integer.parseInt(parts[0].trim());
                            if (existingId == id) {
                                return true;
                            }
                        } catch (NumberFormatException e) {
                            System.err.println("Warning: Invalid ID format at line " + lineNumber + ": " + line);
                        }
                    }
                }
            } catch (IOException e) {
                System.err.println("Error reading file: " + e.getMessage());
                return false; 
            }
            return false;
        }

        //Helper method untuk validasi format waktu
        private boolean isValidTimeFormat(String timeStr) {
            if (timeStr == null || timeStr.trim().isEmpty()) {
                return false;
            }
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
                LocalTime.parse(timeStr.trim(), formatter);
                return true;
            } catch (DateTimeParseException e) {
                return false;
            }
        }    

        // Login doctor dengan validasi jadwal
        private void loginDoctor(Scanner scanner, String filePath) {
            System.out.print("Enter Doctor ID: ");
            String idInput = scanner.nextLine();

            Doctor doctor = findDoctorInFile(filePath, idInput);
            if (doctor == null) {
                System.out.println("Doctor not found or schedule format invalid.");
                return;
            }

            LocalTime now = LocalTime.now();
            if (now.isBefore(doctor.startTime) || now.isAfter(doctor.endTime)) {
                System.out.println("Login denied. Not within scheduled hours (" + 
                                 doctor.startTime + " - " + doctor.endTime + ").");
                return;
            }

            // Cek apakah doctor sudah login
            if (findLoggedInDoctor(String.valueOf(doctor.id)) != null) {
                System.out.println("Doctor is already logged in.");
                return;
            }

            Node newNode = new Node(doctor);
            newNode.next = head;
            head = newNode;
            doctor.login();
            System.out.println("Login successful: " + doctor.name + " (" + doctor.specialty + ")");
        }

        // Logout doctor (LIFO - Last In First Out)
        private void logoutDoctor(Scanner scanner) {
            if (head == null) {
                System.out.println("No doctors currently logged in.");
                return;
            }
            
            System.out.print("Enter Doctor ID to logout: ");
            String doctorId = scanner.nextLine();
            
            // Jika logout doctor di head
            if (head.doctor.id == Integer.parseInt(doctorId)) {
                System.out.println("Doctor logged out: " + head.doctor.name);
                head.doctor.logout();
                head = head.next;
                return;
            }
            
            // Cari doctor di linked list
            Node current = head;
            while (current.next != null) {
                if (current.next.doctor.id == Integer.parseInt(doctorId)) {
                    System.out.println("Doctor logged out: " + current.next.doctor.name);
                    current.next.doctor.logout();
                    current.next = current.next.next;
                    return;
                }
                current = current.next;
            }
            
            System.out.println("Doctor with ID " + doctorId + " not found in logged in list.");
        }

        // Schedule appointment untuk doctor yang login
        private void scheduleAppointment(Scanner scanner, PatientLinkedList patientList) {
            if (head == null) {
                return;
            }
            System.out.println("__________________________________________________________________________________");
            // Cek apakah ada doctor yang login
            System.out.print("Enter Doctor ID: ");
            String doctorId = scanner.nextLine();
            Doctor doctor = findLoggedInDoctor(doctorId);
            
            if (doctor == null) {
                System.out.println("Doctor not found or not logged in.");
                return;
            }

            System.out.print("Enter Patient ID: ");
            int patientId = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            
            // Cek apakah patient ada di sistem
            PatientLinkedList.Patient patient = patientList.findPatientById(patientId);
            if (patient == null) {
                System.out.println("Patient with ID " + patientId + " not found.");
                System.out.print("Would you like to add this patient? (y/n): ");
                String choice = scanner.nextLine();
                if (choice.equalsIgnoreCase("y")) {
                    System.out.print("Enter Patient Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Patient Age: ");
                    int age = Integer.parseInt(scanner.nextLine());
                    System.out.print("Enter Patient Address: ");
                    String address = scanner.nextLine();
                    System.out.print("Enter Patient Phone: ");
                    String phone = scanner.nextLine();
                    System.out.print("Enter Medical History (comma separated): ");
                    String[] medicalHistory = scanner.nextLine().split(",");
                    patient = new PatientLinkedList.Patient(patientId, name, age, address, phone, medicalHistory);
                    patientList.addPatient(patient);
                } else {
                    System.out.println("Appointment cancelled.");
                    return;
                }
            }
            String patientName = patient.name;

            // Cek apakah appointment time valid
            System.out.print("Enter Appointment Time (HH:mm): ");
            String time = scanner.nextLine();
            
            // Time format validation
            LocalTime appointmentTime;
            appointmentTime = LocalTime.parse(time, DateTimeFormatter.ofPattern("HH:mm"));

            if (appointmentTime.isBefore(doctor.startTime) || appointmentTime.isAfter(doctor.endTime)) {
                System.out.println("Appointment time must be within doctor's schedule (" + 
                                 doctor.startTime + " - " + doctor.endTime + ").");
                return;
            }

            // Menambahkan appointment ke queue doctor
            Appointment appointment = new Appointment(patientId, patientName, time, doctorId);
            doctor.appointmentQueue.enqueue(appointment);
            
            System.out.println("Appointment scheduled successfully for " + patient.name + 
                             " with Dr. " + doctor.name + " at " + time);
        }

        // Process next appointment untuk doctor
        private void processNextAppointment(Scanner scanner) {
            if (head == null) {
                System.out.println("No doctors currently logged in.");
                return;
            }

            System.out.print("Enter Doctor ID: ");
            String doctorId = scanner.nextLine();
            Doctor doctor = findLoggedInDoctor(doctorId);
            
            if (doctor == null) {
                System.out.println("Doctor not found or not logged in.");
                return;
            }

            Appointment appointment = doctor.appointmentQueue.dequeue();
            if (appointment != null) {
                System.out.println("Processing appointment: \n" + appointment);
                System.out.println("Appointment completed successfully!");
            } else {
                System.out.println("No appointments to process for Dr. " + doctor.name);
            }
        }

        // View appointments untuk doctor tertentu
        private void viewDoctorAppointments(Scanner scanner) {
            if (head == null) {
                System.out.println("No doctors currently logged in.");
                return;
            }

            System.out.print("Enter Doctor ID: ");
            String doctorId = scanner.nextLine();
            Doctor doctor = findLoggedInDoctor(doctorId);
            
            if (doctor == null) {
                System.out.println("Doctor not found or not logged in.");
                return;
            }

            System.out.println("Appointments for " + doctor.name + ":");
            doctor.appointmentQueue.display();
        }

        // Find logged in doctor by ID
        private Doctor findLoggedInDoctor(String doctorId) {
            Node current = head;
            while (current != null) {
                if (String.valueOf(current.doctor.id).equals(doctorId)) {
                    return current.doctor;
                }
                current = current.next;
            }
            return null;
        }

        // View last logged in doctor
        private void viewLastLoggedIn() {
            if (head == null) {
                System.out.println("No doctors currently logged in.");
            } else {
                Doctor d = head.doctor;
                System.out.println("Last logged-in doctor: " + d.name + " | " + d.specialty);
                System.out.println("Appointments in queue: " + d.appointmentQueue.getSize());
            }
        }

        // View all logged in doctors
        private void viewAllLoggedIn() {
            if (head == null) {
                System.out.println("No doctors currently logged in.");
                return;
            }

            Node current = head;
            System.out.println("All logged-in doctors:");
            int count = 1;
            while (current != null) {
                Doctor d = current.doctor;
                System.out.println(count+ ". Id: "+ d.id + " | " + d.name + " | " + d.specialty + " | " + d.startTime + " - " + d.endTime + 
                                 " | Appointments: " + d.appointmentQueue.getSize());
                count++;
                current = current.next;
            }
        }

        // Find doctor in file by ID
        private Doctor findDoctorInFile(String filePath, String idInput) {
            try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
                String line;
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(",");
                    if (parts.length != 5) continue;
                    if (parts[0].equals(idInput)) {
                        int id = Integer.parseInt(parts[0]);
                        String name = parts[1];
                        String specialty = parts[2];
                        LocalTime start = LocalTime.parse(parts[3], formatter);
                        LocalTime end = LocalTime.parse(parts[4], formatter);
                        return new Doctor(id, name, specialty, start, end);
                    }
                }
            } catch (IOException e) {
                System.out.println("Error reading file: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Error parsing doctor data: " + e.getMessage());
            }
            return null;
        }

        // Method untuk load semua doctor dari file
        public Doctor[] loadAllDoctors(String filePath) {
            Doctor[] doctors = new Doctor[100];
            int count = 0;
            
            try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
                String line;
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
                while ((line = reader.readLine()) != null && count < doctors.length) {
                    String[] parts = line.split(",");
                    if (parts.length == 5) {
                        int id = Integer.parseInt(parts[0]);
                        String name = parts[1];
                        String specialty = parts[2];
                        LocalTime start = LocalTime.parse(parts[3], formatter);
                        LocalTime end = LocalTime.parse(parts[4], formatter);
                        doctors[count++] = new Doctor(id, name, specialty, start, end);
                    }
                }
                System.out.println("Loaded " + count + " doctors from file.");
            } catch (IOException e) {
                System.out.println("Error loading doctors: " + e.getMessage());
            }
            return doctors;
        }
    }

    // Method untuk mendapatkan doctor dari file
    public Doctor getDoctorFromFile(String doctorId) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 5 && parts[0].equals(doctorId)) {
                    int id = Integer.parseInt(parts[0]);
                    String name = parts[1];
                    String specialty = parts[2];
                    LocalTime start = LocalTime.parse(parts[3], formatter);
                    LocalTime end = LocalTime.parse(parts[4], formatter);
                    return new Doctor(id, name, specialty, start, end);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        return null;
    }
}