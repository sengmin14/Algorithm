import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main
{
	static int N;
	static int M;
	static int K;
	
	static int answer;
	
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		while(N >= 2 && M >= 1 && M+N >= 3+K)
		{
			N = N-2;
			M = M-1;
			answer++;
		}
		
		System.out.println(answer);
	}
}
