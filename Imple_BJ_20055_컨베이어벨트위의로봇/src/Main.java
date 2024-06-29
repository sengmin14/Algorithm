import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main
{
	static int N;
	static int K;
	static int answer;
	static int[] arr;
	static int[] robot;
	
	static int cnt;
	
	public boolean moveRobot()
	{
		for(int i = N-2; i >= 0 ; i--)
		{
			if(arr[i+1] >= 1 && robot[i+1] == 0 && robot[i] == 1)
			{
				robot[i+1] = 1;
				robot[i] = 0;
				arr[i+1] -= 1;
				if(arr[i+1] == 0)
				{
					answer++;
					if(answer == K)
					{
						return false;
					}
				}
			}
		}
		return true;
	}
	
	public void popRobot()
	{
		if(robot[N-1] == 1)
		{
			robot[N-1] = 0;
		}
	}
	
	public boolean pushRobot()
	{
		if(arr[0] >= 1 && robot[0] == 0)
		{
			robot[0] = 1;
			arr[0]--;
			if(arr[0] == 0)
			{
				answer++;
				if(answer == K)
				{
					return false;
				}
			}
		}
		return true;
	}
	
	public void move()
	{
		int tmp = arr[N*2-1];
		for(int i = N*2-2; i >= 0 ; i--)
		{
			arr[i+1] = arr[i];
		}
		
		for(int i = N-2; i >= 0 ; i--)
		{
			robot[i+1] = robot[i];
		}
		robot[0] = 0;
		arr[0] = tmp;
		
		
	}
	
	public static void main(String[] args) throws IOException
	{
		Main T = new Main();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		arr = new int[N*2];
		robot = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N*2; i++)
		{
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		while(true)
		{
			cnt++;
			T.move();
			T.popRobot();
			if(!T.moveRobot())
			{
				break;
			}
			T.popRobot();
			if(!T.pushRobot())
			{
				break;
			}
			
		}
		System.out.println(cnt);
		
	}
}