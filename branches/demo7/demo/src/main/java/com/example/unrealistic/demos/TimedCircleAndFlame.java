package com.example.unrealistic.demos;

import org.percepta.mgrankvi.unrealistic.ConductorCell;
import org.percepta.mgrankvi.unrealistic.UnrealisticComponent;

public class TimedCircleAndFlame extends UnrealisticComponent {

	private static final long serialVersionUID = -3698813263032202763L;

	public TimedCircleAndFlame() {
	}

	public void init() {
		setWidth("150px");
		setHeight("150px");
		setFps(15);

		final ConductorCell cell = new ConductorCell("demo/images/CircleSprites.png", 90, 90, 29, 5);
		cell.setLoop(false);
		cell.setOffsetX(23);
		cell.setOffsetY(30);

		addImage(cell);

		addImage(createFlame(94, 81, 82));

		addImage(createFlame(101, 17, 56));

		addImage(createFlame(108, 81, 28));

		addImage(createFlame(116, 54, 93));

		addImage(createFlame(122, 28, 28));

		addImage(createFlame(128, 92, 56));

		addImage(createFlame(136, 28, 82));

		addImage(createFlame(145, 54, 17));
	}

	private ConductorCell createFlame(final int delay, final int offsetX, final int offsetY) {
		final ConductorCell flame = new ConductorCell("demo/images/LongerSmallFlameWithColor.png", 25, 25, 14, 2);
		flame.setLoopStartColumn(8);
		flame.setReverse(true);

		flame.setDelay(delay);
		flame.setOffsetX(offsetX);
		flame.setOffsetY(offsetY);

		return flame;
	}
}
