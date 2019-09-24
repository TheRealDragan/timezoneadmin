package timezoneadmin.token;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.petar.timezoneadmin.TimeZoneAdminApplication;
import com.petar.timezoneadmin.jwt.security.JwtGenerator;
import com.petar.timezoneadmin.jwt.security.JwtValidator;
import com.petar.timezoneadmin.mappers.UserMapper;
import com.petar.timezoneadmin.model.User;
import com.petar.timezoneadmin.utils.StatusChangeTimeByUser;
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {
		TimeZoneAdminApplication.class
})
public class JwtTokenMechanismTest {
	
	@Autowired
	private JwtGenerator generator;
	
	@Autowired
	private JwtValidator validator;
	
	@Autowired
	private UserMapper mapper;
	
	@Autowired
	private StatusChangeTimeByUser statusChangeTimeByUser;
	
	@After
	public void resetGenerator( ) {
		generator.setJwtDuration(180000l);
	}
	
	@Test
	public void testCreatingValidToken() {
		User randomUser = mapper.findAllUsers().get(0);
		String token = generator.generate(randomUser);
		User validatedUser = validator.validate(token);
		assertNotNull(validatedUser);
		assertTrue(validatedUser.getUsername().equals(randomUser.getUserName()));
	}
	
	@Test
	public void testValidatingExpiredToken() throws InterruptedException {
		User randomUser = mapper.findAllUsers().get(0);
		generator.setJwtDuration(500l);
		String token = generator.generate(randomUser);
		Thread.sleep(600);
		User validatedUser = validator.validate(token);
		assertNull(validatedUser);
	}
	
	@Test
	public void testTokenInvalidation() {
		User randomUser = mapper.findAllUsers().get(0);
		String firstToken = generator.generate(randomUser);
		assertNotNull(validator.validate(firstToken));
		statusChangeTimeByUser.addToMap(randomUser.getId(), System.currentTimeMillis());
		String secondToken = generator.generate(randomUser);
		assertNull(validator.validate(firstToken));
		assertNotNull(validator.validate(secondToken));
	}
}
