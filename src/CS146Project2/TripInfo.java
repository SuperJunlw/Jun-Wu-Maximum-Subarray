//This class creates an object to store the maximum sum, arrival day
//and departure day

package CS146Project2;

/**
 * TripInfo represent a info of an array
 * A TripInfo has a maxSum, an arriveDay and a departDay
 */

public class TripInfo 
{
	private int maxSum;  
	private int arriveDay;
	private int departDay;
	
	//getters and setters
	public int getMaxSum() {
		return maxSum;
	}
	public void setMaxSum(int maxSum) 
	{
		this.maxSum = maxSum;
	}
	public int getArriveDay() 
	{
		return arriveDay;
	}
	public void setArriveDay(int arriveDay) 
	{
		this.arriveDay = arriveDay;
	}
	public int getDepartDay() 
	{
		return departDay;
	}
	public void setDepartDay(int departDay) 
	{
		this.departDay = departDay;
	}
	
	
}
