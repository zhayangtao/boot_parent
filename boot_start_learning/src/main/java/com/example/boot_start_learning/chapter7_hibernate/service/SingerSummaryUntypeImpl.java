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
 * 查询非类型化结果
 */
@Service("singerSummaryUntype")
@Repository
@Transactional
public class SingerSummaryUntypeImpl {
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional(readOnly = true)
    public void displayAllSingerSummary() {
        List result = entityManager.createQuery(
                "select s.firstName, s.lastName, a.title from Singer s " +
                        "left join s.albums a " +
                        "where a.releaseDate = (select max(a2.releaseDate) " +
                        "from Album a2 where a2.singer.id = s.id)")
                .getResultList();
        result.forEach(value -> {
            Object[] values = (Object[]) value;
            System.out.println(values[0] + "," + values[1]);
        });
    }

}
