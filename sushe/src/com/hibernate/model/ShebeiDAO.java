package com.hibernate.model;

import java.util.List;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A data access object (DAO) providing persistence and search support for
 * Shebei entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.hibernate.model.Shebei
 * @author MyEclipse Persistence Tools
 */
public class ShebeiDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(ShebeiDAO.class);
	// property constants
	public static final String SHEBEI_BUILDING_ID = "shebeiBuildingId";
	public static final String SHEBEI_NAME = "shebeiName";
	public static final String SHEBEI_STATE = "shebeiState";
	public static final String SHEBEI_GOUMAISHIJIAN = "shebeiGoumaishijian";
	public static final String SHEBEI_YOUXIAOSHIJIAN = "shebeiYouxiaoshijian";
	public static final String SHEBEI_ZUIJINJIACHASHIJIAN = "shebeiZuijinjiachashijian";
	public static final String SHEBEI_BIANHAO = "shebeiBianhao";
	public static final String SHEBEI_BUILDING_NAME = "shebeiBuildingName";
	public static final String SHEBEI_THREE = "shebeiThree";
	Session sess;

	public void save(Shebei transientInstance) {
		log.debug("saving Shebei instance");
		try {
			try{getSession().getTransaction().begin();
			getSession().save(transientInstance);
			getSession().getTransaction().commit();
			}catch(Exception e){
		e.printStackTrace();
		getSession().getTransaction().rollback();
			}
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Shebei persistentInstance) {
		log.debug("deleting Shebei instance");
		try {
			try{
				getSession().getTransaction().begin();
			getSession().delete(persistentInstance);
			getSession().getTransaction().commit();
			}catch(
				Exception e){e.printStackTrace();
				getSession().getTransaction().rollback();
				
			}
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Shebei findById(java.lang.Integer id) {
		log.debug("getting Shebei instance with id: " + id);
		try {
			Shebei instance = (Shebei) getSession().get(
					"com.hibernate.model.Shebei", id);
			return instance;
		
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Shebei instance) {
		log.debug("finding Shebei instance by example");
		try {
			List results = getSession()
					.createCriteria("com.hibernate.model.Shebei")
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
		log.debug("finding Shebei instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Shebei as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByShebeiBuildingId(Object shebeiBuildingId) {
		return findByProperty(SHEBEI_BUILDING_ID, shebeiBuildingId);
	}

	public List findByShebeiName(Object shebeiName) {
		return findByProperty(SHEBEI_NAME, shebeiName);
	}

	public List findByShebeiState(Object shebeiState) {
		return findByProperty(SHEBEI_STATE, shebeiState);
	}

	public List findByShebeiGoumaishijian(Object shebeiGoumaishijian) {
		return findByProperty(SHEBEI_GOUMAISHIJIAN, shebeiGoumaishijian);
	}

	public List findByShebeiYouxiaoshijian(Object shebeiYouxiaoshijian) {
		return findByProperty(SHEBEI_YOUXIAOSHIJIAN, shebeiYouxiaoshijian);
	}

	public List findByShebeiZuijinjiachashijian(Object shebeiZuijinjiachashijian) {
		return findByProperty(SHEBEI_ZUIJINJIACHASHIJIAN,
				shebeiZuijinjiachashijian);
	}

	public List findByShebeiBianhao(Object shebeiBianhao) {
		return findByProperty(SHEBEI_BIANHAO, shebeiBianhao);
	}

	public List findByShebeiBuildingNamePage(String hql,String page,String rows) {
	;
		 int currentpage = Integer.parseInt((page == null || page == "0") ? "1": page);//第几页  
	        int pagesize = Integer.parseInt((rows == null || rows == "0") ? "10": rows);//每页多少行  
	        List list = getSession().createQuery(hql)  
                    .setFirstResult((currentpage - 1) * pagesize).setMaxResults(pagesize).list();  
     return list;
	}
	public int getSearchTotal( String shebeiBuildingName){
		String hql=" select count(*) from Shebei as sb where sb.shebeiBuildingName='"+shebeiBuildingName+"'";
		int rows=0;
		 sess = getSession();
		 sess.getTransaction().begin();
	 Query query =getSession().createQuery(hql);
	 sess.getTransaction().commit();
	Long temp=(Long)query.list().get(0);
	if(sess!=null){
		sess.close();
	}
	rows=temp.intValue(); 
	return rows;
	}

	public List findByShebeiThree(Object shebeiThree) {
		return findByProperty(SHEBEI_THREE, shebeiThree);
	}

	public List findAll() {
		log.debug("finding all Shebei instances");
		try {
			String queryString = "from Shebei";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Shebei merge(Shebei detachedInstance) {
		log.debug("merging Shebei instance");
		try {
			Shebei result = (Shebei) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Shebei instance) {
		log.debug("attaching dirty Shebei instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Shebei instance) {
		log.debug("attaching clean Shebei instance");
		try {
			getSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
	
	
	
	// 统计一共有多少条数据
	
	
	
	//方法一
		public int getTotal(){
			String hql=" select count(*) from Shebei";
			int rows=0;
		 Query query =getSession().createQuery(hql);
		Long temp=(Long)query.list().get(0);
		rows=temp.intValue(); 
		return rows;
		}
	//方法二
	
	public int getNum(){
		String hql="from Shebei";
		int rows=0;
	 Query query =getSession().createQuery(hql);
	 List list=query.list();
	 rows =list.size();
	 getSession().close();
	 return rows;
	}
	public List<Shebei> queryByPage( String hql, int offset,int pageSize){
	List<Shebei> list=null;
		try{
			getSession().beginTransaction();
		Query query = getSession().createQuery(hql).setFirstResult(offset).setMaxResults(pageSize);
		list=query.list();
		getSession().getTransaction().commit();
	}
	catch(Exception e){
		getSession().getTransaction().rollback();
		e.printStackTrace();
	}
		return list;
		
	}
	 // 根据第几页获取，每页几行获取数据  
	public List getShebeiList(String hql,String page, String rows) {  
        
        //当为缺省值的时候进行赋值  
        int currentpage = Integer.parseInt((page == null || page == "0") ? "1": page);//第几页  
        int pagesize = Integer.parseInt((rows == null || rows == "0") ? "10": rows);//每页多少行  
          sess=getSession();
          sess.getTransaction().begin();
          
        List list = getSession().createQuery(hql)  
                       .setFirstResult((currentpage - 1) * pagesize).setMaxResults(pagesize).list();  
  sess.getTransaction().commit();
  if(sess!=null){
	  sess.close();
  }
        return list;  
    } 
}