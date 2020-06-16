import java.util.*;
import java.io.*;
class Project
{
	public static double[] weight(double tf[],double idf[])
	{
		double w[] = new double[tf.length];
		for(int i=0;i<w.length;i++)
		{
			w[i] = tf[i]*idf[i];
			w[i] = ((double)Math.round(w[i]*100))/100.0;
		}
		return w;
	}
	public static double[][] score(double[][][] w, int lenq, int lend,ArrayList<String> arrq)
	{
		double sum[][] = new double[lenq][lend];
		for(int i=0;i<lenq;i++)
		{
			for(int j=0;j<lend;j++)
			{
				for(int k=0;k<w[i][j].length;k++)
				{
					sum[i][j]=sum[i][j]+w[i][j][k];
				}
			}
		}
		for(int i=0;i<lenq;i++)
		{
			for(int j=0;j<lend;j++)
			{
				sum[i][j] = (((double)Math.round(sum[i][j]*100))/100.0);
			}
		}
		return sum;
	}	
	public static double[] inv_freq(String q,ArrayList<String> arrd)
	{
		HashMap<String,Integer> hm = new HashMap<>();
		String name[] = q.split(" ");
		HashMap<String,Boolean> ch = new HashMap<>(); 
		for(int i=0;i<name.length;i++){
			if(!hm.containsKey(name[i].toLowerCase()))
			{
				hm.put(name[i].toLowerCase(),0);
				ch.put(name[i].toLowerCase(),false);
			}
		}
		double ans[] = new double[ch.size()];
		int k=0;
		for(int i=0;i<name.length;i++){
			if(!ch.get(name[i].toLowerCase()))
			{
				for(int j=0;j<arrd.size();j++)
				{
					String temp = arrd.get(j).toLowerCase();
					if(temp.contains(name[i].toLowerCase()))
					{
						ans[k]++;
					}
				}
				ch.put(name[i].toLowerCase(),true);
				k++;
			}
		}
		for(int i=0;i<ch.size();i++)
		{
			if(ans[i]>0)
			{
				ans[i] = 1+((double)Math.round(Math.log10(((double)arrd.size())/ans[i])*100))/100.0;
			}
		}
		return ans;
		
	}
	public static double[] term_freq(String q,String d){
		HashMap<String,Integer> hm = new HashMap<>();
		HashMap<String,Boolean> ch = new HashMap<>();
		String name[] = q.split(" "); 
		for(int j=0;j<name.length;j++){
			if(!hm.containsKey(name[j].toLowerCase())){
				hm.put(name[j].toLowerCase(),0);
				ch.put(name[j].toLowerCase(),false);
			}
		}
		name = d.split(" ");
		for(int j=0;j<name.length;j++){
			if(hm.containsKey(name[j].toLowerCase())){
				hm.put(name[j].toLowerCase(),hm.get(name[j].toLowerCase())+1);
				
			}
		}
		name = q.split(" ");
		double ans[] = new double[hm.size()];
		int j=0;
		for(int i=0;i<name.length;i++){
			if(!ch.get(name[i].toLowerCase())){
				if(hm.get(name[i].toLowerCase())>0)
				{
					ans[j] = 1+((double)Math.round(Math.log10(hm.get(name[i].toLowerCase()))*100))/100.0;
					j++;
					ch.put(name[i].toLowerCase(),true);
				}
				else
				{
					ans[j]=0;
					ch.put(name[i].toLowerCase(),true);
					j++;
				}
			}
		}
		return ans;
		
		
		
	}
	public static ArrayList<String> docpath()
	{
		ArrayList<String> arr = new ArrayList<>();
		
		String bpath = "./dcheck";
		File f = new File(bpath);
		File ar[]=null;
		if(f.exists())
			ar = f.listFiles();
		if(ar==null)
			return null;
		for(int i=0;i<ar.length;i++)
		{
			String ans = "";
			try
			{
				if(!ar[i].exists())
					throw new Exception();
				FileReader in = new FileReader(ar[i]);
				BufferedReader br = new BufferedReader(in);
				String line=null;
				while((line=br.readLine())!=null)
				{
					ans = ans+" "+line;
				}
				arr.add(ans);
				in.close();
			}
			catch(Exception e)
			{
			
			}
		}
		return arr;
		
	}
	public static ArrayList<String> querypath()
	{
		ArrayList<String> arr = new ArrayList<>();
		
		String bpath = "./qcheck";
		File f = new File(bpath);
		File ar[]=null;
		if(f.exists())
			ar = f.listFiles();
		if(ar==null)
			return null;
		for(int i=0;i<ar.length;i++)
		{
			String ans = "";
			try
			{
				if(!ar[i].exists())
					throw new Exception();
				FileReader in = new FileReader(ar[i]);
				BufferedReader br = new BufferedReader(in);
				String line=null;
				while((line=br.readLine())!=null)
				{
					ans = ans+" "+line;
				}
				ans = ans.trim();
				arr.add(ans);
				in.close();
			}
			catch(Exception e)
			{
			
			}
		}
		return arr;
		
	}
	public static void main(String arg[])
	{
		ArrayList<String> arrd = docpath();
		ArrayList<String> arrq = querypath();
		/*for(int i=0;i<arrq.size();i++)
		{
			System.out.println(arrq.get(i));
		}*/
		int row = arrq.size();
		int col = arrd.size();
		double termf[][][] = new double[row][col][];
		//HashMap<String,Integer> inv = new HashMap<>();
		//int w[][] =  new int[row][col];
		for(int i=0;i<row;i++)
		{
			for(int j=0;j<col;j++)
			{
				termf[i][j] = term_freq(arrq.get(i),arrd.get(j));
			}
		}
		
		double inv[][] = new double[row][];
		for(int i=0;i<row;i++)
		{
			inv[i] = inv_freq(arrq.get(i),arrd);
		}
		double w[][][] = new double[row][col][];
		for(int i=0;i<row;i++)
		{
			for(int j=0;j<col;j++)
			{
				w[i][j]=weight(termf[i][j],inv[i]);
			}
			
		}
		
		double sum[][] = score(w,arrq.size(),arrd.size(),arrq);
		
		Dis d = new Dis();
		d.printscore(sum,arrd,arrq);
		ArrayList<ArrayList<pair>> ans = new ArrayList<ArrayList<pair>>(); 
		d.printtopscore(sum,arrd,arrq,ans);
		Eval e =new Eval();
		e.findeval(arrq);
		
	}
}
