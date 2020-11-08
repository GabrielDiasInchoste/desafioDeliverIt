package br.com.gabrielDias.desafioDeliverIt.service;

import java.time.Duration;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

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

		listAccountEntity.forEach(accountEntity -> {
			AccountDTO accountDTO = modelMapper.map(accountEntity, AccountDTO.class);
			accountDTO.setValue(getValue(accountDTO.getDaysDelay(), accountDTO));
			response.getListAccounts().add(accountDTO);
			
		});
		log.info("AccountService.getAccounts - end - ");

		return response;
	}

	@Override
	public AccountDTO postAccount(AccountRequestDTO accountRequestDTO) {
		log.info("AccountService.postAccount - start - ");

		AccountEntity accountEntity = modelMapper.map(accountRequestDTO, AccountEntity.class);

		Long daysDelay = Duration.between(accountRequestDTO.getExpirationDate(), accountRequestDTO.getPaymentDate()).toDays();
		log.debug(String.format("AccountService.postAccount - daysDelay = %s", daysDelay.toString()));

		if (daysDelay > 0 && daysDelay <= 3) {

			accountEntity.setFined(2D);
			accountEntity.setInterest(0.1);
			accountEntity.setDaysDelay(daysDelay);
		} else if (daysDelay > 3 && daysDelay <= 5) {
			accountEntity.setFined(3D);
			accountEntity.setInterest(0.2);
			accountEntity.setDaysDelay(daysDelay);
		} else if (daysDelay > 5){
			accountEntity.setFined(5D);
			accountEntity.setInterest(0.3);
			accountEntity.setDaysDelay(daysDelay);

		}		
		AccountDTO response = modelMapper.map(accountEntity, AccountDTO.class);
		response.setValue(getValue(daysDelay, response));

		accountRepository.save(accountEntity);
		log.info("AccountService.postAccount - end - ");
		return response;
	}

	private Double getValue(Long days, AccountDTO accountDTO) {
		if(ObjectUtils.isEmpty(accountDTO.getFined())) {
			return null;
		}
		Double value = accountDTO.getOriginalValue();
		value += (accountDTO.getFined() / 100) * value;
		value += ((accountDTO.getInterest() * days) / 100) * value;
		
		return value;
	}

}
