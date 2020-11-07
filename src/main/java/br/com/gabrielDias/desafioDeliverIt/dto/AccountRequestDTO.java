package br.com.gabrielDias.desafioDeliverIt.dto;

import java.time.ZonedDateTime;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountRequestDTO {

	@NotBlank(message = "Nome é Obrigatorio")
	private String name;

	@NotBlank(message = "Valor Original é Obrigatorio")
	private Double originalValue;

	@NotBlank(message = "Data do Vencimento é Obrigatorio")
	private ZonedDateTime expirationDate;

	@NotBlank(message = "Data do Pagamento é Obrigatorio")
	private ZonedDateTime paymentDate;
}
