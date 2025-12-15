package be.condorcet.LegoVente.lego_vente_api.repository;

import be.condorcet.LegoVente.lego_vente_api.model.Instruction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstructionRepository extends JpaRepository<Instruction, Integer> {
}
