package com.ssw331.warehousebackend.hiveService.hiveImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ssw331.warehousebackend.MySQLDTO.Movie;
import com.ssw331.warehousebackend.MySQLDTO.MovieProduct;
import com.ssw331.warehousebackend.MySQLDTO.Product;
import com.ssw331.warehousebackend.hiveService.HiveProductByNameService;
import com.ssw331.warehousebackend.mapper.MovieMapper;
import com.ssw331.warehousebackend.mapper.MovieProductMapper;
import com.ssw331.warehousebackend.mapper.ProductMapper;
import com.ssw331.warehousebackend.service.ProductByNameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class HiveProductByNameServiceImpl implements HiveProductByNameService {
    @Autowired
    private MovieMapper movieMapper;
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private MovieProductMapper movieProductMapper;
    @Override
    public List<Product> getProductsByMovieName(String movieName) {
        QueryWrapper<Movie> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("movie_name", movieName);
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
        return products;
    }
}
