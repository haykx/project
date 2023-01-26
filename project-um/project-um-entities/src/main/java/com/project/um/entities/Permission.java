package com.project.um.entities;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "t_permissions")
public class Permission extends BaseEntity{
  @Column(name = "name", nullable = false)
  private String name;

  public Permission(String name) {
    this.name = name;
  }
}
