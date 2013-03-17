package thoughtworks.functionClass;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import java.util.Arrays;

import org.junit.Test;

public class RoleNumberTransferTest {
	private String num1 = "1";
	private String num2 = "12345";
	private String num3 = "012";
	private String num4 = "135";
	private String num5 = "11";
	private String num6 = "1234";
	
	@Test
	public void shouldNum1CanBeTransferToArray(){
		assertThat(Arrays.equals(RoleNumberTransfer.transferInputToRoleNumberArray(num1), 
				new int[]{1}),is(true));		
	}
	
	@Test
	public void shouldNum2CanBeTransferToArray(){
		assertThat(Arrays.equals(RoleNumberTransfer.transferInputToRoleNumberArray(num2), 
				new int[]{1,2,3,4,5}),is(true));		
	}

	@Test
	public void shouldNum3CanBeTransferToArray(){
		assertThat(Arrays.equals(RoleNumberTransfer.transferInputToRoleNumberArray(num3), 
				new int[]{0,1,2}),is(true));		
	}
	
	@Test
	public void shouldNum1NotWithinTheTotalNumberLimits(){
		assertThat(RoleNumberTransfer.isNumberOfPlayersWithinTheLimits(
				num1.toCharArray()),is(false));		
	}
	
	@Test
	public void shouldNum2BeyondTheTotalNumberLimits(){
		assertThat(RoleNumberTransfer.isNumberOfPlayersWithinTheLimits(
				num2.toCharArray()),is(false));		
	}
	
	@Test
	public void shouldNum3NotWithinRoleNumberLimits(){
		assertThat(RoleNumberTransfer.isRoleNumbersWithinTheLimits(
				num3.toCharArray()),is(false));		
	}
	
	@Test
	public void shouldNum4NotWithinRoleNumberLimits(){
		assertThat(RoleNumberTransfer.isRoleNumbersWithinTheLimits(
				num4.toCharArray()),is(false));		
	}
	
	@Test
	public void shouldNum5RoleNumberRepeat(){
		assertThat(RoleNumberTransfer.isRoleNumbersNotRepeat(
				num5.toCharArray()),is(false));		
	}
	
	@Test
	public void shouldNum6WithinTheTotalNumberLimits(){
		assertThat(RoleNumberTransfer.isNumberOfPlayersWithinTheLimits(
				num6.toCharArray()),is(true));			
	}
	
	@Test
	public void shouldNum6WithinRoleNumberLimits(){
		assertThat(RoleNumberTransfer.isRoleNumbersWithinTheLimits(
				num6.toCharArray()),is(true));		
	}
	
	@Test
	public void shouldNum6RoleNumberNotRepeat(){
		assertThat(RoleNumberTransfer.isRoleNumbersNotRepeat(
				num6.toCharArray()),is(true));		
	}	
}
