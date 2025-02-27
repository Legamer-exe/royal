import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Plat {
    private String nom;
    private String description;
    private double prix;
    private int calories;
    private String categorie;
    private String type;
    private LocalDate dateCreation;
    private boolean disponibilite;
    private List<String> ingredients;
    private String cuisineType;
    private int tempsPreparation;
    private double note;
    private String imageUrl;

    public Plat(String nom, String description, double prix, int calories, String categorie, String type, LocalDate dateCreation, boolean disponibilite, List<String> ingredients, String cuisineType, int tempsPreparation, double note, String imageUrl) {
        this.nom = nom;
        this.description = description;
        this.prix = prix;
        this.calories = calories;
        this.categorie = categorie;
        this.type = type;
        this.dateCreation = dateCreation;
        this.disponibilite = disponibilite;
        this.ingredients = ingredients;
        this.cuisineType = cuisineType;
        this.tempsPreparation = tempsPreparation;
        this.note = note;
        this.imageUrl = imageUrl;
    }

    public String getNom() {
        return nom;
    }

    public String getCategorie() {
        return categorie;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        if (prix >= 0) {
            this.prix = prix;
        } else {
            System.out.println("Le prix doit être positif.");
        }
    }

    public String getDescription() {
        return null;
    }

    public String getCalories() {
        return null;
    }
}

class Categorie {
    private String nom;
    List<Plat> plats;

    public Categorie(String nom) {
        this.nom = nom;
        this.plats = new ArrayList<>();
    }

    public String getNom() {
        return nom;
    }

    public void ajouterPlat(Plat plat) {
        plats.add(plat);
    }
}

class Menu {
    private int id;
    private String nomMenu;
    private LocalDate dateCreation;
    private String type;
    private List<Categorie> categories;

    public Menu(int id, String nomMenu, LocalDate dateCreation, String type) {
        this.id = id;
        this.nomMenu = nomMenu;
        this.dateCreation = dateCreation;
        this.type = type;
        this.categories = new ArrayList<>();
    }

    public String getNomMenu() {
        return nomMenu;
    }

    public void ajouterPlat(Plat plat) {
        boolean categoryExists = false;
        for (Categorie categorie : categories) {
            if (categorie.getNom().equalsIgnoreCase(plat.getCategorie())) {
                categorie.ajouterPlat(plat);
                categoryExists = true;
                break;
            }
        }
        if (!categoryExists) {
            Categorie nouvelleCategorie = new Categorie(plat.getCategorie());
            nouvelleCategorie.ajouterPlat(plat);
            categories.add(nouvelleCategorie);
        }
    }

    public Plat chercherPlatParNom(String nom) {
        for (Categorie categorie : categories) {
            for (Plat plat : categorie.plats) {
                if (plat.getNom().equalsIgnoreCase(nom)) {
                    return plat;
                }
            }
        }
        return null;
    }
}

class employe {
    private int id;
    private String nom;
    private String prenom;
    private String role;
    private LocalDate dateEmbauche;
    private double salaire;

    public employe(int id, String nom, String prenom, String role, LocalDate dateEmbauche, double salaire) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.role = role;
        this.dateEmbauche = dateEmbauche;
        this.salaire = salaire;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getRole() {
        return role;
    }

    public double getSalaire() {
        return salaire;
    }
}

class Commande {
    private int numeroCommande;
    private List<Plat> plats;

    public Commande(int numeroCommande) {
        this.numeroCommande = numeroCommande;
        this.plats = new ArrayList<>();
    }

    public int getNumeroCommande() {
        return numeroCommande;
    }

    public List<Plat> getPlats() {
        return plats;
    }

    public void ajouterPlat(Plat plat) {
        plats.add(plat);
    }

    public double calculerTotal() {
        return plats.stream().mapToDouble(Plat::getPrix).sum();
    }
}

class Restaurant {
    private int id;
    private String nomRestaurant;
    private String adresse;
    private Menu menu;
    private List<employe> employes;
    private List<commande> commandes;

    public Restaurant(int id, String nomRestaurant, String adresse, Menu menu) {
        this.id = id;
        this.nomRestaurant = nomRestaurant;
        this.adresse = adresse;
        this.menu = menu;
        this.employes = new ArrayList<>();
        this.commandes = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getNomRestaurant() {
        return nomRestaurant;
    }

    public String getAdresse() {
        return adresse;
    }

    public Menu getMenu() {
        return menu;
    }

    public List<employe> getEmployes() {
        return employes;
    }

    public void ajouterEmploye(employe employe) {
        employes.add(employe);
    }

    public void supprimerEmploye(employe employe) {
        employes.remove(employe);
    }

    public void afficherEmployes() {
        for (employe employe : employes) {
            System.out.println("ID: " + employe.getId() + ", Nom: " + employe.getNom() + " " + employe.getPrenom() + ", Rôle: " + employe.getRole() + ", Salaire: " + employe.getSalaire());
        }
    }

    public void ajouterCommande(commande commande) {
        commandes.add(commande);
    }

    public void afficherCommandes() {
        for (commande commande : commandes) {
            System.out.println("Commande #" + commande.getNumeroCommande() + ":");
            for (Plat plat : commande.getPlats()) {
                System.out.println("- " + plat.getNom() + ": " + plat.getPrix() + " euros");
            }
            System.out.println("Total: " + commande.calculerTotal() + " euros");
        }
    }

    public List<commande> getCommandes() {
        return commandes;
    }
}

public class Main {
    private static final String RESTAURANTS_FILE = "restaurants.txt";
    private static final String EMPLOYEES_FILE = "employes.txt";
    private static final String PLATS_FILE = "plats.txt";
    private static final String COMMANDES_FILE = "commandes.txt";

    private static List<Restaurant> restaurants = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        try {
            while (true) {
                afficherMenu();
                int choix = scanner.nextInt();
                scanner.nextLine();

                switch (choix) {
                    case 1:
                        ajouterRestaurant();
                        break;
                    case 2:
                        ajouterEmploye();
                        break;
                    case 3:
                        ajouterPlat();
                        break;
                    case 4:
                        supprimerEmploye();
                        break;
                    case 5:
                        afficherEmployes();
                        break;
                    case 6:
                        prendreCommande();
                        break;
                    case 7:
                        afficherCommandes();
                        break;
                    case 8:
                        sauvegarderCommandes();
                        break;
                    case 9:
                        chargerCommandes();
                        break;
                    case 10:
                        System.out.println("A+ ✌\uFE0F");
                        return;
                    default:
                        System.out.println("NON ! \nRéessay ! \n");
                }
            }
        } finally {
            scanner.close();
        }
    }

    private static void afficherMenu() {
        System.out.println("Menu utilisateur :");
        System.out.println("1. Ajouter un restaurant");
        System.out.println("2. Ajouter un employé à un restaurant");
        System.out.println("3. Ajouter un plat au menu d'un restaurant");
        System.out.println("4. Supprimer un employé d'un restaurant");
        System.out.println("5. Afficher les employés d'un restaurant");
        System.out.println("6. Prendre une commande pour un restaurant");
        System.out.println("7. Afficher toutes les commandes d'un restaurant");
        System.out.println("8. Sauvegarder les commandes d'un restaurant");
        System.out.println("9. Charger les commandes d'un restaurant");
        System.out.println("10. Quitter");
    }

    private static void ajouterRestaurant() {
        System.out.println("Entrez l'ID du restaurant :");
        int id = scanner.nextInt();
        scanner.nextLine();

        if (id <= 0) {
            System.out.println("L'ID doit être un entier positif.");
            return;
        }

        System.out.println("Entrez le nom du restaurant :");
        String nom = scanner.nextLine();
        System.out.println("Entrez l'adresse du restaurant :");
        String adresse = scanner.nextLine();
        System.out.println("Entrez le nom du menu :");
        String nomMenu = scanner.nextLine();
        Menu menu = new Menu(id, nomMenu, LocalDate.now(), "Standard");
        Restaurant restaurant = new Restaurant(id, nom, adresse, menu);
        restaurants.add(restaurant);
        sauvegarderRestaurant(restaurant);
        System.out.println("Restaurant ajouté avec succès !");
    }

    private static void ajouterEmploye() {
        Restaurant restaurant = demanderEtTrouverRestaurant();
        if (restaurant != null) {
            System.out.println("Entrez l'ID de l'employé :");
            int idEmploye = scanner.nextInt();
            scanner.nextLine();

            if (idEmploye <= 0) {
                System.out.println("L'ID doit être un entier positif.");
                return;
            }

            System.out.println("Entrez le nom de l'employé :");
            String nom = scanner.nextLine();
            System.out.println("Entrez le prénom de l'employé :");
            String prenom = scanner.nextLine();
            System.out.println("Entrez le rôle de l'employé :");
            String role = scanner.nextLine();
            System.out.println("Entrez le salaire de l'employé :");
            double salaire = scanner.nextDouble();
            scanner.nextLine();

            if (salaire < 0) {
                System.out.println("Le salaire doit être positif.");
                return;
            }

            employe employe = new employe(idEmploye, nom, prenom, role, LocalDate.now(), salaire);
            restaurant.ajouterEmploye(employe);
            sauvegarderEmploye(employe, restaurant.getId());
            System.out.println("Employé ajouté avec succès !");
        }
    }

    private static void ajouterPlat() {
        Restaurant restaurant = demanderEtTrouverRestaurant();
        if (restaurant != null) {
            System.out.println("Entrez le nom du plat :");
            String nom = scanner.nextLine();
            System.out.println("Entrez la description du plat :");
            String description = scanner.nextLine();
            System.out.println("Entrez le prix du plat :");
            double prix = scanner.nextDouble();
            scanner.nextLine();

            if (prix < 0) {
                System.out.println("Le prix doit être positif.");
                return;
            }

            System.out.println("Entrez les calories du plat :");
            int calories = scanner.nextInt();
            scanner.nextLine();

            if (calories < 0) {
                System.out.println("Les calories doivent être positives.");
                return;
            }

            System.out.println("Entrez la catégorie du plat :");
            String categorie = scanner.nextLine();
            Plat plat = new Plat(nom, description, prix, calories, categorie, "Standard", LocalDate.now(), true, new ArrayList<>(), "Française", 30, 0.0, "");
            restaurant.getMenu().ajouterPlat(plat);
            sauvegarderPlat(plat, restaurant.getId());
            System.out.println("Plat ajouté avec succès !");
        }
    }

    private static void supprimerEmploye() {
        Restaurant restaurant = demanderEtTrouverRestaurant();
        if (restaurant != null) {
            System.out.println("Entrez l'ID de l'employé à supprimer :");
            int idEmploye = scanner.nextInt();
            scanner.nextLine();
            employe employe = trouverEmployeParId(restaurant, idEmploye);
            if (employe != null) {
                restaurant.supprimerEmploye(employe);
                System.out.println("Employé supprimé avec succès !");
            } else {
                System.out.println("Employé non trouvé.");
            }
        }
    }

    private static void afficherEmployes() {
        Restaurant restaurant = demanderEtTrouverRestaurant();
        if (restaurant != null) {
            restaurant.afficherEmployes();
        }
    }

    private static void prendreCommande() {
        Restaurant restaurant = demanderEtTrouverRestaurant();
        if (restaurant != null) {
            commande commande = creerCommande(restaurant);
            restaurant.ajouterCommande(commande);
            sauvegarderCommande(commande, restaurant.getId());
            System.out.println("Commande prise avec succès !");
        }
    }

    private static commande creerCommande(Restaurant restaurant) {
        commande commande = new commande(restaurant.getCommandes().size() + 1);
        while (true) {
            System.out.println("Entrez le nom du plat à ajouter (ou 'fin' pour terminer) :");
            String nomPlat = scanner.nextLine();
            if (nomPlat.equalsIgnoreCase("fin")) {
                break;
            }
            Plat plat = restaurant.getMenu().chercherPlatParNom(nomPlat);
            if (plat != null) {
                commande.ajouterPlat(plat);
            } else {
                System.out.println("Plat non trouvé.");
            }
        }
        return commande;
    }

    private static void afficherCommandes() {
        Restaurant restaurant = demanderEtTrouverRestaurant();
        if (restaurant != null) {
            restaurant.afficherCommandes();
        }
    }

    private static void sauvegarderCommandes() {
        Restaurant restaurant = demanderEtTrouverRestaurant();
        if (restaurant != null) {
            System.out.println("Entrez le nom du fichier pour sauvegarder les commandes :");
            String fichier = scanner.nextLine();
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(fichier))) {
                for (commande commande : restaurant.getCommandes()) {
                    writer.write("Commande #" + commande.getNumeroCommande() + " :");
                    writer.newLine();
                    for (Plat plat : commande.getPlats()) {
                        writer.write("- " + plat.getNom() + " : " + plat.getPrix() + " euros");
                        writer.newLine();
                    }
                    writer.write("Total : " + commande.calculerTotal() + " euros");
                    writer.newLine();
                    writer.newLine();
                }
                System.out.println("Commandes sauvegardées avec succès !");
            } catch (IOException e) {
                System.err.println("Erreur lors de la sauvegarde des commandes : " + e.getMessage());
            }
        }
    }

    private static void chargerCommandes() {
        Restaurant restaurant = demanderEtTrouverRestaurant();
        if (restaurant != null) {
            System.out.println("Entrez le nom du fichier pour charger les commandes :");
            String fichier = scanner.nextLine();
            try (BufferedReader reader = new BufferedReader(new FileReader(fichier))) {
                String line;
                commande commande = null;
                while ((line = reader.readLine()) != null) {
                    if (line.startsWith("Commande #")) {
                        if (commande != null) {
                            restaurant.ajouterCommande(commande);
                        }
                        commande = new commande(Integer.parseInt(line.split("#")[1].trim().split(" ")[0]));
                    } else if (line.startsWith("- ")) {
                        String nomPlat = line.split(":")[0].substring(2).trim();
                        Plat plat = restaurant.getMenu().chercherPlatParNom(nomPlat);
                        if (plat != null) {
                            commande.ajouterPlat(plat);
                        }
                    } else if (line.startsWith("Total")) {
                    }
                }
                if (commande != null) {
                    restaurant.ajouterCommande(commande);
                }
                System.out.println("Commandes chargées avec succès !");
            } catch (IOException e) {
                System.err.println("Erreur lors du chargement des commandes : " + e.getMessage());
            }
        }
    }

    private static Restaurant demanderEtTrouverRestaurant() {
        System.out.println("Entrez l'ID du restaurant :");
        int idRestaurant = scanner.nextInt();
        scanner.nextLine();
        return trouverRestaurantParId(idRestaurant);
    }

    private static Restaurant trouverRestaurantParId(int id) {
        for (Restaurant restaurant : restaurants) {
            if (restaurant.getId() == id) {
                return restaurant;
            }
        }
        System.out.println("Restaurant non trouvé.");
        return null;
    }

    private static employe trouverEmployeParId(Restaurant restaurant, int id) {
        for (employe employe : restaurant.getEmployes()) {
            if (employe.getId() == id) {
                return employe;
            }
        }
        return null;
    }

    private static void sauvegarderRestaurant(Restaurant restaurant) {
        File file = new File(RESTAURANTS_FILE);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.err.println("Erreur lors de la création du fichier : " + e.getMessage());
                return;
            }
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            writer.write("Restaurant ID: " + restaurant.getId());
            writer.newLine();
            writer.write("Nom: " + restaurant.getNomRestaurant());
            writer.newLine();
            writer.write("Adresse: " + restaurant.getAdresse());
            writer.newLine();
            writer.write("Menu: " + restaurant.getMenu().getNomMenu());
            writer.newLine();
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Erreur lors de la sauvegarde du restaurant : " + e.getMessage());
        }
    }

    private static void sauvegarderEmploye(employe employe, int restaurantId) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(EMPLOYEES_FILE, true))) {
            writer.write("Restaurant ID: " + restaurantId);
            writer.newLine();
            writer.write("Employé ID: " + employe.getId());
            writer.newLine();
            writer.write("Nom: " + employe.getNom());
            writer.newLine();
            writer.write("Prénom: " + employe.getPrenom());
            writer.newLine();
            writer.write("Rôle: " + employe.getRole());
            writer.newLine();
            writer.write("Salaire: " + employe.getSalaire());
            writer.newLine();
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Erreur lors de la sauvegarde de l'employé : " + e.getMessage());
        }
    }

    private static void sauvegarderPlat(Plat plat, int restaurantId) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(PLATS_FILE, true))) {
            writer.write("Restaurant ID: " + restaurantId);
            writer.newLine();
            writer.write("Nom: " + plat.getNom());
            writer.newLine();
            writer.write("Description: " + plat.getDescription());
            writer.newLine();
            writer.write("Prix: " + plat.getPrix());
            writer.newLine();
            writer.write("Calories: " + plat.getCalories());
            writer.newLine();
            writer.write("Catégorie: " + plat.getCategorie());
            writer.newLine();
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Erreur lors de la sauvegarde du plat : " + e.getMessage());
        }
    }

    private static void sauvegarderCommande(commande commande, int restaurantId) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(COMMANDES_FILE, true))) {
            writer.write("Restaurant ID: " + restaurantId);
            writer.newLine();
            writer.write("Commande #" + commande.getNumeroCommande() + " :");
            writer.newLine();
            for (Plat plat : commande.getPlats()) {
                writer.write("- " + plat.getNom() + " : " + plat.getPrix() + " euros");
                writer.newLine();
            }
            writer.write("Total : " + commande.calculerTotal() + " euros");
            writer.newLine();
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Erreur lors de la sauvegarde de la commande : " + e.getMessage());
        }
    }
}