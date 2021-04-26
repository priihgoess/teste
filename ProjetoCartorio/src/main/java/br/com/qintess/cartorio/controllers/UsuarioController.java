package br.com.qintess.cartorio.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;


import br.com.qintess.cartorio.models.Cartorio;
import br.com.qintess.cartorio.models.Usuario;
import br.com.qintess.cartorio.repositorios.CartorioRepositorio;
import br.com.qintess.cartorio.repositorios.ServicoRepositorio;
import br.com.qintess.cartorio.repositorios.UsuarioRepositorio;


@Controller
public class UsuarioController<UserLogin> {

	
	private static final String Usuario = null;
	@Autowired
	private UsuarioRepositorio ur;
	private Object usuario;
	
	@RequestMapping(value="clientes/cadastroCliente",method= RequestMethod.GET)
	public String cadastroCliente() {
		return "views/clientes/cadastroCliente";
	}

	@RequestMapping(value="clientes/cadastroCliente",method= RequestMethod.POST)
	public String form(Usuario usuario) {	
	ur.save(usuario);
		return "redirect:cadastroCliente";
	}
	
	@RequestMapping(value="/listaClientes")
	public ModelAndView listCliente() {
		ModelAndView mv = new ModelAndView("views/clientes/listaClientes");
		Iterable<Usuario> usuarios = ur.findAll();
		mv.addObject("usuario", usuarios);
		return mv;
	
	}
		@GetMapping(value="/listaClientes")
		public ModelAndView listtagemCliente() {
			ModelAndView mv = new ModelAndView("views/clientes/listaClientes");
			mv.setViewName("usuario/listaClientes");
			("usuario", Usuario);
			return mv;
		}
	

//============= Métodos para Pesquisa ==========

@GetMapping("/search")
public ModelAndView search() {
	ModelAndView mv = new ModelAndView("search");
	mv.addObject("usuario", new Usuario());
	return mv;
}

@GetMapping("/search-concluidos")
public ModelAndView usu7arioConcluidos() {
	ModelAndView mv = new ModelAndView("search-concluidos");
	mv.addObject("usuario", new Usuario());
	mv.addObject("UsuarioConcluidos", this.listCliente());
	return mv;
}


@GetMapping("/search-All")
public ModelAndView searchAll(@RequestParam(defaultValue = "1") int page) {
	ModelAndView mv = new ModelAndView("search-all");
	mv.addObject("usuario", new Usuario());
	Pageable pagreq = PageRequest.of(page - 1, 6, Sort.by("nome"));
	Class<? extends Object> paginaResult = ( this.usuario.getClass());
	mv.addObject("allUsuario", paginaResult);
	return mv;
}

@PostMapping("search-result")
public ModelAndView resultPesquisa(@RequestParam(required = false) String nome) {
	ModelAndView mv = new ModelAndView("search-result");
	List<Usuario> nomeUsuario;

	if (nome == null || nome.trim().isEmpty()) {
		nomeUsuario = this.usuario.listarTodosUsuario(Sort.by("nome"));
	} else {
		nomeUsuario = this.usuario.buscarUsuario(nome);
	}

	mv.addObject("nomeUsuario", nomeUsuario);
	return mv;
}

//============ MÉTODO PARA REMOÇÃO DO USUARIO ==========

@GetMapping("excluir-usuario")
public ModelAndView removerUsuario(@RequestParam Integer id, @RequestParam(defaultValue = "1") int page) {
	ModelAndView mv = new ModelAndView("search-all");
	mv.addObject("usuario", new Usuario());
	mv.addObject("msgExclusao", "Usuario Excluido");
	Pageable pagreq = PageRequest.of(page - 1, 6, Sort.by("nome"));
	Page<Usuario> paginaResult = this.usuario.allUsuario(pagreq);
	mv.addObject("allUsuario", paginaResult);
	this.usuario.excluirUsuario(id);
	return mv;
}

//============ MÉTODO PARA EDIÇÃO DO USUARIO ==========

@GetMapping("editar-usuario")
public ModelAndView editarUsuario(@RequestParam Integer id) {
	ModelAndView mv = new ModelAndView("Cadastro-edit");
	mv.addObject("usuario", new Usuario());
	this.usuario.editarUsuario(id);
	this.usuario.excluirUsuario(id);
	mv.addObject("msgedit", "editado com sucesso");
	return mv;
}

//============ EFETUAR LOGIN ============
@PostMapping("/index")
public ModelAndView login(@ModelAttribute UserLogin user, HttpSession session) {
	ModelAndView mv = new ModelAndView("login");
	mv.addObject("user", new UserLogin());
		UserLogin loginuser;
		try {
			loginuser = this.usuario.efetuarlogin(user.getLogin(), user.getsenha());
			session.setAttribute("userLogado", loginuser);
			session.getAttribute("userLogado");
		} catch (ServiceException e) {
			mv.addObject("msgLoginErro", e.getMessage());
			return mv;
		}
	return index();
}

//====== INSIRIR USUÁRIO =====
@GetMapping("/cadastro-usuario")
public ModelAndView usercadastro(@ModelAttribute UserLogin usuario) {
	ModelAndView mv = new ModelAndView("user-cadastro");
	mv.addObject("usuario", new Object());
	return mv;
}

@PostMapping("user-cadastro")
public ModelAndView cadastrousuario(@ModelAttribute UserLogin usuario) {
	ModelAndView mv = new ModelAndView("user-cadastro");
	mv.addObject("usuario", usuario);
	try {
		this.usuario.salvarUsuario(usuario);
	} catch (Exception e) {
		mv.addObject("erro", e.getMessage());
	}
	mv.addObject("usuario", "usuariocriado");
	return mv;
}
//======= FINALIZAR SESSÃO ========= 
@PostMapping("/logout")
public String logout(HttpSession session) {
	session.invalidate();
	return "redirect:/";
}

}
//	@GetMapping ("/usuario")
//	public List<Usuario> getUsuarios(){
//		return repo.findAll();		
//
//	}
//	@GetMapping("/usuario/{id}")
//	public Usuario listaUsuario(@PathVariable(value="id") int id) {
//		return repo.findById(id);
//
//	}
//	@PostMapping("/usuario")
//	public Usuario criarUsuario(@RequestBody Usuario  usuario) {
//		return repo.save(usuario);
//	}
//	@DeleteMapping("/usuario")
//	public void deletaUsuario(@RequestBody Usuario  usuario) {
//		repo.delete(usuario);
//	}
//	@PutMapping("/usuario")
//	public Usuario atualizaUsuario(@RequestBody Usuario  usuario) {
//		return repo.save(usuario);
//	}

