package br.com.qintess.cartorio.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.qintess.cartorio.models.Usuario;

public interface UsuarioRepositorio extends JpaRepository<Usuario, Integer> {


}
