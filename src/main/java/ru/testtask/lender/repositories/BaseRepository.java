package ru.testtask.lender.repositories;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public abstract class BaseRepository<TItem> {
    protected SessionFactory sessionFactory;

    public BaseRepository(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    protected TItem getById(Class<TItem> obj, long id) {
        Session session = sessionFactory.openSession();
        TItem item = session.find(obj, id);
        session.close();
        return item;
    }

    public abstract TItem getById(long id);
    
    public void add(TItem item) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.persist((item));
            tx.commit();
        }
        catch (Exception e) {
            if (tx!=null) tx.rollback();
            throw e;
        }
        finally {
            session.close();
        }
    }
}
