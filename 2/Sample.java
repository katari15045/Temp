import java.util.Scanner;
import java.util.LinkedHashSet;

public class Sample
{
	private static Scanner scanner;
	private static LinkedHashSet<String> stringSet;
	private static int length;

	public static void main( String[] args )
	{
		takeUserInput();
		System.out.println(stringSet);

	}

	private static void takeUserInput()
	{
		int count = 0;
		String inpString;

		scanner = new Scanner(System.in);
		System.out.print("Length : ");
		length = scanner.nextInt();
		inpString = scanner.nextLine();	// Take new line as input
		stringSet = new LinkedHashSet<String>(length);
		System.out.println("Enter array elements one in each line.");

		while( count < length )
		{
			inpString = scanner.nextLine();
			stringSet.add(inpString);

			count = count + 1;
		}
	}
}