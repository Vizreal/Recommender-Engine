import java.util.*;

/**
 * Write a description of MovieRunnerWithFilters here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MovieRunnerWithFilters {
    public void printAverageRatings(){
        ThirdRatings object = new ThirdRatings();
        ArrayList<Rating> list = new ArrayList<Rating>();
        
        int minimalRaters = 35;
        
        System.out.println("read data for " + object.getRaterSize() + " raters");
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
    
    public void printAverageRatingsByYear(){
        ThirdRatings object = new ThirdRatings();
        ArrayList<Rating> list = new ArrayList<Rating>();
       
        int minimalRaters = 20;
        int year = 2000;
        
        YearAfterFilter yaf = new YearAfterFilter(year);
        
        
        System.out.println("read data for " + object.getRaterSize() + " raters");
        //System.out.println("Total number of movies = " + object.getMovieSize());
        //System.out.println("Total number of raters = " + object.getRaterSize());
        MovieDatabase.initialize("ratedmovies_short.csv");
        System.out.println("read data for " + MovieDatabase.size() + " movies");
        
        list = object.getAverageRatingsByFilter(minimalRaters,yaf);
        Collections.sort(list);
        
        System.out.println("found " + list.size() + " movies");
        for ( Rating temp: list ){
            System.out.println( temp.getValue() + " " + MovieDatabase.getYear(temp.getItem())
                                + " " + MovieDatabase.getTitle(temp.getItem()));
        }
    }
    
    public void printAverageRatingsByGenre(){
        ThirdRatings object = new ThirdRatings();
        ArrayList<Rating> list = new ArrayList<Rating>();
       
        int minimalRaters = 20;
        String genre = "Comedy";
        
        GenreFilter gf = new GenreFilter(genre);
        
        
        System.out.println("read data for " + object.getRaterSize() + " raters");
        //System.out.println("Total number of movies = " + object.getMovieSize());
        //System.out.println("Total number of raters = " + object.getRaterSize());
        MovieDatabase.initialize("ratedmovies_short.csv");
        System.out.println("read data for " + MovieDatabase.size() + " movies");
        
        list = object.getAverageRatingsByFilter(minimalRaters,gf);
        Collections.sort(list);
        
        System.out.println("found " + list.size() + " movies");
        for ( Rating temp: list ){
            System.out.println( temp.getValue() + " " + MovieDatabase.getTitle(temp.getItem()));
            System.out.println( "    " + MovieDatabase.getGenres(temp.getItem()));                    
        }
    }
    
    public void printAverageRatingsByMinutes(){
        ThirdRatings object = new ThirdRatings();
        ArrayList<Rating> list = new ArrayList<Rating>();
       
        int minimalRaters = 5;
        int minf = 105;
        int maxf = 135;
        
        MinutesFilter mf = new MinutesFilter(minf,maxf);
        
        
        System.out.println("read data for " + object.getRaterSize() + " raters");
        //System.out.println("Total number of movies = " + object.getMovieSize());
        //System.out.println("Total number of raters = " + object.getRaterSize());
        MovieDatabase.initialize("ratedmovies_short.csv");
        System.out.println("read data for " + MovieDatabase.size() + " movies");
        
        list = object.getAverageRatingsByFilter(minimalRaters,mf);
        Collections.sort(list);
        
        System.out.println("found " + list.size() + " movies");
        for ( Rating temp: list ){
            System.out.println( temp.getValue() + " Time: " + MovieDatabase.getMinutes(temp.getItem())
                                 + " " + MovieDatabase.getTitle(temp.getItem()));                   
        }
    }
    
    public void printAverageRatingsByDirector(){
        ThirdRatings object = new ThirdRatings();
        ArrayList<Rating> list = new ArrayList<Rating>();
       
        int minimalRaters = 4;
        String director = "Clint Eastwood,Joel Coen,Martin Scorsese,Roman Polanski,Nora Ephron,Ridley Scott,Sydney Pollack";
        
        DirectorFilter df = new DirectorFilter(director);
        
        
        System.out.println("read data for " + object.getRaterSize() + " raters");
        //System.out.println("Total number of movies = " + object.getMovieSize());
        //System.out.println("Total number of raters = " + object.getRaterSize());
        MovieDatabase.initialize("ratedmovies_short.csv");
        System.out.println("read data for " + MovieDatabase.size() + " movies");
        
        list = object.getAverageRatingsByFilter(minimalRaters,df);
        Collections.sort(list);
        
        System.out.println("found " + list.size() + " movies");
        for ( Rating temp: list ){
            System.out.println( temp.getValue() + " " + MovieDatabase.getTitle(temp.getItem()));
            System.out.println( "    " + MovieDatabase.getDirector(temp.getItem()));                    
        }
    }
    
    public void printAverageRatingsByYearAfterAndGenre(){
        ThirdRatings object = new ThirdRatings();
        ArrayList<Rating> list = new ArrayList<Rating>();
       
        int minimalRaters = 8;
        int year = 1990;
        String genre = "Drama";
        
        AllFilters af = new AllFilters();
       
        af.addFilter(new GenreFilter(genre));
        af.addFilter(new YearAfterFilter(year));
        
        System.out.println("read data for " + object.getRaterSize() + " raters");
        //System.out.println("Total number of movies = " + object.getMovieSize());
        //System.out.println("Total number of raters = " + object.getRaterSize());
        MovieDatabase.initialize("ratedmovies_short.csv");
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
    
    public void printAverageRatingsByDirectorAndMinutes(){
        ThirdRatings object = new ThirdRatings();
        ArrayList<Rating> list = new ArrayList<Rating>();
       
        int minimalRaters = 3;
        int minf = 90;
        int maxf = 180;
        String director = "Clint Eastwood,Joel Coen,Tim Burton,Ron Howard,Nora Ephron,Sydney Pollack";
        
        AllFilters af = new AllFilters();
       
        af.addFilter(new DirectorFilter(director));
        af.addFilter(new MinutesFilter(minf,maxf));
        
        
        System.out.println("read data for " + object.getRaterSize() + " raters");
        //System.out.println("Total number of movies = " + object.getMovieSize());
        //System.out.println("Total number of raters = " + object.getRaterSize());
        MovieDatabase.initialize("ratedmovies_short.csv");
        System.out.println("read data for " + MovieDatabase.size() + " movies");
        
        list = object.getAverageRatingsByFilter(minimalRaters,af);
        Collections.sort(list);
        
        System.out.println("found " + list.size() + " movies");
        for ( Rating temp: list ){
            System.out.println( temp.getValue() + " Time: " + MovieDatabase.getMinutes(temp.getItem())
                                + " " + MovieDatabase.getTitle(temp.getItem()));
            System.out.println( "    " + MovieDatabase.getDirector(temp.getItem()));                    
        }
    }
}
