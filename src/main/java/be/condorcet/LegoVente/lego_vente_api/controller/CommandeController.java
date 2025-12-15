package be.condorcet.LegoVente.lego_vente_api.controller;

import be.condorcet.LegoVente.lego_vente_api.model.Commande;
import be.condorcet.LegoVente.lego_vente_api.service.CommandeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/commande")
public class CommandeController {

    private final CommandeService commandeService;

    public CommandeController(CommandeService commandeService) {
        this.commandeService = commandeService;
    }
    @PostMapping("/add")
    public ResponseEntity<?> addCommande(@RequestBody Commande commandeJson) {
        try {
     
            if (commandeJson.getUtilisateur() == null || commandeJson.getArticle() == null) {
                return new ResponseEntity<>("Utilisateur et Article requis", HttpStatus.BAD_REQUEST);
            }

            int userId = commandeJson.getUtilisateur().getId();
            int articleId = commandeJson.getArticle().getId();
            int quantite = commandeJson.getQuantite(); 
            Commande nouvelleCommande = commandeService.commander(userId, articleId, quantite);
            
            return new ResponseEntity<>(nouvelleCommande, HttpStatus.CREATED);

        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}