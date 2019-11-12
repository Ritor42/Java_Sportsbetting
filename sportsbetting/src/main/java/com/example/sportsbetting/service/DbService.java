package com.example.sportsbetting.service;

import com.example.sportsbetting.config.JpaConfig;
import com.example.sportsbetting.domain.OutcomeOdd;
import com.example.sportsbetting.domain.Player;
import com.example.sportsbetting.domain.SportEvent;
import com.example.sportsbetting.domain.Wager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.List;

public class DbService {
    private static final Logger logger = LoggerFactory.getLogger(DbService.class);
    private static DbService model;
    private EntityManager em;

    private DbService() {
        ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(JpaConfig.class);
        EntityManagerFactory emf = context.getBean(EntityManagerFactory.class);
        this.em = emf.createEntityManager();
    }

    public static DbService GetInstance() {
        if (model == null) {
            model = new DbService();
        }

        return model;
    }

    public void Save(Object entity) {
        em.getTransaction().begin();
        em.persist(entity);
        em.flush();
        em.getTransaction().commit();

        logger.info("Saved entity: " + entity.toString());
    }

    public void Delete(Object entity) {
        em.getTransaction().begin();
        em.remove(entity);
        em.flush();
        em.getTransaction().commit();

        logger.info("Deleted entity: " + entity.toString());
    }

    public List<OutcomeOdd> getOutcomeOdds() {
        return em.createQuery("select e from OutcomeOdd e", OutcomeOdd.class).getResultList();
    }

    public OutcomeOdd getOutcomeOdd(Integer id) {
        String jql = "select e from OutcomeOdd e where e.id = :id";

        TypedQuery<OutcomeOdd> query = em.createQuery(jql, OutcomeOdd.class)
                .setParameter("id", id);

        List<OutcomeOdd> odds = query.getResultList();

        if (odds.isEmpty()) {
            return null;
        } else {
            return odds.get(0);
        }
    }

    public List<SportEvent> getEvents() {
        return em.createQuery("select e from SportEvent e", SportEvent.class).getResultList();
    }

    public List<Wager> getWagers() {
        return em.createQuery("select e from Wager e", Wager.class).getResultList();
    }

    public Wager getWager(Integer id) {
        String jql = "select e from Wager e where e.id = :id";

        TypedQuery<Wager> query = em.createQuery(jql, Wager.class)
                .setParameter("id", id);

        List<Wager> wagers = query.getResultList();

        if (wagers.isEmpty()) {
            return null;
        } else {
            return wagers.get(0);
        }
    }

    public List<Player> getPlayers() {
        return em.createQuery("select e from Player e", Player.class).getResultList();
    }

    public Player getPlayer(String email, String password) {
        String jql = "select e from Player e where e.email = :email and e.password = :password";

        TypedQuery<Player> query = em.createQuery(jql, Player.class)
                .setParameter("email", email)
                .setParameter("password", password);

        List<Player> players = query.getResultList();

        if (players.isEmpty()) {
            logger.info("Failed login: " + email);
            return null;
        } else {
            logger.info("Successful login: " + email);
            return players.get(0);
        }
    }

    public Player getPlayer(Integer id) {
        String jql = "select e from Player e where e.id = :id";

        TypedQuery<Player> query = em.createQuery(jql, Player.class)
                .setParameter("id", id);

        List<Player> players = query.getResultList();

        if (players.isEmpty()) {
            return null;
        } else {
            return players.get(0);
        }
    }
}
