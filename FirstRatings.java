import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;
/**
 * Write a description of FirstRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class FirstRatings {
    public ArrayList<Movie> loadMovies(String filename){
        FileResource fr = new FileResource(filename.toString());
        int totCount = 0 ;
        CSVParser parser = fr.getCSVParser(false);
        ArrayList<Movie> mov = new ArrayList<Movie>();
        
        HashMap<String,Integer> DMovies = new HashMap<String,Integer>();
        for (CSVRecord rec : parser){
            if ( totCount == 0 ){
                totCount = 1;
                continue;
            }
            String id = rec.get(0);
            String title = rec.get(1);
            String year = rec.get(2);
            String country = rec.get(3);
            String genre = rec.get(4);
            String director = rec.get(5);
            String min = rec.get(6);
            int minutes = Integer.parseInt(min.trim());
            String poster = rec.get(7);
            Movie movData = new Movie(id,title, year, genre, director, country, poster, minutes);
            
            String[] Dnames = director.split(",");
            for ( int i = 0 ; i < Dnames.length; i ++ ){
                if ( !DMovies.containsKey(Dnames[i])){
                    DMovies.put(Dnames[i],1);
                }
                else{
                    DMovies.put(Dnames[i],DMovies.get(Dnames[i])+1);
                }
            }
            mov.add(movData);
        }
        Directors(DMovies);
        //System.out.println("Length = " + mov.size());
        return mov;
    }
    
    private void Directors(HashMap<String,Integer> DMovies){
        int max = 0;
        String Dirname = " ";
        for ( String dname : DMovies.keySet() ){
            int movCount = DMovies.get(dname);
            if ( movCount > max ){
                max = movCount ;
                Dirname = dname;
            }
        }
        if ( DMovies.containsValue(max)){ 
            //System.out.println("Director with most movies - " + Dirname + " with " + max + " movies");
        }
        else{
            //System.out.println("NULL");
        }        
    }    
    
    public ArrayList<Rater> loadRaters(String filename){
        FileResource fr = new FileResource(filename.toString());
        int totCount = 0 ;
        CSVParser parser = fr.getCSVParser(false);
        ArrayList<Rater> mov = new ArrayList<Rater>();
        
        for (CSVRecord rec : parser){
            if ( totCount == 0 ){
                totCount = 1;
                continue;
            }
            String raterId = rec.get(0);
            String movieId = rec.get(1);
            String rating = rec.get(2);
            //String time = rec.get(3);
            
            double values = Double.parseDouble(rating.trim());        
            
            int flag = -1;
            for ( Rater aisehi : mov ){                
                if ( aisehi.getID().equals(raterId) ){
                    int index = mov.indexOf(aisehi);
                    Rater temp = mov.get(index);
                    //System.out.println("FERGE");
                    mov.remove(index);
                    temp.addRating(movieId , values );
                    mov.add(temp);
                    flag = 0;
                }                
            }
            if (flag == -1){
                Rater newRater = new PlainRater(raterId);
                newRater.addRating(movieId , values );
                mov.add(newRater);
            }
        }
        
        //System.out.println("Total number of raters = " + mov.size());
        //System.out.println("Total number of movies = " + movies.size());
        
        /*for ( Rater temp : mov ){
            System.out.println(temp.getID()+" did "+temp.numRatings()+ " ratings ");
            ArrayList<String> list = new ArrayList<String>();
            list = temp.getItemsRated();
            for(int k=0; k < list.size(); k++){
                System.out.println(list.get(k)+ " " + temp.getRating(list.get(k)));
                
            }
        }*/
        return mov;
    }
    
    public void testLoadMovies(){
        String fName = "ratedmovies_short.csv";
        String genre = "Comedy";
        int min = 150;
        //System.out.println("File is - " + fName);
        fName = "ratedmoviesfull.csv";
        System.out.println("File is - " + fName);
        ArrayList<Movie> movies = loadMovies(fName);
        
        int minBasedMovieCount = 0;
        int genreBasedMovieCount = 0;
        for ( Movie temp : movies ){                
            if ( temp.getMinutes() > min){
                minBasedMovieCount += 1 ;
            }    
            if ( temp.getGenres().contains(genre) ){
                genreBasedMovieCount += 1;
            }
        }
        System.out.println("Total number of movies more than " + min + " mins = " + minBasedMovieCount);
        System.out.println("Total number of movies having genre " + genre + " = " + genreBasedMovieCount);
    }
    
    public void testLoadRater(){
        String fName = "ratings_short.csv";
        String raterId = "193";
        String movie = "1798709";
        //System.out.println("File is - " + fName);
        
        
        fName = "ratings.csv";
        
        System.out.println("File is - " + fName);
        
        ArrayList<Rater> mov = loadRaters(fName);
        
        int movcount = 0;
        for (Rater rec : mov){
            if ( rec.hasRating(movie)){
                movcount = movcount +1 ;
            }
        }
        System.out.println(" ###### ");
        System.out.println("Total number of raters for movie - "+ movie + " = " + movcount);
        
        for ( Rater temp : mov ){                
                if ( temp.getID().equals(raterId) ){
                    System.out.println(temp.getID()+" did "+temp.numRatings()+ " ratings ");
                    
                }                
            }
        System.out.println(" ###### ");
        int max = 0;
        ArrayList<String> raters = new ArrayList<String>();
        for ( Rater temp : mov ){
            int num = temp.numRatings();
            if ( num > max ){
                max = num;
                raters.clear();
            } 
            if ( num == max ){
                raters.add(temp.getID());
            }
        }  
        System.out.println(raters.size() + " raters have " + max + " ratings.");
        for ( String temp : raters ){
            System.out.println("whose RaterID is - " + temp);               
        }
    }
}
