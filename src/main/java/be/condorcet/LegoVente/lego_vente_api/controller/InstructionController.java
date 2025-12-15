package be.condorcet.LegoVente.lego_vente_api.controller;

import be.condorcet.LegoVente.lego_vente_api.model.Instruction;
import be.condorcet.LegoVente.lego_vente_api.service.InstructionService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/instruction")
public class InstructionController {

    private final InstructionService instructionService;

    public InstructionController(InstructionService instructionService) {
        this.instructionService = instructionService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody @Valid Instruction instruction, @RequestParam int articleId) {
        try {
            return new ResponseEntity<>(instructionService.creerInstruction(instruction, articleId), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/all")
    public List<Instruction> list() {
        return instructionService.getAll();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        instructionService.supprimerInstruction(id);
        return new ResponseEntity<>("Instruction supprimée", HttpStatus.OK);
    }
}