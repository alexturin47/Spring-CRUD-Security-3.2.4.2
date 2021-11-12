package org.example.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="role")
public class Role {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="role")
    private String role;

//    @ManyToMany(mappedBy = "role")
//    private List<User> users;
//    @ManyToOne
//    @JoinColumn(name="user_id")
//    private User user;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

//    public List<User> getUsers() {
//        return users;
//    }
//
//    public void setUsers(List<User> users) {
//        this.users = users;
//    }
}
