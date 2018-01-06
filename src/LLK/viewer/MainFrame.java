package LLK.viewer;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.border.EmptyBorder;

import LLK.dao.HandleDB;
import LLK.entity.ImageButton;
import LLK.logic.LLKOperation;

public class MainFrame extends JFrame implements ActionListener {
	
	private LLKOperation op = new LLKOperation();
	
	private JPanel contentPane;
	JPanel imagePanel = new JPanel();
	private int nums = 3;
	HandleDB hdb = new HandleDB();
	private AudioClip music = null;
	int[][] numArr = new int[14][14];
	private JLabel lblScore = new JLabel("　");
	
	//private String []strGrade = {"難度を選ぶ", "3 x 3", "4 x 4", "5 x 5"};
	private JComboBox<String> cbGrade = new JComboBox<String>(/*strGrade*/);
	private boolean isRun = false;
	private int cnt = 0;
	
	private int motoRow = 0;
	private int motoCol = 0;
	
	private Timer timer;
	
	private int timeleft = 200;
	private int score = 0;
	
	private class MyTask extends TimerTask {		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			//System.out.println("+1s " + timeleft);
			timeleft--;
			lblLeftTime.setText(timeleft + "s");
			if (timeleft == 0) {
				JOptionPane.showMessageDialog(null, "時間切れ");
				this.cancel();
			}
		}
	}
	
	public void startThread() {
		if (timer != null) {
			timer.cancel();
		}
		timeleft = nums * 20;
		lblLeftTime.setText(timeleft + "s");
		lblScore.setText(String.valueOf(score));
		timer = new Timer();
		timer.schedule(new MyTask(), 1000, 1000);
	}

	private JButton btnShow = new JButton("サポット");
	private JButton btnStart = new JButton("スタット");
	private JButton btnMusic = new JButton("おんがく");
	private JButton btnOrder = new JButton("ランクメ");
	private JButton btnReset = new JButton("レセット");
	private JLabel label = new JLabel("残り時間：");
	private JLabel lblLeftTime = new JLabel(" ");

	ImageButton [][] btnArr = null;
	private final JLabel label_1 = new JLabel("ポイント：");
		
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				}catch(Exception e) {
					e.getStackTrace();
				}
			}
		});
	}
	
	public void init() {
		imagePanel.removeAll();
		initGrade();
		initNums();
		cnt = nums * nums;
		score = 0;
		imagePanel.setLayout(new GridLayout(nums,nums));
		btnArr = new ImageButton[nums + 1][nums + 1];
					
		for (int i = 1; i <= nums; i++) {
			for (int j = 1; j <= nums; j++) {
				System.out.print(numArr[i][j]);
				ImageButton btn = new ImageButton(i, j, numArr[i][j], nums);
				imagePanel.add(btn);
				btnArr[i][j] = btn;
				btnArr[i][j].addActionListener(this);
			}
			System.out.println("");
		}
	}
	
	public void musicSwitch(ActionEvent e) {
		JButton btn = (JButton) e.getSource();
		if (!isRun){
			new JOptionPane().showMessageDialog(null, "音楽はゲーム始めるからです");
		}
		else if ("おんがく".equals(btn.getText().trim())) {
			music.loop();
			btn.setText("音楽切れ");
		}
		else{
			music.stop();
			btn.setText("おんがく");
		}
	}
		
	public void initGrade() {
		nums = cbGrade.getSelectedIndex();
		if(nums == 0)
			nums = 1;
		nums += 2;
		nums *= 2;
		System.out.println("level = " + nums);
	}
	
	public void initNums() {
		op.shokika(numArr);
		op.tsukuru(nums, numArr);
	}
	
	public void initMusic() {
		URL urlMusic = this.getClass().getResource("/music/sample.wav");
		music = Applet.newAudioClip(urlMusic);
	}
	
	public MainFrame() {
		this.setTitle("連々見〜いかづち殿の作品〜");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 770, 560);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5,5,5,5));
		setContentPane(contentPane);
		SpringLayout sl_contentPane = new SpringLayout();
		sl_contentPane.putConstraint(SpringLayout.WEST, btnMusic, 83, SpringLayout.EAST, imagePanel);
		sl_contentPane.putConstraint(SpringLayout.EAST, btnMusic, -66, SpringLayout.EAST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, btnShow, 83, SpringLayout.EAST, imagePanel);
		sl_contentPane.putConstraint(SpringLayout.EAST, btnShow, -66, SpringLayout.EAST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.NORTH, btnStart, 19, SpringLayout.SOUTH, btnShow);
		sl_contentPane.putConstraint(SpringLayout.WEST, btnStart, 0, SpringLayout.WEST, btnShow);
		sl_contentPane.putConstraint(SpringLayout.EAST, btnStart, -66, SpringLayout.EAST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.NORTH, btnShow, 18, SpringLayout.SOUTH, btnMusic);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, btnOrder, -222, SpringLayout.SOUTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.NORTH, btnMusic, 16, SpringLayout.SOUTH, btnOrder);
		sl_contentPane.putConstraint(SpringLayout.EAST, lblLeftTime, -10, SpringLayout.EAST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblScore, 30, SpringLayout.EAST, label_1);
		sl_contentPane.putConstraint(SpringLayout.EAST, lblScore, -40, SpringLayout.EAST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblLeftTime, 30, SpringLayout.EAST, label_1);
		sl_contentPane.putConstraint(SpringLayout.WEST, cbGrade, 60, SpringLayout.EAST, imagePanel);
		sl_contentPane.putConstraint(SpringLayout.EAST, cbGrade, -57, SpringLayout.EAST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, btnOrder, 83, SpringLayout.EAST, imagePanel);
		sl_contentPane.putConstraint(SpringLayout.EAST, btnOrder, -66, SpringLayout.EAST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.NORTH, btnReset, 21, SpringLayout.SOUTH, btnStart);
		sl_contentPane.putConstraint(SpringLayout.WEST, btnReset, 0, SpringLayout.WEST, btnStart);
		
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblScore, 0, SpringLayout.NORTH, label_1);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, lblScore, 16, SpringLayout.NORTH, label_1);
		sl_contentPane.putConstraint(SpringLayout.WEST, label_1, 0, SpringLayout.WEST, label);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, label_1, -30, SpringLayout.NORTH, label);
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblLeftTime, 230, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, lblLeftTime, 245, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.NORTH, cbGrade, 53, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.NORTH, label, 229, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, label, 53, SpringLayout.EAST, imagePanel);
		sl_contentPane.putConstraint(SpringLayout.NORTH, imagePanel, 15, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, imagePanel, 15, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, imagePanel, 515, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, imagePanel, 515, SpringLayout.WEST, contentPane);
		contentPane.setLayout(sl_contentPane);
		
		imagePanel.setBackground(Color.GRAY);
		contentPane.add(imagePanel);
		contentPane.add(btnReset);
		btnReset.addActionListener(this);
		btnMusic.addActionListener(this);
		
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		contentPane.add(btnStart);
		btnStart.addActionListener(this);
		contentPane.add(btnShow);
		btnShow.addActionListener(this);
		contentPane.add(btnMusic);
		btnOrder.addActionListener(this);
		contentPane.add(label);

		lblLeftTime.setFont(new Font("Arial Black", Font.PLAIN, 13));
		lblScore.setFont(new Font("Arial Black", Font.PLAIN, 13));
		lblLeftTime.setForeground(new Color(102, 111, 255));
		lblScore.setForeground(new Color(102, 111, 255));
		contentPane.add(lblLeftTime);
		contentPane.add(btnOrder);
		cbGrade.setModel(new DefaultComboBoxModel(new String[] {"難度を選ぶ", "６階", "８階", "１０階", "１２階"}));
		cbGrade.setBounds(591, 323, 110, 29);
		contentPane.add(cbGrade);
		
		contentPane.add(label_1);
		contentPane.add(lblScore);		
		
		initMusic();
		//init();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == btnMusic) {
			//System.out.println("Music");
			musicSwitch(e);
		}
		else if (e.getSource() == btnReset) {
			System.out.println("Reset");
			if (isRun) {
				op.henka(nums, cnt, numArr);
				for (int i = 1; i <= nums; i++) {
					for (int j = 1; j <= nums; j++) {
						btnArr[i][j].setNum(numArr[i][j]);
						btnArr[i][j].updateImage(true);
					}
				}
				score -= 5;
				if (score < 0) score = 0;
				lblScore.setText(String.valueOf(score));
			}
			else {
				new JOptionPane().showMessageDialog(null, "地図を変化するのはゲーム始めるからです");
			}
		}
		else if (e.getSource() == btnStart) {
			//System.out.println("Start");
			if (isRun) {
				int n = JOptionPane.showConfirmDialog(this, "今のゲームから離れますか？");
				//System.out.println(n);
				if (n == 0)
					init();
					startThread();
			}
			else {
				init();
				btnStart.setText("やり直し");
				isRun = true;
				startThread();
			}
		}
		else if (e.getSource() == btnShow) {
			//System.out.println("info");
			JOptionPane.showMessageDialog(this, " このゲームは説明ないから、信じられないでしょう！\r\n如果看不懂日语，版权者雷伯涵推荐您去学一学，或者查字典\r\n");
		}
		else if (e.getSource() == btnOrder) {
			//System.out.println("rank");
			TimeOrderDialog dia = new TimeOrderDialog(this, true, nums);
		}
		else {
			//System.out.println("preRow:" + motoRow + " preCol:" + motoCol);
			sousaku(e);
		}
	}
	
	public void sousaku(ActionEvent e) {
		ImageButton btn = (ImageButton) e.getSource();
		int row = btn.getRow();
		int col = btn.getCol();
		
		//System.out.println("row:" + row + " col:" + col + "type" + btn.getNum());
		
		if (motoRow != 0 && motoCol != 0) {
			if (op.handan(row, col, motoRow, motoCol, nums, numArr)) {
				cnt = cnt - 2;
				score += timeleft / 30;
				btnArr[row][col].updateImage(false);
				btnArr[motoRow][motoCol].updateImage(false);
				numArr[row][col] = -1;
				numArr[motoRow][motoCol] = -1;
				motoRow = 0;
				motoCol = 0;
				if (cnt == 0) {
					lblScore.setText(String.valueOf(score));
					isRun = false;
					btnStart.setText("スタート");
					JOptionPane.showMessageDialog(this, "成功しました");
					timer.cancel();
					//int timeleft = Integer.parseInt(lblLeftTime.getName());
					int time = nums * 20 - timeleft;
					String name = JOptionPane.showInputDialog(this, "名前を入り込みなさい");
					if (name == null || "".equals(name.trim())) {
						name = "nounymous";
					}
					hdb.insertInfo(name, time, score, nums);
				}
			}
			else if (numArr[row][col] > 0 && numArr[motoRow][motoCol] > 0){
				motoRow = row;
				motoCol = col;
				score-=2;
				if (score < 0) score = 0;
			}
			else {
				motoRow = 0;
				motoCol = 0;
			}			
		}
		else {
			motoRow = row;
			motoCol = col;
		}
		lblScore.setText(String.valueOf(score));
		
		for (int i = 1; i <= nums; i++) {
			for (int j = 1; j <= nums; j++)
				System.out.print(numArr[i][j] + " ");
			System.out.println("");
		}
		System.out.println(cnt);
	}
}
