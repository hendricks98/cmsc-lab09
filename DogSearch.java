import java.util.Iterator;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;

public class DogSearch{
		
	public static ArrayList<Dog> readDogs(){
		ArrayList<Dog> dogArr = new ArrayList<Dog>();
		
		Scanner dogIn = new Scanner(System.in);
		File dogList = new File("Dog_Names.csv");
		
		try {
			dogIn = new Scanner(dogList);
		dogIn.useDelimiter(",|\\n");
		} catch (FileNotFoundException e) {
			System.out.println("No Dog_Names.csv file found!");
		}

		// get the first descriptor entries out (dogName , count)
		dogIn.nextLine();
		
		while (dogIn.hasNext()){
			String name = dogIn.next();
			int count = Integer.parseInt(dogIn.next());
			
			dogArr.add(new  Dog(name, count) );		
		}
		return dogArr;
	}
	
	public static void option1(ArrayList<Dog> dogArr){
		
		System.out.println("Enter a dog's name?");
		Scanner keyIn = new Scanner(System.in);
		String query = keyIn.nextLine();
		query = query.toUpperCase();
		
		boolean found = false;
		for (int i = 0; i < dogArr.size(); i++){
			if(dogArr.get(i).getName().equals(query)){
				System.out.println(dogArr.get(i).getName() + " is registered " + dogArr.get(i).getCount() + " times.");
				found = true;
			}
		}
		
		if (found != true){
			System.out.println("No dog named " + query + " found.");
		}
	}
	
	public static void option2(ArrayList<Dog> dogArr){
		
		Collections.sort(dogArr);
		
		for (int i = 0; i < dogArr.size(); i++){
			System.out.println(dogArr.get(i).getName());
		}
	}
	
	
	public static void option3(ArrayList<Dog> dogArr){
		int size = dogArr.size() - 1;
		int correct = 0;
		int total = 0;
		
		boolean playAgain = true;
		while (playAgain !=false){
			
			int dog1 = 0;
			int dog2 = 0;
			
			// keep generating random numbers till they are at least 5 apart
			while (Math.abs((dogArr.get(dog1).getCount())-(dogArr.get(dog2).getCount())) < 5){
				dog1 = 0 + (int)(Math.random() * (( (size) - 0) + 1));
				dog2 = 0 + (int)(Math.random() * (( (size) - 0) + 1));
			}
			
			System.out.println("Which name is more popular for Anchorage dogs? (Type 1 or 2)");
			System.out.println("1." + dogArr.get(dog1).getName() + "\t2." + dogArr.get(dog2).getName());
			total = total + 1;
			
			Scanner keyIn = new Scanner(System.in);
			
			String guess = keyIn.next();
			int guessNum = Integer.parseInt(guess);
			
			if ((dogArr.get(dog1).getCount() - (dogArr.get(dog2).getCount())) > 0){
				if (guessNum == 1){
					System.out.println("Yup, the more popular dog is " + dogArr.get(dog1).getName());
					correct = correct + 1;
				} else {
					System.out.println("Nope, the more popular dog is " + dogArr.get(dog1).getName());
				}
			} else if ((dogArr.get(dog1).getCount() - (dogArr.get(dog2).getCount())) < 0) {
				if (guessNum == 1){
					System.out.println("Nope, the more popular dog is " + dogArr.get(dog2).getName());
				} else {
					System.out.println("Yup, the more popular dog is " + dogArr.get(dog2).getName());
					correct = correct + 1;
				}
			}
		
			System.out.println("Do you want to play again? (Y/N)");
			String again = keyIn.next();
			if (again.equals("Y")){
				// do nothing
			} else {
				playAgain = false;
				System.out.println("You guessed correctly " + correct + " out of " + total + " times.");
			}
		}
		
		
	}
	
	public static void main(String[] args){
		ArrayList<Dog> arr = readDogs();
		
		if (args.length < 1){
			System.out.println("No command line arg!");
			System.exit(0);
		}
		
		if (args[0].equals("1")){
			option1(arr);
		} else if (args[0].equals("2")){
			option2(arr);
		} else if (args[0].equals("3")){
			option3(arr);
		}
	}
}