package br.org.estudante.sesisenai.apiprojeto.repository;

import br.org.estudante.sesisenai.apiprojeto.entity.Funcionario;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {

  List<Funcionario> findAllByNomeContainingIgnoreCase(String nome);

}
