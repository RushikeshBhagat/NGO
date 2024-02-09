package com.jnit;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.jnit.Login_CredentialsService.AuthenticationResult;

@RestController
public class Login_CredentialsController {

	@Autowired
	private Login_CredentialsService login_CredentialsService;
	
	@Configuration
    public class CorsConfig implements WebMvcConfigurer {
        @Override
        public void addCorsMappings(CorsRegistry registry) {
            registry.addMapping("/**")
                    .allowedOrigins("http://localhost:3000")
                    .allowedMethods("GET", "POST", "PUT", "DELETE")
                    .allowCredentials(true);
        }
    }
	
    @RequestMapping(value="/authenticate",method = RequestMethod.POST)
    public ResponseEntity<AuthenticationResult> authenticateUser(@RequestBody AuthenticationRequest authenticationRequest) {
        // authenticationRequest is a POJO class that holds 'username' and 'password'

        // Call the service method to validate the credentials
        Login_CredentialsService.AuthenticationResult result = login_CredentialsService.authenticateUser(authenticationRequest.getUsername(), authenticationRequest.getPassword());

        if (result.isAuthenticated()) {
           
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(result, HttpStatus.UNAUTHORIZED);
        }
    }

  

    // This POJO class is used to receive 'username' and 'password' in the request body
    static class AuthenticationRequest {
        private String username;
        private String password;
		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}

        
    }
	
}
