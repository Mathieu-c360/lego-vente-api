package be.condorcet.LegoVente.lego_vente_api.service;

import be.condorcet.LegoVente.lego_vente_api.model.Theme;
import be.condorcet.LegoVente.lego_vente_api.repository.ThemeRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ThemeService {

   private final ThemeRepository themeRepository;

    public ThemeService(ThemeRepository themeRepository) {
        this.themeRepository = themeRepository;
    }

    public Theme createTheme(Theme theme) {
        return themeRepository.save(theme);
    }

    public List<Theme> getAllThemes() {
        return themeRepository.findAll();
    }
    

    public Theme getThemeById(int id) throws Exception {
        return themeRepository.findById(id)
                .orElseThrow(() -> new Exception("Thème introuvable id: " + id));
    }

    public Theme updateTheme(int id, Theme themeModifie) throws Exception {
        Theme themeExistant = themeRepository.findById(id)
                .orElseThrow(() -> new Exception("Thème introuvable"));
        

        themeExistant.setNom(themeModifie.getNom());
        
        return themeRepository.save(themeExistant);
    }


    public void deleteTheme(int id) {
        themeRepository.deleteById(id);
    }
}