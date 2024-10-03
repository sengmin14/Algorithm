import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static int[][] map;
	static boolean isOk;
	
	public boolean isValid(int k, int i, int j)
	{
		for(int m = 0; m < 9; m++)
		{
			if(map[m][j] == k || map[i][m] == k)
			{
				return false;
			}
		}
		
		int ii = (i / 3) * 3;
		int jj = (j / 3) * 3;
		
		for(int m = ii; m < ii+3; m++)
		{
			for(int n = jj; n < jj+3; n++)
			{
				if(map[m][n] == k)
				{
					return false;
				}
			}
		}
		return true;
	}
	
	public void DFS(int depth)
	{	
		if(isOk)
		{
			return;
		}
		
		if(depth == 81)
		{
			isOk = true;
			return;
		}
		else
		{
			int i = depth/9;
			int j = depth%9;
			if(map[i][j] != 0)
			{
				DFS(depth + 1);
			}
			else
			{
				for(int k = 1; k <= 9; k++)
				{
					if(isValid(k, i, j))
					{
						map[i][j] = k;
						DFS(depth+1);
						if(isOk) return;
						map[i][j] = 0;
					}
				}
			}
		}
		
		
	}
	
	public static void main(String[] args) throws IOException
	{
		Main T = new Main();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		map = new int[9][9];
		
		for(int i = 0; i < 9; i++)
		{
			st = new StringTokenizer(br.readLine());
			String str = st.nextToken();
			for(int j = 0; j < 9; j++)
			{
				map[i][j] = str.charAt(j) - '0';
			}
		}
		
		T.DFS(0);
		
		for(int i = 0; i < 9; i++)
		{
			for(int j = 0; j < 9; j++)
			{
				System.out.print(map[i][j]);
			}
			System.out.println();
		}
		
	}
}
