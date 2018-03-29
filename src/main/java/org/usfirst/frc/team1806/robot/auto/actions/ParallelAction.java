package org.usfirst.frc.team1806.robot.auto.actions;

import java.util.ArrayList;
import java.util.List;

/**
 * Composite action, running all sub-actions at the same time All actions are started then updated until all actions
 * report being done.
 *
 * @param A
 *            List of Action objects
 */
public class ParallelAction implements Action {
    private boolean[] finishedActions;
    private final ArrayList<Action> mActions;
    private ArrayList<Boolean> mActionStates;

    public ParallelAction(List<Action> actions) {
        mActions = new ArrayList<>(actions.size());
        for (Action action : actions) {
            mActions.add(action);
            mActionStates.add(false);
        }
    }

    @Override
    public boolean isFinished() {
        boolean all_finished = true;
        for(int i = 0; i < mActions.size(); i++)
        {
            if(!mActionStates.get(i) && !mActions.get(i).isFinished()){
                all_finished = false;
            }
        }
        return all_finished;
    }

    @Override
    public void update() {

        for(int i = 0; i < mActions.size(); i++)
        {

            if(mActionStates.get(i) == false){
                Action currentAction = mActions.get(i);
                if(currentAction.isFinished()){
                    currentAction.done();
                    mActionStates.set(i, true);
                }
                else{
                    currentAction.update();
                }
            }

        }
    }

    @Override
    public void done() {
        for(int i = 0; i < mActions.size(); i++)
        {
            if(mActionStates.get(i) == false){
                Action currentAction = mActions.get(i);
                currentAction.done();
            }
        }
    }

    @Override
    public void start() {
        for (Action action : mActions) {
            action.start();
        }
    }
}