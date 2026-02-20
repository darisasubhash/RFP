package com.addressbook;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AddressBookSys {

    private Map<String, AddressBook> addressBookMap = new HashMap<>();


    // UC-6 Add new Address Book
    public void addAddressBook(String name) {
        if (addressBookMap.containsKey(name)) {
            System.out.println("Address Book already exists!");
        } else {
            addressBookMap.put(name, new AddressBook());
            System.out.println("Address Book '" + name + "' created successfully!");
        }
    }

    public AddressBook getAddressBook(String name) {
        return addressBookMap.get(name);
    }

    public void displayAddressBooks() {
        if (addressBookMap.isEmpty()) {
            System.out.println("No Address Books available ");
        } else {
            System.out.println("Available Address Books : ");
            for (String name : addressBookMap.keySet()) {
                System.out.println("  " + name);
            }
        }
    }
    //UC-8 Search by City or State
    public List<Contact> searchPersonByCity(String city) {

        return addressBookMap.values().stream()
                .flatMap(addressBook -> addressBook.getContactList().stream())
                .filter(contact -> contact.getCity().trim().equalsIgnoreCase(city.trim()))
                .collect(Collectors.toList());
    }
    public List<Contact> searchPersonByState(String state) {

        return addressBookMap.values().stream()
                .flatMap(addressBook -> addressBook.getContactList().stream())
                .filter(contact -> contact.getState().trim().equalsIgnoreCase(state.trim()))
                .collect(Collectors.toList());
    }
    //UC-9 storing as dictionary
    public Map<String,List<Contact>> viewPersonByCity(){
        return addressBookMap.values().stream()
                .flatMap(addressBook -> addressBook.getContactList().stream())
                .collect(Collectors.groupingBy(Contact::getCity));
    }
    public Map<String,List<Contact>> viewPersonByState(){
        return addressBookMap.values().stream()
                .flatMap(addressBook -> addressBook.getContactList().stream())
                .collect(Collectors.groupingBy(Contact::getCity));
    }

    //UC-10 count by city or state
    public Map<String, Long> countPersonsByCity() {
        return addressBookMap.values().stream()
                .flatMap(addressBook -> addressBook.getContactList().stream())
                .collect(Collectors.groupingBy(Contact::getCity,Collectors.counting()));
    }
    public Map<String, Long> countPersonsByState() {
        return addressBookMap.values().stream()
                .flatMap(book -> book.getContactList().stream())
                .collect(Collectors.groupingBy(Contact::getState,Collectors.counting()));
    }
}
