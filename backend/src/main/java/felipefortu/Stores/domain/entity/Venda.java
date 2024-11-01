package felipefortu.Stores.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "vendas")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Venda {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne
    @JoinColumn(name = "produto_id", nullable = false)
    private Produto produto;

    @Column(nullable = false)
    private String nomeProduto; // Certifique-se de que este campo está aqui

    @Column(nullable = false)
    private String precoUnitario;

    @Column(nullable = false)
    private int quantidade;

    @Column(nullable = false)
    private String subtotal;

    @Column(nullable = false)
    private String precoTotal;

    @Column(nullable = false)
    private LocalDateTime dataVenda;

    // O método setNomeProduto deve ser gerado automaticamente pelo Lombok
    // Se você estiver usando Lombok corretamente, não precisa adicionar manualmente
}
