import java.util.ArrayList;
import java.util.List;

public class commande {
    private int numeroCommande;
    private List<Plat> plats;
    private double total;

    public commande(int numeroCommande) {
        this.numeroCommande = numeroCommande;
        this.plats = new ArrayList<>();
        this.total = 0.0;
    }

    public void ajouterPlat(Plat plat) {
        if (plat != null) {
            plats.add(plat);
            total += plat.getPrix();
        } else {
            throw new IllegalArgumentException("Le plat ne peut pas être null.");
        }
    }

    public double calculerTotal() {
        return total;
    }

    public void afficherCommande() {
        System.out.println("Commande #" + numeroCommande + ":");
        for (Plat plat : plats) {
            System.out.println(plat.getNom());
        }
        System.out.println("Total : " + total + " euros");
    }

    @Override
    public String toString() {
        return "Commande #" + numeroCommande + ", Total : " + total + " euros";
    }

    public int getNumeroCommande() { return numeroCommande; }
    public void setNumeroCommande(int numeroCommande) { this.numeroCommande = numeroCommande; }

    public List<Plat> getPlats() { return plats; }
    public void setPlats(List<Plat> plats) {
        this.plats = plats;
        this.total = plats.stream().mapToDouble(Plat::getPrix).sum();
    }

    public double getTotal() { return total; }
    public void setTotal(double total) {
        if (total >= 0) {
            this.total = total;
        } else {
            throw new IllegalArgumentException("Le total ne peut pas être négatif.");
        }
    }
}