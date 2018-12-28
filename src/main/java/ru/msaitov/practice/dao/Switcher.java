package ru.msaitov.practice.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Repository;
import ru.msaitov.practice.dao.additionalFunctions.AddingQueryString;
import ru.msaitov.practice.dao.factory.DaoFactory;
import ru.msaitov.practice.dao.factory.DaoFactoryHQL;
import ru.msaitov.practice.dao.factory.DaoFactoryJPQLCriteria;
import ru.msaitov.practice.dao.factory.TypeDao;

import javax.persistence.EntityManager;

/**
 * Выбор между реализациями Dao, установить реализацию можно в поле typeDao
 */
@Repository
@ComponentScan
public class Switcher {

    private final EntityManager em;
    private final AddingQueryString addingQS;

    @Value("${daoFactory.type}")
    private String currentTypeDao;

    @Autowired
    public Switcher(final EntityManager em, final AddingQueryString addingQS) {
        this.em = em;
        this.addingQS = addingQS;
    }

    /**
     * Получить реализацию Фабрики Dao
     * @return
     */
    public DaoFactory getDaoFactory() {

        TypeDao typeDao = TypeDao.valueOf(currentTypeDao);

        if (typeDao == TypeDao.JPQL_CRITERIA) {
            return new DaoFactoryJPQLCriteria(em);
        } else if (typeDao == TypeDao.HQL) {
            return new DaoFactoryHQL(em, addingQS);
        }
        return null;
    }
}
