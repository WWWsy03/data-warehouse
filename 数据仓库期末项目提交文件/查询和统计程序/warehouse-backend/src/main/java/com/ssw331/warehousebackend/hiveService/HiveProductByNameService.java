package com.ssw331.warehousebackend.hiveService;

import com.ssw331.warehousebackend.MySQLDTO.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface HiveProductByNameService {
    List<Product> getProductsByMovieName(String movieName);
}
