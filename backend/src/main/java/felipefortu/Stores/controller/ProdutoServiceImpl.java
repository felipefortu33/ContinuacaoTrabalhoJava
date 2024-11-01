package felipefortu.Stores.controller;

import felipefortu.Stores.domain.entity.Produto;
import felipefortu.Stores.domain.enums.ProdutoCategoria;
import felipefortu.Stores.domain.service.ProdutoService;
import felipefortu.Stores.infra.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProdutoServiceImpl implements ProdutoService {

    private final ProdutoRepository produtoRepository;

    @Override
    @Transactional
    public Produto save(Produto produto) {
        return produtoRepository.save(produto);
    }

    @Override
    public Optional<Produto> getById(String id) {
        return produtoRepository.findById(id);
    }

    @Override
    public List<Produto> search(ProdutoCategoria categoria, String query) {
        return produtoRepository.findByCategoriaAndProdutoOrPrecoLike(categoria, query);
    }


    @Override
    public boolean delete(String id) {
        if (produtoRepository.existsById(id)) {
            produtoRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    @Transactional
    public boolean baixaProduto(String id, int quantidade) {
        Optional<Produto> optionalProduto = produtoRepository.findById(id);
        if (optionalProduto.isPresent()) {
            Produto produto = optionalProduto.get();
            int novaQuantidade = Integer.parseInt(produto.getQuantidade()) - quantidade;

            if (novaQuantidade < 0) {
                throw new IllegalArgumentException("Quantidade insuficiente em estoque.");
            }

            produto.setQuantidade(String.valueOf(novaQuantidade));
            produtoRepository.save(produto);
            return true;
        }
        return false;
    }



}
