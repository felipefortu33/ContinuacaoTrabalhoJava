package felipefortu.Stores.domain.service;

import felipefortu.Stores.controller.VendaDTO;
import felipefortu.Stores.domain.entity.Venda;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;

@Service
public interface VendaService {


    Venda processarVenda(String produtoId, int quantidade) throws ParseException;

    List<VendaDTO> getAllVendas();
}