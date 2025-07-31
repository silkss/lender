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

    public List<Customer> getBy(
        String name,
        String surname,
        String patronymic,
        long passportnumber,
        String phoneNumber) {
        StringBuilder query = new StringBuilder("from ContactEntity ");

        if (name != null) {
            query.append(" where name = '" + name + "'");
        }
        if (surname != null) {
            query.append(" where surname = '" + surname + "'");
        }
        if (patronymic!= null) {
            query.append(" where patronymic = '" + patronymic + "'");
        }

        Session session = sessionFactory.openSession();
        List<Customer> customers = session
            .createQuery(query.toString(), Customer.class)
            .getResultList();

        return customers;
    }
}
