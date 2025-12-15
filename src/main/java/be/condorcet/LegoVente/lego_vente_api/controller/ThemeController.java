package be.condorcet.LegoVente.lego_vente_api.controller;

import be.condorcet.LegoVente.lego_vente_api.model.Theme;
import be.condorcet.LegoVente.lego_vente_api.service.ThemeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/theme")
public class ThemeController {

    private final ThemeService themeService;

    public ThemeController(ThemeService themeService) {
        this.themeService = themeService;
    }

    @PostMapping("/add")
    public ResponseEntity<Theme> addTheme(@RequestBody Theme theme) {
        return new ResponseEntity<>(themeService.createTheme(theme), HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public List<Theme> getAllThemes() {
        return themeService.getAllThemes();
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable int id, @RequestBody Theme theme) {
        try {
            return new ResponseEntity<>(themeService.updateTheme(id, theme), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        themeService.deleteTheme(id);
        return new ResponseEntity<>("Thème supprimé avec succès", HttpStatus.OK);
    }
}