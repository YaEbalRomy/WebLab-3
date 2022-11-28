import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.Cacheable;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Random;


@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class DatabaseManager {
    public static DatabaseManager instance;
    private final EntityManager entityManager;

    private DatabaseManager() {
        entityManager = Persistence.createEntityManagerFactory("Point").createEntityManager();
    }

    public static DatabaseManager getInstance() {
        if (instance == null)
            instance = new DatabaseManager();
        return instance;
    }

    public List<Point> getCollectionFromDataBase() {
        return entityManager.createQuery("SELECT point FROM points point", Point.class).getResultList();
    }

    public String addPoint(Point point) {
        //System.out.println(entityManager.contains(point));
        entityManager.getTransaction().begin();
        entityManager.persist(point);
        entityManager.getTransaction().commit();
        return "commit";

        /*
         if (new Random().nextLong() > 10) {
            entityManager.persist(point);
            entityManager.getTransaction().commit();
            return "commit";
        } else {
            System.out.println("rollback");
            entityManager.getTransaction().rollback();
            return "rollback";
        }
         */

    }

    public void removeAllPoints() {
        entityManager.getTransaction().begin();
        entityManager.createQuery("DELETE FROM points").executeUpdate();
        entityManager.getTransaction().commit();

    }
}

