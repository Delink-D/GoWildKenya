import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;

public class PlacesTest{

	@Rule
	public DatabaseRule database = new DatabaseRule();

	Places myPlaces = new Places(1, "Nairobi", 3246, -33.789d, 39.789d,"image1", "image2", "This describe");


	@Test
	public void places_instantiatesCorrectly_true() {
		Places myPlaces = new Places(1, "Nairobi", 3246, -33.789, 39.789,"image1", "image2", "This describe");
		assertEquals(true, myPlaces instanceof Places);
	}

	@Test 
	public void save_instantiatesCorrectly_true() {
		myPlaces.save();
		assertEquals(Places.getAll().get(0), myPlaces);
	}
	@Test
	public void places_instantiatesCorrectlyWithName_true() {
		assertEquals("Nairobi", myPlaces.getName());
	}

	@Test
	public void places_instantiatesCorrectlyWithCostof_visit() {
		assertEquals(3246, myPlaces.getCostofVisit());
	}

	@Test
	public void places_instantiatesWithLat_33() {
		assertEquals(-33.789, myPlaces.getLat(),0.01);
	}

	@Test
	public void getListId_InstantiatesPlaceWithLIstId_1() {
        OurList list0 = new OurList("Game Parks", 1);
        list0.save();
        Places myPlaces = new Places(list0.getId(), "Nairobi", 3246, -33.789d, 39.789d,"image1", "image2", "This describe");
		myPlaces.save();
		assertEquals(Places.getAll().get(0).getListId(), myPlaces.getListId());
	}

	@Test
	public void getimage1_InstantiatesPlaceWithimage1_link() {
		assertEquals("image1", myPlaces.getImage1());
	}

	@Test
	public void getimage1_InstantiatesPlaceWithimage2_link() {
		assertEquals("image2", myPlaces.getImage2());
	}

	@Test
	public void all_returnsAllInstancesOfPlace_true() {
		myPlaces.save();
		assertEquals(true, myPlaces.getAll().get(0).equals(myPlaces));
	}
	@Test
	public void save_assignsIdToPLace() {
		myPlaces.save();
		Places savedPlace = Places.getAll().get(0);
		assertEquals(savedPlace.getId(), myPlaces.getId());
	}

}