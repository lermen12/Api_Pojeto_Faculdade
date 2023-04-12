package br.org.estudante.sesisenai.apiprojeto.controller;

import br.org.estudante.sesisenai.apiprojeto.dto.FuncionarioDTO;
import br.org.estudante.sesisenai.apiprojeto.entity.Funcionario;
import br.org.estudante.sesisenai.apiprojeto.service.FuncionarioService;
import java.util.List;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/funcionario")
public class FuncionarioController {

  private final FuncionarioService service;

  public FuncionarioController(FuncionarioService service) {
    this.service = service;
  }

  @PostMapping
  public ResponseEntity<Funcionario> cadastrarFuncionario(@RequestBody Funcionario funcionario) {
    try {
      service.salvarFuncionario(funcionario);
      return new ResponseEntity<>(funcionario, HttpStatus.OK);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<String> excluirFuncionario(@PathVariable Long id) {
    try {
      service.excluirFuncionario(id);
    } catch (Exception e) {
      return new ResponseEntity<>("Funcionário não encontrado!", HttpStatus.BAD_REQUEST);
    }
    return new ResponseEntity<>("Funcionário excluido com sucesso!", HttpStatus.OK);
  }

  @GetMapping
  public ResponseEntity<List<Funcionario>> buscarFuncionarios() {
    try {
      List<Funcionario> funcionarios = service.buscarFuncionarios();
      return new ResponseEntity<>(funcionarios, HttpStatus.OK);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @GetMapping("/{id}")
  public ResponseEntity<Funcionario> buscarFuncionarioPorId(@PathVariable Long id) {
    try {
      Optional<Funcionario> funcionario = service.buscarFuncionarioPorId(id);
      return new ResponseEntity<>(funcionario.get(), HttpStatus.OK);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @PutMapping("/{id}")
  public ResponseEntity<Optional<Funcionario>> EditarFuncionario(@RequestBody FuncionarioDTO funcionarioDTO, @PathVariable Long id) {
    try {
      service.editarFuncionario(id, funcionarioDTO);
      Optional<Funcionario> funcionario = service.buscarFuncionarioPorId(id);
      return new ResponseEntity<>(funcionario, HttpStatus.OK);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @GetMapping("/nome")
  public ResponseEntity<List<Funcionario>> buscarFuncionariosPorNome(@RequestParam String nome) {
    try {
      List<Funcionario> funcionarios = service.buscarPorNomeFuncionario(nome);
      return new ResponseEntity<>(funcionarios, HttpStatus.OK);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
