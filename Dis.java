import java.util.*;
import java.io.*;
class pair
{
	int doc;
	double val;
	pair(int doc,double val)
	{
		this.doc = doc;
		this.val = val;
	}
}
class Sortbypair implements Comparator<pair> 
{ 
    public int compare(pair a, pair b) 
    { 
    	if(a.val<b.val)
    		return 1;
    	if(b.val<a.val)
    		return -1;
    	return 0;
         
    } 
} 
class Dis
{
	public void printscore(double sum[][],ArrayList<String> arrd,ArrayList<String> arrq)
	{
		try
		{
			//for(int i=0;i<arrq.size();i++)
			//{
				
			FileWriter writer = new FileWriter("output.txt");
			BufferedWriter buffer = new BufferedWriter(writer);
			buffer.write("Final score for every query\n");
			for(int i=0;i<arrq.size();i++)
			{
				if(arrq.get(i).length()<=100)
				{
					buffer.write(arrq.get(i));
					for(int j=0;j<100-arrq.get(i).length();j++)
					{
						buffer.write(" ");
					}
					for(int j=0;j<arrd.size();j++)
					{
						buffer.write(" "+sum[i][j]);
					}
					buffer.write("\n");
				}
				else
				{
					buffer.write(arrq.get(i).substring(0,100));
					for(int j=0;j<100-arrq.get(i).length();j++)
					{
						buffer.write(" ");
					}
					for(int j=0;j<arrd.size();j++)
					{
						buffer.write(" "+sum[i][j]);
					}
					int k=100;
					buffer.write("\n");
					buffer.write(arrq.get(i).substring(100));
					buffer.write("\n");
				}	
					
			}     
    			buffer.close();   
			//}
		}
		catch(Exception e)
		{
			System.out.print(e.getMessage());
		}
			
	
	}
	public void printtopscore(double sum[][],ArrayList<String> arrd,ArrayList<String> arrq,ArrayList<ArrayList<pair>> ans)
	{
		try
		{
			//for(int i=0;i<arrq.size();i++)
			//{
				
			FileWriter writer = new FileWriter("topscore.txt");
			BufferedWriter buffer = new BufferedWriter(writer);
			buffer.write("top score for every query\n");
			
			for(int i=0;i<arrq.size();i++)
			{
				ArrayList<pair> ar = new ArrayList<>();
				for(int j=0;j<arrd.size();j++)
				{
					ar.add(new pair(j,sum[i][j]));
				} 
				Collections.sort(ar,new Sortbypair());
				ans.add(ar);
				if(arrq.get(i).length()<=50)
				{
					buffer.write(arrq.get(i));
					for(int j=0;j<50-arrq.get(i).length();j++)
					{
						buffer.write(" ");
					}
					for(int j=0;j<10;j++)
					{
						buffer.write(" "+"(Doc"+ar.get(j).doc+":"+ar.get(j).val+")");
					}
					buffer.write("\n");
				}
				else
				{
					buffer.write(arrq.get(i).substring(0,50));
					for(int j=0;j<50-arrq.get(i).length();j++)
					{
						buffer.write(" ");
					}
					for(int j=0;j<10;j++)
					{
						buffer.write(" "+"(Doc"+ar.get(j).doc+":"+ar.get(j).val+")");
					}
					buffer.write("\n");
					buffer.write(arrq.get(i).substring(50));
					buffer.write("\n");
				}	
					
			}     
    			buffer.close();   
			//}
		}
		catch(Exception e)
		{
			System.out.print(e.getMessage());
		}
	}
}
