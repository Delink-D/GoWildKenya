import org.sql2o.*;
import java.util.ArrayList;
import java.util.List;

public class Category{

	private String cat_name;
	private int id;

	public Category(String name){

		this.cat_name = name;
	}

	public String getName(){
		return cat_name;
	}

	public int getId(){
		return id;
	}

	//overriding method 
	@Override 
	public boolean equals(Object otherCategory){
		if(!(otherCategory instanceof Category)){
			return false;
		}else{
			Category newCategory = (Category) otherCategory;
			return this.getName().equals(newCategory.getName());
		}
	}

	//saving method
	public void save(){
		try(Connection con = DB.sql2o.open()){
			String sql = "INSERT INTO category (cat_name) VALUES (:name)";
			this.id = (int) con.createQuery(sql, true)
			.addParameter("name", this.cat_name)
			.executeUpdate()
			.getKey();
		}
	}

	// returning all instance of category method
	public static List<Category> all(){
		String sql = "SELECT * FROM category";
		try(Connection con = DB.sql2o.open()){
			return con.createQuery(sql).executeAndFetch(Category.class);
		}
	}

	//Finding category method
	public static Category find(int id){
		try(Connection con = DB.sql2o.open()){
			String sql = "SELECT * FROM category WHERE id = :id";
			Category  category = con.createQuery(sql)
			.addParameter("id", id)
			.throwOnMappingFailure(false)
			.executeAndFetchFirst(Category.class);
			return category;
		}
	}

	// update method
	public void update(String name){
		try(Connection con = DB.sql2o.open()) {
			String sql = "UPDATE category SET cat_name = :name WHERE id = :id";
			con.createQuery(sql)
			.addParameter("name", cat_name)
			.addParameter("id", id)
			.executeUpdate();
		}
	}

	//delete method
	public void delete(){
		try(Connection con = DB.sql2o.open()){
			String sql = "DELETE FROM category WHERE id = :id;";
			con.createQuery(sql)
			.addParameter("id", id)
			.executeUpdate();
		}
	}

	// get all lists of this category
	public List<OurList> getLists(){
		try(Connection con = DB.sql2o.open()){
			String sql = "SELECT * FROM list WHERE cat_id = :id;";
			return con.createQuery(sql)
			.addParameter("id", this.id)
			.executeAndFetch(OurList.class);
		}
	}
}