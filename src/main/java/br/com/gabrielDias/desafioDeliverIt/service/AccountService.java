package br.com.gabrielDias.desafioDeliverIt.service;

import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gabrielDias.desafioDeliverIt.dto.AccountDTO;
import br.com.gabrielDias.desafioDeliverIt.dto.AccountRequestDTO;
import br.com.gabrielDias.desafioDeliverIt.dto.AccountsResponseDTO;
import br.com.gabrielDias.desafioDeliverIt.entity.AccountEntity;
import br.com.gabrielDias.desafioDeliverIt.repository.AccountRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AccountService implements AccountInterface {

	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public AccountsResponseDTO getAccounts() throws Exception {
		log.info("AccountService.getAccounts - start - ");
		AccountsResponseDTO response = new AccountsResponseDTO();

		List<AccountEntity> listAccountEntity = accountRepository.findAll();
		if (listAccountEntity.isEmpty()) {
			log.error("AccountService.getAccounts - Accounts Not Fount - ");
			throw new Exception("Accounts Not Fount");
		}

		listAccountEntity.forEach(
				accountEntity -> response.getListAccounts().add(modelMapper.map(accountEntity, AccountDTO.class)));
		log.info("AccountService.getAccounts - end - ");

		return response;
	}

	@Override
	public AccountDTO postAccount(AccountRequestDTO accountRequestDTO) {
		log.info("AccountService.postAccount - start - ");

		AccountEntity accountEntity = modelMapper.map(accountRequestDTO, AccountEntity.class);

		Long days = Duration.between(accountRequestDTO.getExpirationDate(), accountRequestDTO.getPaymentDate()).toDays();
		log.debug(String.format("AccountService.postAccount - days = %s", days.toString()));

		if (days > 0 && days <= 3) {
			
			accountEntity.setFined(2D);
			accountEntity.setInterest(0.1);
		} else if (days > 3) {
			accountEntity.setFined(3D);
			accountEntity.setInterest(0.2);
		}else {
			accountEntity.setFined(5D);
			accountEntity.setInterest(0.3);
		}
		accountRepository.save(accountEntity);
		log.info("AccountService.postAccount - end - ");

		return modelMapper.map(accountEntity, AccountDTO.class);
	}

}
