package felipefortu.Stores.controller;

import felipefortu.Stores.domain.entity.Venda;
import felipefortu.Stores.domain.service.VendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/v1/venda")
public class VendaController {

    private final VendaService vendaService;

    @Autowired
    public VendaController(VendaService vendaService) {
        this.vendaService = vendaService;
    }

    @PostMapping("/{produtoId}")
    public ResponseEntity<Venda> registrarVenda(@PathVariable String produtoId, @RequestParam int quantidade) {
        try {
            Venda venda = vendaService.processarVenda(produtoId, quantidade);
            return new ResponseEntity<>(venda, HttpStatus.CREATED); // Retorna 201 Created
        } catch (RuntimeException | ParseException e) {
            // Aqui você pode adicionar um log para registrar o erro
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null); // Retorna 400 Bad Request em caso de erro
        }
    }

    @GetMapping("/vendas")
    public List<VendaDTO> getVendas() {
        return vendaService.getAllVendas();
    }


}
