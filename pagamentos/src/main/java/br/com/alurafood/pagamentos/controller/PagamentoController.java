package br.com.alurafood.pagamentos.controller;

import br.com.alurafood.pagamentos.dtos.PagamentoDto;
import br.com.alurafood.pagamentos.services.PagamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.net.URI;

@RestController("/pagamentos")
public class PagamentoController {

    @Autowired
    PagamentoService service;

    // código omitido

    @GetMapping
    public Page<PagamentoDto> listar(@PageableDefault(size = 10) Pageable paginacao) {
        return service.obterTodos(paginacao);
    }

    // código omitido

    @GetMapping("/{id}")
    public ResponseEntity<PagamentoDto> detalhar(@PathVariable @NotNull Long id) {
        PagamentoDto dto = service.obterPorId(id);

        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<PagamentoDto> cadastrar(@RequestBody @Valid PagamentoDto dto, UriComponentsBuilder uriBuilder) {
        PagamentoDto pagamento = service.criarPagamento(dto);
        URI endereco = uriBuilder.path("/pagamentos/{id}").buildAndExpand(pagamento.getId()).toUri();

        return ResponseEntity.created(endereco).body(pagamento);
    }
}
