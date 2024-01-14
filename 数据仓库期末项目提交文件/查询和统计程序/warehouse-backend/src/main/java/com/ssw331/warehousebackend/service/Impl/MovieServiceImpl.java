package com.ssw331.warehousebackend.service.Impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ssw331.warehousebackend.MySQLDTO.Movie;
import com.ssw331.warehousebackend.MySQLDTO.MovieProduct;
import com.ssw331.warehousebackend.MySQLDTO.Product;
import com.ssw331.warehousebackend.mapper.MovieMapper;
import com.ssw331.warehousebackend.mapper.MovieProductMapper;
import com.ssw331.warehousebackend.mapper.ProductMapper;
import com.ssw331.warehousebackend.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieMapper movieMapper;
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private MovieProductMapper movieProductMapper;

    @Override
    public List<Movie> getMoviesWithHighGradeProducts() {
// 获取所有评分大于 4.9 的产品
        List<Product> allProducts = productMapper.selectList(new QueryWrapper<Product>().gt("grade", 4.9));

        // 进一步筛选出评论数量大于 100 的产品
        List<Product> filteredProducts = allProducts.stream()
                .filter(p -> p.getCommentNumber() > 990)
                .collect(Collectors.toList());

        System.out.println(filteredProducts.size());


        List<Movie> movies = new ArrayList<>();



        for (Product product : filteredProducts) {
            String productId = product.getProductId().replace("\r", "");
            // 获取MovieProduct表中所有记录并清理product_id字段
            List<MovieProduct> allMovieProducts = movieProductMapper.selectList(new QueryWrapper<>());
            List<MovieProduct> cleanedMovieProducts = allMovieProducts.stream()
                    .filter(mp -> mp.getProductId().replace("\r", "").equals(productId))
                    .collect(Collectors.toList());
            for (MovieProduct movieProduct : cleanedMovieProducts) {
                Movie movie = movieMapper.selectById(movieProduct.getMovieId());
                if (movie != null && !movies.contains(movie)) {
                    movies.add(movie);
                }
            }
        }

        return movies;
    }

    @Override
    public List<String> getMoviesByType(String type) {
        return movieMapper.findMoviesByType(type);
    }

    @Override
    public int countMoviesByYearAndType(int year, String type) {
        return movieMapper.countMoviesByYearAndType(year, type);
    }

    @Override
    public int countMoviesByYearAndDirectorName(int year, String directorName) {
        return movieMapper.countMoviesByYearAndDirectorName(year,directorName);
    }
}
