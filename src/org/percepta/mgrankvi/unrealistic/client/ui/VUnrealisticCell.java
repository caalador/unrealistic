package org.percepta.mgrankvi.unrealistic.client.ui;

import java.util.LinkedList;
import java.util.List;

import org.percepta.mgrankvi.unrealistic.client.ui.data.DelayPosition;
import org.percepta.mgrankvi.unrealistic.client.ui.data.LimitChange;

import com.google.gwt.animation.client.Animation;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Style;
import com.google.gwt.dom.client.Style.Visibility;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.vaadin.terminal.gwt.client.VConsole;

public class VUnrealisticCell {

	private final String image;
	private final int width, height, columns, rows;
	private int delay = 0;
	private int frameDelay = 0;
	private int column = 0;
	private int row = 0;
	private int loopStartColumn = 0;
	private int loopStartRow = 0;
	private int endColumn;
	private int endRow;

	private boolean loop = true;
	private boolean reverse = false;
	private boolean backup = false;
	private boolean hide = false;

	private boolean visible = false;
	private boolean flickering = false;
	private Flicker poller;

	private final List<DelayPosition> delays = new LinkedList<DelayPosition>();
	private final List<LimitChange> limitChanges = new LinkedList<LimitChange>();

	private final Element unreal;

	private VUnrealistic parent;

	public VUnrealisticCell(final String image, final int width, final int height, final int columns, final int rows) {
		super();

		this.image = image;
		this.width = width;
		this.height = height;
		this.columns = endColumn = columns;
		this.rows = endRow = rows;

		unreal = DOM.createDiv();
		unreal.setClassName("unreal-cell");

		final Style style = getStyle();

		style.setPropertyPx("width", width);
		style.setPropertyPx("height", height);
		style.setProperty("position", "absolute");
		style.setVisibility(Visibility.HIDDEN);

		style.setBackgroundImage("url(" + Window.Location.getPath() + "/VAADIN/themes/" + image + ")");
		// style.setProperty("background-position", "0 0");
		setBGPosition(style, "0 0");
	}

	// Work around for GWT dev mode problem with setting property containing -
	static native void setBGPosition(Style style, String position) /*-{
																	style["background-position"] = position;
																	}-*/;

	public void setPath(final String path) {
		VConsole.log("url(" + Window.Location.getHost() + path + " / VAADIN / themes / " + image + ")");
		getStyle().setBackgroundImage("url(" + path + "/VAADIN/themes/" + image + ")");
	}

	public void setDelay(final int delay) {
		this.delay = delay;
	}

	public void setFrameDelay(final int frameDelay) {
		this.frameDelay = frameDelay;
	}

	public void setOffsetX(final int offsetX) {
		getStyle().setPropertyPx("left", offsetX);
	}

	public void setOffsetY(final int offsetY) {
		getStyle().setPropertyPx("top", offsetY);
	}

	public void setLoopStart(final int column, final int row) {
		loopStartColumn = column;
		loopStartRow = row;
	}

	public void setEndFrame(final int column, final int row) {
		endColumn = column;
		endRow = row;
	}

	public void setLoop(final boolean loop) {
		this.loop = loop;
	}

	public void setHide(final boolean hide) {
		this.hide = hide;
	}

	public void addDelay(final DelayPosition delayPosition) {
		delays.add(delayPosition);
	}

	public void addLimitChange(final LimitChange limitChange) {
		limitChanges.add(limitChange);
	}

	public void setReverse(final boolean reverse) {
		this.reverse = reverse;
	}

	private Style getStyle() {
		return unreal.getStyle();
	}

	public Element getElement() {
		return unreal;
	}

	public void setIndex(final int index) {
		getStyle().setProperty("z-index", Integer.toString(9000 + index));
	}

	public void updateFrame() {
		if (delay > 0) {
			delay--;
			return;
		}
		delay = frameDelay;
		for (final DelayPosition delayPosition : delays) {
			if (column == delayPosition.getColumn() && row == delayPosition.getRow()) {
				delay = delayPosition.getDelay();

				if (delayPosition.removeDelay()) {
					delays.remove(delayPosition);
				}
			}
		}
		for (final LimitChange limitChange : limitChanges) {
			if (limitChange.execute()) {
				changeLimits(limitChange);
				limitChanges.remove(limitChange);
			}
		}
		if (!visible) {
			visible = true;
			getStyle().setVisibility(Visibility.VISIBLE);
		}
		if (backup) {
			frameBack();
		} else {
			frameForward();
		}
	}

	private void changeLimits(final LimitChange limitChange) {
		if (limitChange.getEndColumn() != null) {
			endColumn = limitChange.getEndColumn();
		}
		if (limitChange.getEndRow() != null) {
			endRow = limitChange.getEndRow();
		}
		if (limitChange.getLoopStartColumn() != null) {
			loopStartColumn = limitChange.getLoopStartColumn();
			VConsole.log("Set new StartColumn");
		}
		if (limitChange.getLoopStartRow() != null) {
			loopStartRow = limitChange.getLoopStartRow();
			VConsole.log("Set new StartRow");
		}
		if (limitChange.getLoop() != null) {
			loop = limitChange.getLoop();
		}
		if (limitChange.getReverse() != null) {
			reverse = limitChange.getReverse();
		}
		if (limitChange.getHide() != null) {
			getStyle().setVisibility(limitChange.getHide() ? Visibility.HIDDEN : Visibility.VISIBLE);
		}
		if (limitChange.getRemove() != null && limitChange.getRemove()) {
			parent.removeCell(this);
		}
		if (limitChange.getFlicker() != null) {
			VConsole.log("Found flicker request");
			if (poller == null) {
				poller = new Flicker();
			} else if (flickering) {
				poller.cancel();
			}
			poller.scheduleRepeating(limitChange.getFlicker());
			flickering = true;
		} else if (flickering) {
			poller.cancel();
			flickering = false;
		}
		if (limitChange.getFadeIn() != null) {
			VConsole.log("Found fadeIn request");
			getStyle().setOpacity(0.0);
			getStyle().setVisibility(Visibility.VISIBLE);
			final Animation animation = new Animation() {
				@Override
				protected void onUpdate(final double progress) {
					getStyle().setOpacity(progress);
				}
			};
			animation.run(limitChange.getFadeIn());
		}
		if (limitChange.getFadeOut() != null) {
			VConsole.log("Found fade request");
			final Animation animation = new Animation() {
				@Override
				protected void onUpdate(final double progress) {
					getStyle().setOpacity(1.0 - progress);
				}
			};
			animation.run(limitChange.getFadeOut());
		}
	}

	private void frameForward() {
		column++;
		if (column == endColumn && row == endRow) {
			if (reverse) {
				backup = true;
				return;
			}
			if (!loop) {
				parent.stopAnimation(this);
				return;
			}
			row = loopStartRow;
			column = loopStartColumn;
		} else if (column == columns) {
			row++;
			if (row < rows) {
				column = 0;
			} else {
				if (reverse) {
					backup = true;
					row--;
					return;
				}
				if (hide) {
					getStyle().setVisibility(Visibility.HIDDEN);
				}
				if (!loop) {
					parent.stopAnimation(this);
					return;
				}
				row = loopStartRow;
				column = loopStartColumn;
			}
		}
		// getStyle().setProperty("background-position", "-" + column * width +
		// "px -" + row * height + "px");
		setBGPosition(getStyle(), "-" + column * width + "px -" + row * height + "px");
	}

	private void frameBack() {
		column--;
		if (column == 0) {
			row--;
			if (row < 0) {
				row = 0;
				backup = false;
				if (!loop) {
					parent.stopAnimation(this);
				}
				return;
			}
			column = columns - 1;
		}
		if (column == loopStartColumn && row == loopStartRow) {
			backup = false;
			if (!loop) {
				parent.stopAnimation(this);
			}
		}
		// getStyle().setProperty("background-position", "-" + column * width +
		// "px -" + row * height + "px");
		setBGPosition(getStyle(), "-" + column * width + "px -" + row * height + "px");
	}

	public void setParent(final VUnrealistic parent) {
		this.parent = parent;
	}

	class Flicker extends Timer {
		@Override
		public void run() {
			getStyle().setVisibility(getStyle().getVisibility().equals("visible") ? Visibility.HIDDEN : Visibility.VISIBLE);
		}
	}
}
