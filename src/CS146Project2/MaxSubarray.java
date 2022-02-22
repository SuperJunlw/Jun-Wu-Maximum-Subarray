//This class solve the maximum subarray problem in Brute Force approach,
//Divide and Conquer approach and Kadane's Algorithm approach

package CS146Project2;

/**
 * MaxSubarray has three methods to solve max subarray problem
 * in Brute Force, Divide and Conquer and Kadane's Algorithm
 */
public class MaxSubarray 

{
	
//***************************************************************************************************************************************************************
//Solve max subarray using Brute Force
	
	/**
	 * This method Solve the max subarray in BruteForce
	 * @param arr - a array of integers
	 * @return info - an TripInfo object contain maxSum, arriveDay and departDay
	 */
	public static TripInfo BruteForce(int arr[])
	{
		TripInfo info = new TripInfo();
		int max = arr[0];
		int income;
		
		//nested for loop to compare every pair of i and j
		for(int i = 0; i < arr.length; i++)
		{
			income = 0;
			for(int j = i; j < arr.length; j++)
			{
				income += arr[j];
				//keep track of current max
				if(income > max)
				{
					max = income;
					info.setArriveDay(i);
					info.setDepartDay(j);
				}
			}
		}
		
		info.setMaxSum(max);
		if(max <= 0)
		{
			info.setArriveDay(-1);
			info.setDepartDay(-1);
		}
		return info;
	}
	
	
//**************************************************************************************************************************************************************
//solve max subarray using Divide and Conquer	
	
	/**
	 * This method Solve the max subarray using divide and conquer
	 * @param arr - an array of integer
	 * @param low - start index of arr
	 * @param high - last index of arr
	 * @return the max subarray sum of arr
	 */
	public static int DivideNConquer(int arr[], int low, int high)
	{
		//base case
		if(low == high)
		{
			return arr[low];
		}
		
		int mid = (low+high)/2;
		int leftMax = DivideNConquer(arr, low, mid);     //divide array half
		int rightMax = DivideNConquer(arr, mid+1, high); //divide array half
		int crossingMax = findCrossingMax(arr, low, mid, high); //find the crossing max of current subarray
		
		if(leftMax >= rightMax && leftMax >= crossingMax)
		{
			return leftMax;
		}
		else if(rightMax >= leftMax && rightMax >= crossingMax)
		{
			return rightMax;
		}
		else
		{
			return crossingMax;
		}
	}
	
	/**
	 * this method find the crossing max of a given subarray
	 * @param arr - an array of integer
	 * @param low - start index of arr
	 * @param mid - middle index of arr
	 * @param high - last index of arr
	 * @return max sum of given subarray
	 */
	private static int findCrossingMax(int[] arr, int low, int mid, int high) 
	{
		int leftSum = Integer.MIN_VALUE;
		int sum = 0;
		//int leftArrive = -1, leftDepart = mid;
		//int rightArrive = mid+1, rightDepart = -1;
		
		//find the max of left half of the subarray
		for(int i = mid; i >= low; i--)
		{
			sum += arr[i];
			if(sum > leftSum)
			{
				leftSum = sum;
				//leftArrive = i;
			}
		}
		
		//find the max of right half of the subarray
		int rightSum = Integer.MIN_VALUE;
		sum = 0;
		for(int j = mid+1; j <= high; j++)
		{
			sum += arr[j];
			if(sum > rightSum)
			{
				rightSum = sum;
				//rightDepart = j;
			}
		}
		
		if(leftSum >= rightSum && leftSum >= leftSum+rightSum)
		{
			//info.setArriveDay(leftArrive);
			//info.setDepartDay(leftDepart);
			return leftSum;
		}
		else if(rightSum >= leftSum && rightSum >= leftSum+rightSum)
		{
			//info.setArriveDay(rightArrive);
			//info.setDepartDay(rightDepart);
			return rightSum;
		}
		else
		{
			//info.setArriveDay(leftArrive);
			//info.setDepartDay(rightDepart);
			return leftSum+rightSum;			//crossing max
		}
	}
	

//*************************************************************************************************************************************************************
//Solve max subarray using Kadane's Algorithm
	
	/**
	 * This method Solve the max subarray in Kadane's Algorithm
	 * @param arr - a array of integers
	 * @return info - an TripInfo object contain maxSum, arriveDay and departDay
	 */
	public static TripInfo KadaneAlgorithm(int arr[])
	{
		TripInfo info = new TripInfo();
		int maxSum = 0, maxTemp = 0;
		int arrive = -1, depart = -1;
		int tempArrive = 0;
		
		for(int i = 0; i < arr.length; i++)
		{
			maxTemp += arr[i];
			//if maxTemp is negative, maxTemp =0 and start again
			if(maxTemp < 0)
			{
				maxTemp = 0; 
				arrive = i+1;
			}
			//save the current max to maxSum
			if(maxTemp > maxSum)
			{
				maxSum = maxTemp;
				depart = i;
				tempArrive = arrive;
			}
		}
		arrive = tempArrive;
		info.setArriveDay(arrive);
		info.setDepartDay(depart);
		
		if(maxSum <= 0)
		{
			info.setArriveDay(-1);
			info.setDepartDay(-1);
		}
		
		info.setMaxSum(maxSum);
		return info;
	}
}
