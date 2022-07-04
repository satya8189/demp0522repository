package com.example.demo0522.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.demo0522.dao.CustomRepository;
import com.example.demo0522.dao.UserRepository;
import com.example.demo0522.entity.User;
import com.example.demo0522.model.UserBean;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@Service
public class UserService {
	
	@Autowired
	UserRepository userRepository;
	@Autowired
	CustomRepository  customRepository;
	
	@Bean
	public RestTemplate restTemplate() {
		return  new RestTemplate();
	}

	public UserBean getDetails(Long userId) {
		
		Optional<User> userOptional=userRepository.findById(userId);
		
			User user=userOptional.isPresent()?userOptional.get():null;
	//	User user=customRepository.getUserDateWithHql(userId);
		//userRepository.getUserDatawithFirstNameAndLastName("", "");
		

		
		UserBean userBean=new UserBean();
		if(user!=null) {
			
			userBean.setFirstName(user.getFirstName());
			userBean.setLastName(user.getLastName());
			userBean.setQulification(user.getQualification());
			userBean.setUserId(user.getUserId());
			userBean.setAddress(user.getAddress());
			userBean.setStatus(user.getStatus());
			//return userBan;
		}
		//UserBean userBean=getOtherServiceUserDetails(userId);
		return userBean;
	}
	@HystrixCommand(fallbackMethod = "fallbackGetOtherServiceUserDetails", commandProperties = {
			   @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1")
			})
	public  UserBean getOtherServiceUserDetails(Long userId){
		String url="http://localhost:8080/demo/userDetails/"+userId;
		
		ResponseEntity<UserBean> result=restTemplate().exchange(url, HttpMethod.GET, null, UserBean.class);
		
		UserBean userBean=result.getBody();
		return userBean;
		
	}
	public  UserBean fallbackGetOtherServiceUserDetails(){
		UserBean userBean=new UserBean();
		userBean.setStatus("400");
		return userBean;
	}

	public UserBean saveUser(UserBean userBean) {
	
		User user= new User();
		user.setFirstName(userBean.getFirstName());
		user.setLastName(userBean.getLastName());
		user.setQualification(userBean.getQulification());
		user.setAddress(userBean.getAddress());
		user.setStatus(userBean.getStatus());
		userRepository.save(user);
		
		userBean.setUserId(user.getUserId());
		return userBean;
	}
	public UserBean updateUser(UserBean userBean) {
		
		Optional<User> userOptional=userRepository.findById(userBean.getUserId());
		
		User user=userOptional.isPresent()?userOptional.get():null;
		if(user!=null) {
		user.setFirstName(userBean.getFirstName());
		user.setLastName(userBean.getLastName());
		user.setQualification(userBean.getQulification());
		user.setAddress(userBean.getAddress());
		user.setStatus(userBean.getStatus());
		
		userRepository.save(user);
		}
		
		
		userBean.setUserId(user.getUserId());
		return userBean;
	}

	public List<UserBean> getDetailsList() {
	
		List<User> userList=(List<User>) userRepository.findAll();
		
		List<UserBean> userBeanList=new ArrayList<>();
		for(User user:userList) {
			
			UserBean userBan=new UserBean();
			
			userBan.setFirstName(user.getFirstName());
			userBan.setLastName(user.getLastName());
			userBan.setQulification(user.getQualification());
			userBan.setUserId(user.getUserId());
			userBan.setAddress(user.getAddress());
			userBan.setStatus(user.getStatus());
			
			userBeanList.add(userBan);
		}
		return userBeanList;
	}

	public void deleteUser(Long userId) {
		userRepository.deleteById(userId);
		
	}
}
