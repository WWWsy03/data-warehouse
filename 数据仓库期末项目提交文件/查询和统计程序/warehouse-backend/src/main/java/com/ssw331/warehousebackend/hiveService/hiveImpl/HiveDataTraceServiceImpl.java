package com.ssw331.warehousebackend.hiveService.hiveImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ssw331.warehousebackend.MySQLDTO.Movie;
import com.ssw331.warehousebackend.MySQLDTO.MovieProduct;
import com.ssw331.warehousebackend.MySQLDTO.Product;
import com.ssw331.warehousebackend.hiveService.HiveDataTraceService;
import com.ssw331.warehousebackend.mapper.MovieMapper;
import com.ssw331.warehousebackend.mapper.MovieProductMapper;
import com.ssw331.warehousebackend.mapper.ProductMapper;
import com.ssw331.warehousebackend.service.DataTraceService;
import com.ssw331.warehousebackend.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class HiveDataTraceServiceImpl implements HiveDataTraceService {
    @Autowired
    MovieService movieService;
    @Autowired
    MovieMapper movieMapper;
    @Autowired
    MovieProductMapper movieProductMapper;
    @Autowired
    ProductMapper productMapper;
    @Override
    public int getNonMovieCounts() {
        return 253059-169573;
    }

    @Override
    public int getNumberOfHarryMovies() {

        return 8;
    }

    @Override
    public int getNumberOfHarryVersions() {
        QueryWrapper<Movie> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("movie_name", "Harry Potter ");
        List<Movie> movies = movieMapper.selectList(queryWrapper);
        List<Product> products = new ArrayList<>();
        for (Movie movie : movies) {
            QueryWrapper<MovieProduct> queryWrapper2 = new QueryWrapper<>();
            queryWrapper2.eq("movie_id", movie.getMovieId());
            List<MovieProduct> movieProducts = movieProductMapper.selectList(queryWrapper2);
            for (MovieProduct movieProduct : movieProducts) {
                Product product = productMapper.selectById(movieProduct.getProductId().substring(0, movieProduct.getProductId().length() - 1));
                if (product != null) {
                    products.add(product);
                }
            }
        }
        return products.size();
    }


    @Override
    public int getNumberOfMergedWebpages() {
        QueryWrapper<Movie> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("movie_name", "Harry Potter and the Sorcerer's Stone");
        List<Movie> movies = movieMapper.selectList(queryWrapper);
        List<Product> products = new ArrayList<>();
        for (Movie movie : movies) {
            QueryWrapper<MovieProduct> queryWrapper2 = new QueryWrapper<>();
            queryWrapper2.eq("movie_id", movie.getMovieId());
            List<MovieProduct> movieProducts = movieProductMapper.selectList(queryWrapper2);
            for (MovieProduct movieProduct : movieProducts) {
                Product product = productMapper.selectById(movieProduct.getProductId().substring(0, movieProduct.getProductId().length() - 1));
                if (product != null) {
                    products.add(product);
                }
            }
        }
        return products.size();
    }

}
