import java.util.Scanner;


public class Sample
{
	private static Scanner scanner;
	private static String originalString;
	private static String wordToRemove;

	public static void main(String[] args)
	{
		takeUserInput();
		System.out.println( originalString + " -> " + wordToRemove );
	}

	private static void takeUserInput()
	{
		scanner  = new Scanner(System.in);
		System.out.print("Original String : ");
		originalString = scanner.nextLine();
		System.out.print("word to remove : ");
		wordToRemove = scanner.nextLine();

	}
}