/*
 * Authors: Keeton Hersey, AJ Cordero
 * Date: 4/8/18
 * Overview: The implementation of the Kruskal algorithm, toString() method produced output as desired
 */

package standard;

public class Kruskal extends Graph{
	//Only For Undirected(Symmetrical Edged) graphs
	public Kruskal(Graph graph)
	{
		super(graph.names, graph.edges);
		Kinit();
	}
	public String toString()
	{
		String out = "Kruskal:\n";
		for(int i = 0; i < edges.length; i++)
			for(int j = i; j < edges[i].length; j++)
				if(edges[i][j] < Integer.MAX_VALUE/10)
					out += names[i]+"-"+names[j] + " ";
		out+="\n";
		return out;
	}
	static class KNode
	{
		public int index, club;
		public KNode(int index, int club) 
		{
			this.club = club;
			this.index = index;
		}
	}
	static class KEdge{
		public KNode start, end;
		public int value;
		public boolean enabled;
		public KEdge(KNode start, KNode end, int value, boolean enabled)
		{
			this.start = start;
			this.end = end;
			this.value = value;
			this.enabled = enabled;
		}
	}
	private void Kinit()
	{
		KNode[] nodeArray = new KNode[this.names.length];
		for(int i = 0; i < nodeArray.length; i++)
			nodeArray[i] = new KNode(i, Integer.MAX_VALUE-i);
		int count = 0;
		for(int i = 0; i < this.edges.length; i++)
			for(int j = 0; j < this.edges[i].length; j++)
				if(edges[i][j] != Integer.MAX_VALUE)
					count++;
		KEdge[] edgeArray = new KEdge[count];
		count = 0;
		for(int i = 0; i < this.edges.length; i++)
			for(int j = 0; j < this.edges[i].length; j++)
				if(edges[i][j] != Integer.MAX_VALUE)
				{
					edgeArray[count] = new KEdge(nodeArray[i],nodeArray[j],edges[i][j],false);
					count++;
				}
		nodeArray[0].club = 0;
		boolean goOn = false;
		for(KNode e : nodeArray)
			if(e.club != 0)
				goOn = true;
		while(goOn)
		{
			KEdge shortest = new KEdge(null,null,Integer.MAX_VALUE,false);
			for(KEdge e: edgeArray)
				if(e != null && !(e.start.club == e.end.club) && e.value < shortest.value)
					shortest = e;
			shortest.enabled = true;
			int shortClub = Integer.min(shortest.start.club, shortest.end.club);
			for(KNode e : nodeArray)
				if(e.club == shortest.start.club || e.club == shortest.end.club)
					e.club = shortClub;
			goOn = false;
			for(KNode e : nodeArray)
				if(e.club != 0)
					goOn = true;
		}
		for(int i = 0; i < this.edges.length; i++)
			for(int j = 0; j < this.edges[i].length; j++)
			{
				this.edges[i][j] = Integer.MAX_VALUE;
			}
		count = 0;
		for(int i = 0; i < edgeArray.length; i++)
		{
			if(edgeArray[i].enabled)
			{
				count++;
				this.edges[edgeArray[i].start.index][edgeArray[i].end.index] = edgeArray[i].value;
				this.edges[edgeArray[i].end.index][edgeArray[i].start.index] = edgeArray[i].value;
			}
		}

	}
}

