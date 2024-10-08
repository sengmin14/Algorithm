import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
    private static int n, answer;
    private static Atom atom;
    private static Iterator<Atom> itr;
    private static int[][] check = new int[4001][4001];
    private static int[] dx = {0, 0, -1, 1};
    private static int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            n = Integer.parseInt(br.readLine());
            answer = 0;
            LinkedList<Atom> atoms = new LinkedList<>();

            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());

                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                int dir = Integer.parseInt(st.nextToken());
                int value = Integer.parseInt(st.nextToken());

                atoms.add(new Atom(2 * (x + 1000), 2 * (y + 1000), dir, value));
            }

            solution(atoms);
            System.out.println("#" + t + " " + answer);
        }
    }

    private static void solution(LinkedList<Atom> atoms) {
        while (atoms.size() != 0) {
            itr = atoms.iterator();
            while(itr.hasNext()){
                atom = itr.next();
                atom.move(); // 원자 이동
                if(!isValid(atom)) itr.remove(); // 맵 밖으로 나갔으면 삭제
            }
            collision(atoms); // 충돌 처리
        }
    }

    private static void collision(LinkedList<Atom> atoms) {
        for (Atom atom : atoms) { // 0으로 초기화
            check[atom.x][atom.y] = 0;
        }
        for (Atom atom : atoms) { // value 더해줌
            check[atom.x][atom.y] += atom.value;
        }

        itr = atoms.iterator();
        while (itr.hasNext()) {
            atom = itr.next(); 
            if (check[atom.x][atom.y] != atom.value) { // 2개 이상인 원자 삭제 및 값 더해줌
                answer += atom.value;
                itr.remove();
            }
        }
    }

    private static boolean isValid(Atom atom) {
        if (atom.x < 0 || atom.y < 0 || atom.x > 4000 || atom.y > 4000) return false;
        return true;
    }

    static class Atom {
        int x, y;
        int dir;
        int value;

        public Atom(int x, int y, int dir, int value) {
            this.x = x;
            this.y = y;
            this.dir = dir;
            this.value = value;
        }

        public void move() {
            this.x = x + dx[dir];
            this.y = y + dy[dir];
        }
    }
}