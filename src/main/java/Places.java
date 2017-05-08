import org.sql2o.*;
import java.util.*;

public class Places{
	private int id;
	private String place_name;
	private String description;
	private double lat;
	private double lng;
	private int costof_visit;
	private int list_id;
	private String image1;
	private String image2;


	public Places(int list, String name, int costof_visit, double latitude, double longitude, String image1, String image2, String description){
		this.list_id 		= list;
		this.place_name 	= name;
		this.costof_visit 	= costof_visit;
		this.lat 			= latitude;
		this.lng 			= longitude;
		this.image1 		= image1;
		this.image2 		= image2;
		this.description 	= description;	
	}

	public int getId() {
		return id;
	}

	public int getListId() {
		return list_id;
	}

	public String getName(){
		return place_name;
	}

	public int getCostofVisit() {
		return costof_visit;
	}

	public double getLat() {
		return lat;
	}

	public double getLng() {
		return lng;
	}

	public String getImage1() {
		return image1;
	}
	public String getImage2() {
		return image2;
	}

	public String getDescription() {
		return description;
	}

	// method to save
	public void save() {
		try(Connection con = DB.sql2o.open()) {
			String sql = "INSERT INTO places(list_id,place_name,costof_visit,lat,lng,image1,image2,description) VALUES(:list,:name,:cost,:lat,:lng,:img1,:img2,:desc)";
			this.id = (int) con.createQuery(sql, true)
			.addParameter("list", this.list_id)
			.addParameter("name", this.place_name)
			.addParameter("cost", this.costof_visit)
			.addParameter("lat", this.lat)
			.addParameter("lng", this.lng)
			.addParameter("img1", this.image1)
			.addParameter("img2", this.image2)
			.addParameter("desc", this.description)
			.executeUpdate()
			.getKey();
		}
	}

	// method to get all records fro database
	public static List<Places> getAll() {
		try(Connection con = DB.sql2o.open()) {
			String sql = "SELECT * FROM places";
			return con.createQuery(sql)
			.executeAndFetch(Places.class);
		}
	}

	// method to find a place
	public static Places find(int id) {
		try(Connection con = DB.sql2o.open()) {
			String sql = "SELECT * FROM places WHERE id = :id";
			Places place = con.createQuery(sql)
			.addParameter("id", id)
			.executeAndFetchFirst(Places.class);
			return place;
		}
	}

	// update method to update a record
	public void update(String place, int cost, int lat, int lng, String img1, String img2, String description){
		try(Connection con = DB.sql2o.open()) {
			String sql = "UPDATE places SET place_name=:name,costof_visit=:cost,lat=:lat,lng=:lng,image1=:img1,image2=:img2,description=:description WHERE id=:id";
			con.createQuery(sql)
			.addParameter("name", place)
			.addParameter("cost", cost)
			.addParameter("lat", lat)
			.addParameter("lng", lng)
			.addParameter("img1", img1)
			.addParameter("img2", img2)
			.addParameter("description", description)
			.addParameter("id", this.id)
			.executeUpdate();
		}
	}

	// method delete to delete a record
	public void delete() {
		try(Connection con = DB.sql2o.open()) {
			String sql = "DELETE FROM places WHERE id = :id";
			con.createQuery(sql)
			.addParameter("id", this.id)
			.executeUpdate();
		}
	}

	// get the list name
	public OurList getListName() {
		try(Connection con = DB.sql2o.open()) {
			String sql = "SELECT * FROM list WHERE id = :list";
			return con.createQuery(sql)
			.addParameter("list", this.list_id)
			.executeAndFetchFirst(OurList.class);
		}
	}

	// overiding equals method
	@Override 
	public boolean equals(Object otherObject) {
		if (!(otherObject instanceof Places)) {
			return false;
		}else {
			Places newPlace = (Places) otherObject;
			return this.getName().equals(newPlace.getName()) && this.getLat() == newPlace.getLat() && this.getLng() == newPlace.getLng();
		}
	}
}