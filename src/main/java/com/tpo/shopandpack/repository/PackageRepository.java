package com.tpo.shopandpack.repository;

import com.tpo.shopandpack.model.Pack;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PackageRepository extends JpaRepository<Pack, Long>{}