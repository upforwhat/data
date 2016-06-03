package com.hibernate.model;

import java.sql.ResultSet;
import java.util.List;

import javax.swing.text.html.HTMLDocument.HTMLReader.IsindexAction;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opensymphony.xwork2.Result;

/**
 * A data access object (DAO) providing persistence and search support for
 * Baoxiu entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.hibernate.model.Baoxiu
 * @author MyEclipse Persistence Tools
 */
public class BaoxiuDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(BaoxiuDAO.class);
	// property constants
	public static final String BAOXIUSHIJIAN = "baoxiushijian";
	public static final String CHULISHIJIAN = "chulishijian";
	public static final String STATE = "state";
	public static final String BAOXIU_DOMITORYID = "baoxiuDomitoryid";
	public static final String BAOXIU_STUDENT_ID = "baoxiuStudentId";
	public static final String BAOXIU_STUDENT_NAME = "baoxiuStudentName";
	public static final String BAOXIU_DOMITORY_NAME = "baoxiuDomitoryName";
	public static final String BAOXIU_XIANMU = "baoxiuXianmu";
	public static final String BAOXIU_BUILDING_NAME = "baoxiuBuildingName";
	public static final String BAOXIU_BUILDING_ID = "baoxiuBuildingId";
	public static final String BAOXIU_ONE = "baoxiuOne";
	public static final String BAOXIU_TWO = "baoxiuTwo";
	public static final String BAOXIU_THREEE = "baoxiuThreee";
Session sess=null;
	public int save(Baoxiu transientInstance) {
		log.debug("saving Baoxiu instance");
		int rs=0;
		try {
			try{
				sess=getSession();
				sess.beginTransaction();
			getSession().save(transientInstance);
			rs=1;
			sess.getTransaction().commit();
			}catch(Exception e){
				sess.getTransaction().rollback();
				rs=0;
				e.printStackTrace();
				
			}
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
		return rs;
	}

	public int delete(Baoxiu persistentInstance) {
		log.debug("deleting Baoxiu instance");
		int rs=0;
		try {
			
			try{
				
			
			sess=getSession();
			sess.beginTransaction();
		getSession().delete(persistentInstance);
		rs=1;
			sess.getTransaction().commit();
			}
			catch(Exception e){
				sess.getTransaction().rollback();
				rs=0;
				e.printStackTrace();
			}
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
		return rs;
	}

	public Baoxiu findById(java.lang.Integer id) {
		log.debug("getting Baoxiu instance with id: " + id);
		try {
			Baoxiu instance = (Baoxiu) getSession().get(
					"com.hibernate.model.Baoxiu", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Baoxiu instance) {
		log.debug("finding Baoxiu instance by example");
		try {
			List results = getSession()
					.createCriteria("com.hibernate.model.Baoxiu")
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
		log.debug("finding Baoxiu instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Baoxiu as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByMyProperty(String where) {
	
		try {
			String queryString = "from Baoxiu as model "+where;
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByBaoxiushijian(Object baoxiushijian) {
		return findByProperty(BAOXIUSHIJIAN, baoxiushijian);
	}

	public List findByChulishijian(Object chulishijian) {
		return findByProperty(CHULISHIJIAN, chulishijian);
	}

	public List findByState(Object state) {
		return findByProperty(STATE, state);
	}

	public List findByBaoxiuDomitoryid(Object baoxiuDomitoryid) {
		return findByProperty(BAOXIU_DOMITORYID, baoxiuDomitoryid);
	}

	public List findByBaoxiuStudentId(Object baoxiuStudentId) {
		return findByProperty(BAOXIU_STUDENT_ID, baoxiuStudentId);
	}

	public List findByBaoxiuStudentName(Object baoxiuStudentName) {
		return findByProperty(BAOXIU_STUDENT_NAME, baoxiuStudentName);
	}

	public List findByBaoxiuDomitoryName(Object baoxiuDomitoryName) {
		return findByProperty(BAOXIU_DOMITORY_NAME, baoxiuDomitoryName);
	}

	public List findByBaoxiuXianmu(Object baoxiuXianmu) {
		return findByProperty(BAOXIU_XIANMU, baoxiuXianmu);
	}

	public List findByBaoxiuBuildingName(Object baoxiuBuildingName) {
		return findByProperty(BAOXIU_BUILDING_NAME, baoxiuBuildingName);
	}

	public List findByBaoxiuBuildingId(Object baoxiuBuildingId) {
		return findByProperty(BAOXIU_BUILDING_ID, baoxiuBuildingId);
	}

	public List findByBaoxiuOne(Object baoxiuOne) {
		return findByProperty(BAOXIU_ONE, baoxiuOne);
	}

	public List findByBaoxiuTwo(Object baoxiuTwo) {
		return findByProperty(BAOXIU_TWO, baoxiuTwo);
	}

	public List findByBaoxiuThreee(Object baoxiuThreee) {
		return findByProperty(BAOXIU_THREEE, baoxiuThreee);
	}

	public List findAll() {
		log.debug("finding all Baoxiu instances");
		try {
			String queryString = "from Baoxiu";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Baoxiu merge(Baoxiu detachedInstance) {
		log.debug("merging Baoxiu instance");
		try {
			Baoxiu result = (Baoxiu) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Baoxiu instance) {
		log.debug("attaching dirty Baoxiu instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Baoxiu instance) {
		log.debug("attaching clean Baoxiu instance");
		try {
			getSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
	public int getSearchTotal( String shebeiBuildingName){
		String hql=" select count(*) from Shebei as sb where sb.shebeiBuildingName='"+shebeiBuildingName+"'";
		int rows=0;
	 Query query =getSession().createQuery(hql);
	Long temp=(Long)query.list().get(0);
	rows=temp.intValue(); 
	return rows;
	}
	
	
	//方法一
	public int getTotal(Object parameter[], String appendhql) {
		int rows = 0;
		String hql = " select count(*) from Baoxiu as model ";

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
		 String defalutsort="baoxiushijian";
		 String defalutorder="desc";
		String hql="from Baoxiu as model ";
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
       sess=getSession();
       sess.getTransaction().begin();
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