package org.percepta.mgrankvi.unrealistic.client.ui;

import org.percepta.mgrankvi.unrealistic.UnrealisticComponent;
import org.percepta.mgrankvi.unrealistic.client.ui.data.DelayPosition;
import org.percepta.mgrankvi.unrealistic.client.ui.data.LimitChange;
import org.percepta.mgrankvi.unrealistic.client.ui.state.ConductorState;
import org.percepta.mgrankvi.unrealistic.client.ui.state.DelayPositionState;
import org.percepta.mgrankvi.unrealistic.client.ui.state.LimitChangeState;
import org.percepta.mgrankvi.unrealistic.client.ui.state.UnrealisticState;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Widget;
import com.vaadin.client.communication.StateChangeEvent;
import com.vaadin.client.ui.AbstractComponentConnector;
import com.vaadin.shared.ui.Connect;

@Connect(value = UnrealisticComponent.class)
public class UnrealisticConnector extends AbstractComponentConnector {

	private static final long serialVersionUID = -6447122684737352627L;

	@Override
	protected Widget createWidget() {
		return GWT.create(VUnrealistic.class);
	}

	@Override
	public VUnrealistic getWidget() {
		return (VUnrealistic) super.getWidget();
	};

	@Override
	public UnrealisticState getState() {
		return (UnrealisticState) super.getState();
	}

	@Override
	public void onStateChanged(final StateChangeEvent stateChangeEvent) {
		super.onStateChanged(stateChangeEvent);
		getWidget().setPlaySize(getState().getPlayWidth(), getState().getPlayHeight());

		getWidget().setPlayImage(getState().getPlayString());

		getWidget().setSpeed(1000 / getState().getFps());

		for (final ConductorState cell : getState().getConductors()) {
			getWidget().addCell(createCell(cell));
		}
	}

	private VUnrealisticCell createCell(final ConductorState cell) {
		final VUnrealisticCell animationCell = new VUnrealisticCell(cell.getImage(), cell.getComponentWidth(), cell.getComponentHeight(), cell.getColumns(),
				cell.getRows());

		animationCell.setDelay(cell.getDelay());
		animationCell.setFrameDelay(cell.getFrameDelay());
		animationCell.setLoopStart(cell.getLoopStartColumn() - 1, cell.getLoopStartRow() - 1);
		animationCell.setLoop(cell.isLoop());
		animationCell.setHide(cell.isHide());
		animationCell.setOffsetX(cell.getOffsetX());
		animationCell.setOffsetY(cell.getOffsetY());
		animationCell.setReverse(cell.isReverse());
		if (cell.getEndColumn() != null && cell.getEndRow() != null) {
			animationCell.setEndFrame(cell.getEndColumn(), cell.getEndRow() - 1);
		} else {
			animationCell.setEndFrame(cell.getColumns(), cell.getRows());
		}

		for (final LimitChangeState limitChange : cell.getChanges()) {
			animationCell.addLimitChange(createLimitChange(limitChange));
		}
		for (final DelayPositionState delay : cell.getDelays()) {
			animationCell.addDelay(createDelay(delay));
		}

		return animationCell;
	}

	private LimitChange createLimitChange(final LimitChangeState limitChange) {
		final LimitChange limit = new LimitChange(limitChange.getDelay());

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

	private DelayPosition createDelay(final DelayPositionState delay) {
		final DelayPosition delayPosition = new DelayPosition(delay.getDelay(), delay.getColumn(), delay.getRow() - 1);

		delayPosition.setRemove(delay.isRemove());
		delayPosition.setRepeat(delay.getRepeat());

		return delayPosition;
	}
}
