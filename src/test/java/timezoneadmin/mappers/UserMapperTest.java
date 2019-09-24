package timezoneadmin.mappers;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.petar.timezoneadmin.TimeZoneAdminApplication;
import com.petar.timezoneadmin.mappers.UserMapper;
import com.petar.timezoneadmin.model.User;
import com.petar.timezoneadmin.model.UserRole;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {
		TimeZoneAdminApplication.class
})
public class UserMapperTest {

	@Autowired
	private UserMapper userMapper;
	
	@After
	public void cleanUp() {
		User firstUser = userMapper.findByUsername(initialUsername);
		User secondUser = userMapper.findByUsername(newUsername);
		if (firstUser!=null) {
		userMapper.deleteUser(firstUser.getId());
		} 
		if (secondUser!=null) {
		userMapper.deleteUser(secondUser.getId());
		}
	}	
	
	private static final String initialUsername ="XyzXHJ";
	private static final String newUsername = "ZzZzZz";

	@Test
	public void testInsert() {
		User userToInsert = new User();
		userToInsert.setUsername(initialUsername);
		userToInsert.setPassword(initialUsername);
		userToInsert.setRole(UserRole.REGULAR);
		userMapper.createUser(userToInsert);
		User foundUser = userMapper.findByUsername(initialUsername);
		assertNotNull(foundUser);
		assertTrue(initialUsername.equals(foundUser.getUserName()));
		assertTrue(UserRole.REGULAR == foundUser.getRole());
	}
	
	@Test
	public void testUpdate() {
		User userToInsert = new User();
		userToInsert.setUsername(initialUsername);
		userToInsert.setPassword(initialUsername);
		userToInsert.setRole(UserRole.REGULAR);
		userMapper.createUser(userToInsert);
		User foundUser = userMapper.findByUsername(newUsername);
		assertNull(foundUser);
		foundUser = userMapper.findByUsername(initialUsername);
		foundUser.setUsername(newUsername);
		foundUser.setRole(UserRole.ADMIN);
		userMapper.updateUser(foundUser);
		foundUser = userMapper.findByUsername(newUsername);
		assertTrue(newUsername.equals(foundUser.getUserName()));
		assertTrue(UserRole.ADMIN == foundUser.getRole());
	}
	
	@Test
	public void testDelete() {
		User userToInsert = new User();
		userToInsert.setUsername(initialUsername);
		userToInsert.setPassword(initialUsername);
		userToInsert.setRole(UserRole.REGULAR);
		userMapper.createUser(userToInsert);
		User foundUser = userMapper.findByUsername(initialUsername);
		assertNotNull(foundUser);
		userMapper.deleteUser(foundUser.getId());
		foundUser = userMapper.findByUsername(initialUsername);
		assertNull(foundUser);
	}
	
	@Test
	public void testSettingNullField() {
		User userToInsert = new User();
		userToInsert.setUsername(initialUsername);
		boolean caughtException = false;
		try {
			userMapper.createUser(userToInsert);
		} catch (Exception e) {
			caughtException = true;
		}
		assertTrue(caughtException);
		caughtException = false;
		userToInsert.setPassword(initialUsername);
		try {
			userMapper.createUser(userToInsert);
		} catch (Exception e) {
			caughtException = true;
		}
		caughtException = false;
		userToInsert.setRole(UserRole.REGULAR);
		try {
			userMapper.createUser(userToInsert);
		} catch (Exception e) {
			caughtException = true;
		}
		assertTrue(!caughtException);
	}	
}
