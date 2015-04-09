/* Harrison Linowes
 * Period 1
 * A simulation of the hulk moving through a city.  It returns the most enemies that can 
 * be destroyed in a single path while only moving down and to the right.
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Stack;


public class Linowes_Hulk {

	private int[][] city;//Matrix holding number of enemies in city.

	public static void main (String[] args){

		Linowes_Hulk Hulk=new Linowes_Hulk("CP74.txt",12);

		int enemiesDestroyed=Hulk.getTotal(0,0);

		System.out.print(enemiesDestroyed);
	}

	public Linowes_Hulk(String FileName, int size){
		Scanner inFile=null;

		//import file
		try{
			inFile=new Scanner(new File(FileName));
		}catch(FileNotFoundException e){

			System.out.print("FILE NOT FOUND!");
			System.exit(-1);
		}

		//adds the data from file to the matrix
		city=new int[size][size];

		for(int row=0;row<size;row++){

			for(int col=0; col<size;col++){

				city[row][col]=inFile.nextInt();
			}
		}
	}

	//finds the best pathway the hulk can destroy the most baddies
	private int getTotal(int curRow, int curCol){

		int position=city[curRow][curCol];	//holds value in current position
		boolean ROK=rightOK(curRow,curCol);	
		boolean DOK=downOK(curRow,curCol);

		//if both pathways are open
		if(ROK&&DOK){

			int across=position+getTotal(curRow,curCol+1);
			int down=position+getTotal(curRow+1,curCol);

			//returns the best pathway
			if(across>down){

				return across;
			}
			else
				return down;
		}
		//if only right is open
		else if(ROK){

			return position+(getTotal(curRow,curCol+=1));
		}
		//if down is open
		else if(DOK){

			return position+getTotal(curRow+=1,curCol);
		}
		//if the hulk is at the end
		else{

			return position;
		}
	}

	//helper method returns true if hulk can move right
	private boolean rightOK(int row,int col){

		if((col+1)>=city.length||city[row][col+1]==-1)
			return false;

		return true;
	}

	//helper method returns true if hulk can move down
	private boolean downOK(int row, int col){

		if((row+1)>=city.length||city[row+1][col]==-1)
			return false;

		return true;
	}
}