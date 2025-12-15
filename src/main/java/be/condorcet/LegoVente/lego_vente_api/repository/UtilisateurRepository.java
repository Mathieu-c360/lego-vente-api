package be.condorcet.LegoVente.lego_vente_api.repository;

import be.condorcet.LegoVente.lego_vente_api.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Integer> {
    Optional<Utilisateur> findByEmail(String email);
    
    boolean existsByEmail(String email);
}
