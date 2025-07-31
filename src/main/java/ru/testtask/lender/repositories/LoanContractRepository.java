package ru.testtask.lender.repositories;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import ru.testtask.lender.models.LoanContract;

@Repository
public class LoanContractRepository extends BaseRepository<LoanContract> {
    public LoanContractRepository(SessionFactory sessionFactory) { super(sessionFactory); }

    @Override
    public LoanContract getById(long id) { return getById(LoanContract.class, id); }

    public void update(LoanContract newContract) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            LoanContract oldContract = getById(newContract.getId());
            oldContract.setLoanContractStatus(newContract.getLoanContractStatus());
            oldContract.setSignDate(newContract.getSignDate());
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

    public List<LoanContract> getAll() {
        Session session = sessionFactory.openSession();
        List<LoanContract> contracts = session
                .createQuery("FROM LoanContract", LoanContract.class)
                .getResultList();
        session.close();
        return contracts;
    }
}
