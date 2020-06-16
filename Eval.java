import java.util.*;
import java.io.*;
class Eval
{
	public void findeval(ArrayList<String> arrq)
	{
		int binrev[][] = Binv.bin;
		double count[] = new double[50];
		for(int i=0;i<binrev.length;i++)
		{
			for(int j=0;j<6;j++)
			{
				if(binrev[i][j]==1)
				{
					count[i]++;
				}
				
			}
			count[i]=count[i]/6;
			count[i] = ((double)Math.round(count[i]*100))/100;
			//System.out.println(count[i]);
		}
		
		try
		{
			
			FileWriter writer = new FileWriter("eval1.txt");
			BufferedWriter buffer = new BufferedWriter(writer);
			buffer.write("Precision for every query\n");
			for(int i=0;i<arrq.size();i++)
			{
				if(arrq.get(i).length()<=50)
				{
					buffer.write(arrq.get(i));
					for(int j=0;j<50-arrq.get(i).length();j++)
					{
						//System.out.println("rthrtfyhsdrthdrh");
						buffer.write(" ");
					}
					//for(int j=0;j<arrd.size();j++)
					//{
					buffer.write(" "+count[i]);
					//}
					buffer.write("\n");
				}
				else
				{
					buffer.write(arrq.get(i).substring(0,50));
					for(int j=0;j<50-arrq.get(i).length();j++)
					{
						buffer.write(" ");
					}
					//for(int j=0;j<arrd.size();j++)
					//{
					buffer.write(" "+count[i]);
					//}
					buffer.write("\n");
					buffer.write(arrq.get(i).substring(50));
					buffer.write("\n");
				}	
						
					
			}     	
			buffer.close();
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		} 
	}
}
