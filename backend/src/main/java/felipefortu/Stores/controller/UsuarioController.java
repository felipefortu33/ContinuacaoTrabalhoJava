package felipefortu.Stores.controller;

import felipefortu.Stores.domain.entity.Usuario;
import felipefortu.Stores.domain.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/v1/usuario")
@Slf4j
@RequiredArgsConstructor
@CrossOrigin("*")
public class UsuarioController {
    private final UsuarioService usuarioService;
    private final UsuarioMapper mapper;

    @PostMapping
    public ResponseEntity save(
            @RequestParam String username,
            @RequestParam String senha

    ) throws IOException {
        log.info("username {}, password {}", username, senha);
        Usuario usuario = mapper.mapToUsuario(username, senha);
        Usuario savedUsuario = usuarioService.save(usuario);


        return ResponseEntity.ok().build();
    }
}
