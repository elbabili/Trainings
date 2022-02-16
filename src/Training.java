
public class Training {

	public static void main(String[] args) {
		System.out.println("Bonjour et bienvenue dans mon application FullTrainings");
		System.out.println("Nous allons vous proposer une liste de formation actuellement disponible");
		
		String trainings[]  = {"Java","Php","C#","React","Angular"};
		System.out.println("-------------------------");
		
		for(int i=0 ; i<trainings.length ; i++) {
			System.out.print(trainings[i] + " ");
		}
		System.out.println("\n-------------------------");
	}
}
