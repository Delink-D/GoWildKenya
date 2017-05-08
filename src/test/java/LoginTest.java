import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;

public class LoginTest{

	@Rule
		public DatabaseRule database = new DatabaseRule();

		//login instatiate with the class Login
		@Test 
		public void login_loginInstatiateCorrectly_true(){
			Login testLogin = new Login("delink@delink.com", "12345678");
			assertEquals(true, testLogin instanceof Login);
		}

		//login instatiate with description email
		@Test 
		public void login_loginInstatiateCorrectlyWithDescriptionEmail_true(){
			Login testLogin = new Login("delink@delink.com", "12345678");
			assertEquals("delink@delink.com", testLogin.getEmail());
		}

		// login instatiate with the password
		@Test 
		public void login_instatiateCorrectlyWithDescriptionPassWord_true(){
			Login testLogin = new Login("delink@delink.com", "12345678");
			assertEquals("12345678", testLogin.getPassword());
		}		
}