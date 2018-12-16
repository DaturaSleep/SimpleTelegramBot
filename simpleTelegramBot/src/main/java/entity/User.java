package entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class User {
	
	@Id
	@Column(name = "id", nullable = false,updatable =true)
	private long id;
	
	@Column(name = "name",nullable = true,updatable =true)
	private String name;
	
	@OneToMany(cascade = CascadeType.ALL, 
	        orphanRemoval = true)
	private List<Message> messagees = new ArrayList<Message>();
	
	public User() {
	}
	
	public User(long id, String name) {
		
		this.id = id;
		if(name == null)
			this.name =  "No-name User";
		else
		this.name = name;
	}
	public long getId() {return id;}

	public void setId(long id) {this.id = id;}

	public String getName() {return name;}

	public void setName(String name) {this.name = name;}

	public List<Message> getMessagees() {
		return messagees;
	}
	public void setMessagees(List<Message> messagees) {
		this.messagees = messagees;
	}
	@Override
	public String toString() {return "User [id=" + id + ", name=" + name + "]";}

}
