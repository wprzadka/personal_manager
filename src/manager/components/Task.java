package manager.components;

import java.util.List;

class Task {
    String type;
    State progresState;

    String title;
    String description;

    public List<Task> components;

    TaskIterator iterator(){
        return new TaskIterator(this);
    }
}

enum State{
    TODO(0),
    IN_PROGRES(1),
    REVIEW(2),
    DONE(3);

    int progressLevel;
    boolean needsReview = false;

    State(int level){
        progressLevel = level;
    }

    void setNeedsReview(boolean isNeeded){
        needsReview = isNeeded;
    }

    boolean changeStateIfAvailable(State next){
        if(!needsReview || next.progressLevel - progressLevel == 1 || next == State.REVIEW){
            progressLevel = next.progressLevel;
            return true;
        }
        return false;
    }
}
