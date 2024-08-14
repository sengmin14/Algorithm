import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


class Student implements Comparable<Student>
{
	int like;
	int empty;
	int x;
	int y;
	public Student(int like, int empty, int x, int y)
	{
		this.like = like;
		this.empty = empty;
		this.x = x;
		this.y = y;
	}
	@Override
	public int compareTo(Student o)
	{
		// this.x - o.x 작은거 부터 담긴다.

		return 0;
	}
	
}

class Main
{
	static int[][] map;
	static int[][] person;
	static int N;
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		
		
		
	}
}