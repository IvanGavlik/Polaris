package polaris.action;

public enum Action {
	
	TASK("org.eclipse.mylyn.tasks.ui.command.openTask", "eclipse task"),
	BACK_TO("org.eclipse.ui.navigate.backwardHistory", ""),
	FORWARD_TO("org.eclipse.ui.navigate.forwardHistory", ""),
	LAST_EDIT_LOCATION("org.eclipse.ui.edit.text.gotoLastEditPosition", ""),
	OPEN_TYPE("org.eclipse.jdt.ui.navigate.open.type", ""),
	SEARCH("org.eclipse.search.ui.openSearchDialog", "eclipse search"),
	SKIP_ALL_BREAK_POINTS("org.eclipse.debug.ui.commands.SkipAllBreakpoints", "eclipse skip"),
//	NEW_CLASS("org.eclipse.jdt.ui.actions.NewTypeDropDown", " CLASS"); ne radi 
	
	GETTER_SETTER("org.eclipse.jdt.ui.edit.text.java.create.getter.setter", "eclipse get"),
	HASHCODE_EQUALS("org.eclipse.jdt.ui.edit.text.java.generate.hashcode.equals", "eclipse hash"),
	TO_STRING("org.eclipse.jdt.ui.edit.text.java.generate.tostring", "eclipse string"),
	CONSTRUCTOR("org.eclipse.jdt.ui.edit.text.java.generate.constructor.using.fields", ""),
	FORMAT("org.eclipse.jdt.ui.edit.text.java.format", "eclipse format"),
//  FIND("org.eclipse.jdt.ui.edit.text.java.search.occurrences.in.file", " FIND"), ne radi find and replace
	
	STEP_INTO("org.eclipse.debug.ui.commands.StepInto", ""),
	STEP_OVER("org.eclipse.debug.ui.commands.StepOver", ""),
	RUN_TO_LINE("org.eclipse.debug.ui.commands.RunToLine", ""),
	TERMINATE("org.eclipse.debug.ui.actions.Terminate", ""),
	RESUME("org.eclipse.debug.ui.actions.Resume", ""),
//  RUN_AS_ACTION("org.eclipse.debug.internal.ui.actions.RunAsAction", " START"), ne radi 
	REBUILD("org.eclipse.jdt.ui.index.rebuild", ""),

//  MAVEN_UPDATE_PROJECT("org.eclipse.m2e.core.ui.command.updateProject", " UPDATE"), testirati 
	
	NEW("org.eclipse.ui.newWizard", "eclipse new"), // ILI org.eclipse.ui.wizards.new.file
	SAVE("org.eclipse.ui.file.save", "eclipse save"),
	RENAME("org.eclipse.ui.edit.rename", "eclipse rename"),
	REFRESH("org.eclipse.ui.file.refresh", ""), 
	UNDO("org.eclipse.ui.edit.undo", ""), // TODO PROMJENITI KLJUČNU RIJEČ
	CUT("org.eclipse.ui.edit.cut", "eclipse cut"),
	COPY("org.eclipse.ui.edit.copy", "eclipse copy"),
	PASTE("org.eclipse.ui.edit.paste", "eclipse paste"),
	SELECT_ALL("org.eclipse.ui.edit.selectAll", "eclipse all"),
	DELETE("org.eclipse.ui.edit.delete", "eclipse delete"),
	CLOSE("org.eclipse.ui.file.close", "eclipse close"),
	OPEN_WORKSPACE("org.eclipse.ui.file.openWorkspace", ""),
//	OPEN_PROJECT("org.eclipse.ui.project.openProject", "PROJECT"), ne radi 
//	BUILD_PROJECT("org.eclipse.ui.project.buildProject", "BUILD"),
//	REBUILD_PROJECT("org.eclipse.ui.project.rebuildProject", "REBUILD"),
//	CLEAN_PROJECT("org.eclipse.ui.project.cleanAction", " CLEAN");
	
	STOP_PLUGIN("stop plugin", ""),
	ALREADY_STARTED("plugin already started", "");
	
	
	private String eclipseId;
	private String actionId;
	
	private Action(String eclipseId, String actionId) {
		this.eclipseId = eclipseId;
		this.actionId = actionId;
	}
		
	public String getEclipseId() {
		return eclipseId;
	}

	public String getActionId() {
		return actionId;
	}
	
	public String formatCommand() {
		StringBuilder sb = new StringBuilder();
		sb.append(this.name());
		sb.append(": ");
		sb.append(eclipseId);
		return sb.toString();
	}
	
	@Override
	public String toString() {
		return formatCommand();
	}
	
}
