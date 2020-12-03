/**
 * 
 */
package com.seth.jms.model;

import java.io.Serializable;
import java.util.UUID;

/**
 * @author heseth
 *
 */
public class HelloWorldMessage implements Serializable{
	
	private static final long serialVersionUID = 2918986242579378376L;
	
	private UUID id;
	private String message;
	
	public HelloWorldMessage() {
		
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "HelloWorldMessage [id=" + id + ", message=" + message + "]";
	}
	
	
	
}
