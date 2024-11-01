package felipefortu.Stores.controller;

import felipefortu.Stores.domain.entity.Produto;
import felipefortu.Stores.domain.enums.ProdutoCategoria;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Component
public class ProdutoMapper {

    public ProdutoDTO produtoDTO(Produto produto, String url) {
        return ProdutoDTO.builder()
                .url(url)
                .produto(produto.getProduto())
                .preco(produto.getPreco())
                .quantidade(produto.getQuantidade())  // Adicionando quantidade ao DTO
                .build();
    }

    public Produto mapToProduto(MultipartFile file, String produto, String preco, String quantidade, ProdutoCategoria categoria) throws IOException {
        return Produto
                .builder()
                .size(file.getSize())
                .categoria(categoria) // Setando a categoria diretamente
                .file(file.getBytes())
                .produto(produto)
                .preco(preco)
                .quantidade(quantidade)
                .build();
    }
}
