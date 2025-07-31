package ru.testtask.lender.repositories;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.testtask.lender.models.LoanRequest;
import ru.testtask.lender.types.LoanRequestStatus;

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

    public List<LoanRequest> getApprovedRequests(){
        Session session = sessionFactory.openSession();
        List<LoanRequest> requests = session
            .createQuery("FROM LoanRequest where loanRequestStatus = :status", LoanRequest.class)
            .setParameter("status", LoanRequestStatus.APPROVED)
            .getResultList();
            
        session.close();
        return requests;
    }
}
