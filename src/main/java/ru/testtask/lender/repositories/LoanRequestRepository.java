package ru.testtask.lender.repositories;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.testtask.lender.models.LoanRequest;

@Repository
public class LoanRequestRepository {
    private final SessionFactory sessionFactory;

    public LoanRequestRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public LoanRequest getById(long id){
        Session session = sessionFactory.openSession();
        LoanRequest loanRequest = session.find(LoanRequest.class, id);
        session.close();
        return loanRequest;
    }

}
