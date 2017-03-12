import java.util.Scanner;
import java.lang.StringBuilder;


public class Sample
{
	private static Scanner scanner;
	private static String originalString;
	private static String wordToRemove;

	public static void main(String[] args)
	{
		takeUserInput();
		removeWord( wordToRemove, originalString );
	}

	private static void takeUserInput()
	{
		scanner  = new Scanner(System.in);
		System.out.print("Original String : ");
		originalString = scanner.nextLine();
		System.out.print("word to remove : ");
		wordToRemove = scanner.nextLine();
	}

	private static String removeWord( String wordToRemoveLocal, String originalStringLocal )
	{
		// Although the method parametres are not needed(Class variables can be accessed from here), I just want to full fill the problem requirements.

		int startIndex, endIndex;
		StringBuilder stringBuilder = new StringBuilder(originalString);

		startIndex = originalStringLocal.indexOf(wordToRemove);

		if( startIndex == -1 )
		{
			System.out.println("No such word to remove");
			return originalStringLocal;
		}

		endIndex = startIndex + wordToRemove.length() - 1;
		stringBuilder.delete( startIndex, endIndex+1 );

		if( endIndex+1 != stringBuilder.length() )
		{
			stringBuilder.delete(startIndex, startIndex + 1);
		}

		System.out.println(stringBuilder);
		return originalString;
	}
}