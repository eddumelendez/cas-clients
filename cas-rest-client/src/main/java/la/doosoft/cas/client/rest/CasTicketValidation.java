package la.doosoft.cas.client.rest;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.util.StringUtils;

public class CasTicketValidation {

	/**
	 * @param isAuthenticathed
	 * @param response
	 * @return
	 */
	public boolean validateTicket(String response) {
		boolean isAuthenticathed = false;
		if (StringUtils.hasText(response)) {
			final Matcher matcher = Pattern.compile(".*action=\".*/(.*?)\".*")
					.matcher(response);
			String ticket = "";
			if (matcher.matches()) {
				ticket = matcher.group(1);
			}

			if (StringUtils.hasText(ticket)) {
				isAuthenticathed = true;
			}
		}
		return isAuthenticathed;
	}
	
}
