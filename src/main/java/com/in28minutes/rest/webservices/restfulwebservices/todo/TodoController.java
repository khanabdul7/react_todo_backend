package com.in28minutes.rest.webservices.restfulwebservices.todo;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TodoController {

	private TodoService service;
	
	public TodoController(TodoService service) {
		this.service = service;
	}
	
	@GetMapping(path = "/basicauth")   //This endpoint is used to authenticate the requests that are coming
	  public String basicAuthCheck() {
	    return "Success"; 
	  }
	
	@GetMapping("/users/{username}/todo")
	public List<Todo> getAllTodo(@PathVariable String username){
		return service.findByUsername(username);
	}
	
	@GetMapping("/users/{username}/todo/{id}")
	public Todo getSpecificTodo(@PathVariable String username, @PathVariable int id){
		return service.findById(id);
	}
	
	@DeleteMapping("/users/{username}/todo/{id}")
	public ResponseEntity<Void> deleteTodo(@PathVariable String username, @PathVariable int id){
		service.deleteById(id);
		return ResponseEntity.noContent().build(); 
	}
	 
	@PutMapping("/users/{username}/todo/{id}")
	public Todo updateTodo(@PathVariable String username, @PathVariable int id, @RequestBody Todo todo){
		service.updateTodo(todo);
		return todo; 
	}
	
	@PostMapping("/users/{username}/todo")
	public Todo updateTodo(@PathVariable String username, @RequestBody Todo todo){
		Todo createdTodo = service.addTodo(username, todo.getDescription(), todo.getTargetDate(), todo.isDone());
		return createdTodo; 
	}
}
