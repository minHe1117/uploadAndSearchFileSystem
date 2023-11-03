package com.example.uploadfilesystem.Service;

import com.example.uploadfilesystem.Dao.UpLoadRepository;
import com.example.uploadfilesystem.Entity.Upload;
import com.univocity.parsers.csv.CsvParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;


@Service
public class ImplUploadFile implements UpLoadFileService {

    @Autowired
    UpLoadRepository upLoadRepository;

    @Autowired
    private CsvParser csvParser;

    @Override
    public String uploadFile(MultipartFile file) throws Exception {
        if (file.isEmpty()) {
            throw new Exception("Please upload a non-empty file.");
        }

        List<Upload> productList = new ArrayList<>();
        long lineNumber = 0;//Used to keep track of the line numbers
        try (InputStreamReader fileReader = new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8)) {
            // Begin parsing the file, which returns a list of Records.
            List<com.univocity.parsers.common.record.Record> records = csvParser.parseAllRecords(fileReader);
            for (com.univocity.parsers.common.record.Record record : records) {
                lineNumber++; // Increment line number for each record
                try {
                    long skuId = Long.parseLong(record.getString("sku_id")); // This might throw NumberFormatException
                    String color = record.getString("color");
                    String size = record.getString("size");
                    String description = record.getString("title");

                    // Only add the product if sku_id is present and valid
                    Upload item = new Upload(skuId, color, size, description);
                    productList.add(item);
                } catch (NumberFormatException e) {
                    System.err.println("Invalid sku_id at line " + lineNumber + ": " + e.getMessage());
                } catch (IllegalArgumentException e) {
                    System.err.println("Required column missing at line " + lineNumber + ": " + e.getMessage());
                } catch (Exception e) {
                    System.err.println("Error processing record at line " + lineNumber + ": " + e.getMessage());
                }
            }

            if (!productList.isEmpty()) {
                upLoadRepository.saveAll(productList);
            }
        } catch (Exception e) {
            System.err.println("Error reading or parsing TSV file: " + e.getMessage());
            throw e;
        }

        return "Upload file successful，total items: " + lineNumber;
    }
}