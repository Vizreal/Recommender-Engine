import java.util.*;
import edu.duke.*;
/**
 * Write a description of MovieRunnerAverage here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MovieRunnerAverage {
    public void printAverageRatings(){
        SecondRatings object = new SecondRatings();
        ArrayList<Rating> list = new ArrayList<Rating>();
        int minimalRaters = 12;
        
        //System.out.println("Total number of movies = " + object.getMovieSize());
        //System.out.println("Total number of raters = " + object.getRaterSize());
        
        list = object.getAverageRatings(minimalRaters);
        double min = 11.0;
        int minInd = 0;
        
        Collections.sort(list);
        
        System.out.println("Total number of raters = " + list.size());
        for ( Rating temp: list ){
            System.out.println( temp.getValue() + " " + object.getTitle(temp.getItem()));
        }
    }
    
    public void getAverageRatingOneMovie(){
        SecondRatings object = new SecondRatings();
        //String Movie = "No Country for Old Men";
        //String Movie = "Inside Llewyn Davis";
        //String Movie = "The Maze Runner";
        //String Movie = "Moneyball";
        String Movie = "Vacation";
        
        
        System.out.println("\"" + Movie + "\" has rating " + object.getAverageByID(object.getID(Movie),0));
    }
}
