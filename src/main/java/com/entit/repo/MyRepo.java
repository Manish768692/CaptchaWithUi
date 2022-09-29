package com.entit.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.entit.model.Product;

public interface MyRepo extends JpaRepository<Product, Integer> {

}
