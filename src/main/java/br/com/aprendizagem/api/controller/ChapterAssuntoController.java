package br.com.aprendizagem.api.controller;

import br.com.aprendizagem.api.entity.ChapterAssunto;
import br.com.aprendizagem.api.entity.ChapterTag;
import br.com.aprendizagem.api.service.ChapterAssuntoService;
import br.com.aprendizagem.api.service.ChapterTagService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("chapter-assunto")
@CrossOrigin
public class ChapterAssuntoController {

    private final ChapterAssuntoService chapterAssuntoService;
    private final ChapterTagService chapterTagService;

    @GetMapping
    public ResponseEntity<List<ChapterAssunto>> getAllChapterAssunto() {
        return chapterAssuntoService.getAllChapterAssunto();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ChapterAssunto> getChapterAssuntoWithTags(@PathVariable Integer id) {
        ChapterAssunto chapterAssunto = chapterAssuntoService.getChapterAssuntoWithTags(id);
        if (chapterAssunto != null) {
            return ResponseEntity.ok(chapterAssunto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{id}/associar-tags")
    public ResponseEntity<Void> associarTagAAssunto(@PathVariable Integer id, @RequestBody List<Integer> tagIds) {
        ChapterAssunto chapterAssunto = chapterAssuntoService.getChapterAssuntoWithTags(id);
        if (chapterAssunto != null) {
            chapterAssuntoService.associarTagAAssunto(chapterAssunto, tagIds);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }

    }
}
