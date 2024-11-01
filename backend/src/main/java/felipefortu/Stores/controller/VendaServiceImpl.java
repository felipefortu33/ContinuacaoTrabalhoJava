package felipefortu.Stores.controller;

import felipefortu.Stores.domain.entity.Venda;
import felipefortu.Stores.domain.service.VendaService;
import felipefortu.Stores.infra.repository.VendaRepository;
import felipefortu.Stores.infra.repository.ProdutoRepository;
import felipefortu.Stores.domain.entity.Produto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;

@Service
public class VendaServiceImpl implements VendaService {

    @Autowired
    private VendaRepository vendaRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Override
    public Venda processarVenda(String produtoId, int quantidade) throws ParseException {
        // Buscar o produto pelo ID
        Produto produto = produtoRepository.findById(produtoId)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        // Aqui, você deve ter um campo para o preço do produto
        String precoString = produto.getPreco(); // Supondo que o preço esteja no formato "200,00"

        // Converte a string de preço para BigDecimal
        NumberFormat format = NumberFormat.getInstance(Locale.FRANCE);
        Number precoNumber = format.parse(precoString);
        BigDecimal preco = BigDecimal.valueOf(precoNumber.doubleValue());

        // Calcule o subtotal
        BigDecimal subtotal = preco.multiply(BigDecimal.valueOf(quantidade));

        // Crie a venda
        Venda venda = new Venda();
        venda.setProduto(produto);
        venda.setQuantidade(quantidade);
        venda.setSubtotal(String.valueOf(subtotal));
        venda.setNomeProduto(produto.getProduto()); // Adicione esta linha
        venda.setPrecoUnitario(precoString); // Defina o preço unitário se necessário
        venda.setPrecoTotal(String.valueOf(subtotal)); // Defina o preço total
        venda.setDataVenda(LocalDateTime.now()); // Adicione a data da venda

        // Salve a venda no repositório
        return vendaRepository.save(venda); // Retorne a venda salva
    }

    @Override
    public List<VendaDTO> getAllVendas() {
        return List.of();
    }
}
