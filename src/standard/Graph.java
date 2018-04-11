package standard;

public class Graph {
	public String[] names;
	public int[][] edges;
	public Graph(String[] names, int[][] edges)
	{
		if(names != null)
		{
			this.names = new String[names.length];
			for(int i = 0; i < names.length; i++)
				this.names[i] = names[i];
		}
		if(edges != null)
		{
			this.edges = new int[edges.length][edges[0].length];
			for(int i = 0; i < edges.length; i++)
				for(int j = 0; j < edges[i].length; j++)
						this.edges[i][j] = edges[i][j];
		}
	}
	public String toString() {
		String out = "";
		for(String e: names)
			out += " " + e;
		for(int i = 0; i < names.length; i++)
		{
			out += "\n" + names[i];
			for(int j = 0; j < edges[i].length; j++)
				if(edges[i][j] > Integer.MAX_VALUE/10)
					out += " " + (char)126;
				else
					out += " " + edges[i][j];
		}
		out += "\n";
		return out;
	}
}
