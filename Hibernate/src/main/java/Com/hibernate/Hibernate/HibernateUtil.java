package Com.hibernate.Hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil 
{
	private static final SessionFactory sessionfactory= getSessionFactory();
	
	private static SessionFactory getSessionFactory()
	{
		
		try
		{
			return new Configuration().configure("hibernate.cfg.xml").
					addAnnotatedClass(Cricketers.class).
					buildSessionFactory();
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
		return  null;
	}
	
	public static Session getSession()
	{
		return sessionfactory.openSession();
	}
}
