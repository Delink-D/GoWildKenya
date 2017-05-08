import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;

public class AdminTest{

	@Rule
	public DatabaseRule database = new DatabaseRule();

	@Test 
	public void admin_intantiateCorrectly(){
		Admin testAdmin = new Admin("Delink", "delinkdesigns@me.com", "delink321");
		assertEquals(true, testAdmin instanceof Admin);
	}

	@Test 
	public void admin_intantiateWithName_Delink(){
		Admin testAdmin = new Admin("Delink", "delinkdesigns@me.com", "delink321");
		assertEquals("Delink", testAdmin.getName());
	}

	@Test 
	public void admin_intantiateWithEmail_Email(){
		Admin testAdmin = new Admin("Delink", "delinkdesigns@me.com", "delink321");
		assertEquals("delinkdesigns@me.com", testAdmin.getEmail());
	}

	@Test 
	public void equals_returnsTrueIfObjectsEquals_true(){
		Admin testAdmin1 = new Admin("Delink", "delinkdesigns@me.com", "delink321");
		Admin testAdmin2 = new Admin("Delink", "delinkdesigns@me.com", "delink321");
		assertTrue(testAdmin1.equals(testAdmin2));
	}

	@Test 
	public void save_addObjectsToDB_true(){
		Admin testAdmin = new Admin("Delink", "delinkdesigns@me.com", "delink321");
		testAdmin.save();
		assertEquals(Admin.getAll().get(0), testAdmin);
	}

	@Test 
	public void save_addObjectsId_true(){
		Admin testAdmin = new Admin("Delink", "delinkdesigns@me.com", "delink321");
		testAdmin.save();
		assertEquals(Admin.getAll().get(0).getId(), testAdmin.getId());
	}

	@Test 
	public void find_returnsTheSameSearchedObject_true() {
		Admin testAdmin1 = new Admin("Delink", "delinkdesigns@me.com", "delink321");
		testAdmin1.save();
		Admin testAdmin2 = new Admin("Derick", "delinkdesigns@me.com", "delink321");
		testAdmin2.save();
		Admin saveAdmin = Admin.find(testAdmin2.getId());
		assertTrue(testAdmin2.equals(saveAdmin));
	}

	@Test 
	public void update_returnsTheUpdatedName_true() {
		Admin testAdmin = new Admin("Delink", "delinkdesigns@me.com", "delink321");
		testAdmin.save();
		testAdmin.update("Derick", "delinkdesigns@me.com");
		assertEquals("Derick", Admin.find(testAdmin.getId()).getName());
	}

	@Test 
	public void delete_returnsNull_true() {
		Admin testAdmin = new Admin("Delink", "delinkdesigns@me.com", "delink321");
		testAdmin.save();
		testAdmin.delete();
		assertEquals(null, Admin.find(testAdmin.getId()));
	}
}
