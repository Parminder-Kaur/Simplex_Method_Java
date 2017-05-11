
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class takeInputFromFile {

	public static void main(String [] args) {

        // The name of the file to open.
        String fileName = args[0];//"C:\\Users\\balwinder\\Desktop\\Testingprograms\\TestEnviro\\lp_l1_pol_zig9.txt";
        globals gb = new globals();
        float initialArray[][]=new float[gb.getNmax()][gb.getMmax()];//declaration and instantiation  		
    	float objectiveFunc[][] = new float[1][gb.getNmax()];
    	int problem =0;
//    	float Tableau[][] = new float[500][500];
        try {
        	Scanner scanner = new Scanner(new File(fileName));
        	FileWriter  writer      = new FileWriter(args[1]);//"C:\\Users\\balwinder\\Desktop\\Testingprograms\\TestEnviro\\file1.txt");
    		PrintWriter printWriter = new PrintWriter(writer);	
    	            
        	while(scanner.hasNextDouble()){
        		problem++;	
        		System.out.print("\n Processing Problem \n "+ problem);
        		gb.setNmax((int)scanner.nextDouble());	
        		int constantToBeFound = gb.getNmax(); 
//        		System.out.println(gb.getNmax());
        		gb.setMmax((int)scanner.nextDouble());	
//        		System.out.println(gb.getMmax());
        		gb.setTableauRows(gb.getMmax()+1);
        		gb.setTableauColums(gb.getNmax()+1+ gb.getMmax());
//        		System.out.print("ROWS" + gb.getTableauRows() + "COLUMNS"+gb.getTableauColums());
        		initialDictionary initializeArray =new initialDictionary(gb.getNmax(), gb.getMmax());
        	    int basicVars[] = new int[gb.getMmax()+1];
        	    basicVars = initializeArray.initialiseBasicVars();
        		objectiveFunc[0][0] = 0;
       
        		for(int j=1; j<=gb.getNmax();j++){
        			
//        			initialArray = initializeArray.initializeObjectiveFunction(scanner.nextFloat());
        			
        			objectiveFunc[0][j] = scanner.nextFloat();
//        			System.out.print(" "+ objectiveFunc[0][j]);
        				
        		}
//        		System.out.println("\n");
        		
        		for(int i =1; i<= gb.getMmax(); i++)
        		{
        			for(int j=0; j<gb.getNmax()+1; j++)
        			{
//        				objectiveFunc = initializeArray.initialDict(scanner.nextFloat());
        				initialArray[i][j]= scanner.nextFloat();
//        				System.out.print(" "+ initialArray[i][j]);
        				
        			}
        			
//        			System.out.println("\n");
        		
        		}
        		
        		initializeArray.initialDict(initialArray);
        		initializeArray.initializeObjectiveFunction(objectiveFunc);
        		
        		
        		boolean feasibility = initializeArray.dictionaryFeasiblity(initialArray);
//        		initializeArray.prinitObjectFunc();
//        		initializeArray.prinitInitialDict();
        		float[][] Tableau =null;
        		createTableau cT = new createTableau(gb.getTableauColums(), gb.getTableauRows(), gb.getNmax(),gb.getMmax(),false);
    			
        		if(feasibility == false)
        		{
        			
        			gb.setTableauColums(gb.getTableauColums()+1);
        			gb.setTableauRows(gb.getTableauRows()+1);
        			cT = new createTableau(gb.getTableauColums(), gb.getTableauRows(), gb.getNmax(),gb.getMmax(),true);
        			Tableau = cT.initialTableau(objectiveFunc, initialArray);
        		
        		}
        		else
        		{
        			Tableau = cT.initialTableau(objectiveFunc, initialArray);
            		
        		}
        		
        		
//        		System.out.print("Tableau formed");
        		
//        		for(int i=0;i<gb.getTableauRows();i++)
//        		{
//        			for(int j=0;j<gb.getTableauColums();j++)
//        			{
////        				String fc = Tableau[i][j];
//            			printWriter.print(" ");
//                        		 
//        				printWriter.print(Tableau[i][j]);
//
//        				System.out.print(" "+Tableau[i][j]);
//        				
//        			}
//        			printWriter.print("\n");
//
//        			System.out.print("\n");	
//        		}	
//        		printWriter.print("\n");
        		printDictionary printC = new printDictionary(problem,gb.getNmax(),gb.getMmax(),gb.getTableauColums(), gb.getTableauRows(),printWriter);
        		
        		printC.printInitialDictionary(Tableau);
        		if(feasibility == false)
        		{
        			
        			printWriter.print(printDictionary.notFeasibleMessage()+"\n");
        			printC.printAuxilaryDictionary(Tableau);
        			Tableau = cT.initialFeasibleTableau(Tableau);
//        			System.out.print("\n initial feasible tableau \n");
//        			printC.printTableau(Tableau);
        		}
        		printC.printSlackAlongInitial(Tableau, basicVars);
        		tableauOperations tbOp = new tableauOperations(gb.getTableauColums(), gb.getTableauRows(), gb.getNmax(),gb.getMmax());
        		        		
        		boolean optimal = tbOp.checkOptimalSolutionReached(Tableau);
        		int pivotColumn = -1;
        		if(optimal == false)
        		{
        			if(feasibility == false)
        			{
        				pivotColumn = tbOp.pivotAuxVar();
            			printC.printEnteringVar(pivotColumn);
            			int pivotRow=tbOp.choosePivotRowForAux(Tableau);
            			int leaveVar = pivotRow+gb.getNmax();
            			printC.printLeavingVar(leaveVar);        			
            			basicVars=tbOp.updateBasicVars(basicVars,leaveVar, pivotColumn);
            			float pivotNumber = Tableau[pivotRow][pivotColumn];
            			Tableau=tbOp.updatePivotRowForAux(Tableau,pivotRow,pivotNumber);
            			Tableau=tbOp.updateOtherRowsForAux(Tableau,pivotRow,pivotColumn,leaveVar);
            			printC.printDictStep(basicVars,Tableau);
            			
            			//Tableau = tbOp.solveProblem(Tableau, pivotColumn, pivotRow);
//            			System.out.print("\nafter first iteration\n");
//            			printC.printTableau(Tableau);
            			//now that we have stable Tableau we can simply roll over process
            			
            			while(tbOp.checkOptimalSolutionReached(Tableau) == true)
//            			for(int i=0;i<2;i++)
            			{
            				
            				Tableau=tbOp.keepPivoting(Tableau,printC,basicVars,constantToBeFound);
            				
            			}
            			
//            			if(Tableau[0][0] == 0)
//            			{
//            				printC.originalZValueMsg();
//            				tbOp.calculateFinalZ(Tableau, objectiveFunc);	
//            				
//            			}
            			
        			
        			}
        			else
        			{
            			pivotColumn=tbOp.choosePivotColumn(Tableau);

        			}
        			
//        			printWriter.print("Pivot Column = ");
        			if(pivotColumn == -1 && feasibility == true)
        			{
        				System.out.print("No pivot column has been selected");
        					
        			}
        			
//        			printWriter.print(pivotColumn);

        			
        			
        		}
        		else
        		{
        			//TODO: print final solution
        		}
//        		printWriter.print("Tableau Rows = ");
//        		printWriter.print(Tableau.length);

                // Write in file
             	
//        		break;
        	}
        	   // Close connection
//            bw.close();
        	printWriter.close();
        }
        catch(FileNotFoundException ex) {
            System.out.println(
                "Unable to open file '" + 
                fileName + "'");                
        }
        catch(IOException ex) {
            System.out.println(
                "Error reading file '" 
                + fileName + "'");                  
            // Or we could just do this: 
            // ex.printStackTrace();
        }
    }
	
	
}
