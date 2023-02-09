package dev.lili.movies;

import ch.qos.logback.core.net.SyslogOutputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.server.Http2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController //important
@RequestMapping("/api/v1/reviews") //of course important
//the review form will be inside a movies details page or in a pager where you are viewing a single movie, so wie can make the requests to the moviews end point instead of creating a new reviews endpoint. But you can handle that in every project differnetly
public class ReviewController {
    @Autowired
    private ReviewService reviewService;

    @GetMapping
    public ResponseEntity<List<Review>> getAllReviews(){
        return new ResponseEntity<List<Review>>(reviewService.allReviews(), HttpStatus.OK); //OK means 200, any REST API should return proper status codes
    }

    @PostMapping
    public ResponseEntity<Review> createReview(@RequestBody Map<String, String> payload){ //we say to the framework here, that whatever we get as the rquest body, we would like to convert it to a map of the key: String and value: String. We want to name this map payload.
        System.out.println("Größe der Map: " + payload.size());
        System.out.println("reviewBody: " + payload.get("reviewBody"));
        System.out.println("imdbId: " + payload.get("imdbId"));

        return new ResponseEntity<Review>(reviewService.createReview(payload.get("reviewBody"), payload.get("imdbId")), HttpStatus.CREATED);

    }
}
