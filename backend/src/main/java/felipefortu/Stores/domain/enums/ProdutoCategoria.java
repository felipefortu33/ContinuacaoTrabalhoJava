package felipefortu.Stores.domain.enums;

import lombok.Getter;

@Getter
public enum ProdutoCategoria {

    CAMISETA("Camiseta"),
    CALCA("Calça"),
    CHINELO("Chinelo"),
    TENIS("Tênis");

    private final String descricao;

    ProdutoCategoria(String descricao) {
        this.descricao = descricao;
    }

    // Método para obter uma categoria com base na descrição
    public static ProdutoCategoria fromDescricao(String descricao) {
        for (ProdutoCategoria categoria : ProdutoCategoria.values()) {
            if (categoria.getDescricao().equalsIgnoreCase(descricao)) {
                return categoria;
            }
        }
        throw new IllegalArgumentException("Descrição não reconhecida: " + descricao);
    }

    // Método para obter uma categoria com base no nome da enumeração
    public static ProdutoCategoria fromNome(String nome) {
        try {
            return ProdutoCategoria.valueOf(nome.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Categoria não reconhecida: " + nome);
        }
    }
}
