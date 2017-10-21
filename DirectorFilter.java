public class DirectorFilter implements Filter {
    private String[] myDirector;
    
    public DirectorFilter(String director) {
        myDirector = director.split(",");
    }
    
    @Override
    public boolean satisfies(String id) {
        boolean flag = false;
        for ( String dir : myDirector ){
            if (MovieDatabase.getDirector(id).contains(dir)){
                flag = true;
            }
        }
        return flag;
    }

}
