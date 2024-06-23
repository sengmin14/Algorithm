import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main
{
	static String str;
	static int strLen;
	static int[] numCountArr;
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		str = st.nextToken();
		strLen = str.length();
		numCountArr = new int[10];
		long total = 0;
		
		for(int i = 0; i < strLen; i++)
		{
			int tNum = Integer.parseInt(str.substring(i, i+1));
			numCountArr[tNum] += 1;
			total += tNum;
		}
		
		if(!str.contains("0") || total % 3 != 0)
		{
			System.out.println(-1);
		}
		else
		{
			StringBuilder sb = new StringBuilder();
			for(int i = 9; i >= 0; i--)
			{
				while(numCountArr[i] > 0)
				{
					sb.append(i);
					numCountArr[i]--;
				}
			}
			System.out.println(sb.toString());
		}
	}
}