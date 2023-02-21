import java.util.Random;
import java.util.Scanner;

public class Minesweeper {
	int rowNumber, colNumber, size;
	int [][] map;
	int [][] board;
	boolean game = true;
	
	Random rand = new Random();
	Scanner scan = new Scanner(System.in);
	
	Minesweeper(int rowNumber, int colNumber){
		this.rowNumber = rowNumber;
		this.colNumber = colNumber;
		this.map = new int[rowNumber][colNumber];	//A map that shows locations of the randomly generated mines.
		this.board = new int[rowNumber][colNumber];	
		this.size = rowNumber*colNumber;	//Size of the board, defined by the user.
	}
	
	public void run() {
		int row, col, success=0;
		prepareGame();
		gameGuide();
		//print(map);		//Shows the map with the mines. Uncomment if you want it to be shown.
		System.out.println("Game Started!");
		while(game) {
			print(board);
			System.out.print("Select Row: "); 
			row = (scan.nextInt())-1;		//-1 provides a user-friendliness. User selects row:1 col:1 and this interpretes it as row:0 col:0
			System.out.print("Select Column: ");
			col = (scan.nextInt())-1;		//these 4 lines gets the coordinates the user selected (clicked, if there was GUI)
			
			if(row < 0 || row >= rowNumber) {
				System.out.println("Invalid Coordinates");
				continue;			
			}
			if(col < 0 || col >= colNumber) {
				System.out.println("Invalid Coordinates");
				continue;			
			}
			
			if (map[row][col] != -1) {
				check(row,col);
				success++ ;
				if(success == (size - (size/4))) {
					System.out.println("You Won!");
					break;
				}
			}
			else {
				game = false;
				System.out.println("You Found a Mine!");
				System.out.println("Game Over!");
			}
		}
	}
	
	public void check (int r, int c) {
		if((c < colNumber - 1) && (map[r][c+1] == -1)) {	//if the right adjacent of the selected location has mine,
			board[r][c]++ ;		//increase the user's selected location by 1 (it was initially 0)
		}
		if((c > 0) && (map[r][c-1] == -1)) {	//if the left adjacent of the selected location has mine,
			board[r][c]++ ;		//increase the user's selected location by 1 (it was initially 0)
		}
		if((r < rowNumber -1) && (map[r+1][c] == -1)) {	//if the below adjacent of the selected location has mine,
			board[r][c]++ ;		//increase the user's selected location by 1 (it was initially 0)
		}
		if((r > 0) && (map[r-1][c] == -1)) {	//if the above of the selected location has mine,
			board[r][c]++ ;		//increase the user's selected location by 1 (it was initially 0)
		}
		
		if (board[r][c] == 0) {	//if the selected location is still 0, which indicates no adjacent of it has mine, 
			board[r][c] = -2;	//change the selected location's value from 0 to -2.
		}
		
		
		
		//These check the diagonal adjacents of the selected coordinates by the user. Uncomment if you want to include.
		/*
		if(map[r-1][c+1] == -1) {	//if the upper right cross of the selected location has mine,
			map[r][c]++ ;		//increase the user's selected location by 1 (it was initially 0)
		}
		if(map[r+1][c+1] == -1) {	//if the lower right cross of the selected location has mine,
			map[r][c]++ ;		//increase the user's selected location by 1 (it was initially 0)
		}
		if(map[r+1][c-1] == -1) {	//if the lower left cross of the selected location has mine,
			map[r][c]++ ;		//increase the user's selected location by 1 (it was initially 0)
		}
		if(map[r-1][c-1] == -1) {	//if the upper left cross of the selected location has mine,
			map[r][c]++ ;		//increase the user's selected location by 1 (it was initially 0)
		}
		*/
	}
	
	public void prepareGame() {
		int randRow, randCol; //Coordinates of randomly created mines.
		int count = 0;
		while(count != (size/4)) {	// (size/4) indicates that the number mines will be 1/4 of the board size.
			randRow = rand.nextInt(rowNumber);
			randCol = rand.nextInt(colNumber);
			if (map [randRow][randCol] != -1) { 
				map [randRow][randCol] = -1;	//Put a mine to randomly created coordinate, if there is not a -1 (mine) there already.
				count++;
			}
		}
	}
	
	public void print (int[][] arr) {
		for (int i =0 ; i < arr.length ; i++) {
			for (int j=0 ; j < arr[0].length; j++) {
				if (arr[i][j] >=0 )
					System.out.print(" ");
				System.out.print(arr[i][j]+ " ");
			}
			System.out.println();
		}
	}
	
	
	public void gameGuide() {
		System.out.println("===Game Guide and Instructions===");
		System.out.println("*A board with randomly generated mines is now ready.");
		System.out.println("*Number of mines are 1/4 of the board size.");
		System.out.println("*If the selected coordinates(row,column) of the user has a mine, the game ends.");
		System.out.println("*If the selected coordinates(row,column) of the user has no mine but the adjacent does,");
		System.out.println("	-The selected block changes from 0 to number of mines that its adjacents of top, bottom, left and right. (No diagonal adjacent's mines are counted!)");
		System.out.println("*If the selected coordinates(row,column) of the user has no mine and the adjacents don't have any mines either,");
		System.out.println("	-The selected block changes from 0 to -2");
		System.out.println("*This code is designed as non-coder user-friendly. Therefore there is no row 0 or column 0. Code interpretes it in that way though.");
		System.out.println("*If you want the map that shows the mines on the board, uncomment line:25 in Minesweeper.java");
		System.out.println(" ");
	}

}
