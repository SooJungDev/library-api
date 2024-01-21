package com.example.libraryapi.customer.entity;

import java.util.ArrayList;
import java.util.List;

import com.example.libraryapi.borrow.entity.Borrow;
import com.example.libraryapi.common.entity.AbstractEntity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "customer")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Customer extends AbstractEntity {
    @Id
    @Column(name = "customer_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String address;

    private String phone;

    @Builder.Default
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "customer", cascade = CascadeType.PERSIST)
    private List<Borrow> borrowList = new ArrayList<>();

}
