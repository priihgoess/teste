package br.com.qintess.cartorio.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.qintess.cartorio.models.Servico;

public interface ServicoRepositorio extends JpaRepository<Servico, Integer>{
	
}
