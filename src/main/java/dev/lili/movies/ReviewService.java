package dev.lili.movies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service //as this will be a Service- Class.
public class ReviewService {
    @Autowired //dann muss das unten nicht instanziiert werden...
    private ReviewRepository reviewRepository;// we need a reference to our

    @Autowired //dann muss das unten nicht instanziiert werden...
    private MongoTemplate mongoTemplate; //Besides Repositories, there are also Templates to talk to the database. There are times when a Reposiroty is not enough, for example when there are operations so complex, that it cannot implemented within a repository. Or even when you can implement it within a repository, it won't be suitable. => Template: we can use a template to form up a new dynamic query and do the job inside the database without using the repository.

    public List<Review> allReviews(){
            return reviewRepository.findAll();
    }
    public Review createReview(String reviewBody, String imdbId){ //we want to create a new Review and put it to the Movie with that imdbId
        System.out.println("Bis her gekommen in Classe ReviewService");
        Review review = reviewRepository.insert(new Review(reviewBody, LocalDateTime.now(), LocalDateTime.now()));

        System.out.println("Neues Review erstellt mit reviewBody: " + reviewBody + "und imdbId: " + imdbId);

        mongoTemplate.update(Movie.class)
                .matching(Criteria.where("imdbId").is(imdbId)) //key must match with the column name inside the database
                .apply(new Update().push("reviews").value(review))
                .first();
        long count = reviewRepository.count();

        System.out.println("Code ist durch gekommen. Erstellte Reviews sind: " + count);
        // we are using the template to perform an update call on the Movie.class, as each Movie contains an empty Array of reviewIds. => we need to update the reviewIds - Array and push a new reviewId in it.
        // then we have to perfom the matching, to say which movie we are updating. => we are updating a Movie where the imdbId in the database matches the imdbId we received from the user.
        // then we want to apply that update by calling .apply and we create a new Update-Definition which does the job of making the change inside the database. we say .push inside the reviewIds, as we want to update the reviewIds of the found movie, and we give the value of the new created review.
        // we have to say .first() to make sure that we are getting a single movie and we are updating that.
    return review;
    }
}
