package com.project.um.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "t_roles")
public class Role extends BaseEntity {

  @Column(name = "name", nullable = false)
  private String name;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name = "t_roles_permissions",
    joinColumns = @JoinColumn(name = "role_id"),
    inverseJoinColumns = @JoinColumn(name = "permission_id"))
  private List<Permission> permissions = new ArrayList<>();

  public Role(String name, Permission permission) {
    this.name = name;
    this.permissions = List.of(permission);
  }

  public Role(String name, List<Permission> permissions) {
    this.name = name;
    this.permissions = permissions;
  }

}
