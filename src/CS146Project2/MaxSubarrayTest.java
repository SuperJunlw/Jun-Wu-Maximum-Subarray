//This class test the BruteForce(), DivideNConquer() and KadaneAlgorithm()
//of the MaxSubarray class, and test the runtime of all three of the methods in ns

package CS146Project2;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.util.*;

/**
 * this class test the three methods of MaxSubarray class
 * and their runtime 
 */
public class MaxSubarrayTest 
{
	private int[][] testCases; //a 2D array to hold all the arrays in the test file
	private int[] sum;		   //an array to hold all the arrive day in the test file
	private int[] arrive;	   //an array to hold all the depart day in the test file
	private int[] depart;      //an array to hold all the max sum in the test file
	
	//simple arrays that use to test some edge cases
	private int[] arr1 = {-2, -3, 4, -1, -2, 1, 5, -3, 7, 2, 6};
	private int[] arr2 = {-3, -4, -5, -6, -7, -8, -9, -10, 0, 0, -1};
	private int[] arr3 = {2,-3, 4, -1};
	private int[] arr4 = {-5,-2,-6,-3, -7};
	
	
	
	@Before
	/**
	 * this method read the maxSumtest.txt and store the data
	 * into testCases[][], sum[], arrive[] and depart[]
	 * @throws Exception
	 */
	public void setUp() throws Exception 
	{
		testCases = new int[10][100];
		sum = new int[10];
		arrive = new int[10];
		depart = new int[10];
		
		//using FileReader and BufferedReader to read the maxSumtest.txt
		try
		{
			FileReader file = new FileReader("maxSumtest.txt");
			BufferedReader buff = new BufferedReader(file);
			boolean eof = false;
			int count = 0; 
			
			//while loop to read and break down each line of the file
			while(!eof)
			{
				String line = buff.readLine();
				if(line == null)
				{
					eof = true;
				}
				else
				{
					StringTokenizer st = new StringTokenizer(line); //using StringTokenizer to break down the line
					while(st.hasMoreTokens())
					{
						//using for loop to store first 100 entries of the line ([array])
						for(int i = 0; i < 100; i++)
						{
							String entry = st.nextToken();
							int n = Integer.parseInt(entry); //convert string to int
							testCases[count][i] = n;
						}
						
						String s = st.nextToken(); //101th entry of the line (sum)
						int x = Integer.parseInt(s);
						sum[count] = x;
						
						String a = st.nextToken(); //102th entry of the line (arrive day)
						int y = Integer.parseInt(a);
						arrive[count] = y;
						
						String d = st.nextToken();  //103th entry of the line (depart day)
						int z = Integer.parseInt(d);
						depart[count] = z;
						
						count++;
					}
				}
			}
			buff.close();
		}
		catch(IOException e)
		{
			System.out.println("Error: " + e.toString());
		}
	}
	
	
//*****************************************************************************************************************************************************************
//Test the three methods in MaxSubarray class
	
	@Test
	/**
	 * This method test the BruteForce() in MaxSubarray class
	 * using test cases of the file and the simple test cases
	 */
	public void testBruteForce() 
	{
		//1. testing the test cases in the file
		
		TripInfo info1 = new TripInfo();
		for(int i = 0; i < 10; i++)
		{
			info1 = MaxSubarray.BruteForce(testCases[i]);
			
			assertEquals(sum[i], info1.getMaxSum());
			assertEquals(arrive[i], info1.getArriveDay());
			assertEquals(depart[i], info1.getDepartDay());	
		}
		
		//2.testing the simple, edge cases
		
		TripInfo info2 = MaxSubarray.BruteForce(arr1);
		assertEquals(19, info2.getMaxSum());
		assertEquals(2, info2.getArriveDay());
		assertEquals(10, info2.getDepartDay());	
		
		info2 = MaxSubarray.BruteForce(arr2);
		assertEquals(0, info2.getMaxSum());
		assertEquals(-1, info2.getArriveDay());
		assertEquals(-1, info2.getDepartDay());	
		
		info2 = MaxSubarray.BruteForce(arr3);
		assertEquals(4, info2.getMaxSum());
		assertEquals(2, info2.getArriveDay());
		assertEquals(2, info2.getDepartDay());	
		
		info2 = MaxSubarray.BruteForce(arr4);
		assertEquals(-2, info2.getMaxSum());
		assertEquals(-1, info2.getArriveDay());
		assertEquals(-1, info2.getDepartDay());	
	}

	
	@Test
	/**
	 * This method test the DivideNConquer() in MaxSubarray class
	 * using test cases of the file and the simple test cases
	 */
	public void testDivideNConquer() 
	{
		//1.testing the test cases in the file
		
		for(int i = 0; i < 10; i++)
		{
			assertEquals(sum[i], MaxSubarray.DivideNConquer(testCases[i], 0, testCases[i].length-1));	
		}
		
		//2.testing the simple, edge cases
		
		assertEquals(19, MaxSubarray.DivideNConquer(arr1, 0, arr1.length-1));
		
		assertEquals(0, MaxSubarray.DivideNConquer(arr2, 0, arr2.length-1));
		
		assertEquals(4, MaxSubarray.DivideNConquer(arr3, 0, arr3.length-1));
		
		assertEquals(-2, MaxSubarray.DivideNConquer(arr4, 0, arr4.length-1));
	}

	
	@Test
	/**
	 * This method test the KadaneAlgorithm() in MaxSubarray class
	 * using test cases of the file and the simple test cases
	 */
	public void testKadaneAlgorithm() 
	{
		//1.testing the test cases in the file
		
		TripInfo info1 = new TripInfo();
		for(int i = 0; i < 10; i++)
		{
			info1 = MaxSubarray.KadaneAlgorithm(testCases[i]);
			
			assertEquals(sum[i], info1.getMaxSum());
			assertEquals(arrive[i], info1.getArriveDay());
			assertEquals(depart[i], info1.getDepartDay());	
		}
		
		//2.testing the simple, edge cases
		
		TripInfo info2 = MaxSubarray.KadaneAlgorithm(arr1);
		assertEquals(19, info2.getMaxSum());
		assertEquals(2, info2.getArriveDay());
		assertEquals(10, info2.getDepartDay());	
		
		info2 = MaxSubarray.KadaneAlgorithm(arr2);
		assertEquals(0, info2.getMaxSum());
		assertEquals(-1, info2.getArriveDay());
		assertEquals(-1, info2.getDepartDay());		
		
		info2 = MaxSubarray.KadaneAlgorithm(arr3);
		assertEquals(4, info2.getMaxSum());
		assertEquals(2, info2.getArriveDay());
		assertEquals(2, info2.getDepartDay());	
		
		info2 = MaxSubarray.KadaneAlgorithm(arr4);
		assertEquals(0, info2.getMaxSum());   //maxSum is -2 but Kadane's Alg don't consider negative num, so it will return 0
		assertEquals(-1, info2.getArriveDay());
		assertEquals(-1, info2.getDepartDay());	
	}
	
	
	
//**************************************************************************************************************************************************************
//Test the runtime
	
	
	@Test
	/**
	 * This method test the runtime of the three methods in MaxSubarray class
	 */
	public void testRunTime()
	{
		Random r = new Random();
		r.setSeed(20);
		long start, stop, temp;
		long elapsedTime = 0;
		
		//Test the runtime on different input size by change the size of array and for loop
		//tested n = 100, 200, 500, 1000, 2000, 5000, 10000
		int[] arr = new int[100];      
		for(int i = 0; i < 100; i++)  //Create a random array
		{
			arr[i] = r.nextInt();
		}
		
		
		//test the runtime of Brute force
		for(int j = 0; j < 10; j++)
		{
			start = System.nanoTime();
			MaxSubarray.BruteForce(arr);
			stop = System.nanoTime();
			temp = stop-start;
			elapsedTime +=temp;
		}
		elapsedTime = elapsedTime/10;
		System.out.println("Brute force run time: " + elapsedTime);
		
		
		//test the runtime of divide and conquer
		elapsedTime = 0;
		for(int k = 0; k < 10; k++)
		{
			start = System.nanoTime();
			MaxSubarray.DivideNConquer(arr, 0, arr.length-1);
			stop = System.nanoTime();
			temp = stop-start;
			elapsedTime +=temp;
		}
		elapsedTime = elapsedTime/10;
		System.out.println("Divide and conquer run time: " + elapsedTime);
		
		
		//test the runtime of kadane's algorithm
		elapsedTime = 0;
		for(int l = 0; l < 10; l++)
		{
			start = System.nanoTime();
			MaxSubarray.KadaneAlgorithm(arr);
			stop = System.nanoTime();
			temp = stop-start;
			elapsedTime +=temp;
		}
		elapsedTime = elapsedTime/10;
		System.out.println("Kadane Algorithm run time: " + elapsedTime);
	}
}
