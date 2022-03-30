package nowewaluty.sources.session.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import nowewaluty.Currency;
import nowewaluty.exceptions.DaoSessionException;
import nowewaluty.objects.db.CurrencyCode;
import nowewaluty.sources.session.HibernateFactory;
import nowewaluty.strategies.Dao;

public class CurrencyCodeDao implements Dao<CurrencyCode> {

	@Override
	public CurrencyCode get(long id) throws DaoSessionException {

		try {
			HibernateFactory factory = new HibernateFactory();
			SessionFactory sessionFactory;
			sessionFactory = factory.factory();
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			Query query = session.createQuery("FROM CurrencyCode WHERE currency_id = :id");
			query.setParameter("id", id);
			CurrencyCode currencyCode = (CurrencyCode) query.uniqueResult();
			session.getTransaction().commit();
			session.close();
			return currencyCode;
		} catch (Exception e) {
			throw new DaoSessionException("Błąd w tworzeniu sesji połączenia z bazą danych", e);
		}

	}

	@Override
	public void save(CurrencyCode t) throws DaoSessionException {

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
	public void update(long id, CurrencyCode t) throws DaoSessionException {
		try {
			HibernateFactory factory = new HibernateFactory();
			SessionFactory sessionFactory;
			sessionFactory = factory.factory();
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			Query query = session
					.createQuery("UPDATE CurrencyCode SET currency_code =:currency_code WHERE currency_id=:id");
			query.setParameter("currency_code", t.getCode());
			query.setParameter("id", id);
			query.executeUpdate();
			session.getTransaction().commit();
			session.close();
		} catch (Exception e) {
			throw new DaoSessionException("Błąd w tworzeniu sesji połączenia z bazą danych", e);
		}

	}

	public CurrencyCode get(Currency code) throws DaoSessionException {
		try {
			HibernateFactory factory = new HibernateFactory();
			SessionFactory sessionFactory;
			sessionFactory = factory.factory();
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			Query query = session.createQuery("FROM CurrencyCode WHERE currency_code=:code");
			query.setParameter("code", code.code.toUpperCase());
			CurrencyCode currencyCode = (CurrencyCode) query.uniqueResult();
			session.getTransaction().commit();
			session.close();
			return currencyCode;
		} catch (Exception e) {
			throw new DaoSessionException("Błąd w tworzeniu sesji połączenia z bazą danych", e);
		}

	}

}
