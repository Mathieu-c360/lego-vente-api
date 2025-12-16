package be.condorcet.LegoVente.lego_vente_api.service;

import be.condorcet.LegoVente.lego_vente_api.exception.RessourceIntrouvableException; // 👈 Import Important !
import be.condorcet.LegoVente.lego_vente_api.model.Article;
import be.condorcet.LegoVente.lego_vente_api.model.Theme;
import be.condorcet.LegoVente.lego_vente_api.repository.ArticleRepository;
import be.condorcet.LegoVente.lego_vente_api.repository.ThemeRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final ThemeRepository themeRepository;

    // Injection par constructeur (plus propre)
    public ArticleService(ArticleRepository articleRepository, ThemeRepository themeRepository) {
        this.articleRepository = articleRepository;
        this.themeRepository = themeRepository;
    }

    public Article createArticle(Article article, int themeId) throws Exception {
        Theme theme = themeRepository.findById(themeId)
                .orElseThrow(() -> new RessourceIntrouvableException("Theme introuvable avec l'ID " + themeId));
        
        article.setTheme(theme);
        return articleRepository.save(article);
    }

    public List<Article> getAllArticles() {
        return articleRepository.findAll();
    }
  
    public Article getArticle(int id) {
        return articleRepository.findById(id)
            .orElseThrow(() -> new RessourceIntrouvableException("Aucun article avec l'ID : " + id));
    }
}