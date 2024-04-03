package Com.hibernate.Hibernate;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 * Hello world!
 *
 */
public class App 
{
	Scanner sc= new Scanner(System.in);
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        App a=new App();
//      a.insert();
//      a.insert2();
//      a.displaybyId();
        a.displaybyState();
//        a.Show_all();
//        a.update();
//        a.delete();
    }
    
    
    //Method Too insert Value as per the Old Way
    public void insert()
    {
    	
    	Configuration cfg=new Configuration();
    	cfg.configure("hibernate.cfg.xml");
    	SessionFactory factory=cfg.buildSessionFactory();
    	
    	Cricketers c = new Cricketers();
    	System.out.println("Enter Your Name");
    	c.setName(sc.next());
    	System.out.println("Enter No of Matches Played");
    	c.setMatch_played(sc.nextInt());
    	System.out.println("Enter total no of Score");
    	c.setTotal_Score(sc.nextInt());
    	System.out.println("Enter Players State");
    	c.setState(sc.next());
    	
    	Session session=factory.openSession();
    	Transaction tx=session.beginTransaction();
    	session.save(c);
    	tx.commit();
    	session.close();
    }
    
    
    //method too insert the value through new way
    public void insert2() 
    {    	
		Session session=HibernateUtil.getSession();
		
		Cricketers c = new Cricketers();
    	System.out.println("Enter Your Name");
    	c.setName(sc.next());
    	System.out.println("Enter No of Matches Played");
    	c.setMatch_played(sc.nextInt());
    	System.out.println("Enter total no of Score");
    	c.setTotal_Score(sc.nextInt());
    	System.out.println("Enter Players State");
    	c.setState(sc.next());
    	
    	Transaction tx=session.beginTransaction();
    	session.save(c);
    	tx.commit();
    	session.close();
    }
    
    
    public void displaybyId()
    {
    	Session session=HibernateUtil.getSession();
    	Transaction tx=session.beginTransaction();
    	System.out.println("Enter Cricketers Id ");
    	int id=sc.nextInt();
    	
    	Cricketers c=session.get(Cricketers.class, id);
    	
    	System.out.println("Id of The Player =  "+c.getId());
    	System.out.println("Name of The Player  =   "+c.getName());
    	System.out.println("No of Matches Played  =  "+c.getMatch_played());
    	System.out.println("Total no of Score  =  "+c.getTotal_Score());
    	System.out.println("Players State  =  "+c.getState());
    	tx.commit();
    	session.close();
    }
    
    
    
    public void displaybyState() 
    {
		Session session=HibernateUtil.getSession();
		Transaction tx=session.beginTransaction();
		System.out.println("Enter Players State :");
		String State=sc.next();
		
		List<Cricketers> li=session.createQuery("FROM Cricketers  WHERE Cricketers_state = :state").
				setParameter("state", State)
				.getResultList();
		
		System.out.println("Cri_Id\tName\tMatch_played\tTotal_score\tState");
    	for(Cricketers c:li)
    	{
    		
    		System.out.println(c.getId()+"\t"+c.getName()+"\t"+c.getMatch_played()+"\t\t"+c.getTotal_Score()+"\t\t"+c.getState());      	
    	}
	
	}
    
    
    
    public void Show_all()
    {
    	Session session=HibernateUtil.getSession();
    	Transaction tx=session.beginTransaction();
    	
    	Query Q=session.createQuery("from Cricketers");
    	
    	List<Cricketers> li=Q.getResultList();
    	
    	System.out.println("Cri_Id\tName\tMatch_played\tTotal_score\tState");
    	for(Cricketers c:li)
    	{
    		
    		System.out.println(c.getId()+"\t"+c.getName()+"\t"+c.getMatch_played()+"\t\t"+c.getTotal_Score()+"\t\t"+c.getState());      	
    	}
    	tx.commit();
    	session.close();
    	
    }
    
    public void update() 
    {
		Session session=HibernateUtil.getSession();
		Transaction tx=session.beginTransaction();
		
		System.out.println("Enter Cricketers Id to make Update ");
		int Id=sc.nextInt();
		
		Cricketers c=session.get(Cricketers.class, Id);
		
		System.out.println("Enter New Score :");
		c.setTotal_Score(sc.nextInt());	
		
		tx.commit();
		session.close();
	}
    
    public void delete() 
    {
		Session session=HibernateUtil.getSession();
		Transaction tx=session.beginTransaction();
		
		System.out.println("Enter Cricketers Id to Delete ");
		int Id=sc.nextInt();
		
		Cricketers c=session.get(Cricketers.class, Id);
		
		session.delete(c);
		
		tx.commit();
		session.close();
		
	}
    
}
