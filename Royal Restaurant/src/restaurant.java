import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class restaurant {
    private int id;
    private String nomRestaurant;
    private String adresse;
    private Menu menu;
    private List<commande> commandes;
    private List<employe> employes;

    public restaurant(int id, String nomRestaurant, String adresse, Menu menu) {
        this.id = id;
        this.nomRestaurant = nomRestaurant;
        this.adresse = adresse;
        this.menu = menu;
        this.commandes = new ArrayList<>();
        this.employes = new ArrayList<>();
    }

    public void ajouterCommande(commande commande) {
        if (commande != null) {
            commandes.add(commande);
        } else {
            throw new IllegalArgumentException("La commande ne peut pas être null.");
        }
    }

    public void afficherCommandes() {
        System.out.println("Commandes pour le restaurant " + nomRestaurant + ":");
        for (commande commande : commandes) {
            commande.afficherCommande();
        }
    }

    public void ajouterEmploye(employe employe) {
        if (employe != null) {
            employes.add(employe);
        } else {
            throw new IllegalArgumentException("L'employé ne peut pas être null.");
        }
    }

    public void supprimerEmploye(employe employe) {
        employes.remove(employe);
    }

    public List<employe> chercherEmployeParRole(String role) {
        List<employe> employesParRole = new ArrayList<>();
        for (employe employe : employes) {
            if (employe.getRole().equalsIgnoreCase(role)) {
                employesParRole.add(employe);
            }
        }
        return employesParRole;
    }

    public void afficherEmployes() {
        System.out.println("Employés du restaurant " + nomRestaurant + ":");
        for (employe employe : employes) {
            System.out.println(employe);
        }
    }

    public void sauvegarderCommandes(String fichier) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fichier))) {
            for (commande commande : commandes) {
                writer.write(commande.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Erreur lors de la sauvegarde des commandes : " + e.getMessage());
        }
    }

    public void chargerCommandes(String fichier) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fichier))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.err.println("Erreur lors du chargement des commandes : " + e.getMessage());
        }
    }

    public double totalSalaireEmployes() {
        double total = 0.0;
        for (employe employe : employes) {
            total += employe.getSalaire();
        }
        return total;
    }

    public double totalChiffreAffaire() {
        double total = 0.0;
        for (commande commande : commandes) {
            total += commande.calculerTotal();
        }
        return total;
    }

    public void afficherRestaurant() {
        System.out.println("Restaurant #" + id + " : " + nomRestaurant + ", Adresse : " + adresse);
        System.out.println("Menu : " + menu);
        afficherEmployes();
    }

    public Menu getMenu() { return menu; }
    public void setMenu(Menu menu) { this.menu = menu; }

    public List<commande> getCommandes() { return commandes; }
    public void setCommandes(List<commande> commandes) { this.commandes = commandes; }

    public List<employe> getEmployes() { return employes; }
    public void setEmployes(List<employe> employes) { this.employes = employes; }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNomRestaurant() { return nomRestaurant; }
    public void setNomRestaurant(String nomRestaurant) { this.nomRestaurant = nomRestaurant; }

    public String getAdresse() { return adresse; }
    public void setAdresse(String adresse) { this.adresse = adresse; }
}