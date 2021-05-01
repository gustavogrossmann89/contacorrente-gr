package com.gr.contacorrente.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gr.contacorrente.model.Pessoa;
import com.gr.contacorrente.service.PessoaService;

import io.swagger.annotations.ApiOperation;

/**
 * Controller para Pessoa
 * @author gustavogrossmann
 *
 */
@RestController
@RequestMapping(value="/api")
@CrossOrigin(origins="*")
public class PessoaController {

	@Autowired private PessoaService pessoaService;
	
	@ApiOperation(value = "Buscar pessoas")
	@GetMapping("/pessoas")
	public ResponseEntity<List<Pessoa>> getAll(){
		return ResponseEntity.ok().body(pessoaService.getAll());
	}
	
	@ApiOperation(value = "Buscar pessoa")
	@GetMapping("/pessoas/{id}")
	public ResponseEntity<Pessoa> getPessoa(@PathVariable long id){
		return ResponseEntity.ok().body(pessoaService.findById(id));
	}
}
