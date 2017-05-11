import java.io.FileWriter;
import java.io.PrintWriter;


public class printDictionary {
	
	globals gb = new globals();	
	PrintWriter printWriter;
	int probNumber;
	
	public printDictionary(int probNumber,int nmax,int mmax,int columns, int rows, PrintWriter printWriter){
		gb.setTableauColums(columns);
		gb.setTableauRows(rows);
		gb.setNmax(nmax);
		gb.setMmax(mmax);
		this.printWriter = printWriter;
		this.probNumber = probNumber;
	}
	
	public static String notFeasibleMessage(){
		
		return "\nInfeasible Origin, Conversting it Auxiliary Problem\n";
		
	}
	
	public void printInitialDictionary(float Tableau[][]){
		
		this.printWriter.print("				Problem ");
		this.printWriter.print("#"+this.probNumber);
		this.printWriter.print("\n\n");
		this.printWriter.print("				Given Problem");
		this.printWriter.print("\n\n");
		this.printWriter.print("Maximize");
		this.printWriter.print("\n");
		for(int j=1;j<=gb.getNmax();j++)
		{
			if(Tableau[0][j]>0 || Tableau[0][j] ==0)
			{
		 		this.printWriter.print(" "+"+"+Tableau[0][j]);
				
			}
			else
			this.printWriter.print(" "+Tableau[0][j]);
			this.printWriter.print(" X");
			this.printWriter.print(j);
			
		}
		 this.printWriter.print("\n\n");
		 this.printWriter.print("Subject to");
		 this.printWriter.print("\n");

		 for(int i=1;i<=gb.getMmax();i++)
		 {
			 for(int j=1;j<=gb.getNmax();j++)
				{
				 	if(this.GreaterThanZero(Tableau[i][j]) || this.isZero(Tableau[i][j]))
				 	{
				 		this.printWriter.print("  "+"+"+Tableau[i][j]);
				 	}
				 	else
				 		this.printWriter.print("  "+Tableau[i][j]);
					
					this.printWriter.print(" X");
					this.printWriter.print(j);
					
					
				}
				this.printWriter.print(" <= ");
				if(this.GreaterThanZero(Tableau[i][0]) || this.isZero(Tableau[i][0]))
					this.printWriter.print("  "+"+"+Tableau[i][0]);
				else
					this.printWriter.print("  "+Tableau[i][0]);
	
			 this.printWriter.print("\n");
	 
		 }
		 this.printWriter.print("\n");
		 
		 
	}
	private boolean isZero(float number){
		
		if(number ==0)
		{
			return true;
		}
			
		
		return false;
	}
	private boolean GreaterThanZero(float number){
		
		if(number >0)
			return true;
		
		return false;
	}
	
	public void printAuxilaryDictionary(float Tableau[][]){
		
		this.printWriter.print("Maximize \n");
		this.printWriter.print(Tableau[0][gb.getTableauColums()-1]+ " X" + (gb.getTableauColums()-1) );
		this.printWriter.print("\n\nSubject to \n");
		
		 for(int i=1;i<=gb.getMmax();i++)
		 {
			 for(int j=1;j<=gb.getNmax();j++)
				{
				 	if(this.GreaterThanZero(Tableau[i][j]) || this.isZero(Tableau[i][j]))
				 	{
				 		this.printWriter.print("  "+"+"+Tableau[i][j]);
				 	}
				 	else
				 		this.printWriter.print("  "+Tableau[i][j]);
					
					this.printWriter.print(" X");
					this.printWriter.print(j);
					
					
				}
			 	this.printWriter.print("  "+Tableau[i][gb.getTableauColums()-1]+ " X" + (gb.getTableauColums()-1));
			 		 
				this.printWriter.print(" <= ");
				if(this.GreaterThanZero(Tableau[i][0]) || this.isZero(Tableau[i][0]))
					this.printWriter.print("  "+"+"+Tableau[i][0]);
				else
					this.printWriter.print("  "+Tableau[i][0]);
	
			 this.printWriter.print("\n");
	 
		 }
		 this.printWriter.print("\n");

		
		
		
	}
	
	public void printSlackAlongInitial(float Tableau[][],int basicVars[]){
		
		this.printWriter.print("\n				Initial Dictionary\n");
		
		
		 for(int i=1;i<=gb.getMmax();i++)
		 {
			 
			 this.printWriter.print("X"+basicVars[i]+"  =");
//			 if(this.GreaterThanZero(Tableau[i][i+gb.getNmax()]) || this.isZero(Tableau[i][i+gb.getNmax()]))
//			 	{
//			 		this.printWriter.print("  "+"+"+Tableau[i][i+gb.getNmax()]);
//					this.printWriter.print(" X");
//					this.printWriter.print(i+gb.getNmax());
//					this.printWriter.print(" = ");
//					
//
//			 	}
//			 	else
//			 	{
//			 		this.printWriter.print("  "+Tableau[i][i+gb.getNmax()]);
//			 		this.printWriter.print(" X");
//					this.printWriter.print(i+gb.getNmax());
//					this.printWriter.print(" = ");
//					
//			 	}
			 if(this.GreaterThanZero(Tableau[i][0]) || this.isZero(Tableau[i][0]))
					this.printWriter.print("  "+"+"+Tableau[i][0]);
				else
					this.printWriter.print("  "+Tableau[i][0]);
				

			 for(int j=1;j<=gb.getNmax();j++)
				{
				 		
				 
				 	if(this.GreaterThanZero(Tableau[i][j]) || this.isZero(Tableau[i][j]))
				 	{
				 		this.printWriter.print("  "+"+"+Tableau[i][j]);
				 	}
				 	else
				 		this.printWriter.print("  "+Tableau[i][j]);
					
					this.printWriter.print(" X");
					this.printWriter.print(j);
					
					
				}
			 	if(this.isZero(Tableau[i][gb.getTableauColums()-1]) || this.GreaterThanZero(Tableau[i][gb.getTableauColums()-1]))
			 		this.printWriter.print("  +"+Tableau[i][gb.getTableauColums()-1]+ " X" + (gb.getTableauColums()-1));
			 	else
			 		this.printWriter.print("  "+Tableau[i][gb.getTableauColums()-1]+ " X" + (gb.getTableauColums()-1));
				
				
				 this.printWriter.print("\n");

			
		 }
		 this.printWriter.print("\n------------------------------------------------------------------------------\n");
			this.printWriter.print("  "+Tableau[0][gb.getTableauColums()-1] + " X" + (gb.getTableauColums()-1));
			
		 this.printWriter.print("\n");
	
	}
	
	public void printEnteringVar(int enterVar){
		
		this.printWriter.print("\n Entering Variable = " + enterVar+"\n");
	}
	
	public void printLeavingVar(int pivotRow){
	
		
		this.printWriter.print("\n Leaving Variable = " + pivotRow+"\n");
	}

	public void printTableau(float Tableau[][]){
	
		for(int i=0;i<gb.getTableauRows();i++)
		{
			for(int j=0;j<gb.getTableauColums();j++)
			{
//				System.out.print(Tableau[i][j]);
			}
//			System.out.print("\n");
		}
		
	}
	public void printDictStep(int[] basicVars, float[][] Tableau ) {
		
		 for(int i=1;i<=gb.getMmax();i++)
		 {
			 
			 this.printWriter.print("X"+basicVars[i]+"  =");
		 
			 if(this.GreaterThanZero(Tableau[i][0]) || this.isZero(Tableau[i][0]))
			 {
				 if(Tableau[i][0] == -0.0)
			 		{
			 			Tableau[i][0] = 0;
			 		}
				 
				 this.printWriter.print("  "+"+"+Tableau[i][0]);
			 }
				else
					this.printWriter.print("  "+Tableau[i][0]);
				

			 for(int j=1;j<gb.getTableauColums();j++)
			 {
//				 System.out.print("basic " + basicVars[i]);
//				 System.out.print("tab"+ Tableau[i][j]);
//				 System.out.print("j="+ j);
				if(j!= basicVars[i])
				{
				 	if((this.GreaterThanZero(Tableau[i][j]) || this.isZero(Tableau[i][j])))
				 	{
				 		if(Tableau[i][j] == -0.0)
				 		{
				 			Tableau[i][j] = 0;
				 		}
				 		this.printWriter.print("  "+"+"+Tableau[i][j]);
				 	}
				 	else
				 	{
				 	
				 		this.printWriter.print("  "+Tableau[i][j]);
						
				 	}


					this.printWriter.print(" X");
					this.printWriter.print(j);
				}
			 		
				
				
			 }
		 
			
			 this.printWriter.print("\n");

		 }
		 
		 this.printWriter.print("----------------------------------------------------------------------\n");

		 
		 for(int i=0;i<gb.getTableauColums();i++)
		 {
			 if(i==0)
			 {
				 if(this.isZero(Tableau[0][i]) || this.GreaterThanZero(Tableau[0][i])) 
				 {
					 this.printWriter.print("+"+" "+Tableau[0][i]);
				 }
				 else
				 {
					 this.printWriter.print(" "+Tableau[0][i]);
				 }
			 }
			 else
			 {
				 if(this.isZero(Tableau[0][i]) || this.GreaterThanZero(Tableau[0][i])) 
				 {
				
					 this.printWriter.print("+"+" "+Tableau[0][i]+" X"+i);
			 
				 }
				 else
				 {
					 this.printWriter.print(" "+Tableau[0][i]+" X"+i);
					 
				 }
			}
		 }
		
	}
	
	public void printIntNum(int num){
		
		this.printWriter.print("\n" + num + "\n");
	}
	
	public void printFLoatNum(float num){
		
		this.printWriter.print("\n"+num+"\n");
	}

	public void originalZValueMsg()
	{
		this.printWriter.print("\n \n Calculating Z from original problem \n \n");
		
			
	}
	public void printStr(String str)
	{
		this.printWriter.print(str);
	}
}
