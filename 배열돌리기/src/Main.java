import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main
{
	static int N;
	static int M;
	static int R;
	
	static int[][] arr;
	
	public void rotate()
	{
		int count = Math.min(N, M) / 2;
		
		for(int j=0; j<count; j++) { //라인들 전부 돌리기
			int temp = arr[j][j]; //맨 마지막에 넣기 위해 따로 저장!!!!배열에서 원소 위치 바꿀때 하나를 temp에 저장해두는거랑 같은 원리
			
			for(int k=j+1; k<M-j; k++)
				arr[j][k-1] = arr[j][k];
			
			for(int k=j+1; k<N-j; k++)
				arr[k-1][M-1-j] = arr[k][M-1-j];
			
			for(int k=M-2-j; k>=j; k--)
				arr[N-1-j][k+1] = arr[N-1-j][k];
			
			for(int k=N-2-j; k>=j; k--)
				arr[k+1][j] = arr[k][j];
			
			arr[j+1][j] = temp;
		}
	}
	
	public static void main(String[] args) throws IOException
	{
		Main T = new Main();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
		arr = new int[N][M];
		
		for(int i = 0; i < N; i++)
		{
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++)
			{
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		for(int i = 0; i < R; i++)
		{
			T.rotate();
		}
		
		
		for(int i = 0; i < N; i++)
		{
			for(int j = 0; j < M; j++)
			{
				System.out.print(arr[i][j] + " ");
			}
			System.out.println();
		}
		
		
	}
}