package be.condorcet.LegoVente.lego_vente_api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Le nom de l'article est obligatoire")
    private String nom;

    @Min(value = 1, message = "Il faut au moins 1 pièce")
    private Integer nombrePieces;

    @NotNull(message = "Le prix est obligatoire")
    @DecimalMin(value = "0.0", inclusive = false, message = "Le prix doit être positif")
    private BigDecimal prix;
    private Integer stock;
    @ManyToOne
    @JoinColumn(name = "theme_id")
    private Theme theme;

    public Article() {}

    public Article( String nom, Integer nombrePieces, BigDecimal prix, Theme theme) {
        this.nom = nom;
        this.nombrePieces = nombrePieces;
        this.prix = prix;
        this.theme = theme;
    }
    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    public Integer getNombrePieces() { return nombrePieces; }
    public void setNombrePieces(Integer nombrePieces) { this.nombrePieces = nombrePieces; }
    public BigDecimal getPrix() { return prix; }
    public void setPrix(BigDecimal prix) { this.prix = prix; }
    public Theme getTheme() { return theme; }
    public void setTheme(Theme theme) { this.theme = theme; }
}