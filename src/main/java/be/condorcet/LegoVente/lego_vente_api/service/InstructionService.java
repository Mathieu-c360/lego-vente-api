package be.condorcet.LegoVente.lego_vente_api.service;

import be.condorcet.LegoVente.lego_vente_api.model.Article;
import be.condorcet.LegoVente.lego_vente_api.model.Instruction;
import be.condorcet.LegoVente.lego_vente_api.repository.ArticleRepository;
import be.condorcet.LegoVente.lego_vente_api.repository.InstructionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InstructionService {

    private final InstructionRepository instructionRepository;
    private final ArticleRepository articleRepository;

    public InstructionService(InstructionRepository instructionRepository, ArticleRepository articleRepository) {
        this.instructionRepository = instructionRepository;
        this.articleRepository = articleRepository;
    }

    public Instruction creerInstruction(Instruction instruction, int articleId) throws Exception {
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new Exception("Article introuvable"));
        
        instruction.setArticle(article);
        return instructionRepository.save(instruction);
    }

    public List<Instruction> getAll() {
        return instructionRepository.findAll();
    }
    public void supprimerInstruction(int id) {
        instructionRepository.deleteById(id);
    }
}