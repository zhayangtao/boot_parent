package com.example.boot_start_learning.chapter7_hibernate.service;

import com.example.boot_start_learning.chapter7_hibernate.entities.Singer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * @author no one
 * @version 1.0
 * @since 2019/07/25
 */
@Repository
@Transactional
@Service("jpaSingerService")
public class SingerServiceImpl implements SingerService {
    private final static String ALL_SINGER_NATIVE_QUERY = "select id, first_name, last_name, birth_date, version from singer";
    private static Logger logger = LoggerFactory.getLogger(SingerServiceImpl.class);

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional(readOnly = true)
    public List<Singer> findAll() {
        return entityManager.createNamedQuery(Singer.FIND_ALL, Singer.class).getResultList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Singer> findAllWithAlbum() {
        return entityManager.createNamedQuery(Singer.FIND_ALL_WITH_ALBUM, Singer.class).getResultList();
    }

    @Override
    @Transactional(readOnly = true)
    public Singer findById(Long id) {
        TypedQuery<Singer> query = entityManager.createNamedQuery(Singer.FIND_SINGER_BY_ID, Singer.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    public Singer save(Singer contact) {
        if (contact.getId() == null) {
            logger.info("inserting new singer");
            entityManager.persist(contact);//新增
        } else {
            entityManager.merge(contact);//更新
            logger.info("updating existing singer");
        }
        return contact;
    }

    @Override
    public void delete(Singer contact) {
        Singer singer = entityManager.merge(contact);
        entityManager.remove(singer);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Singer> findAllByNativeQuery() {
        return entityManager.createNativeQuery(ALL_SINGER_NATIVE_QUERY, Singer.class).getResultList();
    }
}
