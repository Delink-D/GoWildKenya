import org.sql2o.*;
import java.util.*;

public class Admin {
	// properites
	private int id;
	private String use_name;
	private String email;
	private String password;

	public Admin(String name, String email, String password) {
		this.use_name 	= name;
		this.email 		= email;
		this.password 	= password;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return use_name;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	// METHOD TO SAVE OBJECTS TO THE DATABASE
	public void save() {
		try(Connection con = DB.sql2o.open()) {
			String sql = "INSERT INTO admin (use_name, email, password) VALUES(:name, :email, :passowrd)";
			this.id = (int) con.createQuery(sql, true)
			.addParameter("name", this.use_name)
			.addParameter("email", this.email)
			.addParameter("passowrd", this.password)
			.executeUpdate()
			.getKey();
		}
	}

	// METHOD TO GET ALL RECORDS FROM THE DATABASE
	public static List<Admin> getAll() {
		try(Connection con = DB.sql2o.open()) {
			String sql = "SELECT * FROM admin";
			return con.createQuery(sql)
			.executeAndFetch(Admin.class);
		}
	}

	// METHOD TO GET ALL RECORDS FROM THE DATABASE
	public static Admin find(int id) {
		try(Connection con = DB.sql2o.open()) {
			String sql = "SELECT * FROM admin WHERE id = :id";
			Admin admin = con.createQuery(sql)
			.addParameter("id", id)
			.executeAndFetchFirst(Admin.class);
			return admin;
		}
	}

	// METHOD TO DELETE AN OBJECT FROM THE DATABASE
	public void update(String name, String email) {
		try(Connection con = DB.sql2o.open()) {
			String sql = "UPDATE admin SET use_name = :name, email = :email WHERE id = :id";
			con.createQuery(sql)
			.addParameter("name", name)
			.addParameter("email", email)
			.addParameter("id", this.id)
			.executeUpdate();			
		}
	}

	// METHOD TO DELETE AN OBJECT FROM THE DATABASE
	public void delete() {
		try(Connection con = DB.sql2o.open()) {
			String sql = "DELETE FROM admin WHERE id = :id";
			con.createQuery(sql)
			.addParameter("id", this.id)
			.executeUpdate();			
		}
	}

	// OVERRIDE EQUALS METHOD
	@Override 
	public boolean equals(Object otherAdmin) {
		if (!(otherAdmin instanceof Admin)) {
			return false;
		}else {
			Admin newAdmin = (Admin) otherAdmin;
			return this.getName().equals(newAdmin.getName()) && this.getEmail().equals(newAdmin.getEmail());
		}
	}
}