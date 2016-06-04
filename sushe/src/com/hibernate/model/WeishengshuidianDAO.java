package com.hibernate.model;

import java.util.List;

import javax.swing.text.html.HTMLDocument.HTMLReader.IsindexAction;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A data access object (DAO) providing persistence and search support for
 * Weishengshuidian entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.hibernate.model.Weishengshuidian
 * @author MyEclipse Persistence Tools
 */
public class WeishengshuidianDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(WeishengshuidianDAO.class);
	// property constants
	public static final String DOMITORY_ID = "domitoryId";
	public static final String SCORE = "score";
	public static final String SHUI = "shui";
	public static final String DIAN = "dian";
	public static final String JINE = "jine";
	public static final String SHIIJIAN = "shiijian";
	public static final String BUILDING_ID = "buildingId";
	public static final String WSSD_BUILDING_NAME = "wssdBuildingName";
	public static final String WSSD_DOMITORY_NAME = "wssdDomitoryName";
	Session sess = null;

	public void save(Weishengshuidian transientInstance) {
		log.debug("saving Weishengshuidian instance");
		try {
			try {
				sess = getSession();
				sess.getTransaction().begin();
				sess.save(transientInstance);
				getSession().getTransaction().commit();
			} catch (Exception e) {
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

	public void delete(Weishengshuidian persistentInstance) {
		log.debug("deleting Weishengshuidian instance");
		try {
			try {
				sess = getSession();

				sess.beginTransaction();
				sess.delete(persistentInstance);
				getSession().getTransaction().commit();
			} catch (Exception e) {
				e.printStackTrace();
				sess.getTransaction().rollback();
				sess.close();
			}

			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Weishengshuidian findById(java.lang.Integer id) {
		log.debug("getting Weishengshuidian instance with id: " + id);
		try {
			Weishengshuidian instance = (Weishengshuidian) getSession().get(
					"com.hibernate.model.Weishengshuidian", id);
			
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Weishengshuidian instance) {
		log.debug("finding Weishengshuidian instance by example");
		try {
			List results = getSession()
					.createCriteria("com.hibernate.model.Weishengshuidian")
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
		log.debug("finding Weishengshuidian instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Weishengshuidian as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByMYProperty(String hql, int[] value) {
		log.debug("finding Weishengshuidian instance with property: " + hql
				+ ", value: " + value);
		try {

			String queryString = "from Weishengshuidian as model " + hql;
			Query queryObject = getSession().createQuery(queryString);
			for (int i = 0; i < value.length; i++) {
				queryObject.setParameter(i, value[i]);
			}
	
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByDomitoryId(Object domitoryId) {
		return findByProperty(DOMITORY_ID, domitoryId);
	}

	public List findByScore(Object score) {
		return findByProperty(SCORE, score);
	}

	public List findByShui(Object shui) {
		return findByProperty(SHUI, shui);
	}

	public List findByDian(Object dian) {
		return findByProperty(DIAN, dian);
	}

	public List findByJine(Object jine) {
		return findByProperty(JINE, jine);
	}

	public List findByShiijian(Object shiijian) {
		return findByProperty(SHIIJIAN, shiijian);
	}

	public List findByBuildingId(Object buildingId) {
		return findByProperty(BUILDING_ID, buildingId);
	}

	public List findByWssdBuildingName(Object wssdBuildingName) {
		return findByProperty(WSSD_BUILDING_NAME, wssdBuildingName);
	}

	public List findByWssdDomitoryName(Object wssdDomitoryName) {
		return findByProperty(WSSD_DOMITORY_NAME, wssdDomitoryName);
	}

	public List findAll() {
		log.debug("finding all Weishengshuidian instances");
		try {sess=getSession();
			String queryString = "from Weishengshuidian";
			Query queryObject = getSession().createQuery(queryString);
			sess.close();
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Weishengshuidian merge(Weishengshuidian detachedInstance) {
		log.debug("merging Weishengshuidian instance");
		try {
			Weishengshuidian result = (Weishengshuidian) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Weishengshuidian instance) {
		log.debug("attaching dirty Weishengshuidian instance");
		try {
			getSession().saveOrUpdate(instance);
		
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Weishengshuidian instance) {
		log.debug("attaching clean Weishengshuidian instance");
		try {
			getSession().buildLockRequest(LockOptions.NONE).lock(instance);
			
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}


	public List StuFind(String sort, String order, int domitoryid) {
		log.debug("finding Weishengshuidian instance with property: " + sort
				+ ", value: " + sort);
		try {
			String hqla = " from Weishengshuidian as w where w.domitoryId=? ";
			if (!isInvalid(sort)) {
				hqla += " order by " + sort;
			}
			if (!isInvalid(order)) {
				hqla +="\t"+order;
			} else {
				hqla += " order by w.shiijian desc ";
			}

			Query q = getSession().createQuery(hqla);
			q.setParameter(0, domitoryid);
		
			
			return q.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
	public List findByMYProperty(String hql, Object[] value) {
		log.debug("finding Weishengshuidian instance with property: " + hql
				+ ", value: " + value);
		try {

			String queryString =hql;
			Query queryObject = getSession().createQuery(queryString);
			for (int i = 0; i < value.length; i++) {
				queryObject.setParameter(i, value[i]);
			}
			queryObject.setMaxResults(2);
			
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
	
	public int getTotal(Object parameter[], String appendhql) {
		int rows = 0;
		String hql = " select count(*) from Weishengshuidian as model ";

		if (!isInvalid(appendhql)) {
			hql += appendhql;
		}
		Query query = getSession().createQuery(hql);
		if (parameter.length > 0) {
			for (int i = 0; i < parameter.length; i++) {
				query.setParameter(i, parameter[i]);
			}

		}
		Long temp = (Long) query.list().get(0);
		rows = temp.intValue();
	
		return rows;
	}
	 // 根据第几页获取，每页几行获取数据  
		public List getList(String appendhql,Object[]parameter,String page, String rows,String sort,String order) {  
	     //当为缺省值的时候进行赋值  
			 String defalutsort="shiijian";
			 String defalutorder="desc";
			String hql="from Weishengshuidian as model ";
			if(!isInvalid(appendhql)){
				hql+=appendhql;
			}
		
			if(!isInvalid(sort)){
				defalutsort=sort;	
			}
			hql+=" order by "+defalutsort;
			if(!isInvalid(order)){
				defalutorder=order;	
			}
			hql+="\t"+defalutorder;
	       int currentpage = Integer.parseInt((page == null || page == "0") ? "1": page);//第几页  
	       int pagesize = Integer.parseInt((rows == null || rows == "0") ? "10": rows);//每页多少行  
	       sess =getSession();
	       sess.beginTransaction();
	       Query query=getSession().createQuery(hql).setFirstResult((currentpage - 1) * pagesize).setMaxResults(pagesize); 
	       if (parameter.length > 0) {
				for (int i = 0; i < parameter.length; i++) {
					System.out.println(parameter[i]);
					query.setParameter(i, parameter[i]);
				}

			}
	       sess.getTransaction().commit();
	       System.out.println(hql);
	       List list = query.list();  
	       if(sess!=null){
	    	   sess.close();
	       }
	    
	       return list;  
	   } 
	
	private boolean isInvalid(String value) {
		return (value == null || value.length() == 0);
	}

	
	
	
	
}