package felipefortu.Stores.infra.repository.specs;

import felipefortu.Stores.domain.entity.Produto;
import felipefortu.Stores.domain.enums.ProdutoCategoria;

import org.springframework.data.jpa.domain.Specification;

public class ProdutoSpecs {
    private ProdutoSpecs(){}

    public static Specification<Produto> categoriaEqual(ProdutoCategoria categoria){
        return (root, query1, criteriaBuilder) -> criteriaBuilder.equal(root.get("categoria"), categoria);
    }

    public static Specification<Produto> produtoLike(String produto){
        return (root, query1, criteriaBuilder) -> criteriaBuilder.like(criteriaBuilder.upper(root.get("produto")), "%" + produto.toUpperCase() + "%");
    }

    public static Specification<Produto> precoLike(String preco){
        return (root, query1, criteriaBuilder) -> criteriaBuilder.like(criteriaBuilder.upper(root.get("preco")), "%" + preco.toUpperCase() + "%");
    }
}
