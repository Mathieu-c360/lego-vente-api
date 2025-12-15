package be.condorcet.LegoVente.lego_vente_api.service;

import be.condorcet.LegoVente.lego_vente_api.exception.RessourceIntrouvableException;
import be.condorcet.LegoVente.lego_vente_api.model.*;
import be.condorcet.LegoVente.lego_vente_api.repository.*;
import org.springframework.stereotype.Service;

@Service
public class CommandeService {

    private final CommandeRepository commandeRepository;
    private final UtilisateurRepository utilisateurRepository;
    private final ArticleRepository articleRepository;


    public CommandeService(CommandeRepository commandeRepository, 
                           UtilisateurRepository utilisateurRepository, 
                           ArticleRepository articleRepository) {
        this.commandeRepository = commandeRepository;
        this.utilisateurRepository = utilisateurRepository;
        this.articleRepository = articleRepository;
    }

    public Commande commander(int utilisateurId, int articleId, int quantite) throws Exception { 

    Utilisateur user = utilisateurRepository.findById(utilisateurId)
            .orElseThrow(() -> new RessourceIntrouvableException("Utilisateur inconnu"));
    
    Article article = articleRepository.findById(articleId)
            .orElseThrow(() -> new RessourceIntrouvableException("Article inconnu"));


    if (article.getStock() < quantite) {
         throw new Exception("Stock insuffisant !");
    }


    Commande c = new Commande();
    c.setUtilisateur(user);
    c.setArticle(article);
    c.setQuantite(quantite); 
    c.setDateCommande(java.time.LocalDate.now());

    article.setStock(article.getStock() - quantite);
    articleRepository.save(article);

    return commandeRepository.save(c);
}
}