package br.com.locacar.view.componentes;

import javax.swing.table.*;
import javax.swing.*;
import java.awt.*;

/**
 * Classe responsável pela personalização do JTable!
 * @author Felipe Nascimento
 */
@SuppressWarnings("serial")
public class GridView extends JTable {
	private static final int TOOLTIP_WIDTH = 500;
	DefaultTableModel model;
	String tooltipText;
	JPopupMenu popup;
	
	public GridView(DefaultTableModel model, JPopupMenu popup, String tooltipText) {
		this.model = model;
		this.popup = popup;
		this.tooltipText = tooltipText;
		initComponents();
	}
	
	private void initComponents() {
		if (tooltipText != null) {
			int width = SwingUtilities.computeStringWidth(getFontMetrics(getFont()), tooltipText);
			if (width > TOOLTIP_WIDTH) {
				width = TOOLTIP_WIDTH;
			}
			setToolTipText(String.format("<html><p width='%d'>%s</p></html>", width, tooltipText));
		}
		JTableHeader headerCols = this.getTableHeader();
		headerCols.setBackground(Color.BLUE);
		headerCols.setForeground(Color.WHITE);
		headerCols.setFont(new Font("Calibri", Font.BOLD, 12));
		setModel(model);
		setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		setFillsViewportHeight(true);
		setFont(new Font("Calibri", Font.BOLD, 12));
		setSelectionBackground(Color.WHITE);
		setSelectionForeground(Color.BLUE);
		setCursor(new Cursor(Cursor.HAND_CURSOR));
		setComponentPopupMenu(popup);
	}
}