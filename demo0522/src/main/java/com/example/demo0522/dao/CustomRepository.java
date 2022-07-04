package com.example.demo0522.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.example.demo0522.entity.User;



@Repository
public class CustomRepository {
	
	 @PersistenceContext
	  protected EntityManager entityManager;
	 
	 
	 
	 
	 
	 
	 
	 public User getUserDateWithSql(Long idValue) {
		 String queryString="select * from users where userId=:id";
		 Query query=this.entityManager.createNativeQuery(queryString,User.class);
		 
		 query.setParameter("id", idValue);
		
		User u=(User) query.getSingleResult();
		return u;
	 }
		 
	
	
	 
	 public User getUserDateWithHql(Long idValue) {
		 
		 String queryString="SELECT u FROM User u WHERE u.userId =:userId";
		 Query query=this.entityManager.createQuery(queryString);
		 
		 query.setParameter("userId", idValue);
		
		User u=(User) query.getSingleResult();
		return u;
	 }
	 
	 public User getUserDataWithCriteria(Long idValue) {
		 
		 
		 CriteriaBuilder cb = this.entityManager.getCriteriaBuilder();
 			 
		  CriteriaQuery<User> cq = cb.createQuery(User.class);
		   
		// Root<User> userRoot = cq.from(User.class);
		 
		  List<User> uList=entityManager.createQuery(cq).getResultList();
		  return uList.get(0);
		
	 }
		 
		 /*
		 
	      
	 public List<Users> getData(Long id) {
		 
		 CriteriaBuilder cb = this.entityManager.getCriteriaBuilder();
			 
		  CriteriaQuery<Users> cq = cb.createQuery(Users.class);
		   
		  Root<Users> userRoot = cq.from(Users.class);
		  //ListJoin<Users, UserDetails> tasks = userRoot.join(Users_.);		
		  //Join datosElementoJoin = userRoot.join("users",JoinType.LEFT);
		  //Join datosElementoJoinTreated = cb.treat(datosElementoJoin, UserDetails.class);
		 // Predicate p1=cb.equal(datosElementoJoinTreated.get("pan"),"3344e433");
		//  cq.where(p1);
		  List<Users> uList=entityManager.createQuery(cq).getResultList();
		  return uList;
		  
		  
	    }

	 
	 public Education getEducationByCriteria(Long id) {
		 
		 CriteriaBuilder cb = this.entityManager.getCriteriaBuilder();
		 	 			 
		  CriteriaQuery<Education> cq = cb.createQuery(Education.class);
		 		  
		  Root<Education> item = cq.from(Education.class);
		  item.join("users");
		  
	
		  
		 // List<Predicate> predicates = new ArrayList<Predicate>();
		  
		  Predicate p1=cb.equal(item.get("educationId"),id);
		 // predicates.add(p1);
		  
		
		  cq.where(p1);
		  
		  Education list= entityManager.createQuery(cq).getSingleResult();
		 return list;
	 }
	  	 */
}
