import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main
{
	static int N;
	static int M;
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		if(N == 1)
		{
			System.out.println(1);
		}
		else if(N == 2)
		{
			System.out.println(Integer.min(((M+1)/2), 4));
		}
		else
		{
			if(M >= 7)
			{
				System.out.println(M-2);
			}
			else
			{
				System.out.println(Integer.min(M, 4));
			}
		}
	}
}