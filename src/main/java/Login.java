import org.sql2o.*;
import java.util.ArrayList;
import java.util.List;

public class Login {

	private String email;
	private String password;

	//thee login constructor
	public Login(String email, String password){

		this.email = email;
		this.password = password;

	}

	public String getEmail(){
		return email;
	}

	public String getPassword(){
		return password;
	}
	
	public boolean login() {
		try(Connection con = DB.sql2o.open()) {
			
			String sql = "SELECT * FROM admin WHERE email = :email";
			Admin admin = con.createQuery(sql)
			.addParameter("email", this.email)
			.executeAndFetchFirst(Admin.class);

			
			if (admin instanceof Admin) {
				if (this.password.equals(admin.getPassword())) {
					return true;
				}else {
					return false;
					// throw new IllegalArgumentException("Invalid  password!");
				}
			}else {
				return false;
				// throw new IllegalArgumentException("Invalid email Address or password!");	

			}
		}
	}

	// @override method
	@Override 
	public boolean equals(Object anotherLogin){
		if (!(anotherLogin instanceof Login)){
			return false;
		}else {
			Login newLogin = (Login) anotherLogin;
			return this.getEmail().equals(newLogin.getEmail()) &&
			       this.getPassword().equals(newLogin.getPassword());
		}
	}
}
