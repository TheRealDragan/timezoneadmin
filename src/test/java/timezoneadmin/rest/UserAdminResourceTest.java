package timezoneadmin.rest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import javax.servlet.ServletContext;
import javax.validation.constraints.AssertTrue;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.petar.timezoneadmin.TimeZoneAdminApplication;
import com.petar.timezoneadmin.configuration.SecurityConfiguration;
import com.petar.timezoneadmin.controller.UserAdministrationController;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {
		TimeZoneAdminApplication.class
})
@AutoConfigureMockMvc
public class UserAdminResourceTest {
	
	 @Autowired
	 private MockMvc mockMvc;
	 
	 @Test
	    public void testCallProtectedResource() throws Exception {
		 boolean exceptionCaught = false;
		 try {
	      mockMvc.perform(MockMvcRequestBuilders.get("/admin/timezones"))
	                .andExpect(MockMvcResultMatchers.status().isOk());
		 } catch (RuntimeException e) {
			 exceptionCaught = true;
			 assertEquals("The request is missing a JWT token!", e.getMessage());
		 }
		 assertTrue(exceptionCaught);

	    }
	 
	 @Test
	 public void testCallUnprotectedResource() throws Exception {
		 mockMvc.perform(MockMvcRequestBuilders.get("/time/current-gmt-milis"))
         .andExpect(MockMvcResultMatchers.status().isOk());
	 }

}
