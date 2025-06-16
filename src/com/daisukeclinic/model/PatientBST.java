package com.daisukeclinic.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

class PatientNode {
    String id;
    String name;
    int age;
    String address;
    String phone;
    String[] medicalHistory;
    PatientNode left, right;

    PatientNode(String id, String name, int age, String address, String phone, String[] medicalHistory) {
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

    void display() {
        System.out.println("ID: " + id + " | Name: " + name + " | Age: " + age +
                           " | Address: " + address + " | Phone: " + phone + " | Medical History: " + getMedicalHistoryString());
    }
}

public class PatientBST {
    private PatientNode root;

    public void insert(String id, String name, int age, String address, String phone, String[] medicalHistory) {
        root = insertRec(root, id, name, age, address, phone, medicalHistory);
    }

    private PatientNode insertRec(PatientNode current, String id, String name, int age, String address, String phone, String[] medicalHistory) {
        if (current == null) {
            return new PatientNode(id, name, age, address, phone, medicalHistory);
        }

        if (id.compareTo(current.id) < 0) {
            current.left = insertRec(current.left, id, name, age, address, phone, medicalHistory);
        } else {
            current.right = insertRec(current.right, id, name, age, address, phone, medicalHistory);
        }

        return current;
    }

    public void search(String id) {
        PatientNode result = searchRec(root, id);
        if (result != null) {
            System.out.println("Pasien ditemukan:");
            result.display();
        } else {
            System.out.println("Pasien dengan ID " + id + " tidak ditemukan.");
        }
    }

    private PatientNode searchRec(PatientNode node, String id) {
        if (node == null || node.id.equals(id)) {
            return node;
        }

        if (id.compareTo(node.id) < 0) {
            return searchRec(node.left, id);
        } else {
            return searchRec(node.right, id);
        }
    }

    public void inorderDisplay() {
        inorderRec(root);
    }

    private void inorderRec(PatientNode node) {
        if (node != null) {
            inorderRec(node.left);
            node.display();
            inorderRec(node.right);
        }
    }

    public static PatientBST loadFromFile(String filename) {
        PatientBST bst = new PatientBST();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            int lineNum = 1;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 6) {
                    String id = parts[0].trim();
                    String name = parts[1].trim();
                    String ageStr = parts[2].trim().replaceAll("[^0-9]", "");
                    String address = parts[3].trim();
                    String phone = parts[4].trim();
                    String[] medicalHistory = parts[5].trim().split(";");
                    int age;
                    try {
                        age = Integer.parseInt(ageStr);
                    } catch (NumberFormatException e) {
                        System.out.println("Umur tidak valid pada baris " + lineNum + ": '" + parts[2] + "'");
                        lineNum++;
                        continue;
                    }
                    bst.insert(id, name, age, address, phone, medicalHistory);
                } else {
                    System.out.println("Format tidak cocok pada baris  " + lineNum);
                }
                lineNum++;
            }
        } catch (IOException e) {
            System.out.println("Gagal membaca file: " + e.getMessage());
        }
        return bst;
    }
}
