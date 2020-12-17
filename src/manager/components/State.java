package manager.components;

public enum State{
    TODO(0),
    IN_PROGRESS(1),
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

    public State next(State current){
        switch (current){
            case TODO:
                return IN_PROGRESS;
            case IN_PROGRESS:
                if (current.needsReview){
                    return REVIEW;
                }
                return DONE;
            default:
                return DONE;
        }
    }

    public State prev(State current){
        switch (current){
            case DONE:
                if (current.needsReview){
                    return REVIEW;
                }
                return IN_PROGRESS;
            case REVIEW:
                return IN_PROGRESS;
            default:
                return TODO;
        }
    }
}
