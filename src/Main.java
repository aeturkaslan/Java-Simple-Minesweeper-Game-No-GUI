import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		System.out.println("Welcome to Minesweeper Game!");
		System.out.println("Enter Number of Rows and Columns  :");
		Scanner scan = new Scanner(System.in);
		int row, column;
		System.out.println("Number of Rows: " );
		row = scan.nextInt(); 
		System.out.println("Number of Columns: " );
		column = scan.nextInt(); 
		
		Minesweeper mine = new Minesweeper(row, column);
		mine.run();


	}

}
