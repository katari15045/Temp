import java.util.Scanner;

public class Sample
{
	private static Scanner scanner;
	private static double number;

	public static void main(String[] args)
	{
		takeUserInput();
	}

	private static void takeUserInput()
	{

		scanner = new Scanner(System.in);
		System.out.print("Number : ");
		number = scanner.nextDouble();

		System.out.println("Your number : " + number );
	}
}