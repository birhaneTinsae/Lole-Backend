package com.eshi.addis.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Entity(name = "ingredients")
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotNull(message = "Ingredient name is required")
    @NotBlank(message = "Ingredient name shouldn't be blank")
    private String name;
    private boolean available;
   // @JsonIgnoreProperties(value = {"ingredient","category"})
    @JsonIgnore
    @OneToMany(mappedBy = "ingredient")
    private List<MenuIngredient> menuIngredients;
    @ManyToOne
    @JsonIgnore
   // @JsonIgnoreProperties(value = {"ingredients","category"})
    private Restaurant restaurant;
}
