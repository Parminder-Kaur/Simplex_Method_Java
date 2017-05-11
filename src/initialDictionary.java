
public class initialDictionary {

	globals gb = new globals();
	float initialArray[][];
	float objectiveFunc[][];
	initialDictionary(int nmax, int mmax){
		
		gb.setNmax(nmax);
		gb.setMmax(mmax);
		initialArray =new float[gb.getNmax()][gb.getMmax()];//declaration and instantiation  		
		objectiveFunc = new float[1][gb.getNmax()];

	}
	
	public float[][] initialDict(float value[][]){  
	 	
		//printing array  
		
		initialArray = value;
		
		return initialArray;
				
    }
	
	public float[][] initializeObjectiveFunction(float value[][]){
			
		objectiveFunc = value;
		
		return objectiveFunc;
	}
	
	public void prinitInitialDict() {
//	System.out.print(gb.getMmax());
//	System.out.print(gb.getNmax());
//		System.out.print("initial Dictionary");
		for(int i =1; i<=gb.getMmax();i++ )
		{
			for(int j =0; j< gb.getNmax()+1; j++)
			{
//				System.out.print(" "+initialArray[i][j]);
				
			}
//			System.out.print("\n");
		}
		
	}
	public void prinitObjectFunc() {
//		System.out.print("objective func");
		
		for(int j=0;j<gb.getNmax()+1;j++)
		{
//			System.out.print(" "+objectiveFunc[0][j]);
		}
//		System.out.print("\n");
		
	}
	
	public boolean dictionaryFeasiblity(float dictionary[][])
	{
		boolean feasibility = true;
		for(int i=0;i<gb.getMmax();i++)
		{
			if(dictionary[i][0]<0)
			{
				feasibility = false;
				break;
			}
			
		}
		return feasibility;
	}
	
	public int[] initialiseBasicVars(){
		
		int basicVars[] = new int[gb.getMmax()+1];
		
		for(int i=1;i<=gb.getMmax();i++)
		{
			basicVars[i] = i+gb.getNmax();
		}
		
		return basicVars;
	}
}

