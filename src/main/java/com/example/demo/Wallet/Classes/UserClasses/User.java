package com.example.demo.Wallet.Classes.UserClasses;

import javax.persistence.*;

@Entity
@Table(name = "User", uniqueConstraints={@UniqueConstraint(columnNames = "emailID"),
        @UniqueConstraint(columnNames = "userName"),
        @UniqueConstraint(columnNames = "mobileNumber")})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long userID;


    private String userName;
    private Long mobileNumber;
    private String emailID;

    private String firstName;
    private String lastName;
    private String address1;
    private String address2;


    public void getDetailsFromUserWithoutId(UserWithoutId userWithoutId){
        this.setMobileNumber(userWithoutId.getMobileNumber());
        this.setUserName(userWithoutId.getUserName());
        this.setLastName(userWithoutId.getLastName());
        this.setFirstName(userWithoutId.getFirstName());
        this.setEmailID(userWithoutId.getEmailID());
        this.setAddress1(userWithoutId.getAddress1());
        this.setAddress2(userWithoutId.getAddress2());
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public void setMobileNumber(Long mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public long getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(long mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getEmailID() {
        return emailID;
    }

    public void setEmailID(String emailID) {
        this.emailID = emailID;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }
}
/*
 {
         "userName": "Sapna_Goyal",
         "mobileNumber": 8197639827,
         "emailID": "sg@gmail.com",
         "firstName": "Sapna",
         "lastName" : "Goyal",
         "address1": "25, Urban Estate",
         "address2": "same"
         }
 */