package nowewaluty.sources.session.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

import nowewaluty.Currency;
import nowewaluty.exceptions.DaoSessionException;
import nowewaluty.objects.db.CurrencyCode;
import nowewaluty.sources.session.HibernateFactory;
import nowewaluty.strategies.Dao;

@Component
public class CurrencyCodeDao implements Dao<CurrencyCode> {

	SessionFactory sessionFactory;

	public CurrencyCodeDao(HibernateFactory factory) throws DaoSessionException {
		try {
			sessionFactory = factory.factory();
		} catch (Exception e) {
			throw new DaoSessionException("Błąd w tworzeniu sesji połączenia z bazą danych", e);
		}
	}

	@Override
	public CurrencyCode get(long id) {

		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Query query = session.createQuery("FROM CurrencyCode WHERE currency_id = :id");
		query.setParameter("id", id);
		CurrencyCode currencyCode = (CurrencyCode) query.uniqueResult();
		session.getTransaction().commit();
		session.close();
		return currencyCode;

	}

	@Override
	public void save(CurrencyCode t) {

		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(t);
		session.getTransaction().commit();
		session.close();

	}

	@Override
	public void update(long id, CurrencyCode t) {

		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Query query = session
				.createQuery("UPDATE CurrencyCode SET currency_code =:currency_code WHERE currency_id=:id");
		query.setParameter("currency_code", t.getCode());
		query.setParameter("id", id);
		query.executeUpdate();
		session.getTransaction().commit();
		session.close();

	}

	public CurrencyCode get(Currency code) {

		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Query query = session.createQuery("FROM CurrencyCode WHERE currency_code=:code");
		query.setParameter("code", code.code.toUpperCase());
		CurrencyCode currencyCode = (CurrencyCode) query.uniqueResult();
		session.getTransaction().commit();
		session.close();
		return currencyCode;

	}

	public List<CurrencyCode> getAll() {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Query query = session.getNamedQuery("CurrencyCode_AllCodes");
		List<CurrencyCode> codes = query.getResultList();
		return codes;

	}

}
