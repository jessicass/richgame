package thoughtworks.functionClass;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import org.junit.Test;

import thoughtworks.functionClass.PositionUpdate;

public class PositionUpdateTest {
	@Test
	public void shouldPositionNegative1NotInMapArea(){
		assertThat(PositionUpdate.isPositionInMapArea(-1), is(false));
	}
	
	@Test
	public void shouldPosition70NotInMapArea(){
		assertThat(PositionUpdate.isPositionInMapArea(70), is(false));
	}
	
	@Test
	public void shouldPosition50InMapArea(){
		assertThat(PositionUpdate.isPositionInMapArea(50), is(true));
	}
	
	@Test
	public void shouldNextPositionBe10WhenCurrentPositionIs9(){
		assertThat(PositionUpdate.getNextPosition(9), is(10));
	}
	
	@Test
	public void shouldNextPositionBe0WhenCurrentPositionIs69(){
		assertThat(PositionUpdate.getNextPosition(69), is(0));
	}
	
	@Test
	public void shouldCurrentPositionBe34WhenCurrentPositionIs30WithStep4(){
		assertThat(PositionUpdate.getCurrentPositionWithDistance(30, 4), is(34));
	}
	
	@Test
	public void shouldCurrentPositionBe1WhenCurrentPositionIs67WithStep4(){
		assertThat(PositionUpdate.getCurrentPositionWithDistance(67, 4), is(1));
	}
	
	@Test
	public void shouldCurrentPositionBe63WhenCurrentPositionIs67WithStepNegative4(){
		assertThat(PositionUpdate.getCurrentPositionWithDistance(67, -4), is(63));
	}
	
	@Test
	public void shouldCurrentPositionBe67WhenCurrentPositionIs1WithStepNegative4(){
		assertThat(PositionUpdate.getCurrentPositionWithDistance(1, -4), is(67));
	}
}
