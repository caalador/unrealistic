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
import com.vaadin.client.VConsole;

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
