package se.mindroad;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class UserService {
	private Map<Integer,User> Users = new HashMap<Integer,User>();
	private int id = 0;
	
	public Collection<User> getAllUsers() { 
		return Users.values();
	}
	public User getUser(int id) {
	
		for(User User : Users.values()) {
			if(User.getId() == id) {
				return User;
			}
		}
		return null;
	}
	
	public User createUser(String name, String email) {
		User User = new User();
		User.setId(id);
		User.setName(name);
		User.setEmail(email);
		Users.put(id,User);
		id++;
		return User;
	}
	
	public User updateUser(int id,String name, String email) {
		User temp = getUser(id);
		temp.setName(name);
		temp.setEmail(email);
		return temp; 
	}
	
	public void deleteUser(int id) {
		Users.remove(id);
	}
	
	public boolean existsName(String name) {
		for(User User : Users.values()) {
			if(User.getName() == name) {
				return true;
			}
		}
		return false;
	}
	
	public boolean existsID(int id) {
		for(User User : Users.values()) {
			if(User.getId() == id) {
				return true;
			}
		}
		return false;
	}
}
