package com.example.uploadfilesystem.Dao;

import com.example.uploadfilesystem.Entity.Upload;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UpLoadRepository extends JpaRepository<Upload,Long> {
}
