package com.example.algamoney.api.repository.lancamento;

import com.example.algamoney.api.model.Lancamento;
import com.example.algamoney.api.model.Lancamento_;
import com.example.algamoney.api.repository.filter.LancamentoFilter;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by victor on 08/09/2017.
 */
public class LancamentoRepositoryImpl implements LancamentoRepositoryQuery {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Lancamento> filtrar(LancamentoFilter filter) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Lancamento> criteria = criteriaBuilder.createQuery(Lancamento.class);

        Root<Lancamento> root = criteria.from(Lancamento.class);

        Predicate[] predicates = criarRestricoes(filter, criteriaBuilder, root);
        criteria.where(predicates);

        TypedQuery<Lancamento> query = em.createQuery(criteria);
        return query.getResultList();
    }

    private Predicate[] criarRestricoes(LancamentoFilter filter, CriteriaBuilder builder, Root<Lancamento> root) {
        List<Predicate> predicates = new ArrayList<>();
        if (!StringUtils.isEmpty(filter.getDescricao())) {
            predicates.add(builder.like(
                    builder.lower(root.get(Lancamento_.descricao)),"%".concat(filter.getDescricao().toLowerCase().concat("%"))
            ));
        }

        if (filter.getDataVencimentoDe() != null) {
            predicates.add(builder.greaterThanOrEqualTo(root.get(Lancamento_.dataVencimento),filter.getDataVencimentoDe()));
        }

        if (filter.getDataVencimentoAte() != null) {
            predicates.add(builder.lessThanOrEqualTo(root.get(Lancamento_.dataVencimento),filter.getDataVencimentoAte()));
        }

        return predicates.toArray(new Predicate[predicates.size()]);
    }
}
