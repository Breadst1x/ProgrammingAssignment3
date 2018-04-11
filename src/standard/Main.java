/*
 * Authors: Keeton Hersey, AJ Cordero
 * Date: 4/8/18
 * Overview: Implementation and manipulaton of graphs
 */


package standard;

import java.io.*;

public class Main {

	public static void main(String args[])
	{
		String[] names;
		int[][] edges;
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
		}
		catch(Exception e)
		{
			System.err.print(e);
		}
	}
}
