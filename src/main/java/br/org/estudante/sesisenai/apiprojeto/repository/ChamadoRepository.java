package br.org.estudante.sesisenai.apiprojeto.repository;

import br.org.estudante.sesisenai.apiprojeto.entity.Chamado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChamadoRepository extends JpaRepository<Chamado, Long> {

}
