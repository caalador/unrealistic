package com.example.unrealistic;

import org.percepta.mgrankvi.unrealistic.ConductorCell;
import org.percepta.mgrankvi.unrealistic.UnrealisticComponent;
import org.percepta.mgrankvi.unrealistic.client.ui.data.LimitChange;

import com.example.unrealistic.demos.CFDemo;
import com.example.unrealistic.demos.TimedCircleAndFlame;
import com.vaadin.annotations.Theme;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@Theme("demo")
public class UnrealisticApplication extends UI {

	private static final long serialVersionUID = 3694512868981467643L;

	private final VerticalLayout content = new VerticalLayout();
	private final HorizontalLayout contentLayout = new HorizontalLayout();

	@Override
	protected void init(final VaadinRequest request) {
		getPage().setTitle("Unrealistic Application");
		setContent(content);
		// createControllLayout();
		selectLayout();

		content.addComponent(contentLayout);
		final ConductorCell cell = new ConductorCell("demo/images/Vroom.png", 100, 50, 14, 2);
		cell.setLoopStartColumn(10);
		cell.setLoopStartRow(2);
		final UnrealisticComponent component = createAndAddComponent(150, 150);
		component.setFps(10);
		component.setPlayHeight(0);
		component.addImage(cell);
		contentLayout.addComponent(component);
	}

	private void selectLayout() {
		final NativeSelect select = new NativeSelect();
		select.addItem("Blue");
		select.addItem("Flame");
		select.addItem("Long Flame");
		select.addItem("Long Flame reverse");
		select.addItem("Long Flame loop position set");
		select.addItem("Dripp");
		select.addItem("Circle");
		select.addItem("Circle with fade");
		select.addItem("Circle flame timing");
		select.addItem("Demo...");
		select.setImmediate(true);
		select.addValueChangeListener(new ValueChangeListener() {
			private static final long serialVersionUID = -838069154357449340L;

			@Override
			public void valueChange(final ValueChangeEvent event) {
				contentLayout.removeAllComponents();
				final String selection = (String) event.getProperty().getValue();
				if ("Blue".equals(selection)) {
					final UnrealisticComponent component = createAndAddComponent(150, 150);
					component.addImage("demo/images/TestFlame.png", 50, 50, 10, 1);
					component.setFps(10);
				} else if ("Flame".equals(selection)) {
					final ConductorCell cell = new ConductorCell("demo/images/FlameSprites.png", 50, 50, 14, 1);
					final UnrealisticComponent component = createAndAddComponent(150, 150);
					component.setFps(10);
					component.addImage(cell);
				} else if ("Long Flame".equals(selection)) {
					final ConductorCell cell = new ConductorCell("demo/images/LongerFlame.png", 50, 50, 14, 2);
					final UnrealisticComponent component = createAndAddComponent(150, 150);
					component.setFps(10);
					component.addImage(cell);
				} else if ("Long Flame reverse".equals(selection)) {
					final ConductorCell cell = new ConductorCell("demo/images/LongerFlame.png", 50, 50, 14, 2);
					cell.setReverse(true);
					final UnrealisticComponent component = createAndAddComponent(150, 150);
					component.setFps(10);
					component.addImage(cell);
				} else if ("Long Flame loop position set".equals(selection)) {
					final ConductorCell cell = new ConductorCell("demo/images/LongerFlame.png", 50, 50, 14, 2);
					cell.setLoopStartColumn(7);
					final UnrealisticComponent component = createAndAddComponent(150, 150);
					component.setFps(10);
					component.addImage(cell);
				} else if ("Dripp".equals(selection)) {
					final UnrealisticComponent component = createAndAddComponent(150, 250);
					component.addImage(dripp());
				} else if ("Circle".equals(selection)) {
					final ConductorCell cell = new ConductorCell("demo/images/CircleSprites.png", 90, 90, 29, 5);
					cell.setLoop(false);
					cell.setOffsetX(23);
					cell.setOffsetY(30);
					final UnrealisticComponent component = createAndAddComponent(150, 150);
					component.addImage(cell);
				} else if ("Circle with fade".equals(selection)) {
					final ConductorCell cell = new ConductorCell("demo/images/CircleSprites.png", 90, 90, 29, 5);
					cell.setLoop(false);
					cell.setOffsetX(23);
					cell.setOffsetY(30);
					final LimitChange limit = new LimitChange(145);
					limit.setFadeOut(5000);
					cell.addLimit(limit);
					final UnrealisticComponent component = createAndAddComponent(150, 150);
					component.addImage(cell);
				} else if ("Circle flame timing".equals(selection)) {
					final TimedCircleAndFlame circleAndFlame = new TimedCircleAndFlame();
					circleAndFlame.init();
					contentLayout.addComponent(circleAndFlame);
				} else if ("Demo...".equals(selection)) {
					final CFDemo demo = new CFDemo();
					demo.init();
					contentLayout.addComponent(demo);
				}
			}
		});
		content.addComponent(select);
	}

	private UnrealisticComponent createAndAddComponent(final int width, final int height) {
		final UnrealisticComponent component = new UnrealisticComponent();
		component.setWidth(width + "px");
		component.setHeight(height + "px");
		component.setFps(15);

		contentLayout.addComponent(component);
		return component;
	}

	private ConductorCell dripp() {
		final ConductorCell cell = new ConductorCell("demo/images/Dripp.png", 50, 250, 17, 2);
		cell.setOffsetX(100);
		cell.setFrameDelay(1);
		cell.setLoopStartColumn(7);
		cell.setLoopStartRow(2);
		cell.setEnd(13, 2);

		// LimitChange limit = new LimitChange(46);
		// limit.setLoopStartColumn(14);
		// limit.setLoopStartRow(2);
		// cell.addLimit(limit);

		// LimitChange limit = new LimitChange(50);
		// limit.setLoopStartColumn(14);
		// limit.setLoopStartRow(2);
		// cell.addLimit(limit);

		LimitChange limit = new LimitChange(50);
		limit.setLoopStartColumn(14);
		limit.setEndColumn(17);
		cell.addLimit(limit);

		limit = new LimitChange(70);
		limit.setLoop(false);
		cell.addLimit(limit);

		return cell;
	}
}
