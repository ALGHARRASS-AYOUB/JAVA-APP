
public class Date {
	int jour,mois,annee;

	public Date(int jour, int mois, int annee) {
		super();
		this.jour = jour;
		this.mois = mois;
		this.annee = annee;
	}

	@Override
	public String toString() {
		return "Date [jour=" + jour + ", mois=" + mois + ", annee=" + annee + "]";
	}
	

}
