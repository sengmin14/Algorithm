import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int N;
	static int[] arr;
	
	static long A;
	static long B;
	
	static long answer;
	
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		arr = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++)
		{
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine());
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		
		for(int i = 0; i < N; i++)
		{
			if(arr[i] > A)
			{
				answer++;
				arr[i] -= A;
				answer += Math.ceil(arr[i]/(double)B);
//				System.out.println(Math.ceil(arr[i]/(double)B));
			}
			else
			{
				answer++;
				arr[i] -= A;
				continue;
			}
//			System.out.println(answer);
		}
		
		System.out.println(answer);
	}
}
