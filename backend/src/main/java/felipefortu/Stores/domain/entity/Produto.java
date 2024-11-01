package felipefortu.Stores.domain.entity;

import felipefortu.Stores.domain.enums.ProdutoCategoria;
import felipefortu.Stores.domain.enums.ProdutoCategoria;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column
    @Lob
    private byte[] file;
    @Column
    private String produto;
    @Column
    private String preco;
    @Column
    private String quantidade;
    @Column
    private ProdutoCategoria categoria;
    @Column
    private long size;


    public String getFileName() {
       return getProduto().concat(".").concat(getCategoria().name());
    }
}
