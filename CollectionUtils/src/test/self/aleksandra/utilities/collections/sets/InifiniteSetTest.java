package self.aleksandra.utilities.collections.sets;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

public class InifiniteSetTest {

	@Test
	public void testLongs() {
		Set<Long> testSet = new InfiniteSet<Long>(Long.class);
		for (Long l = -745L; l < 943; l++) {
			Assert.assertTrue(testSet.contains(l));
		}
		
		Assert.assertTrue(testSet.contains(Long.MAX_VALUE));
		Assert.assertTrue(testSet.contains(Long.MIN_VALUE));
		
		List<Long> retain = Arrays.asList(10L, 12L, 888L, 1000L, 69L);
		
		Assert.assertTrue(testSet.retainAll(retain));
		Assert.assertFalse(testSet.contains(Long.MAX_VALUE));
		Assert.assertFalse(testSet.contains(Long.MIN_VALUE));
		
		Assert.assertTrue(testSet.contains(10L));
		Assert.assertTrue(testSet.contains(12L));
		Assert.assertTrue(testSet.contains(888L));
		Assert.assertTrue(testSet.contains(1000L));
		Assert.assertTrue(testSet.contains(69L));
		
		for (Long l = 1001L; l < 10000; l++) {
			Assert.assertFalse(testSet.contains(l));
		}
	}
	
	public class Hat {
		public String type;
		public Hat(String type) {
			this.type = type;
		}
	}
	
	@Test
	public void testHats() {
		List<Hat> hats = new ArrayList<>();
		hats.add(new Hat("Yankee with no brim!"));
		hats.add(new Hat("Stetson"));
		hats.add(new Hat("jaunty"));
		
		Set<Hat> testSet = new InfiniteSet<Hat>();
		Assert.assertTrue(testSet.containsAll(hats));
		Assert.assertFalse(testSet.isEmpty());
		Assert.assertTrue(((InfiniteSet<Hat>)testSet).degrade());
		Assert.assertFalse(testSet.containsAll(hats));
		Assert.assertTrue(testSet.isEmpty());
	}
}
