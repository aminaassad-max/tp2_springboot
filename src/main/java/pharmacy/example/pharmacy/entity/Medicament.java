package pharmacy.example.pharmacy.entity;

import jakarta.persistence.*;

@Entity
public class Medicament {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;

    private float prix;

    private int stock;


    public Medicament() {
    }


    public Medicament(String nom, float prix, int stock) {
        this.nom = nom;
        this.prix = prix;
        this.stock = stock;
    }


    public Long getId() {
        return id;
    }


    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }


    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }


    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}