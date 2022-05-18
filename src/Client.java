
public class Client {
	int id;
	String addresseLivraison,addresseFacturation;
	String telephone,email;
	public Client(int id, String addresseLivraison, String addresseFacturation, String telephone, String email) {
		this.id = id;
		this.addresseLivraison = addresseLivraison;
		this.addresseFacturation = addresseFacturation;
		this.telephone = telephone;
		this.email = email;
	}
	@Override
	public String toString() {
		return "Client [id=" + id + ", addresseLivraison=" + addresseLivraison + ", addresseFacturation="
				+ addresseFacturation + ", telephone=" + telephone + ", email=" + email + "]";
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAddresseLivraison() {
		return addresseLivraison;
	}
	public void setAddresseLivraison(String addresseLivraison) {
		this.addresseLivraison = addresseLivraison;
	}
	public String getAddresseFacturation() {
		return addresseFacturation;
	}
	public void setAddresseFacturation(String addresseFacturation) {
		this.addresseFacturation = addresseFacturation;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
	

}
