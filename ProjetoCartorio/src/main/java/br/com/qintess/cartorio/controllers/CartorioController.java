package br.com.qintess.cartorio.controllers;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


import br.com.qintess.cartorio.models.Cartorio;
import br.com.qintess.cartorio.repositorios.CartorioRepositorio;

@Controller
public class CartorioController {
	
	@Autowired
	private CartorioRepositorio sr;
	private Object cartorioservice;

	@RequestMapping(value="/cadastrarCartorio",method= RequestMethod.GET)
	public String form() {
		return "views/cartorio/formCartorio";
	}

	@RequestMapping(value="/cadastrarCartorio",method= RequestMethod.POST)
	public String form(Cartorio cartorio) {	
	sr.save(cartorio);
		return "redirect:cadastrarCartorio";
	}

	@RequestMapping(value="/cartorio")
	public ModelAndView listServicos() {
		ModelAndView mv = new ModelAndView("views/cartorio/novoIndex");
		Iterable<Cartorio> cartorios = sr.findAll();
		mv.addObject("cartorios", cartorios);
		return mv;
	}
	
	@RequestMapping(value="/{id}",method= RequestMethod.GET)
	public ModelAndView detalheCartorio(@PathVariable("id") int id) {		
		Optional<Cartorio> cartorios= sr.findById(id);
		ModelAndView mv = new ModelAndView("views/cartorio/detalheCartorio");
		mv.addObject("cartorios", cartorios);
		System.out.println("cartorios"+ cartorios);
		return mv;
			
	}

//	============= Métodos para Pesquisa ==========

	@GetMapping("/search")
	public ModelAndView search() {
		ModelAndView mv = new ModelAndView("search");
		mv.addObject("cartorio", new Cartorio());
		return mv;
	}

	@GetMapping("/search-concluidos")
	public ModelAndView cartorioConcluidos() {
		ModelAndView mv = new ModelAndView("search-concluidos");
		mv.addObject("cartorio", new Cartorio());
		mv.addObject("CartorioConcluidos", this.listServicos()
		return mv;
	}
	

	@GetMapping("/search-All")
	public ModelAndView searchAll(@RequestParam(defaultValue = "1") int page) {
		ModelAndView mv = new ModelAndView("search-all");
		mv.addObject("cartorio", new Cartorio());
		Pageable pagreq = PageRequest.of(page - 1, 6, Sort.by("nome"));
		Page<Cartorio> paginaResult = this.cartorioservice.allCartorio(pagreq);
		mv.addObject("allCartorio", paginaResult);
		return mv;
	}

	@PostMapping("search-result")
	public ModelAndView resultPesquisa(@RequestParam(required = false) String nome) {
		ModelAndView mv = new ModelAndView("search-result");
		List<Cartorio> nomeUsuario;

		if (nome == null || nomeUsuario.trim().isEmpty()) {
			nome = this.cartorio.listarTodosCartorio(Sort.by("nome"));
		} else {
			nome = this.cartorio.buscaCartorio(nome);
		}

		mv.addObject("nome", nome);
		return mv;
	}

//	============ MÉTODO PARA REMOÇÃO DO CARTORIO ==========

	@GetMapping("excluir-cartorio")
	public ModelAndView removerCartorio(@RequestParam Integer id, @RequestParam(defaultValue = "1") int page) {
		ModelAndView mv = new ModelAndView("search-all");
		mv.addObject("cartorio", new Cartorio());
		mv.addObject("msgExclusao", "Cartorio Excluido");
		Pageable pagreq = PageRequest.of(page - 1, 6, Sort.by("nome"));
		Page<Cartorio> paginaResult = this.cartorioservice.allCartorio(pagreq);
		mv.addObject("allCartorio", paginaResult);
		this.cartorioservice.excluirCartorio(id);
		return mv;
	}

//	============ MÉTODO PARA EDIÇÃO DO CARTORIO ==========

	@GetMapping("editar-cartorio")
	public ModelAndView editarCartorio(@RequestParam Integer id) {
		ModelAndView mv = new ModelAndView("Cadastro-edit");
		mv.addObject("cartorio", new Cartorio());
		this.cartorioservice.editarCartorio(id);
		this.cartorioservice.excluirCartorio(id);
		mv.addObject("msgedit", "editado com sucesso");
		return mv;
	}


//	====== INSIRIR USUÁRIO =====
	@GetMapping("/cadastro-Cartorio")
	public ModelAndView usercadastro(@ModelAttribute UserLogin cartorio) {
		ModelAndView mv = new ModelAndView("user-cadastro");
		mv.addObject("cartorio", new UserLogin());
		return mv;
	}
	
	@PostMapping("user-Cartorio")
	public ModelAndView cadastrocartorio(@ModelAttribute UserLogin cartorio) {
		ModelAndView mv = new ModelAndView("user-Cartorio");
		mv.addObject("cartorio", cartorio);
		try {
			this.cartorioservice.salvarCartorio(cartorio);
		} catch (Exception e) {
			mv.addObject("erro", e.getMessage());
		}
		mv.addObject("cartorio", "cartoriocriada");
		return mv;
	}
//	======= FINALIZAR SESSÃO ========= 
	@PostMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
	
}
	
