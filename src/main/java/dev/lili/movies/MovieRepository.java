package dev.lili.movies;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

//Repository class is type interface, extends the MongoRepository, we need to let it know which type of data we are using and what type of ID we are using.

@Repository // so that the framework knows this is a repository
public interface MovieRepository extends MongoRepository<Movie, ObjectId> {
    Optional<Movie> findMovieByImdbId(String imdbId);//optional because it may return null.
    // just by naming this method findMovieByImdbID Spring MongoDB will understand what we want to do.
    // you can search like that for every movie, as long as it is unique. Otherwise you will get back many movies.

}
