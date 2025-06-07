package TpFinal_Progra3.security.services;


import TpFinal_Progra3.security.repositories.CredencialRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    private final CredencialRepository credencialRepository;

    public UserDetailsService(CredencialRepository credencialRepository) {
        this.credencialRepository = credencialRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return credencialRepository.findByEmail(username).orElseThrow();
    }

}
