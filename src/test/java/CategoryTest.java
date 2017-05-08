import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;

public class CategoryTest{

	@Rule
	public DatabaseRule database = new DatabaseRule();

	// test to instatiate a new Category
	@Test 
	public void category_instatiateCorrectly_true(){
		Category testCategory = new Category("Safaris");
		assertEquals(true, testCategory instanceof Category);
	}

	// category instatiate with the description name
	@Test  
	public void category_instatiateCorrectlyWithTheDescriptionName_name(){
		Category testCategory = new Category("Safaris");
		assertEquals("Safaris", testCategory.getName());
	}

	// override equals()
	@Test 
	public void equals_returnsTrueIfNameAreTheSame_true(){
		Category testCategory = new Category("Safaris");
		Category anotherCategory = new Category("Safaris");
		assertTrue(testCategory.equals(anotherCategory));
	}

	//saving category to database
	@Test 
	public void  save_insertObjectIntoDatabase_category(){
	Category testCategory = new Category("Safaris");
	testCategory.save();
	assertEquals(true, Category.all().get(0).equals(testCategory));	
	}

	@Test
	public void save_assignsIdToCategory() {
	  Category testCategory = new Category("Safaris");
	  testCategory.save();
	  Category savedCategory = Category.all().get(0);
	  assertEquals(savedCategory.getId(), testCategory.getId());
	}

	// returning all categories
	@Test 
	public void all_returnAllInstanceOfCategories_true(){
		Category firstCategory = new Category("Safaris");
		firstCategory.save();
		Category secondCategory = new Category("Safaris");
		secondCategory.save();
		assertEquals(true, Category.all().get(0).equals(firstCategory));
		assertEquals(true, Category.all().get(1).equals(secondCategory));
	}

	//finding all the categories
	@Test
	public void find_returnsCategoriesWithSameId_secondCategory() {
	  Category firstCategory = new Category("Safaris");
	  firstCategory.save();
	  Category secondCategory = new Category("Safaris");
	  secondCategory.save();
	  assertEquals(Category.find(secondCategory.getId()), secondCategory);
	}

	// updating category on the database
	public void update_updateCategoryDescription_true(){
		Category testCategory = new Category("Safaris");
		testCategory.save();
		testCategory.update("Entertainment");
		assertEquals("Entertainment", Category.find(testCategory.getId()).getName());
	}

	//deleting a category from the server 
	@Test 
	public void delete_deleteCategoryFromTheServer_true(){
		Category testCategory = new Category("Safaris");
		testCategory.save();
		int myCategoryId = testCategory.getId();
		testCategory.delete();
		assertEquals(null, Category.find(myCategoryId));
	}
}