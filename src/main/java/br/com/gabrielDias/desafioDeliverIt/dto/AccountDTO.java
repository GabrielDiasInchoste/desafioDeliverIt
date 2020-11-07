package br.com.gabrielDias.desafioDeliverIt.dto;

import java.time.ZonedDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountDTO {
	
	private Integer id;

	private String name;
	
	private Double originalValue;
	
	private ZonedDateTime expirationDate;
	
	private ZonedDateTime paymentDate;
	
	private Integer daysDelay;
	
	private Double fined;
	
	private Double interest;
}
