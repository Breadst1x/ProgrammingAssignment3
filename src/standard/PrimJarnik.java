/*
 * Authors: Keeton Hersey, AJ Cordero
 * Date: 4/8/18
 * Overview: The implementation of the Prim-Jarnik algorithm, toString() method produced output as desired
 */

package standard;

public class PrimJarnik extends Graph{
	//Only For Undirected(Symmetrical Edged) graphs
	public PrimJarnik(Graph graph)
	{
		super(graph.names, graph.edges);
		PJinit();
	}
	public String toString()
	{
		String out = "PrimJarnik:\n";
		for(int i = 0; i < edges.length; i++)
			for(int j = i; j < edges[i].length; j++)
				if(edges[i][j] < Integer.MAX_VALUE/10)
					out += names[i]+"-"+names[j] + " ";
		out+="\n";
		return out;
	}
	static class PJNode
	{
		public boolean club;
		public int index;
		public PJNode(int index) 
		{
			club = false;
			this.index = index;
		}
	}
	static class PJEdge{
		public PJNode start, end;
		public int value;
		public boolean enabled;
		public PJEdge(PJNode start, PJNode end, int value, boolean enabled)
		{
			this.start = start;
			this.end = end;
			this.value = value;
			this.enabled = enabled;
		}
	}
	private void PJinit()
	{
		PJNode[] nodeArray = new PJNode[this.names.length];
		for(int i = 0; i < nodeArray.length; i++)
			nodeArray[i] = new PJNode(i);
		int count = 0;
		for(int i = 0; i < this.edges.length; i++)
			for(int j = 0; j < this.edges[i].length; j++)
				if(edges[i][j] != Integer.MAX_VALUE)
					count++;
		PJEdge[] edgeArray = new PJEdge[count];
		count = 0;
		for(int i = 0; i < this.edges.length; i++)
			for(int j = 0; j < this.edges[i].length; j++)
				if(edges[i][j] != Integer.MAX_VALUE)
				{
					edgeArray[count] = new PJEdge(nodeArray[i],nodeArray[j],edges[i][j],false);
					count++;
				}
		nodeArray[0].club = true;
		boolean goOn = false;
		for(PJNode e : nodeArray)
			if(!e.club)
				goOn = true;
		while(goOn)
		{
			PJEdge[] edgesFromClub = new PJEdge[edgeArray.length];
			for(int j = 0; j < edgeArray.length; j++)
				if(edgeArray[j].start.club && !edgeArray[j].end.club)
					edgesFromClub[j] = edgeArray[j];
			boolean getOut = true;
			for(PJEdge e: edgesFromClub)
				if(e != null)
					getOut = false;
			if(getOut)
				break;
			PJEdge shortest = new PJEdge(null,null,Integer.MAX_VALUE,false);
			for(PJEdge e: edgesFromClub)
			{
				if(e != null && e.value < shortest.value)
					shortest = e;
			}
			shortest.enabled = true;
			shortest.end.club = true;
			goOn = false;
			for(PJNode e : nodeArray)
				if(!e.club)
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

