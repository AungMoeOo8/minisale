package ui;

import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;

import data_classes.Product;
import minisale.IIndexed;

public class ListPanel<T extends JPanel & IIndexed> extends JPanel {

	private int gap;
	private ArrayList<T> panels;

	/**
	 * Create the panel.
	 */
	public ListPanel(int gap, T... panels) {
		this.gap = gap;
		this.panels = new ArrayList<>();
		for (T panel : panels) {
			this.panels.add(panel);
		}
		this.setLayout(null);
	}

	public void build() {
		if (this.panels.size() <= 0) {
			return;
		}

		int numberOfPanels = this.panels.size();
		int width = 0;
		int height = 0;

		for (int i = 0; i < numberOfPanels; i++) {
			JPanel panel = this.panels.get(i);

			int w = panel.getWidth();
			int h = panel.getHeight();

			if (w > width) {
				width = w;
			}

			boolean isFirstPanel = i == 0;
			boolean isLastPanel = i == numberOfPanels - 1;

			if (isFirstPanel) {
				panel.setLocation(0, 0);
				height += h + this.gap;
			} else if (isLastPanel) {
				int posY = height;
				panel.setLocation(0, posY);
				height += h;
			} else {
				int posY = height;
				panel.setLocation(0, posY);
				height += h + this.gap;
			}

			this.add(panel);
		}
		
		this.setPreferredSize(new Dimension(width, height));
	}

	public void addPanel(T panel) {
		this.panels.add(panel);
		this.build();
	}

	public void deletePanel(int index) {
		for (int i = 0; i < this.panels.size(); i++) {
			int panelIndex = this.panels.get(i).getIndex();
			if (panelIndex == index) {
				System.out.println("deletePanel: index found at " + i + "/ " + panelIndex + " " + index);
				this.panels.remove(i);
				break;
			}
		}
		this.build();
	}

}
