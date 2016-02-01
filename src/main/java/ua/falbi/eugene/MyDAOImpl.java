package ua.falbi.eugene;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;


public class MyDAOImpl implements MyDAO {
    private final String queryListString = "SELECT a FROM Client a WHERE a.deleted=false";
    private final String queryPatternString = "SELECT a FROM Client a WHERE a.name LIKE :pattern";
    private final String queryTrashString = "SELECT a FROM Client a WHERE a.deleted=true";
    private final String queryHistotyString = "SELECT a FROM Payment a WHERE a.clientID = :cli_id";

    @Autowired
    private EntityManager entityManager;


    @Override
    public List<Client> list() {
        Query query = entityManager.createQuery(queryListString, Client.class);
        return (List<Client>) query.getResultList();
    }


    @Override
    public List<Client> list(String pattern) {
        Query query = entityManager.createQuery(queryPatternString, Client.class);
        query.setParameter("pattern", "%" + pattern + "%");
        return (List<Client>) query.getResultList();
    }


    // Список удаленных объявлений
    @Override
    public List<Client> trashList() {
        Query query = entityManager.createQuery(queryTrashString, Client.class);
        return (List<Client>) query.getResultList();
    }

    @Override
    public Client clientById(long id){
        return entityManager.find(Client.class, id);
    }

    @Override
    public List<Payment> payLog(long id){
        Query query = entityManager.createQuery(queryHistotyString, Payment.class).setParameter("cli_id", id);
        return (List<Payment>) query.getResultList();
    }

    @Override
    public void add(Client cli) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(cli);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            entityManager.getTransaction().rollback();
            ex.printStackTrace();
        }
    }


    @Override
    public void delete(long id) {
        try {
            entityManager.getTransaction().begin();
            Client cli = entityManager.find(Client.class, id);
            entityManager.remove(cli);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            entityManager.getTransaction().rollback();
            ex.printStackTrace();
        }
    }


    @Override
    public void delete(long[] ids) {
        try {
            entityManager.getTransaction().begin();

            for (long id : ids) {
                Client cli = entityManager.find(Client.class, id);
                entityManager.remove(cli);
            }

            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            entityManager.getTransaction().rollback();
            ex.printStackTrace();
        }
    }


    @Override
    public void remove(long id) {
        try {
            entityManager.getTransaction().begin();
            Client cli = entityManager.find(Client.class, id);
            cli.setDeleted(true);
            entityManager.merge(cli);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            entityManager.getTransaction().rollback();
            ex.printStackTrace();
        }
    }


    @Override
    public void remove(long[] ids) {
        try {
            entityManager.getTransaction().begin();

            for (long id : ids) {
                Client cli = entityManager.find(Client.class, id);
                cli.setDeleted(true);
                entityManager.merge(cli);
            }

            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            entityManager.getTransaction().rollback();
            ex.printStackTrace();
        }
    }


    @Override
    public void restore(long id) {
        try {
            entityManager.getTransaction().begin();
            Client cli = entityManager.find(Client.class, id);
            cli.setDeleted(false);
            entityManager.merge(cli);
            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            entityManager.getTransaction().rollback();
            ex.printStackTrace();
        }
    }


    @Override
    public void restore(long[] ids) {
        try {
            entityManager.getTransaction().begin();

            for (long id : ids) {
                Client cli = entityManager.find(Client.class, id);
                cli.setDeleted(false);
                entityManager.merge(cli);
            }

            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            entityManager.getTransaction().rollback();
            ex.printStackTrace();
        }
    }

    @Override
    public void depositMoney(long id, double sum) {
        try {
            entityManager.getTransaction().begin();

            Client cli = entityManager.find(Client.class, id);
            cli.setBalance(cli.getBalance() + sum);
            entityManager.merge(cli);

            Payment payLog = new Payment(true, sum, id);
            entityManager.persist(payLog);

            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            entityManager.getTransaction().rollback();
            ex.printStackTrace();
        }
    }

    @Override
    public void writeoffMoney(long id) {
        try {
            entityManager.getTransaction().begin();

            Client cli = entityManager.find(Client.class, id);
            cli.setBalance(cli.getBalance() - cli.getTarif());
            entityManager.merge(cli);

            Payment payLog = new Payment(false, cli.getTarif(), id);
            entityManager.persist(payLog);

            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            entityManager.getTransaction().rollback();
            ex.printStackTrace();
        }
    }

    @Override
    public void writeoffSelectedMoney(long[] ids) {
        try {
            entityManager.getTransaction().begin();

            for (long id : ids) {
                Client cli = entityManager.find(Client.class, id);
                cli.setBalance(cli.getBalance() - cli.getTarif());
                entityManager.merge(cli);

                Payment payLog = new Payment(false, cli.getTarif(), id);
                entityManager.persist(payLog);
            }

            entityManager.getTransaction().commit();
        } catch (Exception ex) {
            entityManager.getTransaction().rollback();
            ex.printStackTrace();
        }
    }

    @Override
    public byte[] getLogo(long id) {
        try {
            Logo logo = entityManager.find(Logo.class, id);
            return logo.getBody();
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
