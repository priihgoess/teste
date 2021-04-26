package br.com.qintess.cartorio.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Usuario {

	@Id 
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private String documento;

	private String nomeUsuario;

	private String enderenco;
	
	private String telefone;
	
	private String login;

	private String senha;

	@ManyToOne
	private Cartorio cartorio;
	
	@ManyToOne
	private Servico servico;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	public String getNome() {
		return nomeUsuario;
	}

	public void setNome(String nome) {
		this.nomeUsuario = nome;
	}

	public String getEnderenco() {
		return enderenco;
	}

	public void setEnderenco(String enderenco) {
		this.enderenco = enderenco;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getLongin() {
		return login;
	}

	public void setLongin(String longin) {
		this.login = longin;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Cartorio getCartorio() {
		return cartorio;
	}

	public void setCartorio(Cartorio cartorio) {
		this.cartorio = cartorio;
	}

	public Servico getServico() {
		return servico;
	}

	public void setServico(Servico servico) {
		this.servico = servico;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", documento=" + documento + ", nome=" + nomeUsuario + ", enderenco=" + enderenco
				+ ", telefone=" + telefone + ", longin=" + login + ", senha=" + senha + ", cartorio=" + cartorio
				+ ", servico=" + servico + "]";
	}

	}