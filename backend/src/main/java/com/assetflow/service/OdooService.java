package com.assetflow.service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assetflow.model.Asset;
import com.assetflow.repository.AssetRepository;

@Service
public class OdooService {

    @Autowired
    private AssetRepository assetRepo;

    // Simulates fetching assets from Odoo
    public List<Asset> fetchFromOdoo() {
        Asset dummy1 = new Asset();
        dummy1.setName("Odoo Laptop");
        dummy1.setSerialNumber("ODOO-001");
        dummy1.setModel("Dell XPS");
        dummy1.setStatus("In Stock");
        dummy1.setCreatedAt(LocalDateTime.now());
        dummy1.setUpdatedAt(LocalDateTime.now());

        Asset dummy2 = new Asset();
        dummy2.setName("Odoo Monitor");
        dummy2.setSerialNumber("ODOO-002");
        dummy2.setModel("Samsung 27\"");
        dummy2.setStatus("Available");
        dummy2.setCreatedAt(LocalDateTime.now());
        dummy2.setUpdatedAt(LocalDateTime.now());

        return Arrays.asList(dummy1, dummy2);
    }

    public String syncAssets() {
        List<Asset> assets = fetchFromOdoo();
        assetRepo.saveAll(assets);
        return "Synced " + assets.size() + " assets from Odoo (simulated)";
    }
}