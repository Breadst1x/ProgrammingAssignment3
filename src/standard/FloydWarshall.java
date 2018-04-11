/*
 * Authors: Keeton Hersey, AJ Cordero
 * Date: 4/8/18
 * Overview: The implementation of the FloydWarshall algorithm, toString() method manipulates graph to produce output as desired
 */

package standard;

public class FloydWarshall extends Graph{
	public Graph postGraph;
	public FloydWarshall(Graph graph)
	{
		super(graph.names, graph.edges);
		postGraph = graph;
	}
	public String toString()
	{
		return "Floyd-Warshall\n"+FWinit();
	}
	public String FWinit()
	{
		String out = "";
		for(int i = 0; i < edges.length; i++)
			edges[i][i] = 0;
		for(int[] a : edges)
			for(int i = 0; i < a.length; i++)
				if(a[i] == Integer.MAX_VALUE)
					a[i] = Integer.MAX_VALUE/3;
		out += super.toString() + "\n\n";
		for(int k = 0; k < names.length; k++)
			for(int i = 0; i < names.length; i++)
				for(int j = 0; j < names.length; j++)
					if(edges[i][j] > edges[i][k]+edges[k][j])
					{
						edges[i][j] = edges[i][k]+edges[k][j];
						out += super.toString() + "\n\n";
					}
		
		return out;
	}
}
