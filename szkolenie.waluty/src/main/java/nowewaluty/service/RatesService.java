package nowewaluty.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nowewaluty.objects.db.RateValue;
import nowewaluty.sources.session.HibernateFactory;
import nowewaluty.sources.session.dao.RateValueDao;
import nowewaluty.strategies.Dao;

@Service
public class RatesService {

	@Autowired
	private Dao<RateValue> rateValueDao = new RateValueDao(new HibernateFactory());
	private RateValue rateValue = new RateValue();

	public RateValue get() {
		return rateValueDao.get(36795);
	}
}
