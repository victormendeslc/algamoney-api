package com.example.algamoney.api.repository.lancamento;

import com.example.algamoney.api.model.Lancamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LancamentoRepository extends JpaRepository<Lancamento, Long>,LancamentoRepositoryQuery {

}
