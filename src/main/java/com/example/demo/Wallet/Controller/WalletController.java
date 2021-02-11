package com.example.demo.Wallet.Controller;
import com.example.demo.Wallet.Classes.RequestClass;
import com.example.demo.Wallet.Classes.Transaction;
import com.example.demo.Wallet.Classes.TransferDetails;
import com.example.demo.Wallet.Classes.Wallet;
import com.example.demo.Wallet.Repositories.UserRepository;
import com.example.demo.Wallet.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hospital")
public class WalletController {


    @Autowired
    WalletService walletService;

    @Autowired
    UserRepository userRepository;

    @RequestMapping(method = RequestMethod.GET,value = "/hospital/Hello")
    public String helloWorld(){
        return "hello";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/wallet")
    public String createWallet(@RequestBody RequestClass request){
        if(userRepository.findByMobileNumber(request.getPhoneNumber()).isEmpty())
            return "Phone Number does not exists.";
        try {
           Wallet wallet = walletService.createWallet(request);
            return "Wallet Created Successfully" + " with User Id: "+wallet.getUserId() ;

        }
        catch (NullPointerException n) {
            return "Wallet with this Phone number already exists";}
        }

    @RequestMapping(method = RequestMethod.GET, value = "/transaction")
    public Transaction.Status getTransactionStatus(@RequestParam Long txnId){
        return walletService.getTransactionStatus(txnId);
       }

    @RequestMapping(method = RequestMethod.GET, value = "/transaction/")
    public Page<Transaction> getTransactionSummary(@RequestParam Long userId, @RequestParam int pageNo){
        return walletService.getTransactionSummary(userId, pageNo);
    }


    @RequestMapping(method = RequestMethod.PUT,value = "/transaction")
    public String transferMoney(@RequestBody TransferDetails transferDetails){
        return walletService.transferMoney(transferDetails.getPayeePhoneNumber(),transferDetails.getPayerPhoneNumber(),transferDetails.getAmount());
    }



}
