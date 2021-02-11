package com.example.demo.Wallet.Classes.UserClasses;

//This class is created because at adding user details time we want user's details without his id and will be generating id for him.
//This is not actual class. Actual class is User which we are mapping to table and performing CRUD operations.
//This is class without user id.

public class UserWithoutId {
    private String userName;
    private Long mobileNumber;
    private String emailID;

    private String firstName;
    private String lastName;
    private String address1;
    private String address2;


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Long getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(Long mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getEmailID() {
        return emailID;
    }

    public void setEmailID(String emailID) {
        this.emailID = emailID;
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
