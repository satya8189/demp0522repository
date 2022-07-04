package com.example.demo0522.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo0522.model.ErrorBean;


@ControllerAdvice
public class ExceptionController {
	
	

	    @ExceptionHandler(value = { Exception.class })
	    public @ResponseBody ErrorBean handleRuntimeException(Exception ex) {
		 
		 ErrorBean errorBean= new ErrorBean();
		 errorBean.setError(ex.getMessage());
		 errorBean.setMessage("Exception in code");
		 errorBean.setStatus("400");
		 return errorBean;
	    }
	    
	    @ExceptionHandler(value = { DemoException.class })
	    public @ResponseBody ErrorBean handleRuntimeException2(Exception ex) {
		 
		 ErrorBean errorBean= new ErrorBean();
		 errorBean.setError(ex.getMessage());
		 errorBean.setMessage("DemoException in code");
		 errorBean.setStatus("400");
		 return errorBean;
	    }
	    
	 
	 
	 
}
