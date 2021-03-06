package com.example.demo.Wallet.Classes.WalletClasses;

public class TransferDetails {
    private Long payeePhoneNumber;
    private Long payerPhoneNumber;
    private Long amount;


    @Override
    public String toString() {
        return "TransferDetails{" +
                "payeePhoneNumber=" + payeePhoneNumber +
                ", payerPhoneNumber=" + payerPhoneNumber +
                ", amount=" + amount +
                '}';
    }

    public Long getPayerPhoneNumber() {
        return payerPhoneNumber;
    }

    public void setPayerPhoneNumber(Long payerPhoneNumber) {
        this.payerPhoneNumber = payerPhoneNumber;
    }

    public Long getPayeePhoneNumber() {
        return payeePhoneNumber;
    }

    public void setPayeePhoneNumber(Long payeePhoneNumber) {
        this.payeePhoneNumber = payeePhoneNumber;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }
}
 /*
 {
         "payeePhoneNumber": 8198877228,
         "payerPhoneNumber": 9914088562,
         "amount": "0"
 }
 */