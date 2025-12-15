package be.condorcet.LegoVente.lego_vente_api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // 1. On attrape NOTRE exception personnalisée (404 Not Found)
    @ExceptionHandler(RessourceIntrouvableException.class)
    public ResponseEntity<Map<String, Object>> handleRessourceIntrouvable(RessourceIntrouvableException ex) {
        Map<String, Object> errorBody = new HashMap<>();
        errorBody.put("timestamp", LocalDateTime.now());
        errorBody.put("message", ex.getMessage());
        errorBody.put("status", HttpStatus.NOT_FOUND.value());

        return new ResponseEntity<>(errorBody, HttpStatus.NOT_FOUND);
    }

    // 2. On attrape toutes les autres erreurs (400 Bad Request)
    // Par exemple : mot de passe incorrect, stock épuisé, etc.
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGlobalException(Exception ex) {
        Map<String, Object> errorBody = new HashMap<>();
        errorBody.put("timestamp", LocalDateTime.now());
        errorBody.put("message", ex.getMessage());
        errorBody.put("status", HttpStatus.BAD_REQUEST.value());

        return new ResponseEntity<>(errorBody, HttpStatus.BAD_REQUEST);
    }
}
