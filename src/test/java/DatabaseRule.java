import org.junit.rules.ExternalResource;
import org.sql2o.*;

public class DatabaseRule extends ExternalResource {

	@Override 
	protected void before() {
		DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/go_wild_kenya_test", "delink", "0000");
	}

	@Override
	protected void after() {
		try(Connection con = DB.sql2o.open()){
			String sql0 = "DELETE FROM category *;";
			String sql1 = "DELETE FROM list *;";
			String sql2 = "DELETE FROM places *;";
			String sql3 = "DELETE FROM admin *;";
			con.createQuery(sql0).executeUpdate();
			con.createQuery(sql1).executeUpdate();
			con.createQuery(sql2).executeUpdate();
			con.createQuery(sql3).executeUpdate();
		}
	}
}