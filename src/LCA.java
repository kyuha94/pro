import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.StringTokenizer;

// 11437��

public class LCA {

	static int N, M;
	static int[][] par; // 2�� n���� �ܰ� ������ ��ġ�� ����Ѵ�. par[A][3] A�� ���� 2�� 3���� ��ġ�� ����
	static int[] dep; // A�� ���� ����
	static ArrayList<Integer>[] tree;

	static StringTokenizer st;

	public static void main(String[] args) throws Exception {

		System.setIn(new FileInputStream("./src/11437.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		N = Integer.parseInt(br.readLine().trim());
		
		tree = new ArrayList[N+1];
		dep = new int[N+1];
		par = new int[N+1][19];
		
		for (int n = 1; n <= N; n++) {
			tree[n] = new ArrayList<Integer>();
		}
		
		int a, b;
		for (int n = 1; n < N; n++) {
			st = new StringTokenizer(br.readLine().trim());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			par[Math.max(a, b)][0]=Math.min(a, b);
			tree[a].add(b);
			tree[b].add(a);
		}
		
		Deque<Integer> dq = new ArrayDeque<Integer>();
		//par[1][0]=1;
		dq.add(1);
		dep[1]=1;

		// bfs �� ���� ���
		while(!dq.isEmpty()) {
			int now = dq.pollFirst();
			for(int next:tree[now]) {
				if(dep[next]==0) {
					dep[next] = dep[now]+1;
					dq.addLast(next);
				}
			}
		}
		
		for (int n = 1; n <= N; n++) {
			//System.out.println(n+" "+dep[n]);
			for (int l = 1; l < 19; l++) {
				par[n][l]=par[par[n][l-1]][l-1];
			}
		}

		M = Integer.parseInt(br.readLine().trim());
		for (int m = 1; m <= M; m++) {
			System.out.println(" TC==================>"+M);
			st = new StringTokenizer(br.readLine().trim());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			System.out.println(lca(a,b));
		}
	}

	private static int lca(int a, int b) {
		
		// ���̰� �� ���� ���� a�� �����
		if(dep[a]<dep[b]) {
			a^=b;b^=a;a^=b;
		}
		
		int two=1;
		//System.out.println(a+ " "+b);
		//System.out.println(dep[a]+ " "+dep[b]);
		// �ΰ��� ���� ���� ��ŭ a�� �ø���.
		for (int l = 0; l < 19; l++) {
			// �� ���� ���̿� �ش��ϴ� ���̸�ŭ �ø���.
			if( ( (dep[a]-dep[b])&(two<<l) ) !=0) {
				
				System.out.println(" ======>"+l);
				a=par[a][l];
			}
		}

		//System.out.println(a+ " "+b);
		//System.out.println(dep[a]+ " "+dep[b]);
		
		
		if(a==b) return a; 

		for(int i=18; i>0;i--) {
			//System.out.println(par[a][i]+ " "+par[b][i]+"  "+i);
			
			// �ΰ��� �ٸ��ٴ� �� ���������� �ƴϴ�.. 
			if(par[a][i]!=par[b][i]) {
				a = par[a][i];
				b = par[b][i];
			}
		}
		
		return par[a][0];
		
	}
}