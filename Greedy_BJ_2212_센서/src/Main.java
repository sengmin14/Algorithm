import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main
{
	static int N;
	static int K;
	static int[] arr;
	static int[] diff;
	
	static int sum;
	
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		K = Integer.parseInt(st.nextToken());
		
		arr = new int[N];
		diff = new int[N-1];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++)
		{
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(arr);
		
		for(int i = 0; i < N-1; i++)
		{
			diff[i] = arr[i+1] - arr[i];
		}
		
		Arrays.sort(diff);
		
//		System.out.println(Arrays.toString(arr));
//		System.out.println(Arrays.toString(diff));
		
		for(int i = 0; i < N-K; i++)
		{
			sum+=diff[i];
		}
		
		System.out.println(sum);
		
	}
}