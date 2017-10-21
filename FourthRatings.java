/**
 * Write a description of SecondRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class FourthRatings {
    
    private ArrayList<Rater> myRaters;
    private HashMap<String,Double> mySimRatings;
        
    private double getAverageByID( String id , int minimalRaters ){
        int raterCount = 0;
        double sum = 0.0;
        ArrayList<Rater> myRaters = new ArrayList<Rater>(RaterDatabase.getRaters());
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
    
    private double dotProduct( Rater me , Rater r ){
        ArrayList<String> listr = new ArrayList<String>();
        listr = r.getItemsRated();
        double meRating,rRating,sum = 0;
        
        for ( String temp : listr ){
            if ( me.hasRating(temp) ){
                meRating = me.getRating(temp) - 5;
                rRating = r.getRating(temp) - 5;
                sum = sum + rRating*meRating;
            }
        }
        return sum;
    }
    
    private ArrayList<Rating> getSimilarities ( String id ){
        ArrayList<Rater> raters = new ArrayList<Rater>();
        ArrayList<Rating> list = new ArrayList<Rating>();
        raters = RaterDatabase.getRaters();
        double simRating;
        for ( Rater temp : raters ){
            if ( !temp.getID().equals(id)){
                simRating = dotProduct(RaterDatabase.getRater(id),temp);
                if ( simRating > 0 ){
                    list.add(new Rating(temp.getID(),simRating));
                }
            }
        }
        
        Collections.sort(list,Collections.reverseOrder());
        return list;
    }
    
    public ArrayList<Rating> getSimiliarRatings(String id,int numSimilarRaters, int minimalRaters){
        ArrayList<Rating> simRatings = new ArrayList<Rating>();
        simRatings = getSimilarities(id);
        ArrayList<String> myMovies = MovieDatabase.filterBy(new TrueFilter());
        ArrayList<Rating> list = new ArrayList<Rating>();
        myRaters = new ArrayList<Rater>();
        mySimRatings = new HashMap<String,Double>(); 
        
        for ( int i = 0 ; i < numSimilarRaters ; i++ ){
            myRaters.add(RaterDatabase.getRater(simRatings.get(i).getItem()));
            //System.out.println("HTYH");
            mySimRatings.put(simRatings.get(i).getItem(),simRatings.get(i).getValue());
        }
        
        /*for ( String temp : mySimRatings.keySet() ){
            System.out.println(temp + " " + mySimRatings.get(temp));
        }*/
        
        for ( String movID : myMovies ){
            double avg = getWeightedAverageByID(movID,minimalRaters);
            if ( avg != 0.0 ){
                Rating temp = new Rating(movID,avg);
                list.add(temp);
            }
        }
        
        Collections.sort(list,Collections.reverseOrder());
        return list;        
    }
    
    public ArrayList<Rating> getSimiliarRatingsByFilter(String id,int numSimilarRaters, int minimalRaters , Filter category){
        ArrayList<Rating> simRatings = new ArrayList<Rating>();
        simRatings = getSimilarities(id);
        ArrayList<String> myMovies = MovieDatabase.filterBy(category);
        ArrayList<Rating> list = new ArrayList<Rating>();
        myRaters = new ArrayList<Rater>();
        mySimRatings = new HashMap<String,Double>(); 
        
        for ( int i = 0 ; i < numSimilarRaters ; i++ ){
            myRaters.add(RaterDatabase.getRater(simRatings.get(i).getItem()));
            //System.out.println("HTYH");
            mySimRatings.put(simRatings.get(i).getItem(),simRatings.get(i).getValue());
        }
        
        /*for ( String temp : mySimRatings.keySet() ){
            System.out.println(temp + " " + mySimRatings.get(temp));
        }*/
        
        for ( String movID : myMovies ){
            double avg = getWeightedAverageByID(movID,minimalRaters);
            if ( avg != 0.0 ){
                Rating temp = new Rating(movID,avg);
                list.add(temp);
            }
        }
        
        Collections.sort(list,Collections.reverseOrder());
        return list;        
    }
    
    private double getWeightedAverageByID( String id , int minimalRaters ){
        int raterCount = 0;
        double sum = 0.0;
        myRaters.clear();
        myRaters = RaterDatabase.getRaters();
        for ( Rater temp : myRaters ){
            if ( temp.getRating(id)!= -1.0 && mySimRatings.get(temp.getID())!= null){
                //System.out.println(temp.getID());
                sum = sum + (temp.getRating(id) * mySimRatings.get(temp.getID()));
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
    
    
}

