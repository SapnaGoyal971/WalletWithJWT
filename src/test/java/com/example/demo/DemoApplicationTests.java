package com.example.demo;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import com.example.demo.Wallet.Classes.WalletClasses.RequestClass;
import com.example.demo.Wallet.Classes.WalletClasses.Transaction;
import com.example.demo.Wallet.Repositories.WalletRepositories.TransactionRepository;
import com.example.demo.Wallet.Repositories.WalletRepositories.WalletRepository;
import com.example.demo.Wallet.service.WalletService;
import org.junit.Before;
import static org.hamcrest.Matchers.containsString;

import com.example.demo.Wallet.Classes.WalletClasses.Wallet;
import com.fasterxml.jackson.databind.ObjectMapper;
//import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class DemoApplicationTests {


	@Autowired
	private WalletService walletService;

	@MockBean
	private TransactionRepository transactionRepository;

	@MockBean
	private WalletRepository walletRepository;

/*	@Before
	public void setUp(){
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}


	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@MockBean
	private WalletService walletService;

	ObjectMapper objectMapper=new ObjectMapper();

	@Test
	public void createWalletControllerTest() throws Exception {
		RequestClass request=new RequestClass();
		request.setPhoneNumber(1L);
		String jsonRequest=objectMapper.writeValueAsString(request);
		Wallet wallet =new Wallet(1L);

		Mockito.when(walletService.createWallet(request)).thenReturn(wallet);

		this.mockMvc.perform(post("/wallet").contentType(MediaType.APPLICATION_JSON)
				.content(jsonRequest))
				.andExpect(status().isOk()).andExpect(content().string(containsString("Wallet Created")));
	}
*/


	@Test
	public void getTransactionStatusTest(){
		Long txn=62L;
		Transaction trans=new Transaction(1L,2L,0L);
		trans.setStatus(Transaction.Status.FAILURE);
		Mockito.when(transactionRepository.findByTxnID(txn)).thenReturn(trans);

		assertEquals(Transaction.Status.FAILURE,walletService.getTransactionStatus(txn));

	}


	@Test
	public void createWalletTest(){
		Wallet wallet=new Wallet(1L);

		Mockito.when(walletRepository.save(wallet)).thenReturn(wallet);

		assertEquals(wallet,walletService.createWallet(wallet));
	}


	/*@Test
	void contextLoads() {
	}*/



/*	@Test
	public void getTransactionStatusTest() throws Exception {

		Transaction trans=new Transaction(1L,2L,0L);
		trans.setStatus(Transaction.Status.FAILURE);

		Mockito.when(walletService.getTransactionStatus(1L)).thenReturn(trans.getStatus());

		mockMvc.perform(get("/transaction/1L"))
				.andExpect(status().isOk());
				.andExpect((ResultMatcher) jsonPath("$.Status", trans.getStatus()));
}*/

}