
public class createTableau {

	globals gb = new globals();
	boolean aux = false;
	
	public createTableau(int columns, int rows, int nmax,int mmax, boolean aux) {
		// TODO Auto-generated constructor stub
		this.aux =aux;
		gb.setTableauColums(columns);
		gb.setTableauRows(rows);
		gb.setNmax(nmax);
		gb.setMmax(mmax);
		
	}
	
	public float[][] initialTableau(float objectiveFunc[][], float initialDictionary[][]){
		
//		int rows = gb.getTableauRows();
//		int columns = gb.getTableauColums();
		
		int matrixColum =(gb.getTableauColums()-gb.getNmax()-1);
		float identityMatrix[][] = new float[gb.getTableauRows()][matrixColum];
//		System.out.print("R "+gb.getTableauRows());
//		System.out.print("C "+gb.getTableauColums());
//		System.out.print("\n");
//		System.out.print("Identity Matrix");
		for(int i=0;i<gb.getTableauRows();i++)
		{
			for(int j=0; j< matrixColum; j++)
			{
				if(i==0)
				{
					identityMatrix[i][j] =0;
				}
				else if( i>0 && i-j ==1)
				{
					identityMatrix[i][j] = 1;
					
				}
				else
				{
					identityMatrix[i][j] = 0;
				}
				
			}
		}
		
		for(int i=0;i<gb.getTableauRows();i++)
		{
			for(int j=0; j< matrixColum; j++)
			{
//				System.out.print(" "+identityMatrix[i][j]);
			}
//			System.out.print("\n");
		}
//		System.out.print("\n");
		
		float initTableau[][] = new float[gb.getTableauRows()][gb.getTableauColums()];
		int firsthalfColOfTab = gb.getTableauColums()-gb.getMmax();
	//	System.out.print("i="+firsthalfColOfTab);
		for(int i=0;i<gb.getTableauRows();i++)
		{
			//System.out.print("i="+i);
			for(int j=0;j<firsthalfColOfTab;j++)
			{
			//	System.out.print("j="+j);
				//System.out.print("OB"+objectiveFunc[0][j]);
				if(i==0)
				{
				//	System.out.print(objectiveFunc[i][j]);
					initTableau[i][j] = objectiveFunc[i][j]; 
				}
				else
				{
					//System.out.print(initialDictionary[i][j]);
					initTableau[i][j] = initialDictionary[i][j];
				}
				
			}
			
		}
//		System.out.print("initTableau Matrix FIRST HALF");
//		for(int i=0;i<gb.getTableauRows();i++)
//		{
//			for(int j=0;j<firsthalfColOfTab;j++)
//			{
//				System.out.print(" "+initTableau[i][j]);
//				
//			}
//			System.out.print("\n");	
//		}		
//		System.out.print("++++++++");
//		System.out.print("\n");	
		for(int i=0;i<gb.getTableauRows();i++)
		{
			for(int j=gb.getNmax()+1;j<gb.getTableauColums();j++)
			{
				initTableau[i][j]= identityMatrix[i][j-gb.getNmax()-1];
				
			}
		}
	
		if(this.aux == true)
		{
			for(int i=0 ;i < gb.getTableauRows();i++)
			{
				initTableau[i][gb.getTableauColums()-1]=-1;
			}
		}
//		System.out.print("-----------------");
//		System.out.print("initTableau Matrix SECOND HALF");
				
				for(int i=0;i<gb.getTableauRows();i++)
				{
					for(int j=gb.getNmax()+1;j<gb.getTableauColums();j++)
					{
//						System.out.print(" "+initTableau[i][j]);
						
					}
//					System.out.print("\n");	
				}		
//				System.out.print("-----------------");
//				System.out.print("\n");	
//				System.out.print("initTableau Matrix FULL");
//				System.out.print("-----------------");
//				
				for(int i=0;i<gb.getTableauRows();i++)
				{
					for(int j=0;j<gb.getTableauColums();j++)
					{
//						System.out.print(" "+initTableau[i][j]);
						
					}
//					System.out.print("\n");	
				}		
//				System.out.print("-----------------");
			
		
		return initTableau;
	}
	public float[][] initialFeasibleTableau(float Tableau[][]){
		
		float[][] dummyTab = Tableau;
		for(int i=1;i<=gb.getMmax();i++)
		{
			for(int j=1;j<gb.getTableauColums();j++)
			{
				if(Tableau[i][j]>0 || Tableau[i][j]<0)
				{
					dummyTab[i][j] = -(Tableau[i][j]);
				}
				
			}
//			System.out.print("$$" + Tableau[i][gb.getTableauColums()-1]);
//			if(Tableau[i][gb.getTableauColums()-1]<0 || Tableau[i][gb.getTableauColums()-1]>0 )
//			{
//				dummyTab[i][gb.getTableauColums()-1]= -(Tableau[i][gb.getTableauColums()-1]);
//					
//			}
//			System.out.print("**" + Tableau[i][gb.getTableauColums()-1]);
			
		}
			
		
		return dummyTab;
	}
	
	public float[] saveOriginalObj(float Tableau[][]){

		float[] originalObj = new float[gb.getNmax()] ;
		
		
		
		return originalObj;
	}
	
	
}
