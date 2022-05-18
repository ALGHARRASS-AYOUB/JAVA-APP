
public class ClientPhysique extends Client {
	
	String nom,prenom;
	Date date;
	Sexe genre;
	int NSS;
	public ClientPhysique(int id, String addresseLivraison, String addresseFacturation, String telephone, String email,String nom, String prenom, Date date, Sexe genre, int nSS) {
		super(id, addresseLivraison, addresseFacturation, telephone, email);
		this.nom = nom;
		this.prenom = prenom;
		this.date = date;
		this.genre = genre;
		NSS = nSS;
	}
	@Override
	public String toString() {
		return "ClientPhysique [nom=" + nom + ", prenom=" + prenom + ", date=" + date + ", genre=" + genre + ", NSS="+ NSS + "]"+super.toString() ;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Sexe getGenre() {
		return genre;
	}
	public void setGenre(Sexe genre) {
		this.genre = genre;
	}
	public int getNSS() {
		return NSS;
	}
	public void setNSS(int nSS) {
		NSS = nSS;
	}


}
