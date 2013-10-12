package la.doosoft.cas.client.rest;

import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@RunWith(MockitoJUnitRunner.class)
public class CASAuthTest {

	private static final String CAS_USERNAME = "emelendez";
	private static final String URL_CAS_REST = "http://localhost:8080/cas/rest/tickets";

	@InjectMocks
	private CasAuth casAuth;
	
	@Mock
	AuthRestTemplate authRestTemplate;
	
	@Mock
	CasTicketValidation casTicketValidation;

	@Test
	public void testAuthenticationTrue() {		
		String password = "emelendez";
		MultiValueMap<String, String> credentials = new LinkedMultiValueMap<String, String>();
		credentials.add("username", CAS_USERNAME);
		credentials.add("password", password);
	
		when(authRestTemplate.sendCredentials(URL_CAS_REST, credentials)).thenReturn("ticket");
		when(casTicketValidation.validateTicket("ticket")).thenReturn(true);
		
		boolean isAuthenticated = casAuth.authentication(URL_CAS_REST, CAS_USERNAME, password);
		Assert.assertTrue(isAuthenticated);		
	}
	
	@Test
	public void testAuthenticationFalse() {
		String password = "emelendez";
		MultiValueMap<String, String> credentials = new LinkedMultiValueMap<String, String>();
		credentials.add("username", CAS_USERNAME);
		credentials.add("password", password);
		
		when(authRestTemplate.sendCredentials(URL_CAS_REST, credentials)).thenReturn("ticket");
		when(casTicketValidation.validateTicket("ticket")).thenReturn(false);
		
		boolean isAuthenticated = casAuth.authentication(URL_CAS_REST, CAS_USERNAME, password);
		Assert.assertFalse(isAuthenticated);
	}
/*
	@Test(expected=AuthenticationException.class)
	public void testAuthenticationWithoutUsername() {
		String username = "";
		String password = "4naNAX20";
		authenticationUtil.authentication(URL_CAS_REST, username, password);
	}

	@Test(expected=AuthenticationException.class)
	public void testAuthenticationWithoutPassword() {
		String username = CAS_USERNAME;
		String password = "";
		authenticationUtil.authentication(URL_CAS_REST, username, password);	
	}

	@Test(expected=AuthenticationException.class)
	public void testAuthenticationWithoutServer() {
		String server = "";
		String username = CAS_USERNAME;
		String password = "4naNAX20";
		authenticationUtil.authentication(server, username, password);
	}
*/
}
