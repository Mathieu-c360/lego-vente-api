package be.condorcet.LegoVente.lego_vente_api.model;

import jakarta.persistence.*;

@Entity
public class Instruction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String description; 
    
    @ManyToOne
    @JoinColumn(name = "article_id")
    private Article article;

    public Instruction() {
    }

    public Instruction( String description, Article article) {

        this.description = description;
        this.article = article;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }
}