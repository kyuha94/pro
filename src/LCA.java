import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class LCA {

	static int T = 0;
	static int N, R, Q;
	static ArrayList<Integer> t[];
	static int[][] par;
	static int[] dep;

	static StringTokenizer st;

	public static void main(String[] args) throws Exception {

// System.setIn(new FileInputStream("./tc/LCA.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

// T = Integer.parseInt(br.readLine().trim());

		st = new StringTokenizer(br.readLine().trim());
// for (int tc = 1; tc <= T; tc++) {

		N = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());

		par = new int[N + 1][19];
		st = new StringTokenizer(br.readLine().trim());
		t = new ArrayList[N + 1];
		dep = new int[N + 1];
		for (int e = 1; e <= N; e++) {
			t[e] = new ArrayList<Integer>();
		}

		for (int e = 1; e <= N; e++) {
			par[e][0] = Integer.parseInt(st.nextToken());
			if (e != R) {
				t[par[e][0]].add(e);
			}
		}

		Queue<Integer> que = new LinkedList<Integer>();
		que.add(R);
		dep[R] = 0;
		while (!que.isEmpty()) {
			int p = que.poll();
			for (int c : t[p]) {
				dep[c] = dep[p] + 1;
				que.add(c);
			}
		}
		for (int lvl = 1; lvl <= 18; lvl++) {
			for (int e = 1; e <= N; e++) {
				par[e][lvl] = par[par[e][lvl - 1]][lvl - 1];
			}
		}

		for (int i = 1; i <= Q; i++) {

			st = new StringTokenizer(br.readLine().trim());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			if (dep[a] > dep[b]) {
//bw.write("NO\n");
				System.out.println("NO");
			} else {
				if (lca(b, a) == a)
//bw.write("YES\n");
					System.out.println("YES");
				else
//bw.write("NO\n");
					System.out.println("NO");
			}
//bw.newLine();
		}

		bw.flush();

		bw.close();

	}

	private static int lca(int a, int b) {

		// a를 더 깊은 넘으로 만든다. a를 올릴꺼야
		if (dep[a] < dep[b]) {
			// System.out.println("swap");
			a ^= b;
			b ^= a;
			a ^= b;
		}
		for (int i = 0; i < 18; i++) {
			// 2의 i 제곱과 &연산으로 원하는 만큼 올린다.
			// 4 는 100 로 i=3일때
			if (((a << i) & (dep[a] - dep[b])) != 0) {
				a = par[a][i];
			}
			//같은 높이까지 올라왔음.
			if (a == b)
				return a;
			//둘의 2의 17 제곱번째 조상부터 같은지 비교하면서
		}
		for (int i = 18; i > 0; i--) {
			if (par[a][i] != par[b][i]) {
				a = par[a][i];
				b = par[b][i];
			}
		}
		return par[a][0];
	}

}