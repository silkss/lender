package ru.testtask.lender.repositories;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import ru.testtask.lender.models.LoanContract;
import ru.testtask.lender.types.LoanContractStatus;

@Repository
public class LoanContractRepository extends BaseRepository<LoanContract> {
    public LoanContractRepository(SessionFactory sessionFactory) { super(sessionFactory); }

    @Override
    public LoanContract getById(long id) { return getById(LoanContract.class, id); }

    public void update(LoanContract update) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            LoanContract exist = session
                .createQuery("FROM LoanContract where id = :id", LoanContract.class)
                .setParameter("id", update.getId())
                .getSingleResult();

            exist.setLoanContractStatus(update.getLoanContractStatus());
            exist.setSignDate(update.getSignDate());
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

    public List<LoanContract> getSigned() {
        Session session = sessionFactory.openSession();

        List<LoanContract> contracts = session
            .createQuery("FROM LoanContract WHERE loanContractStatus = :status", LoanContract.class)
            .setParameter("status", LoanContractStatus.SIGNED)
            .getResultList();

        session.close();
        return contracts;
    }
}
