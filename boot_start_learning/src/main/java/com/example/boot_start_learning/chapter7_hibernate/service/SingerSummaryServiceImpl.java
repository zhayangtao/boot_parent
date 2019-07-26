package com.example.boot_start_learning.chapter7_hibernate.service;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * @author no one
 * @version 1.0
 * @since 2019/07/25
 */
@Service
@Repository
@Transactional
public class SingerSummaryServiceImpl implements SingerSummaryService {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional(readOnly = true)
    public List<SingerSummary> findAll() {
        return entityManager.createQuery("select new com.example.boot_start_learning.chapter7_hibernate.service.SingerSummary (s.firstName," +
                "s.lastName, a.title) from Singer s " +
                "left join s.albums a " +
                "where a.releaseDate = (select max(a2.releaseDate)" +
                "from Album a2 where a2.singer.id=s.id)", SingerSummary.class).getResultList();
    }
}
