package felipefortu.Stores.controller;

import felipefortu.Stores.domain.entity.Usuario;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class UsuarioMapper {

    public Usuario mapToUsuario(String username , String password) throws IOException {
        return Usuario
                .builder()
                .username(username)
                .senha(password)
                .build();
    }
}
