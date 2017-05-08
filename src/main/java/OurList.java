import org.sql2o.*;
import java.util.*;

public class OurList {
	// proparties
	private int id;
	private String list_name;
	private int cat_id;
	private String list_image;

	public OurList(String name, int catid, String image){
		this.list_name = name;
		this.cat_id = catid;
		this.list_image = image;
	}

	public String getName() {
		return list_name;
	}

	public int getId() {
		return id;
	}

	public int getCategory() {
		return cat_id;
	}

	public String getImage() {
		return list_image;
	}

	//saving method
	public void save(){
		try(Connection con = DB.sql2o.open()){
			String sql = "INSERT INTO list (list_name, cat_id, list_image) VALUES (:name, :catid, :image)";
			this.id = (int) con.createQuery(sql, true)
			.addParameter("name", this.list_name)
			.addParameter("catid", this.cat_id)
			.addParameter("image", this.list_image)
			.executeUpdate()
			.getKey();
		}
	}

	// returning all instance of category method
	public static List<OurList> all(){
		String sql = "SELECT * FROM list";
		try(Connection con = DB.sql2o.open()){
			return con.createQuery(sql)
			.executeAndFetch(OurList.class);
		}
	}

	// method to find a specific object in database.
	public static OurList find(int id) {
		try(Connection con = DB.sql2o.open()) {
			String sql = "SELECT * FROM list WHERE id=:id";
			OurList ourList = con.createQuery(sql)
			.addParameter("id", id)
			.executeAndFetchFirst(OurList.class);
			return ourList;
		}
	}

	public Category getCatName() {
		try(Connection con = DB.sql2o.open()) {
			String sql = "SELECT * FROM category WHERE id=:cat_id";
			return con.createQuery(sql)
			.addParameter("cat_id", cat_id)
			.executeAndFetchFirst(Category.class);
			
		}
	}

	// method to update a certain record
	public void update(String name) {
		try(Connection con = DB.sql2o.open()) {
			String sql = "UPDATE list SET list_name = :name WHERE id=:id";
			con.createQuery(sql)
			.addParameter("name", name)
			.addParameter("id", this.id)
			.executeUpdate();
		}
	}

	// method to update a certain record
	public void delete() {
		try(Connection con = DB.sql2o.open()) {
			String sql = "DELETE FROM list WHERE id=:id";
			con.createQuery(sql)
			.addParameter("id", this.id)
			.executeUpdate();
		}
	}

	// get all places of this list
	public List<Places> getPlaces(){
		try(Connection con = DB.sql2o.open()){
			String sql = "SELECT * FROM places WHERE list_id = :id;";
			return con.createQuery(sql)
			.addParameter("id", this.id)
			.executeAndFetch(Places.class);
		}
	}

	// Overide method
	@Override 
	public boolean equals(Object otherList){
		if (!(otherList instanceof OurList)) {
			return false;
		}else {
			OurList newList = (OurList) otherList;
			return this.getName().equals(newList.getName()) && this.getCategory() == newList.getCategory();
		}
	} 
}