import java.util.*;
/**
 * Write a description of MovieRunnerSimiliarRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MovieRunnerSimiliarRatings {
    public void printAverageRatings(){
        FourthRatings object = new FourthRatings();
        ArrayList<Rating> list = new ArrayList<Rating>();
        
        int minimalRaters = 35;
        RaterDatabase.initialize("ratings.csv");
        System.out.println("read data for " + RaterDatabase.size() + " raters");
        //System.out.println("Total number of movies = " + object.getMovieSize());
        //System.out.println("Total number of raters = " + object.getRaterSize());
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("read data for " + MovieDatabase.size() + " movies");
        
        list = object.getAverageRatings(minimalRaters);
        Collections.sort(list);
        
        System.out.println("found " + list.size() + " movies");
        for ( Rating temp: list ){
            System.out.println( temp.getValue() + " " + MovieDatabase.getTitle(temp.getItem()));
        }
    }
    
    public void printAverageRatingsByYearAfterAndGenre(){
        FourthRatings object = new FourthRatings();
        ArrayList<Rating> list = new ArrayList<Rating>();
       
        int minimalRaters = 8;
        int year = 1990;
        String genre = "Drama";
        
        AllFilters af = new AllFilters();
       
        af.addFilter(new GenreFilter(genre));
        af.addFilter(new YearAfterFilter(year));
        
        RaterDatabase.initialize("ratings.csv");
        System.out.println("read data for " + RaterDatabase.size() + " raters");
        //System.out.println("Total number of movies = " + object.getMovieSize());
        //System.out.println("Total number of raters = " + object.getRaterSize());
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("read data for " + MovieDatabase.size() + " movies");
        
        list = object.getAverageRatingsByFilter(minimalRaters,af);
        Collections.sort(list);
        
        System.out.println("found " + list.size() + " movies");
        for ( Rating temp: list ){
            System.out.println( temp.getValue() + " " + MovieDatabase.getYear(temp.getItem())
                                 + " " + MovieDatabase.getTitle(temp.getItem()));
            System.out.println( "    " + MovieDatabase.getGenres(temp.getItem()));                    
        }
    }
    
    public void printSimilarRatings(){
        FourthRatings object = new FourthRatings();
        String ID = "337";
        int minimalRaters = 3;
        int topSimilarRaters = 10;
        ArrayList<Rating> list = new ArrayList<Rating>();
        
        RaterDatabase.initialize("ratings.csv");
        System.out.println("read data for " + RaterDatabase.size() + " raters");
       
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("read data for " + MovieDatabase.size() + " movies");
        
        list = object.getSimiliarRatings(ID, topSimilarRaters, minimalRaters);
        
        for ( Rating temp : list ){
            System.out.println(temp.getValue() + " " + MovieDatabase.getTitle(temp.getItem()));
        }
    }
    
    public void printSimilarRatingsByGenre(){
        FourthRatings object = new FourthRatings();
        String ID = "964";
        int minimalRaters = 5;
        int topSimilarRaters = 20;
        ArrayList<Rating> list = new ArrayList<Rating>();
        String genre = "Mystery" ;
        GenreFilter gf = new GenreFilter(genre);
        
        RaterDatabase.initialize("ratings.csv");
        System.out.println("read data for " + RaterDatabase.size() + " raters");
       
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("read data for " + MovieDatabase.size() + " movies");
        
        System.out.println("                 ***     ");
        
        list = object.getSimiliarRatingsByFilter(ID, topSimilarRaters, minimalRaters,gf);
        
        for ( Rating temp : list ){
            System.out.println(temp.getValue() + " " + MovieDatabase.getTitle(temp.getItem()));
        }
    }
    
    public void printSimilarRatingsByDirector(){
        FourthRatings object = new FourthRatings();
        String ID = "120";
        int minimalRaters = 2;
        int topSimilarRaters = 10;
        ArrayList<Rating> list = new ArrayList<Rating>();
        String dir = "Clint Eastwood,J.J. Abrams,Alfred Hitchcock,Sydney Pollack,David Cronenberg,Oliver Stone,Mike Leigh" ;
        DirectorFilter df = new DirectorFilter(dir);
        
        RaterDatabase.initialize("ratings.csv");
        System.out.println("read data for " + RaterDatabase.size() + " raters");
       
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("read data for " + MovieDatabase.size() + " movies");
        
        System.out.println("                 ***     ");
        
        list = object.getSimiliarRatingsByFilter(ID, topSimilarRaters, minimalRaters,df);
        
        for ( Rating temp : list ){
            System.out.println(temp.getValue() + " " + MovieDatabase.getTitle(temp.getItem()));
        }
    }
    
    public void printSimilarRatingsByGenreAndMinutes(){
        FourthRatings object = new FourthRatings();
        String ID = "168";
        int minimalRaters = 3;
        int topSimilarRaters = 10;
        ArrayList<Rating> list = new ArrayList<Rating>();
        
        int min = 80;
        int max = 160;
        String genre = "Drama";
        
        AllFilters af = new AllFilters();
       
        af.addFilter(new GenreFilter(genre));
        af.addFilter(new MinutesFilter(min,max));
        
        RaterDatabase.initialize("ratings.csv");
        System.out.println("read data for " + RaterDatabase.size() + " raters");
       
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("read data for " + MovieDatabase.size() + " movies");
        
        System.out.println("                 ***     ");
        
        list = object.getSimiliarRatingsByFilter(ID, topSimilarRaters, minimalRaters,af);
        
        for ( Rating temp : list ){
            System.out.println(temp.getValue() + " " + MovieDatabase.getTitle(temp.getItem()));
        }
    }
    
    
    
    public void printSimilarRatingsByYearAfterAndMinutes(){
        FourthRatings object = new FourthRatings();
        String ID = "314";
        int minimalRaters = 5;
        int topSimilarRaters = 10;
        ArrayList<Rating> list = new ArrayList<Rating>();
        
        int min = 70;
        int max = 200;
        int year = 1975;
        
        AllFilters af = new AllFilters();
       
        af.addFilter(new YearAfterFilter(year));
        af.addFilter(new MinutesFilter(min,max));
        
        RaterDatabase.initialize("ratings.csv");
        System.out.println("read data for " + RaterDatabase.size() + " raters");
       
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("read data for " + MovieDatabase.size() + " movies");
        
        System.out.println("                 ***     ");
        
        list = object.getSimiliarRatingsByFilter(ID, topSimilarRaters, minimalRaters,af);
        
        for ( Rating temp : list ){
            System.out.println(temp.getValue() + " " + MovieDatabase.getTitle(temp.getItem()));
        }
    }
}
