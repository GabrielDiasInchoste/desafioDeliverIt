package br.com.gabrielDias.desafioDeliverIt.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountsResponseDTO {

	private List<AccountDTO> listAccounts = new ArrayList<>();
}
