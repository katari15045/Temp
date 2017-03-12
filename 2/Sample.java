import java.util.Scanner;
import java.util.LinkedHashSet;
import java.util.ArrayList;

public class Sample
{
	private static Scanner scanner;
	private static LinkedHashSet<String> stringSet;
	private static ArrayList<String> arrayList;
	private static int length;

	private static MergeSort mergeSort;

	public static void main( String[] args )
	{
		takeUserInput();
		arrayList = new ArrayList<String>(stringSet);
		mergeSort = new MergeSort(arrayList);
		mergeSort.startMergeSort();
		arrayList = mergeSort.getSortedArray();
		System.out.println(arrayList);
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