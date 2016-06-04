package com.hibernate.model;

import java.math.BigDecimal;
import java.util.List;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A data access object (DAO) providing persistence and search support for Feilv
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 * 
 * @see com.hibernate.model.Feilv
 * @author MyEclipse Persistence Tools
 */
public class FeilvDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(FeilvDAO.class);
	// property constants
	public static final String SHUIDANWEI = "shuidanwei";
	public static final String DIANDANWEI = "diandanwei";
	Session sess=null;

	public void save(Feilv transientInstance) {
		log.debug("saving Feilv instance");
		try {
			try{
				sess=getSession();
			sess.beginTransaction();
			sess.save(transientInstance);
			sess.getTransaction().commit();
			}catch(Exception e){
				e.printStackTrace();
				sess.getTransaction().rollback();
				sess.close();
			}
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Feilv persistentInstance) {
		log.debug("deleting Feilv instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Feilv findById(java.lang.Integer id) {
		log.debug("getting Feilv instance with id: " + id);
		try {
			Feilv instance = (Feilv) getSession().get(
					"com.hibernate.model.Feilv", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Feilv instance) {
		log.debug("finding Feilv instance by example");
		try {
			List results = getSession()
					.createCriteria("com.hibernate.model.Feilv")
					.add(Example.create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding Feilv instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Feilv as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByShuidanwei(Object shuidanwei) {
		return findByProperty(SHUIDANWEI, shuidanwei);
	}

	public List findByDiandanwei(Object diandanwei) {
		return findByProperty(DIANDANWEI, diandanwei);
	}

	public List findAll() {
		log.debug("finding all Feilv instances");
		try {
			String queryString = "from Feilv";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Feilv merge(Feilv detachedInstance) {
		log.debug("merging Feilv instance");
		try {
			Feilv result = (Feilv) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Feilv instance) {
		log.debug("attaching dirty Feilv instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Feilv instance) {
		log.debug("attaching clean Feilv instance");
		try {
			getSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}