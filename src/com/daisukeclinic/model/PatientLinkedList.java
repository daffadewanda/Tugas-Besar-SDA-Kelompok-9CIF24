package com.daisukeclinic.model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class PatientLinkedList {
    public static class Patient {
        int id;
        String name;
        int age;
        String address;
        String phone;
        String[] medicalHistory;

        public Patient(int id, String name, int age, String address, String phone, String[] medicalHistory) {
            this.id = id;   
            this.name = name;
            this.age = age;
            this.address = address;
            this.phone = phone;
            this.medicalHistory = medicalHistory;
        }

        public String getMedicalHistoryString(){
            String result = "(";
            for (int i = 0; i < medicalHistory.length; i++) {
                result += medicalHistory[i];
                if (i < medicalHistory.length - 1) {
                    result += ", ";
                }   
            }
            result += ")";
            return result;
        }

        public void setMedicalHistory(String[] medicalHistory) {
            this.medicalHistory = medicalHistory;
        }

        @Override
        public String toString() {
            return String.format("ID: %d | Name: %s | Age: %d | Address: %s | Phone: %s | Medical History: %s",
                    id, name, age, address, phone, getMedicalHistoryString());
        }
    }

    static class Node {
        Patient data;
        Node next;

        Node(Patient data) {
            this.data = data;
        }
    }

    private Node head;

    // Tambah pasien ke akhir linked list
    public void addPatient(Patient patient) {
        Node newNode = new Node(patient);
        if (head == null) {
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null)
                current = current.next;
            current.next = newNode;
        }
        // System.out.println("Patient added: " + patient.name);
    }

    // Hapus pasien berdasarkan ID
    public void removePatientById(int id) {
        if (head == null) {
            System.out.println("No patient data.");
            return;
        }
        if (head.data.id == id) {
            System.out.println("Patient removed: " + head.data.name);
            head = head.next;
            return;
        }
        Node current = head;
        while (current.next != null && current.next.data.id != id) {
            current = current.next;
        }
        if (current.next == null) {
            System.out.println("Patient with ID " + id + " not found.");
        } else {
            System.out.println("Patient removed: " + current.next.data.name);
            current.next = current.next.next;
        }
    }
    public Patient findPatientById(int id) {
        Node current = head;
        while (current != null) {
            if (current.data.id == id) {
                return current.data;
            }
            current = current.next;
        }
        return null;
    }
    // Cari pasien berdasarkan nama
    public void findPatientByName(String name) {
        Node current = head;
        boolean found = false;
        while (current != null) {
            if (current.data.name.equalsIgnoreCase(name)) {
                System.out.println(current.data);
                found = true;
            }
            current = current.next;
        }
        if (!found) {
            System.out.println("No patient found with name: " + name);
        }
    }

    // Tampilkan semua pasien
    public void displayAllPatients() {
        if (head == null) {
            System.out.println("No patient data available.");
            return;
        }
        System.out.println("All Patients:");
        Node current = head;
        while (current != null) {
            System.out.println(current.data);
            current = current.next;
        }
    }
    // Tambah riwayat medis berdasarkan ID
    public void addMedicalHistoryById(int id, String newEntry) {
    Node current = head;
    while (current != null) {
        if (current.data.id == id) {
            String[] oldHistory = current.data.medicalHistory;
            String[] newHistory = new String[oldHistory.length + 1];
            for (int i = 0; i < oldHistory.length; i++) {
                newHistory[i] = oldHistory[i];
            }
                newHistory[oldHistory.length] = newEntry;
                current.data.setMedicalHistory(newHistory);
                System.out.println("Added medical history for ID " + id + ": " + newEntry);
                return;
            }
            current = current.next;
        }
        System.out.println("Patient with ID " + id + " not found.");
    }

    // Simpan ke file
    public void saveToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("patients.txt"))) {
            Node current = head;
            while (current != null) {
                Patient p = current.data;
                writer.write(p.id + "," + p.name + "," + p.age + "," + p.address + "," + p.phone + "," + String.join(";", p.medicalHistory));
                writer.newLine();
                current = current.next;
            }
            System.out.println("Patient data saved to file.");
        } catch (IOException e) {
            System.out.println("Error saving to file: " + e.getMessage());
        }
    }

    // Load dari file
    public void loadFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader("patients.txt"))) {
            String line;
            head = null;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 6) {
                    int id = Integer.parseInt(parts[0]);
                    String name = parts[1];
                    int age = Integer.parseInt(parts[2]);
                    String address = parts[3];
                    String phone = parts[4];
                    String[] medicalHistory = parts[5].split(";");
                    addPatient(new Patient(id, name, age, address, phone, medicalHistory));
                }
            }
            System.out.println("Patient data loaded from file.");
        } catch (IOException e) {
            System.out.println("Error reading from file: " + e.getMessage());
        }
    }

    // Tambahan interaktif sederhana
    public void handleChoice(int choice, Scanner scanner, String filename) {
        switch (choice) {
            case 1 -> {
                System.out.print("Enter ID: ");
                int id = Integer.parseInt(scanner.nextLine());
                System.out.print("Enter Name: ");
                String name = scanner.nextLine();
                System.out.print("Enter Age: ");
                int age = Integer.parseInt(scanner.nextLine());
                System.out.print("Enter Address: ");
                String address = scanner.nextLine();
                System.out.print("Enter Phone: ");
                String phone = scanner.nextLine();
                System.out.print("Enter Medical History (Just Put (-) ): ");
                String[] medicalHistory = scanner.nextLine().split(",");

                addPatient(new Patient(id, name, age, address, phone, medicalHistory));
            }
            case 2 -> {
                System.out.print("Enter ID to remove: ");
                int id = Integer.parseInt(scanner.nextLine());
                removePatientById(id);
            }
            case 3 -> {
                System.out.print("Enter name to search: ");
                String name = scanner.nextLine();
                findPatientByName(name);
            }
            case 4 -> displayAllPatients();
            case 5 -> {
                System.out.print("Enter ID Patient : ");
                int id = Integer.parseInt(scanner.nextLine());
                System.out.print("Enter Medical Checkup : ");
                String newHistory = scanner.nextLine();
                addMedicalHistoryById(id, newHistory); 
            }
            case 13 -> saveToFile();
            case 14 -> loadFromFile();

            default -> System.out.println("Invalid option.");
        }
    }
}
    