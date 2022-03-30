package nowewaluty.sources.session.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import nowewaluty.exceptions.DaoSessionException;
import nowewaluty.objects.db.Country;
import nowewaluty.sources.session.HibernateFactory;
import nowewaluty.strategies.Dao;

public class CountryDao implements Dao<Country> {

	@Override
	public Country get(long id) throws DaoSessionException {

		try {
			HibernateFactory factory = new HibernateFactory();
			SessionFactory sessionFactory;
			sessionFactory = factory.factory();
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			Query query = session.createQuery("FROM Country WHERE country_id = :id");
			query.setParameter("id", id);
			Country country = (Country) query.uniqueResult();
			session.getTransaction().commit();
			session.close();
			return country;
		} catch (Exception e) {
			throw new DaoSessionException("Błąd w tworzeniu sesji połączenia z bazą danych", e);
		}

	}

	@Override
	public Country get(String name) throws DaoSessionException {
		try {
			HibernateFactory factory = new HibernateFactory();
			SessionFactory sessionFactory;
			sessionFactory = factory.factory();
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			Query query = session.createQuery("FROM Country WHERE country_name = :name");
			query.setParameter("name", name);
			Country country = (Country) query.uniqueResult();
			session.getTransaction().commit();
			session.close();
			return country;
		} catch (Exception e) {
			throw new DaoSessionException("Błąd w tworzeniu sesji połączenia z bazą danych", e);
		}

	}

	@Override
	public void save(Country t) throws DaoSessionException {

		try {
			HibernateFactory factory = new HibernateFactory();
			SessionFactory sessionFactory;
			sessionFactory = factory.factory();
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			session.save(t);
			session.getTransaction().commit();
			session.close();
		} catch (Exception e) {
			throw new DaoSessionException("Błąd w tworzeniu sesji połączenia z bazą danych", e);
		}

	}

	@Override
	public void update(long id, Country t) throws DaoSessionException {
		try {
			HibernateFactory factory = new HibernateFactory();
			SessionFactory sessionFactory;
			sessionFactory = factory.factory();
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			Query query = session.createQuery("UPDATE Country SET country_name=:country_name WHERE country_id=:id ");
			query.setParameter("country_name", t.getName());
			query.setParameter("id", id);
			query.executeUpdate();
			session.getTransaction().commit();
			session.close();
		} catch (Exception e) {
			throw new DaoSessionException("Błąd w tworzeniu sesji połączenia z bazą danych", e);
		}

	}

}
