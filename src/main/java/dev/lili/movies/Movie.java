package dev.lili.movies;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.List;
//wichtig: diese Klasse muss die Attribute + Namen genau abbilden wie in der MongoDB!! Sonst gibt es MappingExpeption!

@Document(collection = "movies") // means each Document will represent one Movie in the collection.
@Data //comes from lombok and takes care of all the getter, setters methods
@AllArgsConstructor //a construktor that takes all the private attributes.
@NoArgsConstructor //another contsructor that takes no attribiutes
public class Movie {
    @Id //lets the framework know that this Attribute should be treated as the unique ID for each Movie inside the database
    private ObjectId id;
    private String imdbId;
    private String title;
    private String releaseDate;
    private String trailerLink;
    private String poster;
    private List<String> backdrops;
    private List<String> genres;
    @DocumentReference // this will cause the database to only store the ids of the reviews and the reviews will be in a seperate collection. this is called "manual reference relationship"
    private List<Review> reviews;

    public Movie(String imdbId, String title, String releaseDate, String trailerLink, String poster, List<String> backdrops, List<String> genres) {
        this.imdbId = imdbId;
        this.title = title;
        this.releaseDate = releaseDate;
        this.trailerLink = trailerLink;
        this.poster = poster;
        this.backdrops = backdrops;
        this.genres = genres;
    }
}
