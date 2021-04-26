package br.com.qintess.cartorio.models;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Cartorio{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String nome;

	private String certidoes;

	private String endereco;

	private String telefone;

	@OneToMany
	private List<Usuario> usuario;

	@ManyToOne
	private Servico servico;

	public List<Usuario> getUsuario() {
		return usuario;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCertidoes() {
		return certidoes;
	}

	public void setCertidoes(String certidoes) {
		this.certidoes = certidoes;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Servico getServico() {
		return servico;
	}

	public void setServico(Servico servico) {
		this.servico = servico;
	}

	public void setUsuario(List<Usuario> usuario) {
		this.usuario = usuario;
	}

	@Override
	public String toString() {
		return "Cartorio [id=" + id + ", nome=" + nome + ", certidoes=" + certidoes + ", endereco=" + endereco
				+ ", telefone=" + telefone + ", usuario=" + usuario + ", servico=" + servico + "]";
	}

	

}