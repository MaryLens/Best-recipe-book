package com.project.recipebook.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "imagesToRecipe")

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImageToRecipe {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "originalFilename")
    private String originalFilename;
    @Column(name = "contentType")
    private String contentType;
    @Column(name = "size")
    private Long size;
    @Column(name = "isPreviewImage")
    private boolean isPreviewImage;

    @Lob
    @Column(name = "bytes", columnDefinition = "BIGINT")
    private byte[] bytes;
    @OneToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    private Recipe recipe;


}
