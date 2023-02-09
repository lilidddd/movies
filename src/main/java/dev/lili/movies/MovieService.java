package dev.lili.movies;
// this is the service class, it does not extend anything. This class is for Database access methods.

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service // that framework knows this is a Service class.
public class MovieService {
    @Autowired //means we want to let the framework know that the MovieRepository should be instantiated for us by the framework.
    private MovieRepository movieRepository;
    public List<Movie> allMovies() {
       return movieRepository.findAll();
       // return movieRepository.findAll(); //find all is defined in the MongoRepository class, that is extended by the MovieRepository interface we just created. This function will pass the Data we said in the MovieRepository interface, so Movies.
    }
    public Optional<Movie> singleMovie(ObjectId id){ //optional means, that it could be that by that id there is nothing found, and it could also return null.
       return movieRepository.findById(id); // Nach der id des Movie-Objektes suchen.
    }
    public Optional<Movie> singleMovieByImdbId(String id){
        return movieRepository.findMovieByImdbId(id);
    }
}
