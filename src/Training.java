
public class Training {

	public static void main(String[] args) {
		System.out.println("Bonjour et bienvenue dans mon application FullTrainings");
		System.out.println("Nous allons vous proposer une liste de formation actuellement disponible \n \n");
		
		String trainings[][]  = {
				{"Java          ", 	"20" , "Java SE 8 : Syntaxe & Poo            " 	, "3000"},
				{"Java avancé   ", 	"20" , "Exceptions, fichiers, Jdbc, thread..."  , "5000"},
				{"Spring        ",	"20" , "Spring Core/Mvc/Security             "	, "5000"},
				{"Php frameworks",	"15" , "Symphony                             "	, "2500"},
				{"C#            ",  "20" , "DotNet Core                          "  , "5000"},
								};
		
		for(int i=0 ; i<trainings.length ; i++) {
			for(int j=0 ; j<trainings[i].length; j++) {				
				System.out.print(trainings[i][j] + " | ");
			}
			System.out.println();
		}
	}
}
