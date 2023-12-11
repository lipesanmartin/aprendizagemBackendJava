package br.com.aprendizagem.api.service;

import br.com.aprendizagem.api.DTO.ChapterAssuntoComentarioDto;
import br.com.aprendizagem.api.entity.ChapterAssuntoComentario;
import br.com.aprendizagem.api.repository.ChapterAssuntoComentarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ChapterAssuntoComentarioService {

    private final ChapterAssuntoComentarioRepository chapterAssuntoComentarioRepository;

    private final ChapterAssuntoService chapterAssuntoService;

    private final UsuarioService usuarioService;

    @Transactional
    public List<ChapterAssuntoComentario> getAllChapterAssuntoComentario() {
        return chapterAssuntoComentarioRepository.findAll();
    }

    @Transactional
    public ChapterAssuntoComentario getChapterAssuntoComentarioById(Long id) {
        ChapterAssuntoComentario chapterAssuntoComentario = chapterAssuntoComentarioRepository.getChapterAssuntoComentarioById(id);
       if (chapterAssuntoComentario != null) {
           return chapterAssuntoComentario;
       } else {
           return null;
       }
    }

    @Transactional
    public ChapterAssuntoComentario postChapterAssuntoComentario(ChapterAssuntoComentarioDto chapterAssuntoComentarioDto) {
        ChapterAssuntoComentario chapterAssuntoComentario = new ChapterAssuntoComentario();
        chapterAssuntoComentario.setTexto(chapterAssuntoComentarioDto.getTexto());
        chapterAssuntoComentario.setData(chapterAssuntoComentarioDto.getData());
        chapterAssuntoComentario.setChapterAssunto(chapterAssuntoService.getChapterAssuntoById(chapterAssuntoComentarioDto.getChapterAssuntoId()));
        chapterAssuntoComentario.setUsuario(usuarioService.getUsuarioById(chapterAssuntoComentarioDto.getUsuarioId()));
        chapterAssuntoComentario.setCurtidas(chapterAssuntoComentarioDto.getCurtidas());
        if (chapterAssuntoComentarioDto.getPaiId() != null) {
            chapterAssuntoComentario.setComentarioPai(getChapterAssuntoComentarioById(chapterAssuntoComentarioDto.getPaiId()));
        }
        return chapterAssuntoComentarioRepository.save(chapterAssuntoComentario);
    }

    @Transactional
    public void deleteChapterAssuntoComentarioById(Integer id) {
        chapterAssuntoComentarioRepository.deleteById(id);
    }

    @Transactional
    public List<ChapterAssuntoComentario> filterByChapterAssuntoId(Integer chapterAssuntoId) {
        return chapterAssuntoComentarioRepository.findByChapterAssuntoId(chapterAssuntoId);
    }

//    public List<ChapterAssuntoComentario> findAllComentarioPai() {
//        return chapterAssuntoComentarioRepository.findAllComentarioPai();
//    }
//
//    public List<ChapterAssuntoComentario> findAllComentarioPaiByChapterAssuntoId(Integer chapterAssuntoId) {
//        return chapterAssuntoComentarioRepository.findAllComentarioPaiByChapterAssuntoId(chapterAssuntoId);
//    }

    @Transactional
    public List<ChapterAssuntoComentarioDto> getAllChapterAssuntoComentarioDto() {
        List<ChapterAssuntoComentario> chapterAssuntoComentarios = chapterAssuntoComentarioRepository.findAll();
        List<ChapterAssuntoComentarioDto> chapterAssuntoComentarioDtos = new ArrayList<>();
        for (ChapterAssuntoComentario chapterAssuntoComentario : chapterAssuntoComentarios) {
            ChapterAssuntoComentarioDto chapterAssuntoComentarioDto = new ChapterAssuntoComentarioDto();
            chapterAssuntoComentarioDtos.add(buildDto(chapterAssuntoComentario, chapterAssuntoComentarioDto));
        }
        return chapterAssuntoComentarioDtos;
    }

    @Transactional
    public List<ChapterAssuntoComentarioDto> getAllChapterAssuntoComentarioDtoByChapterAssuntoId(Integer chapterAssuntoId) {
        List<ChapterAssuntoComentario> chapterAssuntoComentarios = chapterAssuntoComentarioRepository.findByChapterAssuntoId(chapterAssuntoId);
        List<ChapterAssuntoComentarioDto> chapterAssuntoComentarioDtos = new ArrayList<>();
        for (ChapterAssuntoComentario chapterAssuntoComentario : chapterAssuntoComentarios) {
            ChapterAssuntoComentarioDto chapterAssuntoComentarioDto = new ChapterAssuntoComentarioDto();
            chapterAssuntoComentarioDtos.add(buildDto(chapterAssuntoComentario, chapterAssuntoComentarioDto));
        }
        return chapterAssuntoComentarioDtos;
    }

    @Transactional
    public ChapterAssuntoComentario atualizarResposta(Long id, String resposta) {
        ChapterAssuntoComentario chapterAssuntoComentario = chapterAssuntoComentarioRepository.getChapterAssuntoComentarioById(id);
        chapterAssuntoComentario.setTexto(resposta);
        return chapterAssuntoComentarioRepository.save(chapterAssuntoComentario);
    }


    public ChapterAssuntoComentarioDto buildDto(ChapterAssuntoComentario chapterAssuntoComentario, ChapterAssuntoComentarioDto chapterAssuntoComentarioDto) {
        chapterAssuntoComentarioDto.setId(chapterAssuntoComentario.getId());
        chapterAssuntoComentarioDto.setTexto(chapterAssuntoComentario.getTexto());
        chapterAssuntoComentarioDto.setData(chapterAssuntoComentario.getData());
        if (chapterAssuntoComentario.getComentarioPai() != null) {
            chapterAssuntoComentarioDto.setPaiId(chapterAssuntoComentario.getComentarioPai().getId());
        }
        chapterAssuntoComentarioDto.setChapterAssuntoId(chapterAssuntoComentario.getChapterAssunto().getId());
        chapterAssuntoComentarioDto.setUsuario(chapterAssuntoComentario.getUsuario());
        chapterAssuntoComentarioDto.setCurtidas(chapterAssuntoComentario.getCurtidas());
        return chapterAssuntoComentarioDto;
    }

}


