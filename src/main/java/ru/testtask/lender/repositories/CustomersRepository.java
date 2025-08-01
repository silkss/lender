package ru.testtask.lender.repositories;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import lombok.var;
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

    public List<Customer> getByName(String name){
        Session session = sessionFactory.openSession();

        List<Customer> customers = session
            .createQuery("FROM Customer Where name = :name", Customer.class)
            .setParameter("name",name)
            .getResultList();

        session.close();
        return customers;
    }
    public List<Customer> getBy(
        String name,
        String surname,
        String patronymic,
        Long passportNumber,
        String phoneNumber) {
        StringBuilder hql = new StringBuilder("FROM Customer ");
        
        boolean haveAnother = false;
        if (name != null) {
            hql.append("WHERE name = :name ");
            haveAnother = true;
        }
        if (surname != null) {
            if (haveAnother){
                hql.append(" AND surname = :surname");
            }
            else {
                hql.append(" WHERE surname = :surname");
                haveAnother  =true;
            }
        }
        if (patronymic != null) {
            if (haveAnother) {
                hql.append(" AND patronymic = :patronymic");
            }
            else{
                hql.append(" WHERE patronymic = :patronymic");
                haveAnother = true;
            }
        }
        if (passportNumber != null) {
            if (haveAnother) {
                hql.append(" AND passportNumber = :passportNumber");
            }
            else{
                hql.append(" WHERE passportNumber = :passportNumber");
                haveAnother = true;
            }
        }

        if (phoneNumber != null ){
            if (haveAnother) {
                hql.append(" AND phoneNumber = :phoneNumber");
            }
            else{
                hql.append(" WHERE phoneNumber = :phoneNumber");
                haveAnother = true;
            }
        }

        Session session = sessionFactory.openSession();
        Query<Customer> query = session.createQuery(hql.toString(), Customer.class);

        if (name != null) {
            query.setParameter("name", name);
        }
        if (surname != null) {
            query.setParameter("surname", "%" + surname + "%");
        }
        if (patronymic != null) {
            query.setParameter("patronymic", "%" + patronymic + "%");
        }

        if (passportNumber != null) {
            query.setParameter("passportNumber",  passportNumber);
        }

        if (phoneNumber != null ) {
            query.setParameter("phoneNumber", "%" + phoneNumber + "%");
        }
        List<Customer> customers = query.getResultList();
        return customers;
    }
}
