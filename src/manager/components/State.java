package manager.components;

public enum State{
    TODO(0),
    IN_PROGRES(1),
    REVIEW(2),
    DONE(3);

    int progressLevel;
    boolean needsReview = false;

    State(int level){
        progressLevel = level;
    }

    public void setNeedsReview(boolean isNeeded){
        needsReview = isNeeded;
    }

    public boolean changeStateIfAvailable(State next){
        if(!needsReview || next.progressLevel - progressLevel == 1 || next == State.REVIEW){
            progressLevel = next.progressLevel;
            return true;
        }
        return false;
    }

    public int getStateLevel(){
        return progressLevel;
    }
}
