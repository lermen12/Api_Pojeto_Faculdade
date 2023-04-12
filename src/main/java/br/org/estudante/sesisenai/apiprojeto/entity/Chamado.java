package br.org.estudante.sesisenai.apiprojeto.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import java.util.Date;
import lombok.Data;

@Data
@Entity
public class Chamado {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private Date data;
  private String assunto;
  private String discricaoProblema;
  @OneToOne
  private Funcionario funcionario;

}
