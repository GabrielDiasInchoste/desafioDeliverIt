package br.com.gabrielDias.desafioDeliverIt.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.gabrielDias.desafioDeliverIt.dto.AccountDTO;
import br.com.gabrielDias.desafioDeliverIt.dto.AccountRequestDTO;
import br.com.gabrielDias.desafioDeliverIt.dto.AccountsResponseDTO;
import br.com.gabrielDias.desafioDeliverIt.service.AccountInterface;

@RestController
@RequestMapping("/v1/")
public class AccountController {

	
	@Autowired
	private AccountInterface accountInterface;
	
	@GetMapping(value = "account/list", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AccountsResponseDTO> getAccounts() throws Exception {

		return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(accountInterface.getAccounts());
	}
	
	@PostMapping(value = "account", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AccountDTO> postAccount(@RequestBody @Valid AccountRequestDTO accountRequestDTO) throws Exception {

		return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(accountInterface.postAccount(accountRequestDTO));
	}
	
}
