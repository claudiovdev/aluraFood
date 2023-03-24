package br.com.alurafood.pagamentos.controller;

import br.com.alurafood.pagamentos.dtos.PagamentoDto;
import br.com.alurafood.pagamentos.services.PagamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/pagamentos")
public class PagamentoController {

    @Autowired
    PagamentoService service;

    // código omitido

    @GetMapping
    public Page<PagamentoDto> listar(@PageableDefault(size = 10) Pageable paginacao) {
        return service.obterTodos(paginacao);
    }
}
