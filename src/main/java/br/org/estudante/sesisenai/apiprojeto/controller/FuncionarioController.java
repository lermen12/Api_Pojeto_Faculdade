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
@RequestMapping ("/api/funcionario")
public class FuncionarioController {

  private final FuncionarioService service;

  public FuncionarioController(FuncionarioService service) {
    this.service = service;
  }

  @PostMapping
  public ResponseEntity<Funcionario> cadastrarFuncionario(@RequestBody Funcionario funcionario){
        service.salvarFuncionario(funcionario);
        return new ResponseEntity<>(funcionario, HttpStatus.OK);
  }
  @DeleteMapping("/{id}")
  public ResponseEntity<String> excluirFuncionario (@PathVariable Long id){
    service.excluirFuncionario(id);
    return new ResponseEntity<>("Funcionário excluido com sucesso!", HttpStatus.OK);
  }

  @GetMapping
  public ResponseEntity<List<Funcionario>> buscarFuncionarios(){
    List<Funcionario> funcionarios = service.buscarFuncionarios();
    return new ResponseEntity<>(funcionarios, HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Funcionario> buscarFuncionarioPorId(@PathVariable Long id){
    Optional<Funcionario> funcionario = service.buscarFuncionarioPorId(id);
    return new ResponseEntity<>(funcionario.get(), HttpStatus.OK);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Optional<Funcionario>> EditarFuncionario(@RequestBody FuncionarioDTO funcionarioDTO, @PathVariable Long id){
    service.editarFuncionario(id, funcionarioDTO);
    Optional<Funcionario> funcionario = service.buscarFuncionarioPorId(id);
    return new ResponseEntity<>(funcionario, HttpStatus.OK);
  }
  @GetMapping("/nome")
  public ResponseEntity<List<Funcionario>> buscarFuncionariosPorNome(@RequestParam String nome){
    List<Funcionario> funcionarios = service.buscarPorNomeFuncionario(nome);
    return new ResponseEntity<>(funcionarios, HttpStatus.OK);
  }
}
