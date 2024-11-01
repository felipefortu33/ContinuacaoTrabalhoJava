package felipefortu.Stores.controller;

import felipefortu.Stores.domain.entity.Produto;
import felipefortu.Stores.domain.enums.ProdutoCategoria;
import felipefortu.Stores.domain.service.ProdutoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/produto")
@Slf4j
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class ProdutoController {
    private final ProdutoService produtoService;
    private final ProdutoMapper mapper;

    @PostMapping
    public ResponseEntity<Void> save(
            @RequestParam("file") MultipartFile file,
            @RequestParam("produto") String produto,
            @RequestParam("preco") String preco,
            @RequestParam("quantidade") String quantidade,
            @RequestParam("categoria") ProdutoCategoria categoria
    ) throws IOException {
        log.info("file {}, produto {}, preco {}, quantidade {}, categoria {}", file, produto, preco, quantidade, categoria);

        Produto produtoM = mapper.mapToProduto(file, produto, preco, quantidade, categoria);
        produtoService.save(produtoM);

        return ResponseEntity.created(buildProdutoURL(produtoM)).build();
    }

    @GetMapping("{id}")
    public ResponseEntity<byte[]> getProduto(@PathVariable String id) {
        var possibleProduto = produtoService.getById(id);
        if (possibleProduto.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        var produto = possibleProduto.get();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.valueOf(getMediaTypeForCategoria(produto.getCategoria())));
        headers.setContentLength(produto.getSize());
        headers.setContentDispositionFormData("inline; filename=\"" + produto.getFileName() + "\"", produto.getFileName());

        return new ResponseEntity<>(produto.getFile(), headers, HttpStatus.OK);
    }

    private String getMediaTypeForCategoria(ProdutoCategoria categoria) {
        return switch (categoria) {
            case CAMISETA, CALCA -> "text/plain"; // Exemplo, substitua pelo tipo de mídia apropriado
            default -> "application/octet-stream"; // Padrão para tipos desconhecidos
        };
    }

    private URI buildProdutoURL(Produto produto) {
        String produtoPath = "/" + produto.getId();
        return ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path(produtoPath)
                .build().toUri();
    }

    @GetMapping
    public ResponseEntity<List<ProdutoDTO>> search(
            @RequestParam(value = "categoria", required = false) ProdutoCategoria categoria,
            @RequestParam(value = "query", required = false) String query) {
        try {
            Thread.sleep(3000L); // Remover para produção (simulação)

            var result = produtoService.search(categoria, query);

            var produtos = result.stream().map(produto -> {
                var url = buildProdutoURL(produto);
                return mapper.produtoDTO(produto, url.toString());
            }).collect(Collectors.toList());

            return ResponseEntity.ok(produtos);
        } catch (Exception e) {
            log.error("Erro ao buscar produtos: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        log.info("Deletando produto com id {}", id);
        boolean deleted = produtoService.delete(id);

        if (deleted) {
            return ResponseEntity.noContent().build(); // 204 No Content
        } else {
            return ResponseEntity.notFound().build(); // 404 Not Found
        }
    }

    @PutMapping("/baixa/{id}")
    public ResponseEntity<Void> baixaProduto(@PathVariable String id, @RequestParam("quantidade") int quantidade) {
        log.info("Realizando baixa do produto com id {} e quantidade {}", id, quantidade);

        boolean atualizado = produtoService.baixaProduto(id, quantidade);

        if (atualizado) {
            return ResponseEntity.ok().build();
        } else {
            log.warn("Produto com id {} não encontrado para baixa", id);
            return ResponseEntity.notFound().build();
        }
    }


}
