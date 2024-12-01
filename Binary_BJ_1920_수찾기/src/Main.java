import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	
	static int N;
	static int M;
	
	static int[] Aarr;
	static int[] Barr;
	
	public int binary(int no)
	{
		int left = 0;
		int right = Aarr.length-1;
		
		while(left <= right)
		{
			int mid = (left+right)/2;
			if(no < Aarr[mid])
			{
				right = mid-1;
			}
			else if(no > Aarr[mid])
			{
				left = mid+1;
			}
			else
			{
				return 1;
			}
		}
		
		return 0;
	}
	
	public static void main(String[] args) throws IOException
	{
		Main T = new Main();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		Aarr = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++)
		{
			
			Aarr[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(Aarr);
		
		st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		Barr = new int[M];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < M; i++)
		{
			
			Barr[i] = Integer.parseInt(st.nextToken());
			System.out.println(T.binary(Barr[i]));
		}
		
		
	}
}
