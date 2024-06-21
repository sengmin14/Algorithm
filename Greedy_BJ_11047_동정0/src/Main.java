import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main
{
	static int N;
	static int K;
	static int[] arr;
	
	static int answer;
	
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		arr = new int[N];
		
		for(int i = 0; i < N; i++)
		{
			st = new StringTokenizer(br.readLine());
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		for(int i = N-1; i >= 0; i--)
		{
			if(K/arr[i] >= 1)
			{
				int value = K/arr[i];
				answer += value;
				K -= value * arr[i];
//				System.out.println(answer * arr[i]);
			}
		}
		System.out.println(answer);
	}
}