package br.com.gabrielDias.desafioDeliverIt;

import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import br.com.gabrielDias.desafioDeliverIt.controller.AccountController;
import br.com.gabrielDias.desafioDeliverIt.dto.AccountDTO;
import br.com.gabrielDias.desafioDeliverIt.dto.AccountRequestDTO;
import br.com.gabrielDias.desafioDeliverIt.dto.AccountsResponseDTO;
import br.com.gabrielDias.desafioDeliverIt.entity.AccountEntity;
import br.com.gabrielDias.desafioDeliverIt.repository.AccountRepository;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class AccountTest {

	@Autowired
	private AccountController accountController;

	@MockBean
	private AccountRepository accountRepository;

	@Before
	public void setup() {
		MockHttpServletRequest request = new MockHttpServletRequest();
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
	}

	@Test
	public void getAccountTest() throws Exception {

		when(accountRepository.findAll()).thenReturn(Collections.singletonList(new AccountEntity()));

		ResponseEntity<AccountsResponseDTO> responseEntity = accountController.getAccounts();

		Assert.assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
	}

	@Test
	public void getAccountTestException() throws Exception {

		when(accountRepository.findAll()).thenReturn(new ArrayList<>());
		Assertions.assertThrows(Exception.class, () -> accountController.getAccounts());

	}

	@Test
	public void postAccountDaysDelay3Test() throws Exception {

		when(accountRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(new AccountEntity()));

		ResponseEntity<AccountDTO> responseEntity = accountController.postAccount(
				new AccountRequestDTO("Teste", 100D, LocalDateTime.now(), LocalDateTime.now().plusDays(3)));

		Assert.assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
	}

	@Test
	public void postAccountDaysDelay5Test() throws Exception {

		when(accountRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(new AccountEntity()));

		ResponseEntity<AccountDTO> responseEntity = accountController.postAccount(
				new AccountRequestDTO("Teste", 100D, LocalDateTime.now(), LocalDateTime.now().plusDays(5)));

		Assert.assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
	}

	@Test
	public void postAccountDaysDelay6Test() throws Exception {

		when(accountRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(new AccountEntity()));

		ResponseEntity<AccountDTO> responseEntity = accountController.postAccount(
				new AccountRequestDTO("Teste", 100D, LocalDateTime.now(), LocalDateTime.now().plusDays(6)));

		Assert.assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
	}
}
