/*
 * Authors: Keeton Hersey, AJ Cordero
 * Date: 4/8/18
 * Overview: The driving main method for implementing the importing
 *  of the original graphs and implementation of each algorithm
 */


package standard;

import java.io.*;

public class Main {

	public static void main(String args[])
	{
		String[] names = null;
		int[][] edges = null;
		Graph graph = null;
		try {
			BufferedReader read = new BufferedReader(new FileReader("src\\standard\\Input.txt"));
			String vertexNames = read.readLine();
			int count = 1;
			int nextComma = 0;
			while(true)
			{
				nextComma = vertexNames.indexOf(',',nextComma+1);
				if(nextComma == -1)
					break;
				count++;
			}
			names = new String[count];
			for(int i = 0; i < names.length; i++)
			{
				int end = vertexNames.indexOf(',');
				if(end != -1)
					names[i] = vertexNames.substring(0, vertexNames.indexOf(','));
				else
					names[i] = vertexNames.substring(0);
				vertexNames = vertexNames.substring(vertexNames.indexOf(',')+1);
			}
			
			edges = new int[count][count];
			for(int i = 0; i < count; i++)
			{
				String thisLine = read.readLine();
				for(int j = 0; j < count; j++)
				{
					if(thisLine == null)
						break;
					int end = thisLine.indexOf(',');
					String intString;
					if(end != -1)
						intString = thisLine.substring(0, thisLine.indexOf(','));
					else
						intString = thisLine.substring(0);
					thisLine = thisLine.substring(thisLine.indexOf(',')+1);
					if(intString.charAt(0) != 'x')
						edges[i][j] = Integer.parseInt(intString);
					else
						edges[i][j] = Integer.MAX_VALUE;
				}
			}
			
			read.close();
			
			graph = new Graph(names,edges);
		}
		catch(Exception e)
		{
			System.err.println(e);
			e.printStackTrace(System.err);
		}
		System.out.println("Graph\n"+graph);
		System.out.println(new PrimJarnik(graph));
		System.out.println(new Kruskal(graph));
		System.out.println(new FloydWarshall(graph));
		
	}
}
