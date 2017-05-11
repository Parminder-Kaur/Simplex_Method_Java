import java.io.PrintWriter;


public class tableauOperations {
	
	globals gb = new globals();
	boolean reachedZ = false;
	float pivotNumber =0;
	public static final double EPSILON(){
		return 0.00000010;
	}
	public tableauOperations(int columns, int rows, int nmax,int mmax) {
		// TODO Auto-generated constructor stub
		gb.setTableauColums(columns);
		gb.setTableauRows(rows);
		gb.setNmax(nmax);
		gb.setMmax(mmax);
		
	}
	
	public boolean checkOptimalSolutionReached(float tableau[][]){
		
//	System.out.print("Tableau rows = "+ tableau.length);
		
	boolean decision =false;
	int count =0;
			
		for(int j=1; j< gb.getTableauColums();j++)
		{
			if(tableau[0][j] > 0 || tableau[0][j] == 0)
			{
				
				if(tableau[0][j] == 0)
				{
					count++;
				}
				else if(count > gb.getTableauColums()-1 || tableau[0][j] >0)
				{
					decision =true;
				}			
			}	
		}
	
		return decision;
	}
	
	public int choosePivotColumn(float tableau[][]){
		
		int pivotColumn =-2;
		
		for(int j=1;j<gb.getTableauColums();j++)
		{
			//considering smallest script case
			if(tableau[0][j] > 0)
			{
				pivotColumn = j;
//				System.out.print("Got pivot column = "+pivotColumn);
				break;
			}
		}
//		System.out.print("no pivot column selected from tableau");
		return pivotColumn;
	}	
	
	public int pivotAuxVar()
	{
		return gb.getTableauColums()-1;
	}
	
	public int choosePivotRow(float tableau[][], int pivotColumn, int basicVars[]){
		float min=1000;//Math.abs(tableau[1][0]/tableau[1][pivotColumn]);
		int pivotRow=1;
		for(int i=1;i<=gb.getMmax();i++)
		{	
//				System.out.print("Processing row = "+i+"\n");
//				System.out.print("postive pivot column value ="+tableau[i][pivotColumn]);
//				System.out.print("basis = "+tableau[i][0]+ "\n");
//				System.out.print("division = "+Math.abs(tableau[i][0]/tableau[i][pivotColumn])+"\n");
//				System.out.print("min = "+min+"\n");	
//					
					if(Math.abs(tableau[i][0]/tableau[i][pivotColumn])<min && tableau[i][pivotColumn]<0)
					{
						if(tableau[i][pivotColumn]==0 && tableau[i][0] < tableauOperations.EPSILON())
						{
							
							
						}
//						if(tableau[i][0] != 0 && tableau[i][pivotColumn] !=0)
//						{
						else if(tableau[i][pivotColumn]<0)
						{
								min= Math.abs(tableau[i][0]/tableau[i][pivotColumn]);
								pivotRow= i;
						}
						
//						}
					}
			}
//		System.out.print("returning minimum value" + basicVars[pivotRow]);
		return pivotRow;
	}
	
	public int choosePivotRowForAux(float Tableau[][]){
		int pivotRow=-2;
		float smallest =Tableau[1][0];
		pivotRow =1;
		for(int i=1; i<gb.getTableauRows()-1;i++)
		{
			if(Tableau[i+1][0] <smallest )
			{
				smallest = Tableau[i+1][0];
				pivotRow =i+1;
				
			}
		}
//		System.out.print("returning pivot row" + pivotRow);
		return pivotRow;
		
	}
	public float[][] solveProblem(float Tableau[][], int pivotColumn, int pivotRow){
		
		 pivotNumber = this.getPivotNumber(Tableau, pivotColumn, pivotRow);
		 Tableau=this.UpdateRowsOfTableau(Tableau,pivotRow, pivotNumber);		
		 return Tableau;
	}
	private float getPivotNumber(float Tableau[][],int pivotColumn, int pivotRow){
		System.out.print("Pivot row " + pivotRow + "Pivot Column "+ pivotColumn);
		float pivotNumber = Tableau[pivotRow][pivotColumn];
		System.out.print("Pivot number"+ pivotNumber);
		return pivotNumber;
	}
	
	private float[][] UpdateRowsOfTableau(float Tableau[][],int pivotRow, float pivotNumber){
		 for(int i=0; i<12; i++)
		 {
			 for(int j=0;j<14;j++)
			 {
				 System.out.print(Tableau[i][j]);
				 
			 }
			 System.out.print("\n");
		 }
		float TableauCopy[][]= Tableau; 
		 System.out.print("after copy");
		 for(int i=0; i<12; i++)
		 {
			 for(int j=0;j<14;j++)
			 {
				 System.out.print(TableauCopy[i][j]);
				 
			 }
			 System.out.print("\n");
		 }
		 System.out.print("changing pivot row");
		for(int j=0; j<gb.getTableauColums();j++){
				
//			if(Tableau[pivotRow][j]!=0)
//			{
				TableauCopy[pivotRow][j] = Tableau[pivotRow][j]/pivotNumber;
				 System.out.print(TableauCopy[pivotRow][j]);
//			}		
			System.out.print("\n");
		}
		System.out.print("\n");
		 System.out.print("changing other rows except pivot row");
			
		for(int i=0; i< gb.getTableauRows(); i++){
			
			for(int j=0; j<gb.getTableauColums();j++){
				if(i != (pivotRow - gb.getNmax()))
				{
					TableauCopy[i][j]=(Tableau[i][j] - (TableauCopy[pivotRow][j] * (Tableau[i][j]/TableauCopy[pivotRow][j])));
				}
				else
				{
					TableauCopy[i][j]= Tableau[i][j];
				}
			}
//			System.out.print("\n");
		}
		
		 for(int i=0; i<12; i++)
		 {
			 for(int j=0;j<14;j++)
			 {
				 System.out.print(TableauCopy[i][j]);
				 
			 }
				System.out.print("\n");
		 }
		
				
		
		return TableauCopy;
	}
	
	
	public int[] updateBasicVars(int basicVars[], int leaveVar, int enterVar){
		
		for(int i=1;i<=gb.getMmax();i++)
		{
			if(basicVars[i]== leaveVar)
			{
				basicVars[i] = enterVar;
//				System.out.print("Updated basic var " + basicVars[i] +" with "+ enterVar);
			}
			
		}
				
		return basicVars;
		
	}
	public float[][] updatePivotRow(float[][] Tableau, int pivotRow,float pivotNumber2)
	{
		
		for(int j=0;j<gb.getTableauColums();j++){
			
			Tableau[pivotRow][j] = Tableau[pivotRow][j]/pivotNumber2;
			
		}
				
		return Tableau;
	}
	public float[][] updateOtherRowsForAux(float[][] Tableau, int pivotRow,int pivotColumn,int leaveVar)
	{
		for(int j=0;j<gb.getTableauColums();j++)
		{
			Tableau[gb.getTableauRows()-1][j] = Tableau[0][j]; 
			Tableau[0][j] = -Tableau[pivotRow][j];
			Tableau[0][gb.getTableauColums()-1]=0;
			
//			if(j== leaveVar)
//			{
//				Tableau[0][j]= -Tableau[0][j];
//			}
		}
		
		for(int i=1;i<gb.getTableauRows();i++)
		{
			
			if(i !=pivotRow)
			{
				float multiple = Tableau[i][pivotColumn]/Tableau[pivotRow][pivotColumn];
				for(int j=0;j<gb.getTableauColums();j++){
					
						Tableau[i][j] = Tableau[i][j] - (multiple* Tableau[pivotRow][j]); 
					
				}

			}
		}
		
		
		
		
		return Tableau;
		
	}
	
	public float[][] updateOtherRows(float[][] Tableau, int pivotRow,int pivotColumn)
	{
		for(int i=0;i<gb.getTableauRows();i++)
		{
			if(i !=pivotRow)
			{
				float multiple = Tableau[i][pivotColumn]/Tableau[pivotRow][pivotColumn];
				for(int j=0;j<gb.getTableauColums();j++){
					
						Tableau[i][j] = Tableau[i][j] - (multiple* Tableau[pivotRow][j]); 
															
				}

			}
		}
		
		
		return Tableau;
		
	}
	
	public float[][] updatePivotRowForAux(float[][] Tableau, int pivotRow,float pivotNumber2)
	{
		for(int j=0;j<gb.getTableauColums();j++)
			Tableau[pivotRow][j] = -(Tableau[pivotRow][j]);
		
//		Tableau[0][gb.getTableauColums()]=0;
		
		return Tableau;
	}
	
	public float[][] keepPivoting(float Tableau[][],printDictionary printC,int basicVars[], int constantToBeFound){

		int pivotCol = this.choosePivotColumn(Tableau);
		printC.printEnteringVar(pivotCol);
		int pivotRow = this.choosePivotRow(Tableau, pivotCol,basicVars);
		printC.printLeavingVar(basicVars[pivotRow]);
		
		basicVars=this.updateBasicVars(basicVars, basicVars[pivotRow], pivotCol);
		
		float pivotNumber = Math.abs(Tableau[pivotRow][pivotCol]);
//		PrintWriter("pivotRow");
//		printC.printIntNum(pivotRow);
//		printC.printIntNum(pivotCol);
//		printC.printFLoatNum(pivotNumber);
		
		
//		System.out.print("pivot number = "+pivotNumber);
//		System.out.print("before updating pivot row \n");
//		printC.printTableau(Tableau);
		Tableau = this.updatePivotRow(Tableau, pivotRow, pivotNumber);
//		System.out.print("After updating pivot row \n");
//		printC.printTableau(Tableau);
		Tableau = this.updateOtherRows(Tableau, pivotRow, pivotCol);
//		System.out.print("After updating other row \n");
//		printC.printTableau(Tableau);
	
		printC.printDictStep(basicVars,Tableau);
		
		if(Tableau[0][0] == 0 && this.reachedZ == false)
		{
			this.reachedZ = true;
			printC.originalZValueMsg();
			Tableau=this.reCalculateZ(Tableau,basicVars, printC);	
			printC.printDictStep(basicVars,Tableau);

			while(this.checkOptimalSolutionReached(Tableau) == true)			
			{
				this.keepPivoting(Tableau, printC, basicVars, constantToBeFound);
			}
			for(int r=1;r<gb.getMmax();r++)
			{
				if(basicVars[r] == constantToBeFound)
				{
					printC.printStr("\nConstant found = ");
				
					printC.printFLoatNum(Tableau[r][0]);
					break;
				}
			}
			printC.printStr("\nZ  = ");
			if(Tableau[0][0] == 0.0)
			{
				printC.printFLoatNum(Tableau[0][0]);
				
			}else
				printC.printFLoatNum(-(Tableau[0][0]));
			

			return Tableau;
		}
		
		return Tableau;
		
	}
	public float[][] reCalculateZ(float[][] Tableau, int basicVars[], printDictionary printC)
	{
//		printC.printStr("z = ");
//		printC.printFLoatNum(Tableau[gb.getTableauRows()-1][0]);
		
		for(int j=0;j<gb.getTableauColums();j++)
		{
			Tableau[0][j] = Tableau[gb.getTableauRows()-1][j];
		}
		gb.setTableauColums(gb.getTableauColums()-1);
		return Tableau;
		
	}
	
	
	
	public float[][] calculateFinalZ(float[][] Tableau, float[][] objectiveFunc, int basicVars[],printDictionary printC )
	{
		for(int j=0;j<gb.getNmax();j++)
		{
			if(objectiveFunc[0][j] != 0)
			{
//				printC.printStr("\nCatched non -ve obj func val\n");	
//				printC.printFLoatNum(objectiveFunc[0][j]);
//				
				int rowToBeMul = j;
//				printC.printStr("\nsaved index where it is catched\n");	
//				printC.printIntNum(rowToBeMul);
//				printC.printStr("\nlooping to find equivalent value of index to basis value \n");	
//				
				for(int k=0;k<gb.getMmax();k++)
				{
				
					if(basicVars[k] == rowToBeMul)
					{
//						printC.printStr("\nFound one \n");	
//							
//						printC.printIntNum(basicVars[k]);
//						printC.printStr("multiplying row");
//						printC.printIntNum(k);

						for(int s =0;s<gb.getTableauColums();s++)
						{
//							printC.printStr("mul number");
//
//							printC.printFLoatNum(Tableau[k][s]);
//
//							printC.printStr("multiply with");
//							printC.printFLoatNum(objectiveFunc[0][j]);

							Tableau[k][s] = objectiveFunc[0][j] *Tableau[k][s];

							printC.printStr("After multiply");
							printC.printFLoatNum(Tableau[k][s]);

						}
											}
				}
			}
//			printC.printIntNum(basicVars[j]);
//			
//
//			if(objectiveFunc[0][j] != 0 && basicVars[j] == j)
//			{
//				printC.printStr("\nMatched = ");
//				printC.printFLoatNum(basicVars[j]);
//			
//			
//			
//			}
	
			float z[] = new float[gb.getMmax()];
			z[0]=0;
		if(objectiveFunc[0][j] !=0)
		{
			for(int k=0;k<gb.getMmax();k++)
			{
//				Tableau[j][k]
				z[k] = z[k] + Tableau[k][j]; 
				
			}

		}

		printC.printFLoatNum(z[j]);			
		}
		
		
		
		
		
		
		
		return Tableau;
	}
	
}
