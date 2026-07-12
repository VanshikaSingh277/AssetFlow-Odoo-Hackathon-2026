package com.assetflow.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.assetflow.model.Asset;
import com.assetflow.repository.AssetRepository;

@RestController
@RequestMapping("/api/assets")
public class AssetController {

    @Autowired
    private AssetRepository assetRepo;

    @GetMapping
    public List<Asset> getAll() {
        return assetRepo.findAll();
    }

    @GetMapping("/{id}")
    public Asset getOne(@PathVariable Long id) {
        return assetRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Asset not found"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Asset create(@RequestBody Asset asset) {
        asset.setCreatedAt(LocalDateTime.now());
        asset.setUpdatedAt(LocalDateTime.now());
        return assetRepo.save(asset);
    }

    @PutMapping("/{id}")
    public Asset update(@PathVariable Long id, @RequestBody Asset updated) {
        Asset existing = assetRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Asset not found"));
        existing.setName(updated.getName());
        existing.setSerialNumber(updated.getSerialNumber());
        existing.setModel(updated.getModel());
        existing.setStatus(updated.getStatus());
        existing.setUpdatedAt(LocalDateTime.now());
        return assetRepo.save(existing);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        assetRepo.deleteById(id);
    }
}