package com.example.uploadfilesystem.Service;

import com.example.uploadfilesystem.Dao.UpLoadRepository;
import com.example.uploadfilesystem.Entity.ProductInfo;
import com.univocity.parsers.csv.CsvParser;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;


@Service
public class ImplUploadFile implements UpLoadFileService {

    UpLoadRepository upLoadRepository;

    private CsvParser csvParser;

    public ImplUploadFile(UpLoadRepository upLoadRepository, CsvParser csvParser) {
        this.upLoadRepository = upLoadRepository;
        this.csvParser = csvParser;
    }

    @Override
    public String uploadFile(MultipartFile file) throws Exception {
        if (file.isEmpty()) {
            throw new Exception("Please upload a non-empty file.");
        }

        List<ProductInfo> productList = new ArrayList<>();
        long lineNumber = 0;//Used to keep track of the line numbers
        try (InputStreamReader fileReader = new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8)) {
            // Begin parsing the file, which returns a list of Records.
            List<com.univocity.parsers.common.record.Record> records = csvParser.parseAllRecords(fileReader);
            for (com.univocity.parsers.common.record.Record record : records) {
                lineNumber++; // Increment line number for each record
                try {
                    ProductInfo item = getProductInfo(record);
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

    private ProductInfo getProductInfo(com.univocity.parsers.common.record.Record record){
        // Get values from record
        String skuId = record.getString("sku_id");
        String image = record.getString("image");
        String additionalImageLink = record.getString("additional_image_link");
        String styleId = record.getString("style_id");
        String classId = record.getString("class_id");
        String color = record.getString("color");
        String colorCode = record.getString("color_code");
        String colorFamily = record.getString("color_family");
        String size = record.getString("size");
        String sizeId = record.getString("size_id");
        String departmentId = record.getString("department_id");
        String dissectionCode = record.getString("dissection-code");
        String hazmat = record.getString("hazmat");
        String redline = record.getString("redline");
        String promoted = record.getString("promoted");
        String taxCode = record.getString("tax_code");
        String vendor = record.getString("vendor");

        double listPrice = 0.0;
        if(record.getDouble("list_price") != null ){
            listPrice = record.getDouble("list_price");
        }
        double salePrice = 0.0;
        if(record.getDouble("sale_price") != null ){
             salePrice = record.getDouble("sale_price");
        }

        String salePriceEffectiveDate = record.getString("sale_price_effective_date");
        String currency = record.getString("currency");
        String shoprunnerEligible = record.getString("shoprunner_eligible");
        String title = record.getString("title");
        String link = record.getString("link");
        String prodDescription = record.getString("prod_description");
        String startDate = record.getString("start_date");
        String featuredColor = record.getString("featured_color");
        String featuredColorCategory = record.getString("featured_color_category");
        String relatedProducts = record.getString("related_products");
        String preOrder = record.getString("pre_order");
        String handlingCode = record.getString("handling_code");
        String video = record.getString("video");
        String proportion = record.getString("proportion");
        String proportionProducts = record.getString("proportion_products");
        String masterStyle = record.getString("master_style");
        String cannotReturn = record.getString("cannot_return");
        String greatFind = record.getString("great_find");
        String webExclusive = record.getString("web_exclusive");
        String nyDeals = record.getString("ny_deals");
        String promoTagline = record.getString("promo_tagline");
        String partiallyPromoted = record.getString("partially_promoted");
        String productCategory = record.getString("product_category");
        String sortOrder = record.getString("sort_order");
        int quantitySold = record.getInt("quantity_sold");

        double averageRating = 0.0;
        if(record.getDouble("average_rating") != null ){
             averageRating = record.getDouble("average_rating");
        }

        // Create ProductInfo
        ProductInfo product = new ProductInfo();

        product.setSkuId(skuId);
        product.setImage(image);
        product.setAdditionalImageLink(additionalImageLink);
        product.setStyleId(styleId);
        product.setClassId(classId);
        product.setColor(color);
        product.setColorCode(colorCode);
        product.setColorFamily(colorFamily);
        product.setSize(size);
        product.setSizeId(sizeId);
        product.setDepartmentId(departmentId);
        product.setDissectionCode(dissectionCode);
        product.setHazmat(hazmat);
        product.setRedline(redline);
        product.setPromoted(promoted);
        product.setTaxCode(taxCode);
        product.setVendor(vendor);
        product.setListPrice(listPrice);
        product.setSalePrice(salePrice);
        product.setSalePriceEffectiveDate(salePriceEffectiveDate);
        product.setCurrency(currency);
        product.setShoprunnerEligible(shoprunnerEligible);
        product.setTitle(title);
        product.setLink(link);
        product.setProdDescription(prodDescription);
        product.setStartDate(startDate);
        product.setFeaturedColor(featuredColor);
        product.setFeaturedColorCategory(featuredColorCategory);
        product.setRelatedProducts(relatedProducts);
        product.setPreOrder(preOrder);
        product.setHandlingCode(handlingCode);
        product.setVideo(video);
        product.setProportion(proportion);
        product.setProportionProducts(proportionProducts);
        product.setMasterStyle(masterStyle);
        product.setCannotReturn(cannotReturn);
        product.setGreatFind(greatFind);
        product.setWebExclusive(webExclusive);
        product.setNyDeals(nyDeals);
        product.setPromoTagline(promoTagline);
        product.setPartiallyPromoted(partiallyPromoted);
        product.setProductCategory(productCategory);
        product.setSortOrder(sortOrder);
        product.setQuantitySold(quantitySold);
        product.setAverageRating(averageRating);

        return product;
    }
}