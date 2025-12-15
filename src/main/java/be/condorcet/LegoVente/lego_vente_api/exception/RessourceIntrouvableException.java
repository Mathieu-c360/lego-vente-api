package be.condorcet.LegoVente.lego_vente_api.exception;

public class RessourceIntrouvableException extends RuntimeException {
    public RessourceIntrouvableException(String message) {
        super(message);
    }
}