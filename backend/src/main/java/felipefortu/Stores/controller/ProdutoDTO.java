package felipefortu.Stores.controller;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProdutoDTO {

    private String url;
    private String produto;
    private String preco;
    private String quantidade;
    private String categoria; // Optional addition
}