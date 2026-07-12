package com.assetflow.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.assetflow.model.Asset;

@Repository
public interface AssetRepository extends JpaRepository<Asset, Long> {
}