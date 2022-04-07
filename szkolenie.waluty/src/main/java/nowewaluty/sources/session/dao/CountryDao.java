package nowewaluty.sources.session.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

import nowewaluty.exceptions.DaoSessionException;
import nowewaluty.objects.db.Country;
import nowewaluty.sources.session.HibernateFactory;
import nowewaluty.strategies.Dao;

@Component
public class CountryDao implements Dao<Country> {

	SessionFactory sessionFactory;

	public CountryDao(HibernateFactory factory) throws DaoSessionException {
		try {
			sessionFactory = factory.factory();
		} catch (Exception e) {
			throw new DaoSessionException("Błąd w tworzeniu sesji połączenia z bazą danych", e);
		}
	}

	@Override
	public Country get(long id) {

		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Query query = session.createQuery("FROM Country WHERE country_id = :id");
		query.setParameter("id", id);
		Country country = (Country) query.uniqueResult();
		session.getTransaction().commit();
		session.close();
		return country;

	}

	@Override
	public Country get(String name) {

		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Query query = session.createQuery("FROM Country WHERE country_name = :name");
		query.setParameter("name", name);
		Country country = (Country) query.uniqueResult();
		session.getTransaction().commit();
		session.close();
		return country;

	}

	@Override
	public void save(Country t) {

		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(t);
		session.getTransaction().commit();
		session.close();

	}

	@Override
	public void update(long id, Country t) {

		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Query query = session.createQuery("UPDATE Country SET country_name=:country_name WHERE country_id=:id ");
		query.setParameter("country_name", t.getName());
		query.setParameter("id", id);
		query.executeUpdate();
		session.getTransaction().commit();
		session.close();

	}

	public List<Country> getCountryWithMoreCurrencies() {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Query query = session.getNamedQuery("Country_CountryWithMoreThanOneCurrency");
		List<Country> countries = query.getResultList();
		return countries;

	}

}
