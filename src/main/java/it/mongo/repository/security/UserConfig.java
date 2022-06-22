package it.mongo.repository.security;

import it.mongo.repository.Profiles;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile({Profiles.APPLICATION})
@Component
@Data
public class UserConfig 
{

	private String userId;
	private String password;
}
