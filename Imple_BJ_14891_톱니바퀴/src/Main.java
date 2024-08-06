import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main
{
	static int[][] wheel;
	
	public void rotate(int no, int dir)
	{
		if(dir == 1)
		{
			int tmp = wheel[no][7];
			for(int i = 7; i >= 1; i--)
			{
				wheel[no][i] = wheel[no][i-1];
			}
			wheel[no][0] = tmp;
		}
		else if(dir == -1)
		{
			int tmp = wheel[no][0];
			for(int i = 0; i < 7; i++)
			{
				wheel[no][i] = wheel[no][i+1];
			}
			wheel[no][7] = tmp;
		}
	}
	
	public void rightRotate(int no, int dir)
	{
		if(no < 3)
		{
			if(wheel[no][2] != wheel[no+1][6])
			{
//				System.out.println("오른쪽회전 : " + (no+1));
				rightRotate(no+1, -dir);
				rotate(no+1, -dir);
			}
		}
	}
	
	public void leftRotate(int no, int dir)
	{
		if(no > 0)
		{
			if(wheel[no][6] != wheel[no-1][2])
			{
//				System.out.println("왼쪽회전 : " + (no-1));
				leftRotate(no-1, -dir);
				rotate(no-1, -dir);
			}
		}
	}
	
	public void check(int no, int dir)
	{
		rightRotate(no, dir);
		leftRotate(no, dir);
		rotate(no, dir);
	}
	
	public static void main(String[] args) throws IOException
	{
		Main T = new Main();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		wheel = new int[4][8];
		
		for(int i = 0; i < 4; i++)
		{
			st = new StringTokenizer(br.readLine());
			String str = st.nextToken();
			for(int j = 0; j < 8; j++)
			{
				wheel[i][j] = str.charAt(j) - '0';
			}
		}
		
		st = new StringTokenizer(br.readLine());
		int cnt = Integer.parseInt(st.nextToken());
		
		for(int i = 0; i < cnt; i++)
		{
			st = new StringTokenizer(br.readLine());
			int no = Integer.parseInt(st.nextToken())-1;
			int dir = Integer.parseInt(st.nextToken());
			T.check(no, dir);
//			for(int k = 0; k < 4; k++)
//			{
//				System.out.println(Arrays.toString(wheel[k]));
//			}
		}
		int ans = 0;
		if(wheel[0][0] == 1)
		{
			ans += 1;
		}
		if(wheel[1][0] == 1)
		{
			ans += 2;
		}
		if(wheel[2][0] == 1)
		{
			ans += 4;
		}
		if(wheel[3][0] == 1)
		{
			ans += 8;
		}
		
		System.out.println(ans);
		
	}
}