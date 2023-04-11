package br.org.estudante.sesisenai.apiprojeto.service;

import br.org.estudante.sesisenai.apiprojeto.dto.FuncionarioDTO;
import br.org.estudante.sesisenai.apiprojeto.entity.Funcionario;
import br.org.estudante.sesisenai.apiprojeto.repository.FuncionarioRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class FuncionarioService {


  private final FuncionarioRepository funcionarioRepository;

  public FuncionarioService(FuncionarioRepository funcionarioRepository) {
    this.funcionarioRepository = funcionarioRepository;
  }

  public void salvarFuncionario(Funcionario funcionario){
    funcionarioRepository.save(funcionario);
  }

  public void excluirFuncionario(Long id){
    funcionarioRepository.deleteById(id);
  }

  public List<Funcionario> buscarFuncionarios(){
    return funcionarioRepository.findAll();
  }
  public Optional<Funcionario> buscarFuncionarioPorId(Long id){
    return funcionarioRepository.findById(id);
    }
  public void editarFuncionario (Long id, FuncionarioDTO funcionarioDTO){
    Optional<Funcionario> funcionario = funcionarioRepository.findById(id);
    funcionario.get().setNome(funcionarioDTO.getNome());
    funcionario.get().setEmail(funcionarioDTO.getEmail());
    funcionario.get().setSetor(funcionarioDTO.getSetor());
    funcionarioRepository.save(funcionario.get());
  }
  public List<Funcionario> buscarPorNomeFuncionario(String nome){
    return funcionarioRepository.findAllByNomeContainingIgnoreCase(nome);
  }
}


