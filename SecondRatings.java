
/**
 * Write a description of SecondRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class SecondRatings {
    private ArrayList<Movie> myMovies;
    private ArrayList<Rater> myRaters;
    
    public SecondRatings() {
        // default constructor
        this("ratedmoviesfull.csv", "ratings.csv");
        //this("ratedmovies_short.csv", "ratings_short.csv");
        //this("12345.csv", "9876.csv");
    }
    
    public SecondRatings(String moviefile , String ratingsfile) {
        FirstRatings object = new FirstRatings();
        myMovies = object.loadMovies(moviefile);
        myRaters = object.loadRaters(ratingsfile);
    }
    
    public int getMovieSize(){
        return myMovies.size();
    }
    
    public int getRaterSize(){
        return myRaters.size();
    }
    
    public double getAverageByID( String id , int minimalRaters ){
        int raterCount = 0;
        double sum = 0.0;
        for ( Rater temp : myRaters ){
            if ( temp.getRating(id)!= -1.0 ){
                sum = sum + temp.getRating(id);
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
        for ( Movie mov : myMovies ){
            double avg = getAverageByID(mov.getID(),minimalRaters);
            if ( avg != 0.0 ){
                Rating temp = new Rating(mov.getID(),avg);
                list.add(temp);
            }
        }
        return list;
    }
    
    public String getTitle(String id){
        for ( Movie mov : myMovies ){
            if ( mov.getID().equals(id)){
                return mov.getTitle();
            }
        }
        return "ID was not found";
    }
    
    public String getID(String title ){
        for ( Movie mov : myMovies ){
            if ( mov.getTitle().equals(title)){
                return mov.getID();
            }
        }
        return "NO SUCH TITLE";
    }
    
}
