/**
 * Write a description of SecondRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class ThirdRatings {
    private ArrayList<Rater> myRaters;
    
    public ThirdRatings() {
        // default constructor
        this("ratings.csv");
        //this("ratedmovies_short.csv", "ratings_short.csv");
        //this("12345.csv", "9876.csv");
    }
    
    public ThirdRatings(String ratingsfile) {
        FirstRatings object = new FirstRatings();
        myRaters = object.loadRaters(ratingsfile);
    }
    
    public int getRaterSize(){
        return myRaters.size();
    }
    
    public double getAverageByID( String movID , int minimalRaters ){
        int raterCount = 0;
        double sum = 0.0;
        for ( Rater temp : myRaters ){
            if ( temp.getRating(movID)!= -1.0 ){
                sum = sum + temp.getRating(movID);
                //System.out.println("Sum = " + sum);
                raterCount += 1 ;
                //System.out.println("RaterCount = " + raterCount);
            }
        }
        if ( raterCount >= minimalRaters ){
            return sum/raterCount;
        }
        return 0.0;
    }
    
    /*public void tester(){
        for ( Movie mov : myMovies){
            System.out.println(mov.getTitle()+ " " + getAverageByID(mov.getID(),3));
        }
    }*/
       
    public ArrayList<Rating> getAverageRatings( int minimalRaters){
        ArrayList<Rating> list = new ArrayList<Rating>();
        ArrayList<String> myMovies = MovieDatabase.filterBy(new TrueFilter());
        for ( String mov : myMovies ){
            double avg = getAverageByID(mov,minimalRaters);
            if ( avg != 0.0 ){
                Rating temp = new Rating(mov,avg);
                list.add(temp);
            }
        }
        return list;
    }
    
    public ArrayList<Rating> getAverageRatingsByFilter( int minimalRaters , Filter Criteria){
        ArrayList<Rating> list = new ArrayList<Rating>();
        ArrayList<String> myMovies = MovieDatabase.filterBy(Criteria);
        for ( String mov : myMovies ){
            double avg = getAverageByID(mov,minimalRaters);
            if ( avg != 0.0 ){
                Rating temp = new Rating(mov,avg);
                list.add(temp);
            }
        }
        return list;
    }
    
}

