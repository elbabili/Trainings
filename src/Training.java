import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/** Application simple de gestion d'achat de formation en ligne
 * Les formations dispo ou pas sont représentées sous forme de tableau à 2 dimensions
 * et le panier est une HashMap avec la clé = id d'une formation
 * un menu est proposé avec les opérations d'ajout de formation dans le panier ou suppression
 * l'utilisateur a la possibilitée de passer commande à tout instant... 
 * @author El-BabiliM - 2022
 * @since 1.0 
 */

public class Training {
	/** Numéro unique correspondant à la prochaine commande */
	private static long nextNumberOrder = 5446254;
	/** tableau de formation dispo */
	private static final String trainings[][]  = {
			{"ID","FORMATION     ", 	"NB/JOURS" , "DESCRIPTION                          " 	, "PRIX" , "QUANTITE"},
			{"--","--------------", 	"--------" , "-------------------------------------" 	, "----" , "--------"},
			{"1 ","Java          ", 	"20      " , "Java SE 8 : Syntaxe & Poo            " 	, "3000" , "1       "},
			{"2 ","Java avancé   ", 	"20      " , "Exceptions, fichiers, Jdbc, thread..."    , "5000" , "1       "},
			{"3 ","Spring        ",		"20      " , "Spring Core/Mvc/Security             "	, "5000" , "1       "},
			{"4 ","Php frameworks",		"15      " , "Symphony                             "	, "2500" , "1       "},
			{"5 ","C#            ",  	"20      " , "DotNet Core                          "    , "5000" , "1       "}
	};	
	/** tableau de formation non dispo */
	private static final String trainingsNA [][] = {
			{"FORMATION     ", 	    "NB/JOURS " , "DESCRIPTION                "    , "PRIX" },
			{"Python        ",  	"20       " , "Python Data Science        "    , "7000" },
			{"Angular       ",  	"10       " , "Angular & NgRx             "    , "6000" }
	};
	
	private static Scanner scan = new Scanner(System.in);
	/** représente le panier */
	private static Map <Integer,String[]> cart= new HashMap<>();
	
	private static final String TITLE_CART = "CONTENU PANIER";
	private static final String TITLE_RECAP_CART = "RECAP COMMANDE";

	public static void main(String[] args) {
		market();
	}
	
	/** Méthode proposant le menu principal de notre application */
	public static void market() {
		System.out.println("\t Bonjour et bienvenue dans mon application FullTrainings");
		System.out.println("Nous allons vous proposer une liste de formation actuellement disponible");
		displayTrainings();
		int choice = 0;
		
		while(choice != 7) {
			System.out.println("Que voulez vous faire ?");
			System.out.println("1 : afficher la liste des cours");
			System.out.println("2 : afficher le contenu du panier");
			System.out.println("3 : ajouter un cours au panier");
			System.out.println("4 : supprimer un cours du panier");
			System.out.println("5 : passer commande");
			System.out.println("6 : formation prochainement disponible");
			System.out.println("7 : sortir du programme");
			
			choice = input();
			switch(choice) {
				case 1 : displayTrainings();
						break;
				
				case 2 : if(cart.isEmpty())	System.out.println("Le panier est vide ! \n");
						else displayCart(TITLE_CART);
						break;
				
				case 3 : addToCart();							
						break;
				
				case 4 : if(cart.isEmpty())	System.out.println("Le panier est vide ! \n");
						else removeFromCart(); 
						break;
				
				case 5 : if(cart.isEmpty())	System.out.println("Le panier est vide ! \n");
						else order();
						break;
						
				case 6 : displayTrainingsNA();
						break;
				
				case 7 : System.out.println("au revoir !");
						break;
				
				default : System.out.println("erreur de saisie, merci de saisir une valeur comprise dans l'interval \n");
			}
		}
		scan.close();
	}
	
	/** méthode qui affiche toutes les formations prochainement dispo  */
	private static void displayTrainingsNA() {
		System.out.println("Voici la liste des formations prochainement disponibles :");
		for(String [] train : trainingsNA) {
			for(String str : train) 
				System.out.print(str);
			System.out.println();
		}		
		System.out.println();
	}

	/** méthode qui vérifie et oblige les utilisateurs à saisir des entiers  */
	private static int input() {
		while(scan.hasNextInt() == false) {
			System.out.println("merci de saisir une valeur entière !");
			scan.next();
		}
		return scan.nextInt();
	}

	/** méthode qui supprime une formation du panier */
	public static void removeFromCart() {
		System.out.println("saisissez le numéro de formation à supprimer du panier :");
		int id = input();
		int idMax = trainings[0].length-1; 
		if(id < 1 || id > idMax) {
			System.out.println("vous devez saisir une valeur comprise dans le menu \n");
		}
		else if(cart.containsKey(id)){
			String qty = cart.get(id)[5];
			qty = qty.replace(" ", "");
			int quantity = Integer.valueOf(qty);
			if(--quantity == 0) 	cart.remove(id);		
			else cart.get(id)[5] = String.valueOf(quantity);
		}
		else {
			cart.remove(id);	
		}	
		displayCart(TITLE_CART);
	}

	/** méthode qui ajoute une formation au panier */
	public static void addToCart() {
		System.out.println("saisissez le numéro de formation à ajouter au panier :");
		int id = input();
		int idMax = trainings[0].length-1; 
		if(id < 1 || id > idMax) {
			System.out.println("vous devez saisir une valeur comprise dans le menu !");
		}
		else if(cart.containsKey(id))	{
			String qty = cart.get(id)[5];
			qty = qty.replace(" ", "");
			int quantity = Integer.valueOf(qty);
			cart.get(id)[5] = String.valueOf(++quantity);
		}
		else {
			cart.put(id, trainings[id+1]);					
		}	
		displayCart(TITLE_CART);
	}

	/** méthode de gestion de la commande */
	public static void order() {
		displayCart(TITLE_RECAP_CART);
		System.out.println("PRIX TOTAL : " + orderCalc());			
		System.out.println("******************");
		System.out.println("SI VOUS SOUHAITEZ PASSER COMMANDE, TAPER 1, SINON 0");
		int order = input();
		if(order == 1) {
			System.out.println("Afin de confirmer votre commande, veuillez taper valider svp");
			String confirm = scan.next();
			if(confirm.equalsIgnoreCase("VALIDER")) {
				System.out.println("VOICI LE NUMERO DE VOTRE COMMANDE AFIN DE LA SUIVRE : " + nextNumberOrder++ );
				cart.clear();
				System.out.println("Taper 1 pour revenir vers le menu principal");
				scan.next();
			}
			else System.out.println("Nous n'avons pas pu valider votre commande");
		}
		else System.out.println("Abandon commande"); 
	}
	
	/** méthode qui calcul le total de la commande
	 * @return total 
	 */
	public static int orderCalc() {
		int total = 0;
		for (Integer key : cart.keySet()) {  
		    total += Integer.parseInt(cart.get(key)[4].replace(" ", "")) *
		    		Integer.parseInt(cart.get(key)[5].replace(" ", ""));		    
		}
		return total;
	}

	/** Méthode qui affiche le contenu du panier avec un titre spécifique selon l'étape  
	 * @param title titre de la "fenêtre"
	 */
	public static void displayCart(String title) {
		System.out.println("********************************" + title + "********************************");
		if(cart.isEmpty())		System.out.println("VIDE");
		else {
			for(String str : trainings[0])	System.out.print(str + " ");
			System.out.println();
			for(String[] cad : cart.values()) {				
				for(int i = 0 ; i < cad.length ; i++)	System.out.print(cad[i] + " ");
				System.out.println();
			}
		}
		System.out.println("******************************************************************************");
	}
	
	/** méthode qui affiche toutes les formations disponibles */
	public static void displayTrainings() {
		System.out.println("------------------------------------------------------------------------------");
		for(int i=0 ; i<trainings.length ; i++) {
			for(int j=0 ; j<trainings[i].length-1; j++) {				
				System.out.print(trainings[i][j] + " | ");
			}
			System.out.println();
		}
		System.out.println("------------------------------------------------------------------------------");
	}
}
