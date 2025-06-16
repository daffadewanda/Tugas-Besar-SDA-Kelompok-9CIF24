package com.daisukeclinic;

import com.daisukeclinic.model.*;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) throws Exception {
        // menginisialisasi 
        Scanner scanner = new Scanner(System.in);

        PatientLinkedList patientList = new PatientLinkedList();
        DoctorLoginList.DoctorLinkedList doctorSystem  = new DoctorLoginList.DoctorLinkedList();
        String filepath = "doctors.txt";

        int choice;
        int choicePatient;
        int choiceDoctor;
        int choiceAdmin;
        
        // while loop for main menu
        do{     
            patientList.handleChoice(14, scanner, "patients.txt");
            PatientBST patientBST = PatientBST.loadFromFile("patients.txt");
            
            clearConsole();

            halamanUtama(); 
            
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice)  {
                // Menu Patient
                case 1:
                    clearConsole();
                
                    halamanPatient();
                    System.out.print("Enter your choice: ");
                    choicePatient = scanner.nextInt();
                    scanner.nextLine();
                    if(choicePatient == 0) {
                        // Exit to main menu
                        clearConsole();
                        System.out.println("Returning to main menu...");
                        tekanEnter(scanner);
                        break;
                    }
                    else if(choicePatient == 1) {
                        // add new patient
                        clearConsole();

                        patientList.handleChoice(1, scanner, "patients.txt");
                        patientList.handleChoice(13, scanner, "patients.txt");
                    
                        tekanEnter(scanner);
                        break;
                    }
                    else if(choicePatient == 2){
                        // search patient by name
                        clearConsole();

                        patientList.handleChoice(3, scanner, "patients.txt");

                        tekanEnter(scanner);
                    }
                    else if(choicePatient == 3){
                        // schedule appointment
                        clearConsole();

                        doctorSystem.handleChoice(8, scanner, filepath, patientList);
                        doctorSystem.handleChoice(9, scanner, filepath, patientList);
                    
                        tekanEnter(scanner);
                    }
                    else if(choicePatient == 4){
                        // Search patient by ID (BST)
                        clearConsole();

                        System.out.print("Enter Patient ID to search: ");
                        String searchId = scanner.nextLine();
                        patientBST.search(searchId);
                    
                        tekanEnter(scanner);
                    }
                    else{
                        System.out.println("Invalid choice. Please try again.");
                        tekanEnter(scanner);
                    }
                    break;
                // menu Doctor
                case 2:
                    clearConsole();
                    System.out.println("Input password to access Doctor Menu !!!");
                    System.out.print("Password : ");
                    int password = scanner.nextInt();
                    scanner.nextLine();
                    if(password != 1111) {
                        System.out.println("Incorrect password. Returning to main menu...");
                        tekanEnter(scanner);
                        break;
                    }
                    halamanDoctor();
                    System.out.print("Enter your choice: ");
                    choiceDoctor = scanner.nextInt();
                    scanner.nextLine();
                    if(choiceDoctor == 0) {
                        // Exit to main menu
                        clearConsole();
                        System.out.println("Returning to main menu...");
                        tekanEnter(scanner);
                        break;
                    }
                    else if(choiceDoctor == 1) {
                        // Register New Doctor
                        clearConsole();

                        doctorSystem.handleChoice(4, scanner, filepath, patientList);

                        tekanEnter(scanner);
                        break;
                    }
                    else if(choiceDoctor == 2){
                        // Doctor Login
                        clearConsole();

                        doctorSystem.handleChoice(5, scanner, filepath, patientList);

                        tekanEnter(scanner);
                    }
                    else if(choiceDoctor == 3){
                        // Doctor Logout
                        clearConsole();

                        doctorSystem.handleChoice(6, scanner, filepath, patientList);

                        tekanEnter(scanner);
                    }
                    else{
                        System.out.println("Invalid choice. Please try again.");
                        tekanEnter(scanner);
                    }
                    break;
                // menu Admin
                case 3:
                    clearConsole();
                    System.out.println("Input password to access Admin Menu !!!");
                    System.out.print("Password : ");
                    int password1 = scanner.nextInt();
                    scanner.nextLine();
                    if(password1 != 0000) {
                        System.out.println("Incorrect password. Returning to main menu...");
                        tekanEnter(scanner);
                        break;
                    }
                    halamanAdmin();
                    System.out.print("Enter your choice: ");
                    choiceAdmin = scanner.nextInt();
                    scanner.nextLine();
                    if(choiceAdmin == 0) {
                        // Exit to main menu
                        clearConsole();
                        System.out.println("Returning to main menu...");
                        tekanEnter(scanner);
                        break;
                    }
                    else if(choiceAdmin == 1) {
                        // Remove patient by ID
                        clearConsole();

                        patientList.handleChoice(2, scanner, "patients.txt");

                        tekanEnter(scanner);
                        break;
                    }
                    else if(choiceAdmin == 2){
                        // Display All patient
                        clearConsole();

                        patientList.handleChoice(4, scanner, "patients.txt");

                        tekanEnter(scanner);
                    }
                    else if(choiceAdmin == 3){
                        // View Last login doctor
                        clearConsole();

                        doctorSystem.handleChoice(7, scanner, filepath, patientList);
                    
                        tekanEnter(scanner);
                    }
                    else if(choiceAdmin == 4){
                        // process appointment
                        clearConsole();

                        doctorSystem.handleChoice(10, scanner, filepath, patientList);

                        tekanEnter(scanner);
                    }
                    else if(choiceAdmin == 5){
                        // Display upcoming appointment
                        clearConsole();

                        doctorSystem.handleChoice(11, scanner, filepath, patientList);

                        tekanEnter(scanner);
                    }
                    else if(choiceAdmin == 6){
                        // Display all patient (BST Inorder)
                        clearConsole();

                        System.out.println("Displaying all patients in BST (Inorder Traversal):");
                        patientBST.inorderDisplay();

                        tekanEnter(scanner);
                    }
                    else if(choiceAdmin == 7){
                        // Add Medical History
                        clearConsole();

                        patientList.handleChoice(5, scanner, "patients.txt");
                        patientList.handleChoice(13, scanner, "patients.txt");

                        tekanEnter(scanner);
                    break;
                    }
                    else{
                        System.out.println("Invalid choice. Please try again.");
                        tekanEnter(scanner);
                    }
                    break;
                    
                // Exit
                case 0:
                    System.out.println("Thank you for using Daisuke Clinic System!");

                    break;

                default:
                    break;
            }
        }while(choice != 0);
        scanner.close();
    }

    public static void halamanUtama(){
            System.out.println("============================================");
            System.out.println("-------------- Daisuke Clinic --------------");
            System.out.println("============================================");
            System.out.println("1) Patient Menu");
            System.out.println("2) Doctor Menu");
            System.out.println("3) Admin Menu");
            System.out.println("0) EXIT");
            System.out.println("============================================");

    }
    public static void halamanPatient(){
            System.out.println("============================================");
            System.out.println("--------------- Patient Menu ---------------");
            System.out.println("============================================");
            System.out.println("1) Add New Patient");
            System.out.println("2) Search Patient by Name");
            System.out.println("3) Schedule Appointment");
            System.out.println("4) Search Patient by ID (BST)");
            System.out.println("0) Back to Main Menu");
            System.out.println("============================================");

        }
    public static void halamanDoctor(){
            System.out.println("============================================");
            System.out.println("---------------- Doctor Menu ---------------");
            System.out.println("============================================");
            System.out.println("1) Register New Doctor");
            System.out.println("2) Doctor Login");
            System.out.println("3) Doctor Logout");
            System.out.println("0) Back to Main Menu");
            System.out.println("============================================");

        }
    public static void halamanAdmin(){
            System.out.println("============================================");
            System.out.println("---------------- Adimin Menu ---------------");
            System.out.println("============================================");
            System.out.println("1) Remove Patient by ID");
            System.out.println("2) Display All Patients");
            System.out.println("3) View Last Logged In Doctor");
            System.out.println("4) Process Appointment");
            System.out.println("5) Display Upcoming Appointments");
            System.out.println("6) Display All Patients (BST Inorder)");
            System.out.println("7) Add Medical History");
            System.out.println("0) Back to Main Menu");
            System.out.println("============================================");

        }
        
    // Methode untuk clear screen terminal
    public static void clearConsole() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            }
        } 
        catch (Exception e) {
            System.out.println("Error trying to clear console.");
        }
    }

    public static void tekanEnter(Scanner scanner) {
        System.out.println("____________________________________________________________");
        System.out.print("\n Press Enter to contonue ...");
        scanner.nextLine();
        clearConsole();
    }
    
}
