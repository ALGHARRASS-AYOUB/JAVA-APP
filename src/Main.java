
public class Main {
	
	 void testerClienP(){
		
		Date date=new Date(6, 2, 1980);
		ClientPhysique client=new ClientPhysique(0,"fesboulvard saada", "boulvard saada", "0673770138", "houssin.aouita@gmail.com", "AYOUB", "AYOUB",date, Sexe.masculin, 121214);
	System.out.println(client);
		
	 }
	
	 void testerCltEntreprise() {
		 ClientEntreprise entreprise=new ClientEntreprise(450,"fesboulvard saada", "boulvard saada", "0673770138", "houssin.aouita@gmail.com", "SOFAX", "SARL", "afc45562io9");
		 System.out.println(entreprise);
	 }

	public static void main(String[] args) {
		new Main().testerClienP();
		new Main().testerCltEntreprise();
		System.out.println();
		System.out.println("-------");
		
	}
	
	

}
