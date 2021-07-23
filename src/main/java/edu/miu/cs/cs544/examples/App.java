package edu.miu.cs.cs544.examples;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class App {
	private static SessionFactory sessionFactory;

	static {
		sessionFactory = HibernateUtils
				.getSessionFactory(Arrays.asList(Customer.class, Orderes.class, OrderLine.class, Product.class));
	}

	public static void main(String[] args) {
		Session session = null;
		Transaction tx = null;
		//////////////////////////////////
		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

			Product product1 = new Product("Car", "Nice car");
			Product product2 = new Product("Bike", "Nice bike");

			OrderLine orderLine1 = new OrderLine();
			orderLine1.setProduct(product1);
			orderLine1.setQuantity(6);

			OrderLine orderLine2 = new OrderLine();
			orderLine2.setProduct(product2);
			orderLine2.setQuantity(12);

			Orderes order = new Orderes();
			order.setDate(LocalDate.now());
			order.addOrderLine(orderLine1);
			order.addOrderLine(orderLine2);

			Customer customer1 = new Customer("Jorge", "Gil");
			customer1.addOrder(order);

			session.persist(customer1);

			tx.commit();
		} catch (HibernateException e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}

		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			// retrieve all persons
			List<Customer> customerList = session.createQuery("from Customer", Customer.class).list();
			System.out.println("//////////////////////");
			for (Customer e : customerList) {
				System.out.println(e);
			}
			tx.commit();
		} catch (HibernateException e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		// Close the SessionFactory (not mandatory)
		sessionFactory.close();
	}
}
