package felipefortu.Stores.controller;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DashboardService {

    // Simulando dados para exemplo
    public Map<String, Object> getDashboardData() {
        Map<String, Object> data = new HashMap<>();
        data.put("vendas", 150); // Total de vendas
        data.put("produtosMaisVendidos", getProdutosMaisVendidos());
        data.put("produtosEmBaixa", getProdutosEmBaixa());

        return data;
    }

    private List<String> getProdutosMaisVendidos() {
        // Aqui você deve implementar a lógica para buscar os produtos mais vendidos do banco de dados
        return List.of("Produto A", "Produto B", "Produto C");
    }

    private List<String> getProdutosEmBaixa() {
        // Aqui você deve implementar a lógica para buscar os produtos em baixa
        return List.of("Produto D", "Produto E");
    }
}