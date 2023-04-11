package br.org.estudante.sesisenai.apiprojeto.service;

import br.org.estudante.sesisenai.apiprojeto.dto.ChamadoDTO;
import br.org.estudante.sesisenai.apiprojeto.entity.Chamado;
import br.org.estudante.sesisenai.apiprojeto.entity.Funcionario;
import br.org.estudante.sesisenai.apiprojeto.repository.ChamadoRepository;
import br.org.estudante.sesisenai.apiprojeto.repository.FuncionarioRepository;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class ChamadoService {

  private final ChamadoRepository chamadoRepository;
  private final FuncionarioRepository funcionarioRepository;


  public ChamadoService(ChamadoRepository chamadoRepository, FuncionarioRepository funcionarioRepository) {
    this.chamadoRepository = chamadoRepository;
    this.funcionarioRepository = funcionarioRepository;
  }

  public Chamado salvarChamado(Chamado chamado, long id){
  Optional<Funcionario> funcionario = funcionarioRepository.findById(id);
  chamado.setData(new Date());
  chamado.setFuncionario(funcionario.get());

    chamadoRepository.save(chamado);
    return chamado;
  }

  public void excluirChamado (Long id){
    chamadoRepository.deleteById(id);
  }
  public List<Chamado> buscarChamados(){
    return chamadoRepository.findAll();
  }
  public Optional<Chamado> buscarChamadoPorId (Long id){
    return chamadoRepository.findById(id);
  }
  public void editarChamado(ChamadoDTO chamadoDTO, Long id){
    Optional<Chamado> chamado = chamadoRepository.findById(id);
    if (Optional.ofNullable(chamado).isPresent()){
      chamado.get().setAssunto(chamadoDTO.getAssunto());
      chamado.get().setDiscricaoProblema(chamadoDTO.getDiscricaoProblema());
      chamadoRepository.save(chamado.get());
    }
  }
}
