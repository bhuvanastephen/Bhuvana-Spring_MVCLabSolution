package com.greatlearning.smsapp_demo.service;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.greatlearning.smsapp_demo.entity.Student;
@Repository	
public class StudentServiceImpl implements StudentService {

	private SessionFactory sessionfactory;
	
	private Session session;
	
	@Autowired
	public StudentServiceImpl(SessionFactory sessionfactory) {
		this.sessionfactory = sessionfactory;
		try {
			session=sessionfactory.getCurrentSession();
		}catch(HibernateException  ex){
			session = sessionfactory.openSession();
		}
	}

	@Override
	@Transactional
	public List<Student> findAll() {
		Transaction tx = session.beginTransaction();

		// find all the records from the database table
		List<Student> students=session.createQuery("from Student").list();

		tx.commit();


		return students;
	}

	@Override
	@Transactional
	public Student findById(int id) {
		Student student = new Student();
		
		Transaction tx = session.beginTransaction();

		// find record with Id from the database table
		student = session.get(Student.class, id);

		tx.commit();


		return student;
	}

	@Override
	@Transactional
	public void save(Student student) {
		Transaction tx = session.beginTransaction();

		// save transaction
		session.saveOrUpdate(student);
		tx.commit();
	}

	@Override
	@Transactional
	public void deleteById(int id) {
		Transaction tx = session.beginTransaction();

		// get transaction
		Student student = session.get(Student.class, id);

		// delete record
		session.delete(student);

		tx.commit();
	}

}
