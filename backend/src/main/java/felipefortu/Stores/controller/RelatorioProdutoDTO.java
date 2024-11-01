package felipefortu.Stores.controller;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RelatorioProdutoDTO {
    private String produto;
    private String categoria;
    private String preco;
    private String quantidade;
    private int totalProdutos; // Adicione este campo
    private double totalValor; // Adicione este campo
}
