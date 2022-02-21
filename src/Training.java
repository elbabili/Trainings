import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Training {

	public static void main(String[] args) {
		System.out.println("\t Bonjour et bienvenue dans mon application FullTrainings");
		System.out.println("Nous allons vous proposer une liste de formation actuellement disponible");
		
		String trainings[][]  = {
				{"ID","COURS         ", 	"NB/JOURS" , "DESCRIPTION                          " 	, "PRIX" , "QUANTITE"},
				{"--","--------------", 	"--------" , "-------------------------------------" 	, "----" , "--------"},
				{"1 ","Java          ", 	"20      " , "Java SE 8 : Syntaxe & Poo            " 	, "3000" , "1       "},
				{"2 ","Java avancé   ", 	"20      " , "Exceptions, fichiers, Jdbc, thread..."    , "5000" , "1       "},
				{"3 ","Spring        ",		"20      " , "Spring Core/Mvc/Security             "	, "5000" , "1       "},
				{"4 ","Php frameworks",		"15      " , "Symphony                             "	, "2500" , "1       "},
				{"5 ","C#            ",  	"20      " , "DotNet Core                          "    , "5000" , "1       "}
		};		
		//display(trainings);
		market(trainings);
	}
	
	public static void market(String trainings[][]) {
		display(trainings);
		Scanner scan = new Scanner(System.in);
		Map <Integer,String[]> caddy= new HashMap<>();
		boolean exit = false;
		while(!exit) {
			System.out.println("Que voulez vous faire ?");
			System.out.println("1 : afficher la liste des cours");
			System.out.println("2 : afficher le contenu du panier");
			System.out.println("3 : ajouter un cours au panier");
			System.out.println("4 : supprimer un cours du panier");
			System.out.println("5 : sortir du programme");
			
			while(scan.hasNextInt() == false)	scan.next();
			int action = scan.nextInt();
			switch(action) {
				case 1 : display(trainings);
				break;
				
				case 2 : display(caddy);
				break;
				
				case 3 : System.out.println("saisissez le numéro de formation à ajouter au panier :");
				int id = scan.nextInt();
				int idMax = trainings[0].length-1; 
				if(id < 1 || id > idMax) {
					System.out.println("vous devez saisir une valeur comprise dans le menu !");
				}
				else if(caddy.containsKey(id))	{
					String qty = caddy.get(id)[5];
					qty = qty.replace(" ", "");
					int quantity = Integer.valueOf(qty);
					quantity++;
					caddy.get(id)[5] = String.valueOf(quantity);
				}
				else {
					caddy.put(id, trainings[id+1]);					
				}				
				break;
				
				case 4 : System.out.println("saisissez le numéro de formation à supprimer du panier :");
				id = scan.nextInt();
				caddy.remove(id);
				break;
				
				case 5 : System.out.println("au revoir !");
				exit = false;
				break;
				
				default : System.out.println("erreur de saisie, merci de saisir une valeur comprise dans l'interval");
			}
		}
		scan.close();
	}

	public static void display(Map <Integer,String[]> caddy) {
		System.out.println("****contenu du panier****");
		if(caddy.isEmpty())		System.out.println("PANIER VIDE");
		else for(String[] cad : caddy.values()) {
			System.out.println(cad[0] + " " + cad[1] + " " + cad[2] + " " + cad[3] + " " + cad[4] + " " + cad[5]);
		}
		System.out.println("*************************");
	}
	
	public static void display(String trainings[][]) {
		System.out.println("-----------------------------------------------------------------------------------------");
		for(int i=0 ; i<trainings.length ; i++) {
			for(int j=0 ; j<trainings[i].length; j++) {				
				System.out.print(trainings[i][j] + " | ");
			}
			System.out.println();
		}
		System.out.println("-----------------------------------------------------------------------------------------");
	}
}
