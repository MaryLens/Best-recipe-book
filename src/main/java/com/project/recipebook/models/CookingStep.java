package com.project.recipebook.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class CookingStep {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "number")
    private Integer number;
    @Column(name = "description")
    private String description;
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    private Recipe recipe;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "cookingStep")
    private ImageToStep image;

    public void addImageToStep(ImageToStep image) {
        image.setCookingStep(this);
        this.image = image;
    }

    public void removeImageFromStep() {
        this.image = null;
    }

}
