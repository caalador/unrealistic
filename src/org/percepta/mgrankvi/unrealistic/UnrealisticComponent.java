package org.percepta.mgrankvi.unrealistic;

import org.percepta.mgrankvi.unrealistic.client.ui.data.DelayPosition;
import org.percepta.mgrankvi.unrealistic.client.ui.data.LimitChange;
import org.percepta.mgrankvi.unrealistic.client.ui.state.ConductorState;
import org.percepta.mgrankvi.unrealistic.client.ui.state.DelayPositionState;
import org.percepta.mgrankvi.unrealistic.client.ui.state.LimitChangeState;
import org.percepta.mgrankvi.unrealistic.client.ui.state.UnrealisticState;

import com.vaadin.ui.AbstractComponent;

/**
 * Server side component for the widget.
 */
public class UnrealisticComponent extends AbstractComponent {

	private static final long serialVersionUID = -6703276811979276165L;

	//
	// List<ConductorCell> conductors = new LinkedList<ConductorCell>();
	// private boolean sendconfig = false;
	//
	// private int fps = 4;
	// private String playString = "unrealistic/images/Play.png";
	// private int playWidth = 75;
	// private int playHeight = 50;
	// private String path = null;

	@Override
	public UnrealisticState getState() {
		return (UnrealisticState) super.getState();
	}

	public void addImage(final ConductorCell cell) {
		getState().addConductor(createState(cell));
	}

	public void addImage(final String image, final int cellWidth, final int cellHeight, final int columns, final int rows) {
		final ConductorCell newCell = new ConductorCell(image, cellWidth, cellHeight, columns, rows);
		getState().addConductor(createState(newCell));
	}

	private ConductorState createState(final ConductorCell cell) {
		final ConductorState state = new ConductorState();

		state.setDelay(cell.getDelay());
		state.setFrameDelay(cell.getFrameDelay());
		state.setLoopStartColumn(cell.getLoopStartColumn());
		state.setLoopStartRow(cell.getLoopStartRow());
		state.setOffsetX(cell.getOffsetX());
		state.setOffsetY(cell.getOffsetY());
		state.setLoop(cell.shouldLoop());
		state.setReverse(cell.reverseAnimate());
		state.setEndColumn(cell.getEndColumn());
		state.setEndRow(cell.getEndRow());
		state.setHide(cell.shouldHide());
		state.setImage(cell.getImage());
		state.setComponentWidth(cell.getWidth());
		state.setComponentHeight(cell.getHeight());
		state.setColumns(cell.getColumns());
		state.setRows(cell.getRows());

		for (final DelayPosition delay : cell.getDelays()) {
			state.addDelay(createDelayPosition(delay));
		}

		for (final LimitChange limit : cell.getChanges()) {
			state.addLimitChange(createLimitChange(limit));
		}

		return state;
	}

	private DelayPositionState createDelayPosition(final DelayPosition delay) {
		final DelayPositionState delayPosition = new DelayPositionState();

		delayPosition.setDelay(delay.getDelay());
		delayPosition.setColumn(delay.getColumn());
		delayPosition.setRow(delay.getRow());

		delayPosition.setRemove(delay.removeFlag());
		delayPosition.setRepeat(delay.getRepeat());

		return delayPosition;
	}

	private LimitChangeState createLimitChange(final LimitChange limitChange) {
		final LimitChangeState limit = new LimitChangeState();

		limit.setDelay(limitChange.getDelay());

		limit.setLoopStartColumn(limitChange.getLoopStartColumn());
		limit.setLoopStartRow(limitChange.getLoopStartRow());

		limit.setEndColumn(limitChange.getEndColumn());
		limit.setEndRow(limitChange.getEndRow());

		limit.setLoop(limitChange.getLoop());
		limit.setReverse(limitChange.getReverse());

		limit.setRemove(limitChange.getRemove());
		limit.setHide(limitChange.getHide());

		limit.setFlicker(limitChange.getFlicker());
		limit.setFadeIn(limitChange.getFadeIn());
		limit.setFadeOut(limitChange.getFadeOut());

		return limit;
	}

	public void setFps(final int fps) {
		getState().setFps(fps);
	}

	public String getPlayString() {
		return getState().getPlayString();
	}

	public void setPath(final String path) {
		getState().setPath(path);
	}

	public void setPlayString(final String playString) {
		getState().setPlayString(playString);
	}

	public int getPlayWidth() {
		return getState().getPlayWidth();
	}

	public void setPlayWidth(final int playWidth) {
		getState().setPlayWidth(playWidth);
	}

	public int getPlayHeight() {
		return getState().getPlayHeight();
	}

	public void setPlayHeight(final int playHeight) {
		getState().setPlayHeight(playHeight);
	}

	// @Override
	// public void paintContent(final PaintTarget target) throws PaintException
	// {
	// super.paintContent(target);
	//
	// if (sendconfig) {
	// sendconfig = false;
	// target.addAttribute("speed", 1000 / fps);
	// }
	// target.addAttribute("play", playString);
	// target.addAttribute("playWidth", playWidth);
	// target.addAttribute("playHeight", playHeight);
	// target.addAttribute("playLeft", getWidth() / 2 - playWidth / 2);
	// target.addAttribute("playTop", getHeight() / 2 - playHeight / 2);
	//
	// for (final ConductorCell conductor : conductors) {
	// target.startTag("image");
	// target.addAttribute("imageString", conductor.getImage());
	//
	// target.addAttribute("cellwidth", conductor.getWidth());
	// target.addAttribute("cellheight", conductor.getHeight());
	//
	// target.addAttribute("columns", conductor.getColumns());
	// target.addAttribute("rows", conductor.getRows());
	//
	// target.startTag("configuration");
	//
	// if (path != null) {
	// target.addAttribute("path", path);
	// }
	//
	// if (conductor.getDelay() != 0) {
	// target.addAttribute("delay", conductor.getDelay());
	// }
	// if (conductor.getFrameDelay() != 0) {
	// target.addAttribute("frameDelay", conductor.getFrameDelay());
	// }
	//
	// if (conductor.shouldLoop()) {
	// target.addAttribute("loopStartColumn", conductor.getLoopStartColumn() -
	// 1);
	// target.addAttribute("loopStartRow", conductor.getLoopStartRow() - 1);
	// } else {
	// target.addAttribute("loop", conductor.shouldLoop());
	// }
	//
	// if (conductor.shouldHide()) {
	// target.addAttribute("hide", conductor.shouldHide());
	// }
	//
	// if (conductor.getOffsetX() != 0) {
	// target.addAttribute("offsetX", conductor.getOffsetX());
	// }
	// if (conductor.getOffsetY() != 0) {
	// target.addAttribute("offsetY", conductor.getOffsetY());
	// }
	//
	// if (conductor.reverseAnimate()) {
	// target.addAttribute("reverse", conductor.reverseAnimate());
	// }
	//
	// if (conductor.hasEnd()) {
	// target.addAttribute("endColumn", conductor.getEndColumn());
	// target.addAttribute("endRow", conductor.getEndRow() - 1);
	// }
	//
	// target.endTag("configuration");
	//
	// for (final DelayPosition delayPosition : conductor.getDelays()) {
	// target.startTag("delayPosition");
	//
	// target.addAttribute("delay", delayPosition.getDelay());
	// target.addAttribute("delayColumn", delayPosition.getColumn());
	// target.addAttribute("delayRow", delayPosition.getRow() - 1);
	//
	// if (delayPosition.removeFlag()) {
	// target.addAttribute("remove", delayPosition.removeFlag());
	// target.addAttribute("repeat", delayPosition.getRepeat());
	// }
	//
	// target.endTag("delayPosition");
	// }
	//
	// for (final LimitChange limitChange : conductor.getChanges()) {
	// target.startTag("limitChange");
	//
	// target.addAttribute("delay", limitChange.getDelay());
	// if (limitChange.getEndColumn() != null) {
	// target.addAttribute("endColumn", limitChange.getEndColumn() - 1);
	// }
	// if (limitChange.getEndRow() != null) {
	// target.addAttribute("endRow", limitChange.getEndRow() - 1);
	// }
	// if (limitChange.getLoopStartColumn() != null) {
	// target.addAttribute("loopStartColumn", limitChange.getLoopStartColumn() -
	// 1);
	// }
	// if (limitChange.getLoopStartRow() != null) {
	// target.addAttribute("loopStartRow", limitChange.getLoopStartRow() - 1);
	// }
	// if (limitChange.getLoop() != null) {
	// target.addAttribute("loop", limitChange.getLoop());
	// }
	// if (limitChange.getReverse() != null) {
	// target.addAttribute("reverse", limitChange.getReverse());
	// }
	// if (limitChange.getHide() != null) {
	// target.addAttribute("hide", limitChange.getHide());
	// }
	// if (limitChange.getRemove() != null) {
	// target.addAttribute("remove", limitChange.getRemove());
	// }
	// if (limitChange.getFlicker() != null) {
	// target.addAttribute("flicker", limitChange.getFlicker());
	// }
	// if (limitChange.getFadeIn() != null) {
	// target.addAttribute("fadeIn", limitChange.getFadeIn());
	// }
	// if (limitChange.getFadeOut() != null) {
	// target.addAttribute("fadeOut", limitChange.getFadeOut());
	// }
	// target.endTag("limitChange");
	// }
	// target.endTag("image");
	// }
	// }
	//
	// /**
	// * Receive and handle events and other variable changes from the client.
	// *
	// * {@inheritDoc}
	// */
	// @Override
	// public void changeVariables(final Object source, final Map<String,
	// Object> variables) {
	// super.changeVariables(source, variables);
	//
	// // Variables set by the widget are returned in the "variables" map.
	//
	// if (variables.containsKey("click")) {
	// }
	// }

}
