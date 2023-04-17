package com.project.publisher.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "t_publishers")
public class Publisher extends BaseEntity {

    @Column(name = "original_id")
    private UUID originalId;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;
    @OneToMany(mappedBy = "publisher")
    private List<Discussion> discussions = new ArrayList<>();

    @OneToMany(mappedBy = "publisher")
    private List<Survey> surveys = new ArrayList<>();

    public String getFullName() {
        return this.firstName + " " + this.lastName;
    }

}
