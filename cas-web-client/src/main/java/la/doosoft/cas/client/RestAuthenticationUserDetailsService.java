package la.doosoft.cas.client;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.log4j.Logger;
import org.springframework.security.cas.authentication.CasAssertionAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class RestAuthenticationUserDetailsService implements AuthenticationUserDetailsService<CasAssertionAuthenticationToken> {
	
	private static final Logger LOGGER = Logger.getLogger(RestAuthenticationUserDetailsService.class);
		
	@Override
	public UserDetails loadUserDetails(CasAssertionAuthenticationToken token)
			throws UsernameNotFoundException {
		String username = token.getName();
		
		StringBuilder message = new StringBuilder();
		message.append("\n=============================");
		message.append("\nUsername :" + username);
		message.append("\n=============================");
		
		LOGGER.info(message);
		
		Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		
		return new User(username, "", authorities);
	}

}
