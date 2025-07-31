package ru.testtask.lender.repositories;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.testtask.lender.models.Customer;

import java.util.List;

@Repository
public class CustomersRepository extends BaseRepository<Customer> {
    public CustomersRepository(SessionFactory sessionFactory) { super(sessionFactory); }

    public List<Customer> getAll() {
        Session session = sessionFactory.openSession();
        List<Customer> customers = session
                .createQuery("FROM Customer", Customer.class)
                .getResultList();
        session.close();
        return customers;
    }
    
    @Override
    public Customer getById(long id) { return getById(Customer.class, id); }
}
