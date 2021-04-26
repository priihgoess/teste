package br.com.qintess.cartorio.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Servico {


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@OneToMany
	private List<Usuario> usuario;

	@OneToMany
	private List<Cartorio> cartorio;

	private String data;

	private String horas;

	private String servico;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<Usuario> getUsuario() {
		return usuario;
	}

	public void setUsuario(List<Usuario> usuario) {
		this.usuario = usuario;
	}

	public List<Cartorio> getCartorio() {
		return cartorio;
	}

	public void setCartorio(List<Cartorio> cartorio) {
		this.cartorio = cartorio;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getHoras() {
		return horas;
	}

	public void setHoras(String horas) {
		this.horas = horas;
	}

	public String getServico() {
		return servico;
	}

	public void setServico(String servico) {
		this.servico = servico;
	}

	

	@Override
	public String toString() {
		return "Servico [id=" + id + ", usuario=" + usuario + ", cartorio=" + cartorio + ", data=" + data + ", horas="
				+ horas + ", servico=" + servico + "]";
	}
	
	

}
