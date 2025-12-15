package be.condorcet.LegoVente.lego_vente_api.controller;

import be.condorcet.LegoVente.lego_vente_api.model.Utilisateur;
import be.condorcet.LegoVente.lego_vente_api.service.UtilisateurService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/utilisateur")
public class UtilisateurController {

    private final UtilisateurService utilisateurService;

    public UtilisateurController(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> inscription(@RequestBody Utilisateur utilisateur) {
        try {
            Utilisateur nouveau = utilisateurService.inscrire(utilisateur);
            return new ResponseEntity<>(nouveau, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> loginData) {
        try {
            String email = loginData.get("email");
            String password = loginData.get("motDePasse"); // ou "password" selon ton JSON

            if (email == null || password == null) {
                return new ResponseEntity<>("Email et mot de passe requis", HttpStatus.BAD_REQUEST);
            }

            Utilisateur user = utilisateurService.seConnecter(email, password);
            return new ResponseEntity<>(user, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>("Erreur de connexion : " + e.getMessage(), HttpStatus.UNAUTHORIZED);
        }
        
    }
}