
public class globals {

	private int NMAX = 500;
	private int MMAX = 500;
	private int TABROWS = 500;
	private int TABCOLS = 500;
	
	public int getNmax(){
		return this.NMAX;
	}
	
	public int getMmax(){
		return this.MMAX;
	}
	public void setNmax(int value){
	 this.NMAX =value;
	}
	
	public void setMmax(int value){
	 this.MMAX = value;
	}
	
	public void setTableauRows(int value){
		 this.TABROWS =value;
	}
		
	public void setTableauColums(int value){
		 this.TABCOLS = value;
	}
	
	public int getTableauRows(){
		return this.TABROWS;
	}
	
	public int getTableauColums(){
		return this.TABCOLS;
	}
	
}
