package com.addressbook;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class AddressBook {
    private List<Contact> contactList = new ArrayList<>();

    public void addContact(Contact contact) {
        if(contactList.contains(contact)){
            System.out.println("Person already present ");
        }
        else {
            contactList.add(contact);
            System.out.println("Contact added successfully ");
        }
    }
    public void editContact(String name, String address, String city,
                            String state, String zip,
                            String phoneNumber, String email) {
        for (Contact contact : contactList) {
            if (contact.getFirstName().equalsIgnoreCase(name)) {
                contact.setAddress(address);
                contact.setCity(city);
                contact.setState(state);
                contact.setZip(zip);
                contact.setPhoneNumber(phoneNumber);
                contact.setEmail(email);
                System.out.println("Contact updated successfully ");
                return;
            }
        }
        System.out.println("Contact not found ");
    }
    public void deleteContact(String name) {
        for (Contact contact : contactList) {
            if (contact.getFirstName().equalsIgnoreCase(name)) {
                contactList.remove(contact);
                System.out.println("Contact deleted successfully ");
                return;
            }
        }
        System.out.println("Contact not found ");
    }

    public List<Contact> getContactList() {
        return contactList;
    }
    // UC-11  Sort  alphabetically
    public List<Contact> sortContactsByName() {
        return contactList.stream()
                .sorted(Comparator.comparing(Contact::getFirstName,String.CASE_INSENSITIVE_ORDER))
                .collect(Collectors.toList());
    }
    //UC-12 Sort by city,state,zip
    public List<Contact> sortContactsByCity() {
        return contactList.stream()
                .sorted(Comparator.comparing(Contact::getCity,String.CASE_INSENSITIVE_ORDER))
                .collect(Collectors.toList());
    }
    public List<Contact> sortContactsByState() {
        return contactList.stream()
                .sorted(Comparator.comparing(Contact::getState,String.CASE_INSENSITIVE_ORDER))
                .collect(Collectors.toList());
    }
    public List<Contact> sortContactsByZip() {
        return contactList.stream()
                .sorted(Comparator.comparing(Contact::getZip))
                .collect(Collectors.toList());
    }
    //UC-13 read or write using File IO
    public void writeToFile(String filename){
        try(BufferedWriter writer=new BufferedWriter(new FileWriter(filename))){
            for(Contact contact:contactList){
                writer.write(contact.getFirstName()+","+contact.getLastName()+","+contact.getAddress()+
                        ","+contact.getCity()+","+contact.getState()+","+contact.getZip()+
                        ","+contact.getPhoneNumber()+ ","+contact.getEmail());
            }
            System.out.println("AddressBook saved to file successfully..");
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    public void readFromFile(String filename){
        String line;
        try(BufferedReader reader=new BufferedReader(new FileReader(filename))){
            while((line= reader.readLine())!=null){
                String[] data=line.split(",");
                if(data.length>=4){
                    Contact contact=new Contact(data[0],data[1],data[2],data[3],data[4],data[5],data[6],data[7]);
                    contactList.add(contact);
                }
            }
            System.out.println("AddressBook loaded from file sucessfully..");
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    // UC-14 read and write to csv file
    public void writeToCSV(String fileName) {
        try (CSVWriter writer = new CSVWriter(new FileWriter(fileName))) {
            String[] header = {"FirstName","LastName","Address","City","State","Zip","PhoneNumber","Email"};
            writer.writeNext(header);
            for (Contact contact : contactList) {
                String[] data = {
                        contact.getFirstName(),contact.getLastName(),contact.getAddress(), contact.getCity(),
                        contact.getState(),contact.getZip(),contact.getPhoneNumber(),contact.getEmail()};
                writer.writeNext(data);
            }
            System.out.println("Address Book saved as CSV successfully!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public void readFromCSV(String fileName){
        try(CSVReader reader = new CSVReader(new FileReader(fileName))){
            String[] line;
            reader.readNext();
            while ((line = reader.readNext())!=null){
                Contact contact=new Contact(line[0],line[1],line[2],line[3],line[4],line[5],line[6],line[7]);
                contactList.add(contact);
            }
            System.out.println("AddressBook loaded from CSV successfully...");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
