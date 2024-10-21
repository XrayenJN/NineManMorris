package action;

import action.*;
import action.Action;

import javax.swing.*;

/**
 * Action factory class
 * Produce the related action depending on the ActionStatus
 *
 * @author billgultiano, jordannathanael, yuchenwang
 * @version JDK 19
 */
public class ActionFactory {
    /**
     * Produce the action related to the actionStatus that we have
     * @param status ActionStatus
     * @return Action
     */
    public static Action createAction(ActionStatus status) {
        switch (ActionManager.getInstance().getStatus()){
            case PLACE:
                return new PlaceAction();
            case MOVE:
                return new MoveAction();
            case FLY:
                return new FlyAction();
            case REMOVE:
                return new RemoveAction();
            default:
                return null;
        }
    }
}
