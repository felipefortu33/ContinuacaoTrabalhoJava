package felipefortu.Stores.controller;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class DashboardData {
    private int totalVendas;                // Total de vendas
    private List<String> produtosMaisVendidos; // Lista dos produtos mais vendidos
}
