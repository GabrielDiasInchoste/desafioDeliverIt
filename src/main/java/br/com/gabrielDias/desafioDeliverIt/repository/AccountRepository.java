package br.com.gabrielDias.desafioDeliverIt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.gabrielDias.desafioDeliverIt.entity.AccountEntity;

public interface AccountRepository extends JpaRepository<AccountEntity, Integer>{

}
