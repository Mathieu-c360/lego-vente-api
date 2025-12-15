package be.condorcet.LegoVente.lego_vente_api.controller;

import be.condorcet.LegoVente.lego_vente_api.model.Article;
import be.condorcet.LegoVente.lego_vente_api.service.ArticleService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/article") 
public class ArticleController {

    
    private final ArticleService articleService;
    public ArticleController(ArticleService articleService) {
        this.articleService= articleService;
    }
    @PostMapping("/add")
    public ResponseEntity<?> addArticle(@RequestBody Article article) {
        
        try {
            if (article.getTheme() == null) {
                return new ResponseEntity<>("Le thème est obligatoire (ex: \"theme\": {\"id\": 1})", HttpStatus.BAD_REQUEST);
            }
            int themeIdRecupere = article.getTheme().getId();

            Article nouveau = articleService.createArticle(article, themeIdRecupere);
            
            return new ResponseEntity<>(nouveau, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<Article> get(@PathVariable int id) {
        Article a = articleService.getArticle(id);
        return new ResponseEntity<>(a, HttpStatus.OK);
}
}