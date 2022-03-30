package nowewaluty.sources.session.dao;

import java.time.LocalDate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import nowewaluty.Currency;
import nowewaluty.exceptions.DaoSessionException;
import nowewaluty.objects.db.RateValue;
import nowewaluty.sources.session.HibernateFactory;
import nowewaluty.strategies.Dao;

public class RateValueDao implements Dao<RateValue> {

	@Override
	public RateValue get(long id) throws DaoSessionException {

		try {
			HibernateFactory factory = new HibernateFactory();
			SessionFactory sessionFactory;
			sessionFactory = factory.factory();
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			Query query = session.createQuery("FROM RateValue WHERE rate_id = :id");
			query.setParameter("id", id);
			RateValue rateValue = (RateValue) query.uniqueResult();
			session.getTransaction().commit();
			session.close();
			return rateValue;
		} catch (Exception e) {
			throw new DaoSessionException("Błąd w tworzeniu sesji połączenia z bazą danych", e);
		}

	}

	@Override
	public void save(RateValue t) throws DaoSessionException {

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
	public void update(long id, RateValue t) throws DaoSessionException {
		try {
			HibernateFactory factory = new HibernateFactory();
			SessionFactory sessionFactory;
			sessionFactory = factory.factory();
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			Query query = session.createQuery(
					"UPDATE RateValue SET value =:value, date=:date, currency_id =:currency_id WHERE id =:id");
			query.setParameter("value", t.getValue());
			query.setParameter("date", t.getDate());
			query.setParameter("currency_id", t.getCodes().getId());
			query.setParameter("id", id);
			query.executeUpdate();
			session.getTransaction().commit();
			session.close();
		} catch (Exception e) {
			throw new DaoSessionException("Błąd w tworzeniu sesji połączenia z bazą danych", e);
		}

	}

	public RateValue get(Currency currency, LocalDate date) throws DaoSessionException {
		try {
			CurrencyCodeDao currencyCodeDao = new CurrencyCodeDao();
			HibernateFactory factory = new HibernateFactory();
			SessionFactory sessionFactory;
			sessionFactory = factory.factory();
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			Query query = session.createQuery("FROM RateValue WHERE date =:date AND currency_id=:code_id");
			query.setParameter("date", date);
			query.setParameter("code_id", currencyCodeDao.get(currency).getId());
			RateValue rateValue = (RateValue) query.uniqueResult();
			session.getTransaction().commit();
			session.close();
			return rateValue;
		} catch (Exception e) {
			throw new DaoSessionException("Błąd w tworzeniu sesji połączenia z bazą danych", e);
		}

	}

}
