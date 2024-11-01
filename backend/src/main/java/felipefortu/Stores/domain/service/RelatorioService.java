package felipefortu.Stores.domain.service;

import felipefortu.Stores.controller.RelatorioProdutoDTO;
import felipefortu.Stores.domain.entity.Produto;
import felipefortu.Stores.infra.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RelatorioService {
    private final ProdutoRepository produtoRepository;

    public List<RelatorioProdutoDTO> gerarRelatorio() {
        List<Produto> produtos = produtoRepository.findAll();

        return produtos.stream()
                .map(produto -> {
                    double preco = 0;
                    int quantidade = 0;

                    try {
                        // Converte o preço, substituindo a vírgula por ponto e parseando
                        preco = NumberFormat.getInstance(Locale.forLanguageTag("pt-BR")).parse(produto.getPreco()).doubleValue();
                        // Converte a quantidade
                        quantidade = Integer.parseInt(produto.getQuantidade());
                    } catch (ParseException e) {
                        e.printStackTrace(); // Tratar o erro conforme necessário
                    }

                    double totalValor = preco * quantidade; // Calcule o total valor

                    return RelatorioProdutoDTO.builder()
                            .produto(produto.getProduto())
                            .categoria(produto.getCategoria().name())
                            .preco(String.valueOf(preco)) // Usar o preço convertido
                            .quantidade(String.valueOf(quantidade)) // Usar a quantidade convertida
                            .totalProdutos(quantidade) // Total produtos igual à quantidade
                            .totalValor(totalValor) // Total valor calculado
                            .build();
                })
                .collect(Collectors.toList());
    }
}
