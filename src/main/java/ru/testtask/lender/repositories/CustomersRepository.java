package ru.testtask.lender.repositories;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.testtask.lender.models.Customer;

import java.util.List;

@Repository
public class CustomersRepository {
    private final SessionFactory sessionFactory;

    public CustomersRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<Customer> getAll() {
        Session session = sessionFactory.openSession();
        List<Customer> customers = session
                .createQuery("FROM Customer", Customer.class)
                .getResultList();
        session.close();
        return customers;
    }

    public void add(Customer item) {
        Session session = sessionFactory.openSession();

        session.beginTransaction();
        session.persist(item);
        session.getTransaction().commit();

        session.close();
    }

}
