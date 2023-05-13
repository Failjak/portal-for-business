package com.portal.portalforbusiness.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String comment;

    //    @Column(name = "user_id")
//    private Integer userId;
    @ManyToOne()
    private User user;

    @Column(insertable = false)
    private LocalDateTime created_at;

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    @Column(name = "product_id")
    private Integer productId;

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }

    @OneToMany(mappedBy = "comment", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Mark> marks = new ArrayList<>();

    public List<Mark> getMarks() {
        return marks;
    }

    public void setMarks(List<Mark> marks) {
        this.marks = marks;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }


    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getId() {
        return id;
    }

    public String getComment() {
        return comment;
    }

//    public void setUserId(Integer userId) {
//        this.userId = userId;
//    }

//    public Integer getUserId() {
//        return userId;
//    }

    public Integer getProductId() {
        return productId;
    }
}
