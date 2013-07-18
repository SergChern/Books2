package books2.services;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;


import books2.server.Author;
import books2.server.Book;
import books2.server.HibernateUtil;
import books2.server.Snake;

//@Service
public class ZZZService {
    private static final ZZZService zzzService = new ZZZService();
    static Session session = null;
    static Query query = null;
    static String sql = null;
    static Criteria q = null;
    private static List<String> snakes = new ArrayList();
    
    public ZZZService() {
    }
    
    public static ZZZService getZZZService() {
        return zzzService;
    }
    
    // @Transactional(readOnly = false, propagation = Propagation.SUPPORTS)
    public static List getBooks() throws IllegalArgumentException {
        q = HibernateUtil.getCurrentSession().createCriteria(Book.class);
        return q.list();
    }

    // @Transactional(readOnly = false, propagation = Propagation.SUPPORTS)
    public static List getAuthors() throws IllegalArgumentException {
        q = HibernateUtil.getCurrentSession().createCriteria(Author.class);
        return q.list();
    }

    public static Author getAuthor(Long id) throws IllegalArgumentException {         
        return (Author) HibernateUtil.getCurrentSession().load(Author.class, id);
    }
    
    public static Book getBook(Long id) throws IllegalArgumentException {
        return (Book) HibernateUtil.getCurrentSession().load(Book.class, id);
    }
    
    public static List getAuthorAll(Long id) throws IllegalArgumentException {
        if (id == null)
            id = new Long(0);

        session = HibernateUtil.getCurrentSession();
        sql = "select * FROM author a" + " where a.id not in ("
                + "  SELECT a.id FROM author a  join author_book ab on ab.fk_author_id=a.id"
                + " join Book b on b.id = ab.fk_book_id where b.id=:id)";
        query = session.createSQLQuery(sql).addEntity(Author.class);
        return query.setLong("id", id).list();
    }   
   
    // @Transactional(readOnly = false, propagation = Propagation.SUPPORTS)
    public static void saveOrUpdate(Object entity) {
        session = HibernateUtil.getCurrentSession();
        try {
            session.merge(entity); // saveOrUpdate(entity);
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            session.getTransaction().rollback();
            throw e; // or display error message
        }
    }

    // @Transactional(readOnly = false, propagation = Propagation.SUPPORTS)
    public static void delete(Object entity) {
        session = HibernateUtil.getCurrentSession();
        try {
            session.delete(entity);
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            session.getTransaction().rollback();
            throw e; // or display error message
        }
    }

    // @Transactional(readOnly = false, propagation = Propagation.SUPPORTS)
    public static List getBooks(Long idAuthor) throws IllegalArgumentException {
        session = HibernateUtil.getCurrentSession();
        sql = "SELECT * FROM Book b join author_book ab on b.id = ab.fk_book_id"
                + " join author a on ab.fk_author_id=a.id where a.id=:id" + "  ORDER BY b.name";
        query = session.createSQLQuery(sql).addEntity(Book.class);
        return query.setLong("id", idAuthor).list();
    }

    // @Transactional(readOnly = false, propagation = Propagation.SUPPORTS)
    public static List findBooks(String seekName) throws IllegalArgumentException {
        seekName = "%" + seekName + "%";
        session = HibernateUtil.getCurrentSession();
        sql = "SELECT * FROM Book b where LOWER(b.name) like LOWER(:name) ORDER BY b.name";
        query = session.createSQLQuery(sql).addEntity(Book.class);
        return query.setString("name", seekName).list();
    }

    // @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    

    // @Transactional(readOnly = false, propagation = Propagation.SUPPORTS)
    public static void deleteAuthor(Long idAuthor, Long idBook) throws IllegalArgumentException {
        session = HibernateUtil.getCurrentSession();
        sql = null;

        try {
            sql = "delete FROM author_book where fk_author_id=:id";
            query = session.createSQLQuery(sql);
            query.setLong("id", idAuthor);
            query.executeUpdate();

            if (idBook != null) {
                sql = "delete FROM Book where id=:id";
                query = session.createSQLQuery(sql);
                query.setLong("id", idBook);
                query.executeUpdate();
            }

            sql = "delete FROM author where id=:id";
            query = session.createSQLQuery(sql);
            query.setLong("id", idAuthor);
            query.executeUpdate();

            session.getTransaction().commit();
        } catch (RuntimeException e) {
            session.getTransaction().rollback();
            throw e; // or display error message
        }
    }
    public static void clearSnakes() {
        snakes.clear();
        
    }
    public static List<Snake> getSnake(String action, String name) {
        if (snakes.size() == 1)
            snakes.add("hello"+"**"+snakes.get(0).substring(3));
        int i=-1;
        for(int y = 0; y < snakes.size(); y++){
                      if(snakes.get(y).indexOf(action)==0) {
                       i=y;
        break;
                      }
        }
        if (i == -1)
            snakes.add(action+"**"+name);
        else
            while (i + 1 < snakes.size())
                snakes.remove(i + 1);

        List<Snake> snakeList = new ArrayList();
        for (i = 0; i < snakes.size(); i++) {
            snakeList.add(new Snake((String) snakes.get(i)));
        }
        return snakeList;
    }
}
