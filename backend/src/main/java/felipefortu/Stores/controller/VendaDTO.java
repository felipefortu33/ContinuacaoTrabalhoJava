package felipefortu.Stores.controller;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class VendaDTO {
    private String id;
    private String nomeProduto;
    private String precoUnitario;
    private int quantidade;
    private String subtotal;
    private String precoTotal;
    private LocalDateTime dataVenda;
}