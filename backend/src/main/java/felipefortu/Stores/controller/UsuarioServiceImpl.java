package felipefortu.Stores.controller;

import felipefortu.Stores.domain.entity.Usuario;
import felipefortu.Stores.domain.service.UsuarioService;
import felipefortu.Stores.infra.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;

    @Override
    @Transactional
    public Usuario save(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }
}


