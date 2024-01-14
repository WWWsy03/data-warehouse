package com.ssw331.warehousebackend.service.Impl;

import com.ssw331.warehousebackend.Neo4jDTO.*;
import com.ssw331.warehousebackend.service.repo.ActorRepository;
import com.ssw331.warehousebackend.service.repo.DirectorRepository;
import com.ssw331.warehousebackend.service.repo.MovieRepository;
import com.ssw331.warehousebackend.service.Neo4jService;
import com.ssw331.warehousebackend.service.repo.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Neo4jServiceImpl implements Neo4jService {

    MovieRepository movieRepository;

    ProductRepository productRepository;

    ActorRepository actorRepository;

    DirectorRepository directorRepository;

    @Autowired
    private void setMovieRepository(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Autowired
    private void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Autowired
    private void setActorRepository(ActorRepository actorRepository) {
        this.actorRepository = actorRepository;
    }

    @Autowired
    private void setDirectorRepository(DirectorRepository directorRepository) {
        this.directorRepository = directorRepository;
    }

    /**
     *
     * @param name 电影名称
     * @return 包含此电影名称的电影
     */
    @Override
    public List<Product> searchMoviesByName(String name) {
        return productRepository.findProductsByMovieName(name);
    }

    /**
     *
     * @param actorName 演员名称
     * @return 此演员参演的电影
     */
    @Override
    public List<String> searchMoviesByActor(String actorName) {
        return movieRepository.findMoviesByActor(actorName);
    }

    /**
     *
     * @param directorName 导演名称
     * @return 此导演导演的电影
     */
    @Override
    public List<String> searchMoviesByDirector(String directorName) {
        return movieRepository.findMoviesByDirector(directorName);
    }
    @Override
    public List<String> searchActorByDirector(String directorName) {
        return actorRepository.findActorWithDirector(directorName);
    }
    /**
     *
     * @param directorName 导演名称
     * @return 与该导演合作过的导演
     */
    @Override
    public List<String> searchDirectorByDirector(String directorName) {
        return directorRepository.findDirectorsByDirectorName(directorName);
    }

    /**
     *
     * @param actorName 演员名称
     * @return 和该演员合作过的演员
     */
    @Override
    public List<String> searchActorByActor(String actorName) {
        return actorRepository.findActorsByActorName(actorName);
    }

    /**
     *
     * @param actorName 演员名称
     * @return 和该演员合作的导演
     */
    @Override
    public List<String> searchDirectorByActor(String actorName) {
        return directorRepository.findDirectorsWithActor(actorName);
    }

    /**
     *
     * @param category 类别
     * @return 在这一类别下的电影
     */
    @Override
    public List<String> searchMoviesByCategory(String category) {
        List<Movie> movies = movieRepository.findMoviesByTypeContaining(category);
        List<String> mNames = new ArrayList<>();
        movies.forEach((e) -> mNames.add(e.getMovieName()));
        return mNames;
    }

    /**
     *
     * @param grade 评分
     * @return 高于此评分的电影
     */
    @Override
    public List<String> searchMoviesByGradeBetter(double grade) {
        return movieRepository.findWithGrade(grade);
    }


    /**
     *
     * @return 评分高于4.0的电影
     */
    @Override
    public List<String> searchMoviesByReviewPositive() {
        return movieRepository.findWithGrade(4.0);
    }

    /**
     *
     * @return 合作次数最多的导演与演员
     */
    @Override
    public List<Collaboration_DA> searchCollaborationInDA() {
        List<Collaboration_DA> res = actorRepository.findCollaborationBTDA();
        res.remove(0);
        return res;
    }

    /**
     *
     * @return 合作次数最多的导演们
     */
    @Override
    public List<Collaboration_DD> searchCollaborationInDD() {
        return directorRepository.findCollaborationBTDD();
    }

    /**
     *
     * @return 合作次数最多的演员们
     */
    @Override
    public List<Collaboration_AA> searchCollaborationInAA() {
        return actorRepository.findCollaborationBTAA();
    }

    /**
     *
     * @return 热度最高的电影的演员
     */
    @Override
    public List<ReviewMax_AA> searchByReviewAA(String type) {
        return actorRepository.findActorsWithReviewsAndType(type);
    }

    /**
     *
     * @param year 年份 yyyy
     * @param type 类别
     * @return 在这一年内发布的该类别下的电影
     */
    @Override
    public int searchMoviesByYearType(int year, String type) {
        return movieRepository.countMoviesByTypeContainingAndReleaseTimeContaining(type, Integer.toString(year));
    }

    /**
     *
     * @param year 年份 yyyy
     * @param directorName 导演名称
     * @return 在这一年内由该导演导演的电影
     */
    @Override
    public int searchMoviesByYearDirector(int year, String directorName) {
        return movieRepository.countMoviesWithYearAndDirector(Integer.toString(year), directorName);
    }
}
