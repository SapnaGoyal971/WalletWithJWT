package com.example.demo.Wallet.Controller;
import com.example.demo.Kafka.listener.KafkaConsumer;
import com.example.demo.Kafka.model.User;
import com.example.demo.Wallet.Classes.RequestClass;
import com.example.demo.Wallet.Classes.Transaction;
import com.example.demo.Wallet.Classes.TransferDetails;
import com.example.demo.Wallet.Classes.Wallet;
import com.example.demo.Wallet.Repositories.UserRepository;
import com.example.demo.Wallet.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/hospital")
public class WalletController {

    @Autowired
    KafkaTemplate<String, TransferDetails> kafkaTemplate;

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

    private static final String TOPIC="test";

    TransferDetails t=new TransferDetails();
    @KafkaListener(topics = "test", groupId ="group_json", containerFactory = "transferDetailsKafkaListenerFactory")
    public TransferDetails consumeTransferDetailsJson(TransferDetails transferDetails){
     t=transferDetails;
        System.out.println("Consumed Json Message of TransferDetails: "+ transferDetails);
        return transferDetails;
    }

    public TransferDetails getTransferDetailsFromKafkaConsumer(){
        return t;
    }

    @RequestMapping(method = RequestMethod.PUT,value = "/elastic/transaction")
    public String transferMoneyThroughElastic(@RequestBody TransferDetails transferDetails) throws InterruptedException {

        kafkaTemplate.send(TOPIC,transferDetails);
        TimeUnit.SECONDS.sleep(2);

        TransferDetails transferDetailsFromKafkaConsumer=getTransferDetailsFromKafkaConsumer();
        System.out.println(transferDetailsFromKafkaConsumer.getAmount());
        System.out.println(transferDetailsFromKafkaConsumer.getPayeePhoneNumber());
        System.out.println(transferDetailsFromKafkaConsumer.getPayerPhoneNumber());

      //  return "hii";
        return walletService.transferMoneyThroughElastic(transferDetailsFromKafkaConsumer.getPayeePhoneNumber(),
          transferDetailsFromKafkaConsumer.getPayerPhoneNumber(),
          transferDetailsFromKafkaConsumer.getAmount());
    }



}
