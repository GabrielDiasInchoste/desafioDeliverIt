package br.com.gabrielDias.desafioDeliverIt.service;

import br.com.gabrielDias.desafioDeliverIt.dto.AccountDTO;
import br.com.gabrielDias.desafioDeliverIt.dto.AccountRequestDTO;
import br.com.gabrielDias.desafioDeliverIt.dto.AccountsResponseDTO;

public interface AccountInterface {

	AccountsResponseDTO getAccounts() throws Exception;

	AccountDTO postAccount(AccountRequestDTO accountRequestDTO);

}
