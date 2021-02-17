package com.example.demo.Wallet.Controller;
import com.example.demo.Wallet.Classes.WalletClasses.RequestClass;
import com.example.demo.Wallet.Classes.WalletClasses.Transaction;
import com.example.demo.Wallet.Classes.WalletClasses.TransferDetails;
import com.example.demo.Wallet.Classes.WalletClasses.Wallet;
import com.example.demo.Wallet.Repositories.UserRepositories.UserRepository;
import com.example.demo.Wallet.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.logging.*;


@RestController
@RequestMapping("/hospital")

public class WalletController {
    private static final Logger LOGGER = Logger.getLogger(WalletController.class.getName());

    Handler fileHandler = new FileHandler("C:\\Users\\sapna.goyal\\wallet.log");

    @Autowired
    KafkaTemplate<String, TransferDetails> kafkaTemplate;

    @Autowired
    WalletService walletService;

    @Autowired
    UserRepository userRepository;

    public WalletController() throws IOException {
    }

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
        LOGGER.addHandler(fileHandler);
        SimpleFormatter simpleFormatter = new SimpleFormatter();
        fileHandler.setFormatter(simpleFormatter);

        LOGGER.log(Level.INFO,"Consumed Json Message of TransferDetails: "+transferDetails);
      //  System.out.println("Consumed Json Message of TransferDetails: "+ transferDetails);
        return transferDetails;
    }

    public TransferDetails getTransferDetailsFromKafkaConsumer(){
        return t;
    }

    @RequestMapping(method = RequestMethod.PUT,value = "/elastic/transaction")
    public String transferMoneyThroughElastic(@RequestBody TransferDetails transferDetails) throws InterruptedException {
        LOGGER.addHandler(fileHandler);
        SimpleFormatter simpleFormatter = new SimpleFormatter();
        fileHandler.setFormatter(simpleFormatter);

        kafkaTemplate.send(TOPIC,transferDetails);
        TimeUnit.SECONDS.sleep(2);

        TransferDetails transferDetailsFromKafkaConsumer=getTransferDetailsFromKafkaConsumer();
        LOGGER.log(Level.INFO,"Transfer Details from Kafka Consumer in controller: "+transferDetailsFromKafkaConsumer.getAmount() + " "+
                transferDetailsFromKafkaConsumer.getPayeePhoneNumber()+ " "+transferDetailsFromKafkaConsumer.getPayerPhoneNumber() );

      /*  System.out.println(transferDetailsFromKafkaConsumer.getAmount());
        System.out.println(transferDetailsFromKafkaConsumer.getPayeePhoneNumber());
        System.out.println(transferDetailsFromKafkaConsumer.getPayerPhoneNumber());*/

        return walletService.transferMoneyThroughElastic(transferDetailsFromKafkaConsumer.getPayeePhoneNumber(),
          transferDetailsFromKafkaConsumer.getPayerPhoneNumber(),
          transferDetailsFromKafkaConsumer.getAmount());
    }



}
