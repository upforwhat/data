package com.hibernate.model;

import java.util.List;
import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A data access object (DAO) providing persistence and search support for
 * Student entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.hibernate.model.Student
 * @author MyEclipse Persistence Tools
 */
public class StudentDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(StudentDAO.class);
	// property constants
	public static final String STUDENT_DOMITORY_ID = "studentDomitoryId";
	public static final String STUDENT_USERNAME = "studentUsername";
	public static final String STUDENT_PASSWORD = "studentPassword";
	public static final String STUDENT_NAME = "studentName";
	public static final String STUDENT_SEX = "studentSex";
	public static final String STUDENT_CLASS = "studentClass";
	public static final String STUDENT_STATE = "studentState";

	public void save(Student transientInstance) {
		log.debug("saving Student instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Student persistentInstance) {
		log.debug("deleting Student instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Student findById(java.lang.Integer id) {
		log.debug("getting Student instance with id: " + id);
		try {
			Student instance = (Student) getSession().get(
					"com.hibernate.model.Student", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Student instance) {
		log.debug("finding Student instance by example");
		try {
			List results = getSession()
					.createCriteria("com.hibernate.model.Student")
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
		log.debug("finding Student instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Student as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByStudentDomitoryId(Object studentDomitoryId) {
		return findByProperty(STUDENT_DOMITORY_ID, studentDomitoryId);
	}

	public List findByStudentUsername(Object studentUsername) {
		return findByProperty(STUDENT_USERNAME, studentUsername);
	}

	public List findByStudentPassword(Object studentPassword) {
		return findByProperty(STUDENT_PASSWORD, studentPassword);
	}

	public List findByStudentName(Object studentName) {
		return findByProperty(STUDENT_NAME, studentName);
	}

	public List findByStudentSex(Object studentSex) {
		return findByProperty(STUDENT_SEX, studentSex);
	}

	public List findByStudentClass(Object studentClass) {
		return findByProperty(STUDENT_CLASS, studentClass);
	}

	public List findByStudentState(Object studentState) {
		return findByProperty(STUDENT_STATE, studentState);
	}

	public List findAll() {
		log.debug("finding all Student instances");
		try {
			String queryString = "from Student";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Student merge(Student detachedInstance) {
		log.debug("merging Student instance");
		try {
			Student result = (Student) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Student instance) {
		log.debug("attaching dirty Student instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Student instance) {
		log.debug("attaching clean Student instance");
		try {
			getSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}