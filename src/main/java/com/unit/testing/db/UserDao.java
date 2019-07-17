package com.unit.testing.db;

import java.util.HashMap;
import java.util.Map;
import com.unit.testing.models.User;



public class UserDao {

	public Map<String, Object> getUserById(Integer id) {
		Map<String, Object> results = new HashMap<>();
		User user = new User();

		if (id == null) {
			results.put("status", "Data Invalid");
			return results;
		}
		
		try {
			// db logic
			user.setId(id);
			user.setUsername("alan");
			results.put("user", user);
		} catch (Exception caught) {
			// handle exception
		}

		results.put("status", "OK");
		
		return results;
	}
	
}
