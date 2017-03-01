package com.example.vishal.waterreports.model;

/**
 * Created by Team 42, CS2430 Spring 2017
 *
 * Information Holder - represents a single actor in model
 *
 */

public class Actor {

    public String name;
    public final String EMAIL;
    public String homeAddress;
    public AccountType accountType;
    /* attributes for collection of Water Reports */

    /**
     * Create a new actor
     * @param name          the name of the actor
     * @param EMAIL         the email address of the actor
     * @param homeAddress   the home address of the actor
     * @param accountType   the type of account of the actor
     */
    public Actor(String name, String EMAIL, String homeAddress,
                   AccountType accountType) {
        this.name = name;
        this.EMAIL = EMAIL;
        this.homeAddress = homeAddress;
        this.accountType = accountType;
    }

//    /* Getters and setters */
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    /* No setter for this because EMAIL is a read only field */
//    public String getEmail() {
//        return EMAIL;
//    }
//
//    public String getHomeAddress() {
//        return homeAddress;
//    }
//
//    public void setHomeAddress(String homeAddress) {
//        this.homeAddress = homeAddress;
//    }
//
//    public AccountType getAccountType() {
//        return accountType;
//    }
//
//    public void setAccountType(AccountType accountType) {
//        this.accountType = accountType;
//    }
//
//    /**
//     * String representation of the actor and its attributes
//     * @return the string to be displayed
//     */
//    @Override
//    public String toString() {
//        return accountType + ": " + name + ", " + EMAIL + ", " + homeAddress;
//    }

    /* To-be-implemented app usage methods */

}