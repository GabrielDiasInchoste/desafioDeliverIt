package br.com.gabrielDias.desafioDeliverIt.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ACCOUNT")
public class AccountEntity implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ACCOUNT_ID_SEQ")
	@SequenceGenerator(name = "ACCOUNT_ID_SEQ", sequenceName = "ACCOUNT_ID_SEQ", allocationSize = 1)
	@Column(name = "ACCOUNT_ID")
	private Long id;

	@Column(name = "NAME")
	private String name;
	
	@Column(name = "ORIGINAL_VALUE")
	private Double originalValue;
	
	@Column(name = "EXPIRATION_DATE")
	private LocalDateTime expirationDate;
	
	@Column(name = "PAYMENT_DATE")
	private LocalDateTime paymentDate;
	
	@Column(name = "DAYS_DELAY")
	private Long daysDelay;
	
	@Column(name = "FINED")
	private Double fined;
	
	@Column(name = "INTEREST")
	private Double interest;
	
}
