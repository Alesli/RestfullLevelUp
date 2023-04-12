package com.training.jmp.service.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Subscription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "start_date", columnDefinition = "DATE")
    private LocalDate startDate;

    @PrePersist
    public void prePersist() {
        startDate = LocalDate.now();
    }

    @PreUpdate
    public void preUpdate() {
        startDate = LocalDate.now();
    }
}
