package polaris.action;

import java.util.*;


public class ActionManager {
	
	private static List<ActionListener> listeners = new ArrayList<>();
	private static List<Action> actions = new ArrayList<>();

	static {
		actions = Arrays.asList(Action.values());
	}
	
	public static void addListener(ActionListener listener) {
		listeners.add(listener);
	}

	public static void nottfy(Action action) {
		listeners.stream().forEach(el -> el.action(action));
	}
	
	public static void findActionAndNotify(String actionId) {
		Optional<Action> action = actions.stream().filter(el -> el.getActionId().equals(actionId)).findFirst();
		if (action.isPresent()) {
			listeners.stream().forEach(el -> el.action(action.get()));
		}
	}
	
	/*
	public static Action findActionBySocketId(String socketId) {
		for(Action a : ACTOINS) {
			if(a.getSocketId().equals(socketId)) {
				return a;
			}
		}
		return null;
	}
	*/
	
}
