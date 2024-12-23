import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	static int N;
	static int K;
	static int[] arr;
	
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++)
		{
			int a = Integer.parseInt(st.nextToken());
			for(int j = 0; j < K; j++)
			{
				arr[j] += a;
			}
			Arrays.sort(arr);
			
			System.out.println(Arrays.toString(arr));
		}
		
	}

}
