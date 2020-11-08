package br.com.gabrielDias.desafioDeliverIt.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountDTO {
	
	private Long id;

	private String name;
	
	private Double originalValue;
	
	private Double value;
	
	private LocalDateTime expirationDate;
	
	private LocalDateTime paymentDate;
	
	private Long daysDelay;
	
	private Double fined;
	
	private Double interest;
}
