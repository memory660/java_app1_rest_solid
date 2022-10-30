package com.acme.app1.models;

import lombok.*;
import lombok.experimental.Accessors;
import javax.validation.constraints.NotEmpty;
import javax.persistence.*;
import java.io.Serializable;

@Getter                 // lombok
@Setter                 // lombok
/*
L'annotation @Accessors est utilisée pour configurer la façon dont lombok génère et recherche les getters, setters et with-ers.
Par défaut, lombok suit la spécification du bean pour les getters et setters : Le getter pour un champ nommé pepper est getPepper par exemple.
*/
@Accessors(chain=true)  // lombok
@NoArgsConstructor      // Lombok pour générer un constructeur par défaut sans arguments pour notre classe
@AllArgsConstructor     // Lombok utilisée pour générer un constructeur tout-arguments.
@Entity                 // annotation JPA
@Builder                // va aider à créer une instance d'une entité (pour les tests) en utilisant .builder.
@Table(name = "employee")
public class Employee implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;

    @NotEmpty(message = "First name is required")
    private String firstname;

    @NotEmpty(message = "Last name is required")
    private String lastname;

    @Column(nullable = true, name = "email")
    private String email;
}
