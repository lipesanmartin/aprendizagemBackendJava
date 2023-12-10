package br.com.aprendizagem.api.repository;

import br.com.aprendizagem.api.entity.Atividade;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AtividadeRepository extends JpaRepository<Atividade, Long> {

    @Query("SELECT a from Atividade a WHERE a.status != -1")
    List<Atividade> getActiveAtividades();

    @Query("SELECT a FROM Atividade a WHERE a.id NOT IN (SELECT acompanhamento.atividade.id FROM Acompanhamento acompanhamento)")
    List<Atividade> getAtividadesSemAcompanhamento();
}
