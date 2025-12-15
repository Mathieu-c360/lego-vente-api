package be.condorcet.LegoVente.lego_vente_api.service;

import be.condorcet.LegoVente.lego_vente_api.model.Utilisateur;
import be.condorcet.LegoVente.lego_vente_api.repository.UtilisateurRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {

    private final UtilisateurRepository utilisateurRepository;
    public MyUserDetailsService(UtilisateurRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Optional<Utilisateur> userOpt = utilisateurRepository.findByEmail(email);

        if (userOpt.isEmpty()) {
            throw new UsernameNotFoundException("Utilisateur introuvable");
        }

        Utilisateur user = userOpt.get();


        return User.builder()
                .username(user.getEmail())
                .password(user.getMotDePasse())
                .roles(user.getRole()) 
                .build();
    }
}