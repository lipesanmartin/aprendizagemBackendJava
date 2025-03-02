package br.com.aprendizagem.api.repository;

import br.com.aprendizagem.api.DTO.ChapterAssuntoComentarioDto;
import br.com.aprendizagem.api.entity.ChapterAssuntoComentario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ChapterAssuntoComentarioRepository extends JpaRepository<ChapterAssuntoComentario, Integer> {

    @Query("SELECT cac FROM ChapterAssuntoComentario cac WHERE cac.id = :id")
    ChapterAssuntoComentario getChapterAssuntoComentarioById(Long id);

    @Query("SELECT cac FROM ChapterAssuntoComentario cac WHERE cac.chapterAssunto.id = :chapterAssuntoId")
    List<ChapterAssuntoComentario> findByChapterAssuntoId(Integer chapterAssuntoId);

    @Query("SELECT cac FROM ChapterAssuntoComentario cac WHERE cac.chapterAssunto.id = :chapterAssuntoId and cac.comentarioPai is null")
    List<ChapterAssuntoComentario> findByChapterAssuntoIdAndComentarioPaiNull(Integer chapterAssuntoId);

//    @Query("SELECT cac FROM ChapterAssuntoComentario cac WHERE cac.comentarioPai = null")
//    List<ChapterAssuntoComentario> findAllComentarioPai();
//
//    @Query("SELECT cac FROM ChapterAssuntoComentario cac WHERE cac.comentarioPai = null and cac.chapterAssunto.id = :chapterAssuntoId")
//    List<ChapterAssuntoComentario> findAllComentarioPaiByChapterAssuntoId(Integer chapterAssuntoId);
//
    @Query("SELECT cac FROM ChapterAssuntoComentario cac WHERE cac.comentarioPai.id = :comentarioPaiId")
    List<ChapterAssuntoComentario> getFilhosByPaiId(Long comentarioPaiId);
}
