package com.addressbook;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class AddressBookMain {

    private static final Scanner scanner = new Scanner(System.in);
    private static final AddressBookSys bookSys = new AddressBookSys();

    public static void main(String[] args) {
        int choice;
        // UC-6: Default Address Books
        bookSys.addAddressBook("Friends");
        bookSys.addAddressBook("Family");

        do {
            printMenu();
            choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1 -> addAddressBook();
                case 2 -> addSingleContact();
                case 3 -> addMultipleContacts();
                case 4 -> editContact();
                case 5 -> deleteContact();
                case 6 -> searchByCityOrState();
                case 7 -> viewByCity();
                case 8 -> viewByState();
                case 9 -> countByCity();
                case 10 -> countByState();
                case 11 -> sortContactsByName();
                case 12 -> sortContactsByCity();
                case 13 -> sortContactsByState();
                case 14 -> sortContactsByZip();
                case 15 -> writeToFile();
                case 16 -> readFromFile();
                case 0 -> System.out.println("Exiting Address Book Program...");
                default -> System.out.println("Invalid choice!");
            }
        } while (choice != 0);
    }

    private static void printMenu() {
        System.out.println("\n ADDRESS BOOK MENU ");
        System.out.println("1. Add Address Book ");
        System.out.println("2. Add Single Contact ");
        System.out.println("3. Add Multiple Contacts ");
        System.out.println("4. Edit Contact ");
        System.out.println("5. Delete Contact ");
        System.out.println("6. Search Person by City or State ");
        System.out.println("7. View Persons by City ");
        System.out.println("8. View Persons by State ");
        System.out.println("9. Count Persons by City ");
        System.out.println("10. Count Persons by State ");
        System.out.println("11. Sort Contacts by Name ");
        System.out.println("12. Sort Contacts by City )");
        System.out.println("13. Sort Contacts by State ");
        System.out.println("14. Sort Contacts by Zip ");
        System.out.println("15. Write Address Book to File ");
        System.out.println("16. Read Address Book from File ");
        System.out.println("0. Exit");
        System.out.print("Enter your choice: ");
    }

    // UC-6
    private static void addAddressBook() {
        System.out.print("Enter Address Book Name: ");
        String bookName = scanner.nextLine();
        bookSys.addAddressBook(bookName);
    }

    // UC-2
    private static void addSingleContact() {
        AddressBook addressBook = getAddressBook();
        if (addressBook == null) return;
        Contact contact = readContactDetails();
        addressBook.addContact(contact);
    }

    // UC-5
    private static void addMultipleContacts() {
        AddressBook addressBook = getAddressBook();
        if (addressBook == null) return;
        String choice;
        do {
            Contact contact = readContactDetails();
            addressBook.addContact(contact);
            System.out.print("Add another contact? (yes/no): ");
            choice = scanner.nextLine();
        } while (choice.equalsIgnoreCase("yes"));
    }

    // UC-3
    private static void editContact() {
        AddressBook addressBook = getAddressBook();
        if (addressBook == null) return;
        System.out.print("Enter First Name to Edit : ");
        String firstName = scanner.nextLine();
        System.out.print("New Address : ");
        String address = scanner.nextLine();
        System.out.print("New City : ");
        String city = scanner.nextLine();
        System.out.print("New State : ");
        String state = scanner.nextLine();
        System.out.print("New Zip : ");
        String zip = scanner.nextLine();
        System.out.print("New Phone Number : ");
        String phone = scanner.nextLine();
        System.out.print("New Email : ");
        String email = scanner.nextLine();
        addressBook.editContact(firstName, address, city, state, zip, phone, email);
    }
    // UC-4
    private static void deleteContact() {
        AddressBook addressBook = getAddressBook();
        if (addressBook == null) return;
        System.out.print("Enter First Name to Delete Contact : ");
        String firstName = scanner.nextLine();
        addressBook.deleteContact(firstName);
    }
    // UC-8
    private static void searchByCityOrState() {
        System.out.print("Enter City : ");
        String city = scanner.nextLine();
        List<Contact> cityResult = bookSys.searchPersonByCity(city);
        cityResult.forEach(System.out::println);
        System.out.print("Enter State : ");
        String state = scanner.nextLine();
        List<Contact> stateResult = bookSys.searchPersonByState(state);
        stateResult.forEach(System.out::println);
    }

    // UC-9
    private static void viewByCity() {
        Map<String, List<Contact>> cityMap = bookSys.viewPersonByCity();
        cityMap.forEach((city, persons) -> {
            System.out.println("\nCity : " + city);
            persons.forEach(System.out::println);
        });
    }
    private static void viewByState() {
        Map<String, List<Contact>> stateMap = bookSys.viewPersonByState();
        stateMap.forEach((state, persons) -> {
            System.out.println("\nState : " + state);
            persons.forEach(System.out::println);
        });
    }

    private static AddressBook getAddressBook() {
        System.out.print("Enter Address Book Name : ");
        String bookName = scanner.nextLine();
        AddressBook addressBook = bookSys.getAddressBook(bookName);

        if (addressBook == null) {
            System.out.println("Address Book not found!");
        }
        return addressBook;
    }

    private static Contact readContactDetails() {
        System.out.print("First Name : ");
        String firstName = scanner.nextLine();
        System.out.print("Last Name : ");
        String lastName = scanner.nextLine();
        System.out.print("Address : ");
        String address = scanner.nextLine();
        System.out.print("City : ");
        String city = scanner.nextLine();
        System.out.print("State : ");
        String state = scanner.nextLine();
        System.out.print("Zip : ");
        String zip = scanner.nextLine();
        System.out.print("Phone Number : ");
        String phone = scanner.nextLine();
        System.out.print("Email : ");
        String email = scanner.nextLine();
        return new Contact(firstName, lastName, address, city, state, zip, phone, email);
    }

    //UC-10 count by city
    private static void countByCity() {
        Map<String, Long> cityCountMap = bookSys.countPersonsByCity();
        System.out.println("\nPerson Count by City : ");
        cityCountMap.forEach((city, count) -> System.out.println(city + " " + count));
    }
    //count by state
    private static void countByState(){
        Map<String,Long> stateCountMap = bookSys.countPersonsByState();
        System.out.println("\nPerson Count by State : ");
        stateCountMap.forEach((state,count)->System.out.println(state +" "+ count));
    }

    //UC-11 sort alphabetically
    private static void sortContactsByName() {
        AddressBook addressBook = getAddressBook();
        if (addressBook == null) return;
        System.out.println("\nContacts Sorted Alphabetically by Name : ");
        addressBook.sortContactsByName().forEach(System.out::println);
    }
    //UC-12 sort by city,state,zip
    private static void sortContactsByCity() {
        AddressBook addressBook = getAddressBook();
        if (addressBook == null) return;
        System.out.println("\nContacts Sorted by City : ");
        addressBook.sortContactsByCity().forEach(System.out::println);
    }
    private static void sortContactsByState(){
        AddressBook addressBook =getAddressBook();
        if (addressBook == null) return;
        System.out.println("\nContacts Sorted by State : ");
        addressBook.sortContactsByState().forEach(System.out::println);
    }
    private static void sortContactsByZip(){
        AddressBook addressBook=getAddressBook();
        if (addressBook == null) return;
        System.out.println("\nContact Sorted by Zip");
        addressBook.sortContactsByZip().forEach(System.out::println);
    }
    //UC-13 writing and reading from file
    private static void writeToFile() {
        AddressBook addressBook = getAddressBook();
        if (addressBook == null) return;
        System.out.print("Enter file name : ");
        String fileName = scanner.nextLine();
        addressBook.writeToFile(fileName);
    }
    private static void readFromFile() {
        AddressBook addressBook = getAddressBook();
        if (addressBook == null) return;
        System.out.print("Enter file name to read : ");
        String fileName = scanner.nextLine();
        addressBook.readFromFile(fileName);
    }
}
