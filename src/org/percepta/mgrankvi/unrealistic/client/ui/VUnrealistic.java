package org.percepta.mgrankvi.unrealistic.client.ui;

import java.util.LinkedList;
import java.util.List;

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Style.Visibility;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Widget;
import com.vaadin.terminal.gwt.client.ApplicationConnection;
import com.vaadin.terminal.gwt.client.VConsole;

/**
 * Client side widget which communicates with the server. Messages from the
 * server are shown as HTML and mouse clicks are sent to the server.
 */
public class VUnrealistic extends Widget implements ClickHandler {

	/** Set the CSS class name to allow styling. */
	public static final String CLASSNAME = "v-mycomponent";

	public static final String CLICK_EVENT_IDENTIFIER = "click";

	/** The client side widget identifier */
	protected String paintableId;

	/** Reference to the server connection object. */
	protected ApplicationConnection client;
	// private final AbsolutePanel display;

	private final UnPainter poller;

	private boolean pollerSuspendedDueDetach = false;

	private int interval = 200;

	Element play;
	boolean running = false;

	private final List<VUnrealisticCell> cells = new LinkedList<VUnrealisticCell>();
	private final List<VUnrealisticCell> animate = new LinkedList<VUnrealisticCell>();

	/**
	 * The constructor should first call super() to initialize the component and
	 * then handle any initialization relevant to Vaadin.
	 */
	public VUnrealistic() {
		setElement(Document.get().createDivElement());
		setStyleName(CLASSNAME);
		sinkEvents(Event.ONCLICK);
		addDomHandler(this, ClickEvent.getType());

		DOM.setStyleAttribute(getElement(), "overflow", "hidden");
		DOM.setStyleAttribute(getElement(), "position", "relative");

		play = DOM.createDiv();
		play.getStyle().setVisibility(Visibility.VISIBLE);
		play.getStyle().setZIndex(100000);
		play.getStyle().setProperty("position", "absolute");
		play.getStyle().setProperty("left", "45%");
		play.getStyle().setProperty("top", "45%");
		getElement().appendChild(play);

		poller = new UnPainter();
	}

	@Override
	protected void onAttach() {
		super.onAttach();
		if (pollerSuspendedDueDetach) {
			poller.scheduleRepeating(interval);
		}
	}

	@Override
	protected void onDetach() {
		super.onDetach();
		poller.cancel();
		pollerSuspendedDueDetach = true;
	}

	protected void setPlaySize(final int width, final int height) {
		play.getStyle().setPropertyPx("width", width);
		play.getStyle().setPropertyPx("height", height);
	}

	protected void setPlayImage(final String image) {
		play.getStyle().setBackgroundImage("url(" + Window.Location.getPath() + "/VAADIN/themes/" + image + ")");
	}

	protected void setPlayPosition(final int left, final int top) {
		play.getStyle().setPropertyPx("left", left);
		play.getStyle().setPropertyPx("top", top);
	}

	protected void setSpeed(final int speed) {
		interval = speed;
	}

	protected void addCell(final VUnrealisticCell cell) {
		VConsole.log("Adding new Cell");
		cell.setParent(this);

		cells.add(cell);
		animate.add(cell);

		getElement().appendChild(cell.getElement());
	}

	// /**
	// * Called whenever an update is received from the server
	// */
	// public void updateFromUIDL(final UIDL uidl, final ApplicationConnection
	// client) {
	// if (client.updateComponent(this, uidl, true)) {
	// return;
	// }
	//
	// this.client = client;
	// paintableId = uidl.getId();
	//
	// cells.clear();
	//
	// setWidth(uidl.getStringAttribute("width"));
	// setHeight(uidl.getStringAttribute("height"));
	//
	// if (uidl.hasAttribute("speed")) {
	// interval = uidl.getIntAttribute("speed");
	// }
	//
	// final Style playStyle = play.getStyle();
	// playStyle.setBackgroundImage("url(" + Window.Location.getPath() +
	// "/VAADIN/themes/" + uidl.getStringAttribute("play") + ")");
	// playStyle.setPropertyPx("width", uidl.getIntAttribute("playWidth"));
	// playStyle.setPropertyPx("height", uidl.getIntAttribute("playHeight"));
	// playStyle.setProperty("position", "absolute");
	// playStyle.setPropertyPx("left", uidl.getIntAttribute("playLeft"));
	// playStyle.setPropertyPx("top", uidl.getIntAttribute("playTop"));
	//
	// if (uidl.hasAttribute("path")) {
	// playStyle.setBackgroundImage("url(" + uidl.getStringAttribute("path") +
	// "/VAADIN/themes/" + uidl.getStringAttribute("play") + ")");
	// }
	//
	// final Iterator<Object> children = uidl.getChildIterator();
	// while (children.hasNext()) {
	// final UIDL image = (UIDL) children.next();
	//
	// final int height = image.getIntAttribute("cellheight");
	// final int width = image.getIntAttribute("cellwidth");
	//
	// final int columns = image.getIntAttribute("columns");
	// final int rows = image.getIntAttribute("rows");
	// final String imageString = image.getStringAttribute("imageString");
	//
	// final VUnrealisticCell animationCell = new VUnrealisticCell(imageString,
	// width, height, columns, rows);
	//
	// animationCell.setParent(this);
	//
	// if (uidl.hasAttribute("path")) {
	// animationCell.setPath(uidl.getStringAttribute("path"));
	// }
	//
	// final Iterator<Object> data = image.getChildIterator();
	// while (data.hasNext()) {
	// final UIDL config = (UIDL) data.next();
	//
	// if (config.getTag().equals("configuration")) {
	// if (config.hasAttribute("delay")) {
	// animationCell.setDelay(config.getIntAttribute("delay"));
	// }
	// if (config.hasAttribute("frameDelay")) {
	// animationCell.setFrameDelay(config.getIntAttribute("frameDelay"));
	// }
	//
	// if (config.hasAttribute("loop")) {
	// animationCell.setLoop(config.getBooleanAttribute("loop"));
	// } else {
	// animationCell.setLoopStart(config.getIntAttribute("loopStartColumn"),
	// config.getIntAttribute("loopStartRow"));
	// }
	// if (config.hasAttribute("hide")) {
	// animationCell.setHide(true);
	// }
	// if (config.hasAttribute("offsetX")) {
	// animationCell.setOffsetX(config.getIntAttribute("offsetX"));
	// }
	// if (config.hasAttribute("offsetY")) {
	// animationCell.setOffsetY(config.getIntAttribute("offsetY"));
	// }
	// if (config.hasAttribute("reverse")) {
	// animationCell.setReverse(config.getBooleanAttribute("reverse"));
	// }
	// if (config.hasAttribute("endColumn")) {
	// animationCell.setEndFrame(config.getIntAttribute("endColumn"),
	// config.getIntAttribute("endRow"));
	// }
	// } else if (config.getTag().equals("delayPosition")) {
	//
	// final DelayPosition delayPosition = buildDelayPosition(config);
	// animationCell.addDelay(delayPosition);
	// } else if (config.getTag().equals("limitChange")) {
	//
	// final LimitChange change = buildLimitChange(config);
	// animationCell.addLimitChange(change);
	// }
	// }
	//
	// cells.add(animationCell);
	// animate.add(animationCell);
	//
	// getElement().appendChild(animationCell.getElement());
	// }
	//
	// }
	//
	// private DelayPosition buildDelayPosition(final UIDL delayUIDL) {
	// final int delay = delayUIDL.getIntAttribute("delay");
	// final int delayColumn = delayUIDL.getIntAttribute("delayColumn");
	// final int delayRow = delayUIDL.getIntAttribute("delayRow");
	//
	// final DelayPosition delayPosition = new DelayPosition(delay, delayColumn,
	// delayRow);
	// if (delayUIDL.hasAttribute("remove")) {
	// delayPosition.setRemove(delayUIDL.getBooleanAttribute("remove"));
	// delayPosition.setRepeat(delayUIDL.getIntAttribute("repeat"));
	// }
	// return delayPosition;
	// }
	//
	// private LimitChange buildLimitChange(final UIDL limit) {
	// final LimitChange change = new
	// LimitChange(limit.getIntAttribute("delay"));
	//
	// if (limit.hasAttribute("endColumn")) {
	// change.setEndColumn(limit.getIntAttribute("endColumn"));
	// }
	// if (limit.hasAttribute("endRow")) {
	// change.setEndRow(limit.getIntAttribute("endRow"));
	// }
	// if (limit.hasAttribute("loopStartColumn")) {
	// change.setLoopStartColumn(limit.getIntAttribute("loopStartColumn"));
	// }
	// if (limit.hasAttribute("loopStartRow")) {
	// change.setLoopStartRow(limit.getIntAttribute("loopStartRow"));
	// }
	// if (limit.hasAttribute("loop")) {
	// change.setLoop(limit.getBooleanAttribute("loop"));
	// }
	// if (limit.hasAttribute("reverse")) {
	// change.setLoop(limit.getBooleanAttribute("reverse"));
	// }
	// if (limit.hasAttribute("hide")) {
	// change.setHide(limit.getBooleanAttribute("hide"));
	// }
	// if (limit.hasAttribute("remove")) {
	// change.setRemove(limit.getBooleanAttribute("remove"));
	// }
	// if (limit.hasAttribute("flicker")) {
	// change.setFlicker(limit.getIntAttribute("flicker"));
	// }
	// if (limit.hasAttribute("fadeIn")) {
	// change.setFadeIn(limit.getIntAttribute("fadeIn"));
	// }
	// if (limit.hasAttribute("fadeOut")) {
	// change.setFadeOut(limit.getIntAttribute("fadeOut"));
	// }
	// return change;
	// }

	public void stopAnimation(final VUnrealisticCell cell) {
		animate.remove(cell);
	}

	public void removeCell(final VUnrealisticCell cell) {
		animate.remove(cell);
		cells.remove(cell);
		getElement().removeChild(cell.getElement());
	}

	/**
	 * Called when a native click event is fired.
	 * 
	 * @param event
	 *            the {@link ClickEvent} that was fired
	 */
	@Override
	public void onClick(final ClickEvent event) {
		if (!running) {
			poller.scheduleRepeating(interval);
			play.getStyle().setVisibility(Visibility.HIDDEN);
			running = true;
		} else {
			running = false;
			poller.cancel();
			play.getStyle().setVisibility(Visibility.VISIBLE);
		}
	}

	class UnPainter extends Timer {

		@Override
		public void run() {
			for (final VUnrealisticCell cell : animate) {
				cell.updateFrame();
			}
		}
	}
}
