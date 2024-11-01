package felipefortu.Stores.domain.service;

import felipefortu.Stores.domain.entity.Produto;
import felipefortu.Stores.domain.enums.ProdutoCategoria;

import java.util.List;
import java.util.Optional;

public interface ProdutoService {

    Produto save(Produto produto);

    Optional<Produto> getById(String id);

    List<Produto> search(ProdutoCategoria categoria, String query);

    boolean delete(String id);  // Alterado para "id" para maior clareza

    boolean baixaProduto(String id, int quantidade);
}
