
public class ClientEntreprise extends Client{

	String nom,statusJuridique,numeroNational;

	public ClientEntreprise(int id, String addresseLivraison, String addresseFacturation, String telephone,
			String email, String nom, String statusJuridique, String numeroNational) {
		super(id, addresseLivraison, addresseFacturation, telephone, email);
		this.nom = nom;
		this.statusJuridique = statusJuridique;
		this.numeroNational = numeroNational;
	}

	@Override
	public String toString() {
		return "ClientEntreprise [nom=" + nom + ", statusJuridique=" + statusJuridique + ", numeroNational=" + numeroNational + "]"+super.toString() ;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getStatusJuridique() {
		return statusJuridique;
	}

	public void setStatusJuridique(String statusJuridique) {
		this.statusJuridique = statusJuridique;
	}

	public String getNumeroNational() {
		return numeroNational;
	}

	public void setNumeroNational(String numeroNational) {
		this.numeroNational = numeroNational;
	}
	
	
}
