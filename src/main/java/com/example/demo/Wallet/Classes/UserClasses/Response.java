package com.example.demo.Wallet.Classes.UserClasses;

//It is created because in case of get_user_details we have to return only 3 details(username,
// mobileNumber, emailId) out of all the details

public class Response {
    private String firstName;
    private String lastName;
    private Long mobileNumber;
    private String emailID;

    public void fillDetailsFromUser(User user){
        this.setEmailID(user.getEmailID());
        this.setFirstName(user.getFirstName());
        this.setLastName(user.getLastName());
        this.setMobileNumber(user.getMobileNumber());
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
}
