package it.mongo.repository.security;

import io.jsonwebtoken.ExpiredJwtException;
import it.mongo.repository.Profiles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Profile({Profiles.APPLICATION})
@Component
public class JwtTokenAuthorizationOncePerRequestFilter extends OncePerRequestFilter {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	@Qualifier("customUserDetailsService")
	private UserDetailsService userDetailsService;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Value("${sicurezza.header}")
	private String tokenHeader;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException 
	{
		logger.debug("Authentication Request For '{}'", request.getRequestURL());

		final String requestTokenHeader = request.getHeader(this.tokenHeader);
		
		logger.warn("Token: " + requestTokenHeader);

		String username = null;
		String jwtToken = null;
		
		if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) 
		{
			jwtToken = requestTokenHeader.substring(7);
			
			try 
			{
				username = jwtTokenUtil.getUsernameFromToken(jwtToken);
			} 
			catch (IllegalArgumentException e) 
			{
				logger.error("IMPOSSIBILE OTTENERE LA USERID", e);
			} 
			catch (ExpiredJwtException e) 
			{
				logger.warn("TOKEN SCADUTO", e);
			}
		} 
		else 
		{
			logger.warn("TOKEN NON VALIDO");
		}

		logger.debug("JWT_TOKEN_USERNAME_VALUE '{}'", username);
		
		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) 
		{

			UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);

			if (jwtTokenUtil.validateToken(jwtToken, userDetails)) 
			{
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = 
						new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
				
				usernamePasswordAuthenticationToken
						.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			}
		}

		chain.doFilter(request, response);
	}
}
