package com.example.algamoney.api.repository.lancamento;

import com.example.algamoney.api.model.Lancamento;
import com.example.algamoney.api.repository.filter.LancamentoFilter;
import com.example.algamoney.api.repository.projection.ResumoLancamento;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by victor on 08/09/2017.
 */
public interface LancamentoRepositoryQuery {

     Page<Lancamento> filtrar(LancamentoFilter filter, Pageable pageable);
     Page<ResumoLancamento> resumir(LancamentoFilter filter, Pageable pageable);
}
