package it.mongo.repository.security;

import it.mongo.repository.Profiles;
import org.springframework.context.annotation.Profile;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;

@Profile({Profiles.APPLICATION})
@Component
public class JwtUnAuthorizedResponseAuthenticationEntryPoint implements AuthenticationEntryPoint, Serializable 
{

	private static final long serialVersionUID = -8970718410437077606L;

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException 
	{
		response.sendError(HttpServletResponse.SC_UNAUTHORIZED,
				"DEVI INSERIRE UN TOKEN JWT VALIDO PER POTERTI AUTENTICARE");
	}
}
