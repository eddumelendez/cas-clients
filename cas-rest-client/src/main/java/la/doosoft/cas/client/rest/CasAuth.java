package la.doosoft.cas.client.rest;

import org.springframework.util.Assert;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

public class CasAuth {

	private AuthRestTemplate authRestTemplate;

	private CasTicketValidation casTicketValidation;

	public void setAuthRestTemplate(AuthRestTemplate authRestTemplate) {
		this.authRestTemplate = authRestTemplate;
	}
	
	public void setCasTicketValidation(CasTicketValidation casTicketValidation) {
		this.casTicketValidation = casTicketValidation;
	}

	/**
	 * @param server
	 * @param username
	 * @param password
	 */
	public boolean authentication(String server, String username,
			String password) {
		Assert.hasText(server, "[server] parameter is not set.");
		Assert.hasText(username, "[username] parameter is not set.");
		Assert.hasText(password, "[password] parameter is not set.");

		MultiValueMap<String, String> credentials = new LinkedMultiValueMap<String, String>();
		credentials.add("username", username);
		credentials.add("password", password);

		String response = authRestTemplate.sendCredentials(server, credentials);
		return casTicketValidation.validateTicket(response);
	}

}
