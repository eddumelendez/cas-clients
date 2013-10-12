package la.doosoft.cas.client.rest;

import org.apache.log4j.Logger;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

public class AuthRestTemplate {
	
	private static final Logger LOGGER = Logger.getLogger(AuthRestTemplate.class);

	public String sendCredentials(String server,
			MultiValueMap<String, String> credentials) {
		RestTemplate restTemplate = new RestTemplate();	
		String response = "";
		try {
			if (LOGGER.isDebugEnabled()) {
				StringBuilder message = new StringBuilder();
				message.append("\n=============================");
				message.append("\nUsername: " + credentials.get("username"));
				message.append("\nPassword: " + credentials.get("password"));
				message.append("\n=============================");
				LOGGER.debug(message);
			}
			response = restTemplate.postForObject(server, credentials,
					String.class);
		} catch (HttpClientErrorException e) {
			LOGGER.warn(String.format("Status text is %s", e.getStatusText()));
			LOGGER.warn(String.format("Status Code is %s", e.getStatusCode()));
		}
		return response;
	}

}