package felipefortu.Stores.infra.repository;

import felipefortu.Stores.domain.entity.Produto;
import felipefortu.Stores.domain.enums.ProdutoCategoria;
import org.apache.el.stream.Optional;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;

import static felipefortu.Stores.infra.repository.specs.GenericSpecs.conjunction;
import static felipefortu.Stores.infra.repository.specs.ProdutoSpecs.*;
import static org.springframework.data.jpa.domain.Specification.anyOf;
import static org.springframework.data.jpa.domain.Specification.where;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, String>, JpaSpecificationExecutor<Produto> {
    default List<Produto> findByCategoriaAndProdutoOrPrecoLike(ProdutoCategoria categoria, String query) {
        Specification<Produto> spec = where(conjunction());

        if (categoria != null) {
            spec = spec.and(categoriaEqual(categoria));
        }

        if (StringUtils.hasText(query)) {

            spec = spec.and(anyOf(produtoLike(query), precoLike(query)));
        }

        return findAll(spec);
    }

}
