import java.util.Scanner;

public class Sample
{
	private static Scanner scanner;
	private static double number;

	public static void main(String[] args)
	{
		takeUserInput();
		roundTheNumber();
		System.out.println("Rounded num : " + number);
	}

	private static void takeUserInput()
	{

		scanner = new Scanner(System.in);
		System.out.print("Number : ");
		number = scanner.nextDouble();

		System.out.println("Your number : " + number );
	}

	private static void roundTheNumber()
	{
		double toAdd;

		if( number == 0.0 )
		{
			return;
		}

		if( number < 0 )
		{
			dealNegNum();
			return;
		}

		if( number < 100.0 )
		{
			number = 100.0;
			return;
		}

		if( number % 100.0 == 0.0 )
		{
			return;
		}

		toAdd = 100 - ( number % 100.0 );
		number = number + toAdd;
		return;
	}

	private static void dealNegNum()
	{
		double toSubtract;
		number = -1 * number;

		if( number < 100.0 )
		{
			number = 0;
			return;
		}

		if( number % 100.0 == 0.0 )
		{
			number = -1 * number;
			return;
		}

		toSubtract = number % 100;
		number = number - toSubtract;
		number = -1 * number;

		return;
	}
}