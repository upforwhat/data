package com.service.shebei;

import hibernateFactory.HibernateSessionFactory;

import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.hibernate.Query;
import org.hibernate.Session;

import com.hibernate.model.BaseHibernateDAO;
import com.hibernate.model.Shebei;

public class updateshebeistate extends BaseHibernateDAO {
	// ��������
	Session sess = null;

	public void updatestate() {
		// ˼· �ҳ����е��豸 Ȼ����� �������� ����ǰʱ������豸��Чʱ��ʱ�����豸��״̬Ϊ���� over
		String hql = "from Shebei ";
		sess = getSession();
		sess.getTransaction().begin();
		Query query = sess.createQuery(hql);
		sess.getTransaction().commit();
	
		List<Shebei> list = query.list();
		Date sysdate = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		if (list.size() > 0) {
			for (Shebei shebei : list) {
				Date sqldate = null;
				try {
					sqldate = sdf.parse(shebei.getShebeiYouxiaoshijian());
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				if (sqldate.getTime() < sysdate.getTime()) {
					
					try{
						sess.getTransaction().begin();
						shebei.setShebeiState("����");
					sess.update(shebei);
					sess.getTransaction().commit();}
					catch(Exception e){
						e.printStackTrace();
						sess.getTransaction().rollback();
					}
				}
			}

		}
		if (sess != null) {
			sess.close();
		}
	
	}

}
