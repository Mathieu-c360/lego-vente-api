package be.condorcet.LegoVente.lego_vente_api.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Commande {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private LocalDate dateCommande;


    private Integer quantite;

    @ManyToOne
    @JoinColumn(name = "utilisateur_id")
    private Utilisateur utilisateur;


    @ManyToOne
    @JoinColumn(name = "article_id")
    private Article article;


    public Commande() {
        this.dateCommande = LocalDate.now(); 
    }

    public Commande(LocalDate dateCommande, int quantite, Utilisateur utilisateur, Article article) {
        this.dateCommande = dateCommande;
        this.quantite = quantite;
        this.utilisateur = utilisateur;
        this.article = article;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getDateCommande() {
        return dateCommande;
    }

    public void setDateCommande(LocalDate dateCommande) {
        this.dateCommande = dateCommande;
    }

    public Integer getQuantite() {
        return quantite;
    }

    public void setQuantite(Integer quantite) {
        this.quantite = quantite;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }
}