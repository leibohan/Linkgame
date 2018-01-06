package LLK.viewer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import LLK.dao.HandleDB;
import LLK.entity.TimeOrder;

public class TimeOrderDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private MainFrame parent;

	public TimeOrderDialog(MainFrame parent, boolean isShow, int level) {
		super(parent, isShow);
		
		this.parent = parent;
		
		this.setTitle("ランクイン");
		setBounds(100, 100, 450, 250);
		this.setLocationRelativeTo(parent);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		{
			JLabel label = new JLabel("ランキング　リッスト");
			label.setForeground(new Color(0, 128, 0));
			contentPanel.add(label);
		}
			JTextArea taResult = new JTextArea(8, 32);
			taResult.setBackground(Color.LIGHT_GRAY);
			taResult.setEditable(false);
			taResult.setBounds(32, 47, 313, 172);
			contentPanel.add(taResult);
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
		
			HandleDB hdb = new HandleDB();
			ArrayList<TimeOrder> al = hdb.selectInfo(level);
			if (al.size() == 0) {
				taResult.setText("一時ブランク");
			}
			else {
				taResult.append("ランキング\t名前\tタイミング\tポイント\r\n\r\n");
				for (int i = 0; i < al.size(); i++) {
					TimeOrder  itm = al.get(i);
					taResult.append((i+1) + "番 は\t" + itm.getName() + "\t" + itm.getTime() + "\t" + itm.getScore() + "\r\n");
				}
			}
		{
			//JPanel buttonPane = new JPanel();
			//buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			//getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("確認");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
//						int row = parent.getBlankR();
//						int col = parent.getBlankC();
//						System.out.println("control blankR and blankC in MainFrame");
//						
//						Font font = new Font();
						dispose();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("キャンセル");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setVisible(true);

		
	}

}
