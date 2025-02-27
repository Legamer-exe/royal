import java.time.LocalDate;
import java.util.List;

public class plat {
    private String nom;
    private String description;
    private double prix;
    private int calories;
    private String categorie;
    private String taillePortion;
    private LocalDate dateAjout;
    private boolean disponibilite;
    private List<String> ingredients;
    private String typeCuisine;
    private int tempsPreparation;
    private double prixSpecial;
    private String imageURL;

    public plat(String nom, String description, double prix, int calories, String categorie, String taillePortion, LocalDate dateAjout, boolean disponibilite, List<String> ingredients, String typeCuisine, int tempsPreparation, double prixSpecial, String imageURL) {
        this.nom = nom;
        this.description = description;
        this.prix = prix;
        this.calories = calories;
        this.categorie = categorie;
        this.taillePortion = taillePortion;
        this.dateAjout = dateAjout;
        this.disponibilite = disponibilite;
        this.ingredients = ingredients;
        this.typeCuisine = typeCuisine;
        this.tempsPreparation = tempsPreparation;
        this.prixSpecial = prixSpecial;
        this.imageURL = imageURL;
    }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public double getPrix() { return prix; }
    public void setPrix(double prix) {
        if (prix >= 0) {
            this.prix = prix;
        } else {
            throw new IllegalArgumentException("Le prix doit être positif.");
        }
    }

    public int getCalories() { return calories; }
    public void setCalories(int calories) {
        if (calories >= 0) {
            this.calories = calories;
        } else {
            throw new IllegalArgumentException("Les calories doivent être positives.");
        }
    }

    public String getCategorie() { return categorie; }
    public void setCategorie(String categorie) { this.categorie = categorie; }

    public String getTaillePortion() { return taillePortion; }
    public void setTaillePortion(String taillePortion) { this.taillePortion = taillePortion; }

    public LocalDate getDateAjout() { return dateAjout; }
    public void setDateAjout(LocalDate dateAjout) { this.dateAjout = dateAjout; }

    public boolean isDisponibilite() { return disponibilite; }
    public void setDisponibilite(boolean disponibilite) { this.disponibilite = disponibilite; }

    public List<String> getIngredients() { return ingredients; }
    public void setIngredients(List<String> ingredients) { this.ingredients = ingredients; }

    public String getTypeCuisine() { return typeCuisine; }
    public void setTypeCuisine(String typeCuisine) { this.typeCuisine = typeCuisine; }

    public int getTempsPreparation() { return tempsPreparation; }
    public void setTempsPreparation(int tempsPreparation) {
        if (tempsPreparation >= 0) {
            this.tempsPreparation = tempsPreparation;
        } else {
            throw new IllegalArgumentException("Le temps de préparation doit être positif.");
        }
    }

    public double getPrixSpecial() { return prixSpecial; }
    public void setPrixSpecial(double prixSpecial) {
        if (prixSpecial >= 0) {
            this.prixSpecial = prixSpecial;
        } else {
            throw new IllegalArgumentException("Le prix spécial doit être positif.");
        }
    }

    public String getImageURL() { return imageURL; }
    public void setImageURL(String imageURL) { this.imageURL = imageURL; }

    @Override
    public String toString() {
        return "Plat : " + nom + ", Description : " + description + ", Prix : " + prix + ", Catégorie : " + categorie +
                ", Disponibilité : " + (disponibilite ? "Disponible" : "Non disponible");
    }
}