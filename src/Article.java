
public class Article {
	int id;
	String libelle;
	int prixHT,TVA,Qte;
	public Article(int id, String libelle, int prixHT, int tVA, int qte) {
		super();
		this.id = id;
		this.libelle = libelle;
		this.prixHT = prixHT;
		TVA = tVA;
		Qte = qte;
	}
	@Override
	public String toString() {
		return "Article [id=" + id + ", libelle=" + libelle + ", prixHT=" + prixHT + ", TVA=" + TVA + ", Qte=" + Qte
				+ "]";
	}

	

}
