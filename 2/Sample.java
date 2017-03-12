import java.util.Scanner;
import java.util.ArrayList;

public class Sample
{
	private static Scanner scanner;
	private static ArrayList<String> stringArray;
	private static int length;

	public static void main( String[] args )
	{
		takeUserInput();
		System.out.println( stringArray );
	}

	private static void takeUserInput()
	{
		int count = 0;
		String inpString;

		scanner = new Scanner(System.in);
		System.out.print("Length : ");
		length = scanner.nextInt();
		inpString = scanner.nextLine();	// Take new line as input
		stringArray = new ArrayList<String>(length);
		System.out.println("Enter array elements one in each line.");

		while( count < length )
		{
			inpString = scanner.nextLine();
			stringArray.add( count, inpString );

			count = count + 1;
		}
	}
}