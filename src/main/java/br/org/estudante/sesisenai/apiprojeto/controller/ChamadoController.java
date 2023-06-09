package br.org.estudante.sesisenai.apiprojeto.controller;

import br.org.estudante.sesisenai.apiprojeto.dto.ChamadoDTO;
import br.org.estudante.sesisenai.apiprojeto.dto.FuncionarioChamamdoDTO;
import br.org.estudante.sesisenai.apiprojeto.entity.Chamado;
import br.org.estudante.sesisenai.apiprojeto.service.ChamadoService;
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
@RequestMapping("/api/chamado")
public class ChamadoController {

  private final ChamadoService service;

  public ChamadoController(ChamadoService service) {
    this.service = service;
  }

  @PostMapping
  public ResponseEntity<Chamado> cadastrarChamado(@RequestBody Chamado chamado, @RequestParam Long id) {
    try {
      service.salvarChamado(chamado, id);
      return new ResponseEntity<>(chamado, HttpStatus.OK);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<String> excluirChamado(@PathVariable Long id) {
    try {
      service.excluirChamado(id);
    } catch (Exception e) {
      return new ResponseEntity<>("Chamado não encontrado", HttpStatus.BAD_REQUEST);
    }
    return new ResponseEntity<>("Chamado excluido com sucesso!", HttpStatus.OK);
  }

  @GetMapping
  public ResponseEntity<List<Chamado>> buscarChamados() {
    try {
      List<Chamado> chamados = service.buscarChamados();
      return new ResponseEntity<>(chamados, HttpStatus.OK);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @GetMapping("/{id}")
  public ResponseEntity<Chamado> buscarChamadoPorId(@PathVariable Long id) {
    try {
      Optional<Chamado> chamado = service.buscarChamadoPorId(id);
      return new ResponseEntity<>(chamado.get(), HttpStatus.OK);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @PutMapping("/{id}")
  public ResponseEntity<String> EditarChamado(@RequestBody ChamadoDTO chamadoDTO, @PathVariable Long id) {
    try {
      service.editarChamado(chamadoDTO, id);
    } catch (Exception e) {
      return new ResponseEntity<>("Chamado não encontrado!", HttpStatus.BAD_REQUEST);
    }
    return new ResponseEntity<>("Chamado alterado com sucesso!", HttpStatus.OK);
  }

  @GetMapping("/funcionario/{id}")
  public ResponseEntity<List<FuncionarioChamamdoDTO>> listarChamadoPorFuncionario(@PathVariable Long id) {
    try {
      List<FuncionarioChamamdoDTO> dtoList = service.ListarChamadoPorFuncionario(id);
      return new ResponseEntity<>(dtoList, HttpStatus.OK);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
