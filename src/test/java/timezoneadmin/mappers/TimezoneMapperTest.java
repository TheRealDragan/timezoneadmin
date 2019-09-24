package timezoneadmin.mappers;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.petar.timezoneadmin.TimeZoneAdminApplication;
import com.petar.timezoneadmin.mappers.TimeZoneMapper;
import com.petar.timezoneadmin.mappers.UserMapper;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {
		TimeZoneAdminApplication.class
})
public class TimezoneMapperTest {
	
	@Autowired
	private TimeZoneMapper timeZoneMapper;
	
	@Autowired
	private UserMapper userMapper;
	
	private static final String userName ="XyzXHJ";
    private static final String initialTimezoneName="Yz2yz22yzzzyzyz";
    private static final String eventualTimezoneName="UznWsfIIzmQA";
    
    @Before
    private void setUp() {
    	
    }
    
    @After
    private void cleanUp() {
    	
    }
    
    @Test
    private void testInsert() {
    	
    }
    
    @Test 
    private void testUpdate() {
    	
    }
    
    @Test
    private void testDelete() {
    	
    }
    
    @Test
    private void testFindTimezonesByUserId() {
    	
    }
    
    @Test
    private void testFindByNameAndUserId() {
    	
    }
}
