package com.jnit;

import java.util.List;
import com.jnit.UserType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Login_CredentialsService {
	@Autowired
	private Login_CredentialsRepository login_CredentialsRepository;
	
	public List<Login_Credentials> select() {
		List<Login_Credentials> list=login_CredentialsRepository.findAll();
		return list;
	}
	
	public Login_Credentials getUserbyId(int id) {
		return login_CredentialsRepository.findById(id).get();
	}
	
	public void insert(Login_Credentials login_Credentials) {
		login_CredentialsRepository.save(login_Credentials);
	}
	
	public void update(Login_Credentials login_Credentials) {
		login_CredentialsRepository.save(login_Credentials);
	}
	
	public void delete(int id) {
		login_CredentialsRepository.deleteById(id);
	}
	
	 public AuthenticationResult authenticateUser(String username, String password) {
	        // Retrieve the user by username from the database
	        Login_Credentials user = login_CredentialsRepository.findByUsername(username);
	        
	        if (user != null && user.getPassword().equals(password)) {
	            // Check user type and return the result
	            return new AuthenticationResult(true, user.getUserType(),user.getFirst_name(), user.getLast_name());
	        } else {
	            return new AuthenticationResult(false, null, null, null);
	        }
	 }
	 
	    public static class AuthenticationResult {
	        private final boolean isAuthenticated;
	        private final UserType userType;
	        private final String first_name;
	        private final String last_name;
	        public AuthenticationResult(boolean isAuthenticated, UserType userType, String first_name, String last_name) {
	            this.isAuthenticated = isAuthenticated;
	            this.userType = userType;
	            this.first_name = first_name;
	            this.last_name = last_name;
	        }

	        public boolean isAuthenticated() {
	            return isAuthenticated;
	        }

	        public UserType getUserType() {
	            return userType;
	        }
	        
	        public String getFirst_name() {
				return first_name;
			}
	        public String getLast_name() {
				return last_name;
			}
	     
	    }
}

