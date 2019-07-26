package com.example.boot_start_learning.chapter7_hibernate.dao;

import com.example.boot_start_learning.chapter7_hibernate.entities.Singer;
import lombok.Getter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.persistence.Query;
import java.util.List;

/**
 * @author no one
 * @version 1.0
 * @since 2019/07/24
 */
@Transactional
@Repository("singerDao")
@Getter
public class SingerDaoImpl implements SingerDao {

    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    @Override
    @Transactional(readOnly = true)
    public List<Singer> findAll() {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery(" from Singer s");
        return ((org.hibernate.query.Query) query).list();
    }

    @Override
    public List<Singer> findAllWithAlbum() {
        return sessionFactory.getCurrentSession().getNamedQuery("Singer.findAllWithAlbum").list();
    }

    @Override
    public Singer findById(Long id) {
        return null;
    }

    @Override
    public Singer save(Singer contact) {
        return null;
    }

    @Override
    public void delete(Singer contact) {

    }
}
