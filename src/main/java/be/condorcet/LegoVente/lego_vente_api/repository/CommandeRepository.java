package be.condorcet.LegoVente.lego_vente_api.repository;

import be.condorcet.LegoVente.lego_vente_api.model.Commande;
import be.condorcet.LegoVente.lego_vente_api.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommandeRepository extends JpaRepository<Commande, Integer> {

    List<Commande> findByUtilisateur(Utilisateur utilisateur);
}