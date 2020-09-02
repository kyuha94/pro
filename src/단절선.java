import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.StringTokenizer;

// 
public class 단절선 {
	static int T, V, E; //V(1≤V≤10,000), E(1≤E≤100,000)
	static int[] visited;
	static int[] cut;
	static ArrayList<Integer> G[];
	static int seq;
	static ArrayList<Point> pl;
	
	public static class Point implements Comparable<Point> {
		int x;
		int y;
		
		public Point(int a, int b) {
			this.x=a;
			this.y=b;
		}

		@Override
		public int compareTo(Point o) {
			if(this.x<o.x ) return -1;
			else return 1;
		}
	}

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		// System.setIn(new FileInputStream("sample/shortest_path.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		pl = new ArrayList<단절선.Point>();
		
		visited = new int[V+1];
		cut = new int[V+1];
		G = new ArrayList[V+1];
		

		for(int v=1; v<=V;v++ ) {
			visited[v] = 0;
			G[v] = new ArrayList<>();
		}
		
		int a, b;
		for(int e=0; e<E;e++ ) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			
			G[a].add(b);
			G[b].add(a);			
		}
		
		seq=1;
		dfs(1,0);

		Collections.sort(pl);
		System.out.println(pl.size());
		for(Point p : pl) {
			System.out.println(p.x+" "+p.y);
		}
	}

	// 자신이 갈수 있는 가장 앞 번호를 리턴한다.
	private static int dfs(int checkpoint, int parent) {
		
		int ret = seq; 

		// 방문순서
		visited[checkpoint] = seq++;
		
		// checkpoint 와 하위 점들이 checkpoint-parent 간선을 사용하지 않고 더 앞으로 갈수 있나?
		for(int next : G[checkpoint]) {
			if(next == parent) continue;

			if(visited[next]==0) {

				int low = dfs(next, checkpoint);
				
				if( low > visited[checkpoint]) {
					pl.add(new Point(Math.min(checkpoint,next), Math.max(checkpoint,next)));
				}

				ret = Math.min(ret, low);
			}else {
				ret = Math.min(ret, visited[next]);
			}

		}		

		return ret;

	}
}

