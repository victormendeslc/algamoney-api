package com.example.algamoney.api.repository.lancamento;

import com.example.algamoney.api.model.Lancamento;
import com.example.algamoney.api.repository.filter.LancamentoFilter;

import java.util.List;

/**
 * Created by victor on 08/09/2017.
 */
public interface LancamentoRepositoryQuery {

     List<Lancamento> filtrar(LancamentoFilter filter);
}
