package be.condorcet.LegoVente.lego_vente_api.service;

import be.condorcet.LegoVente.lego_vente_api.model.Utilisateur;
import be.condorcet.LegoVente.lego_vente_api.repository.UtilisateurRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UtilisateurService {

    private final UtilisateurRepository utilisateurRepository;
    private final PasswordEncoder passwordEncoder;


    public UtilisateurService(UtilisateurRepository utilisateurRepository, PasswordEncoder passwordEncoder) {
        this.utilisateurRepository = utilisateurRepository;
        this.passwordEncoder = passwordEncoder;
    }


    public Utilisateur inscrire(Utilisateur utilisateur) throws Exception {

        if (utilisateurRepository.findByEmail(utilisateur.getEmail()).isPresent()) {
            throw new Exception("Cet email est déjà utilisé !");
        }

        String motDePasseHash = passwordEncoder.encode(utilisateur.getMotDePasse());
        utilisateur.setMotDePasse(motDePasseHash);


        if (utilisateur.getRole() == null || utilisateur.getRole().trim().isEmpty()) {
            utilisateur.setRole("USER");
        }


        return utilisateurRepository.save(utilisateur);
    }


    public Utilisateur seConnecter(String email, String motDePasseEnClair) throws Exception {

        Utilisateur user = utilisateurRepository.findByEmail(email)
                .orElseThrow(() -> new Exception("Email incorrect ou utilisateur inconnu"));

        if (!passwordEncoder.matches(motDePasseEnClair, user.getMotDePasse())) {
            throw new Exception("Mot de passe incorrect");
        }


        return user;
    }


    public List<Utilisateur> getAllUtilisateurs() {
        return utilisateurRepository.findAll();
    }



    public Utilisateur findById(int id) throws Exception {
        return utilisateurRepository.findById(id)
                .orElseThrow(() -> new Exception("Utilisateur introuvable avec l'ID : " + id));
    }


    public Optional<Utilisateur> findByEmail(String email) {
        return utilisateurRepository.findByEmail(email);
    }
}