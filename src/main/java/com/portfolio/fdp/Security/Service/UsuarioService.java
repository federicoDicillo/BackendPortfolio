
package com.portfolio.fdp.Security.Service;

import com.portfolio.fdp.Security.Entity.Usuario;
import com.portfolio.fdp.Security.Repository.IUsuarioRepository;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UsuarioService {
    @Autowired
    IUsuarioRepository iusuarioRepo;
    
    public Optional<Usuario> getByNombreUsuario(String nombreUsuario){
        return iusuarioRepo.findByNombreUsuario(nombreUsuario);
    }
    
    public boolean existsByNombreUsuario(String nombreUsuario){
        return iusuarioRepo.existsByNombreUsuario(nombreUsuario);
    }
    
    public boolean existsByEmail(String email){
        return iusuarioRepo.existsByEmail(email);
    }
    
    public void save(Usuario usuario){
        iusuarioRepo.save(usuario);
    }
}
