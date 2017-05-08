import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class OurListTest {

    OurList list1 = new OurList("Game Parks", 1, "image");
    OurList list2 = new OurList("Game Reserves", 2, "image");

    @Rule
    public DatabaseRule database = new DatabaseRule();

    @Test
    public void list_instantiatesCorrectly_true() {
        OurList testList = list1;
        assertEquals(true, testList instanceof OurList);
    }

    @Test
    public void getListName_personInstantiatesWithListName_GameParks() {
        OurList testList = list1;
        assertEquals("Game Parks", testList.getName());
    }

    @Test
    public void getCategory_personInstantiatesWithCatId_1() {
        OurList testList = list1;
        assertEquals(1, testList.getCategory());
    }

    @Test
    public void equals_returnsTrueIfNameAndCatAreSame_true() {
        OurList testList = list1;
        OurList anotherList = list1;
        assertTrue(testList.equals(anotherList));
    }

    @Test
    public void save_insertsObjectIntoDatabase_List() {
        OurList testList = list1;
        testList.save();
        assertEquals(true, OurList.all().get(0).equals(testList));
    }

    @Test
    public void save_assignsIdToList() {
        OurList testList = list1;
        testList.save();
        OurList savedList = OurList.all().get(0);
        assertEquals(savedList.getId(), testList.getId());
    }

    @Test
    public void all_returnsAllInstancesOfList_true() {
        OurList firstList = list1;
        firstList.save();
        OurList secondList = list2;
        secondList.save();
        assertEquals(true, OurList.all().get(0).equals(firstList));
        assertEquals(true, OurList.all().get(1).equals(secondList));
    }

    @Test
    public void find_returnsListWithSameId_secondList() {
        OurList firstList = list1;
        firstList.save();
        OurList secondList = list2;
        secondList.save();
        assertEquals(OurList.find(secondList.getId()), secondList);
    }

    @Test 
    public void getCatName_ourListDisplaysCategoryName_Safaris() {
        Category newCategory = new Category("Safaris");
        newCategory.save();
        OurList list3 = new OurList("Game Parks", newCategory.getId(), "image");
        list3.save();
        assertEquals("Safaris", list3.getCatName().getName());
    }

    @Test 
    public void update_ourListUpdatesARecord_WaterParks() {
        OurList list0 = new OurList("Game Parks", 1, "image");
        list0.save();
        list0.update("Water Parks");
        assertEquals("Water Parks", OurList.find(list0.getId()).getName());
    }

    @Test 
    public void delete_ourListdeletesARecord_null() {
        OurList list0 = new OurList("Game Parks", 1, "image");
        list0.save();
        list0.delete();
        assertEquals(null, OurList.find(list0.getId()));
    }
}