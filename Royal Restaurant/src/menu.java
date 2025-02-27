import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class menu {
    private int id;
    private String nomMenu;
    private LocalDate dateCreation;
    private String typeMenu;
    private List<Plat> platsDisponibles;

    public menu(int id, String nomMenu, LocalDate dateCreation, String typeMenu) {
        this.id = id;
        this.nomMenu = nomMenu;
        this.dateCreation = dateCreation;
        this.typeMenu = typeMenu;
        this.platsDisponibles = new ArrayList<>();
    }

    public void ajouterPlat(Plat plat) {
        if (plat != null) {
            platsDisponibles.add(plat);
        } else {
            throw new IllegalArgumentException("Le plat ne peut pas être null.");
        }
    }

    public void supprimerPlat(Plat plat) {
        platsDisponibles.remove(plat);
    }

    public void afficherMenu() {
        System.out.println("Menu : " + nomMenu);
        for (Plat plat : platsDisponibles) {
            System.out.println(plat);
        }
    }

    public Plat chercherPlatParNom(String nom) {
        for (Plat plat : platsDisponibles) {
            if (plat.getNom().equalsIgnoreCase(nom)) {
                return plat;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "Menu : " + nomMenu + ", Type : " + typeMenu + ", Date de création : " + dateCreation;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNomMenu() { return nomMenu; }
    public void setNomMenu(String nomMenu) { this.nomMenu = nomMenu; }

    public LocalDate getDateCreation() { return dateCreation; }
    public void setDateCreation(LocalDate dateCreation) { this.dateCreation = dateCreation; }

    public String getTypeMenu() { return typeMenu; }
    public void setTypeMenu(String typeMenu) { this.typeMenu = typeMenu; }

    public List<Plat> getPlatsDisponibles() { return platsDisponibles; }
    public void setPlatsDisponibles(List<Plat> platsDisponibles) { this.platsDisponibles = platsDisponibles; }
}