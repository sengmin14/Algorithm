import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main
{
	static int N;
	
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		int copyN = N;
		int length = 1;
		int c = 2;
		
		int half = 0;
		
		while(true)
		{
			copyN -= c;
			if(copyN <= 0)
			{
				copyN += c;
				half = copyN;
				break;
			}
			else
			{
				c *= 2;
				length += 1;
			}
		}
		// System.out.println(length);
		// System.out.println(c);
		// System.out.println(half);
		while(length != 0)
		{
			if(half > c/2)
			{
				System.out.print('7');
				half -= c/2;
			}
			else
			{
				System.out.print('4');
			}
			
			c /= 2;
			length--;
		}
		// System.out.println(half);
		// System.out.println(c);
		// System.out.println(length);
		
	}
}