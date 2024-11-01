package felipefortu.Stores.controller;

import felipefortu.Stores.domain.service.RelatorioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/produto/relatorio")
@RequiredArgsConstructor
public class RelatorioController {
    private final RelatorioService relatorioService;

    @GetMapping
    public ResponseEntity<List<RelatorioProdutoDTO>> getRelatorio() {
        try {
            List<RelatorioProdutoDTO> relatorios = relatorioService.gerarRelatorio();
            return ResponseEntity.ok(relatorios);
        } catch (Exception e) {
            // Log the exception (consider using a logging framework)
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }
}
