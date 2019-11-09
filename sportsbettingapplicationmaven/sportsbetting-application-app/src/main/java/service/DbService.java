package service;

import config.JpaConfig;
import domain.Player;
import domain.SportEvent;
import domain.Wager;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

public class DbService {
    private static DbService model;
    private EntityManager em;

    private DbService() {
        ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(JpaConfig.class);
        EntityManagerFactory emf = context.getBean(EntityManagerFactory.class);
        this.em = emf.createEntityManager();
    }

    public static DbService GetDbService() {
        if(model == null) {
            model = new DbService();
        }

        return model;
    }

    public void Add(Object entity) {
        em.getTransaction().begin();
        em.persist(entity);
        em.flush();
        em.getTransaction().commit();
    }

    public List<Wager> getWagers() {
        return em.createQuery("select e from Wager e", Wager.class).getResultList();
    }

    public List<SportEvent> getEvents() {
        return em.createQuery("select e from SportEvent e", SportEvent.class).getResultList();
    }

    public List<Player> getPlayers() {
        return em.createQuery("select e from Player e", Player.class).getResultList();
    }
}
