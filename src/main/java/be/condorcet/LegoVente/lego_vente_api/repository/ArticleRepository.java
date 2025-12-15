package be.condorcet.LegoVente.lego_vente_api.repository;

import be.condorcet.LegoVente.lego_vente_api.model.Article;
import be.condorcet.LegoVente.lego_vente_api.model.Theme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Integer> {
    

    List<Article> findByTheme(Theme theme);

    List<Article> findByNomContaining(String motCle);
}