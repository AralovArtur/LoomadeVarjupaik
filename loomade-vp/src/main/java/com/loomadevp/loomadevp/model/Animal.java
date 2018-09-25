package com.loomadevp.loomadevp.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@Table(name = "Loomad")

public class Animal {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Max(45)
    @NotNull
    private String liik;

    @NotNull
    private int vanus;

    @Max(45)
    @NotNull
    private String linn;

    @Override
    public String toString() {
        return "Animal{" +
                "liik='" + liik + '\'' +
                ", vanus=" + vanus +
                ", linn='" + linn + '\'' +
                '}';
    }
}
