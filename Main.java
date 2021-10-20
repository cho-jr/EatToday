package eatToday;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class Main extends JFrame {

	static JButton btnExit;
	private JPanel contentPane;
	protected Main(UserVO uVo) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 797, 733);
		setLocationRelativeTo(null);
		setTitle("오늘 뭐먹지 - 메인");
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNowUser = new JLabel(uVo.getName()+"님의 소중한 한끼");
		lblNowUser.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNowUser.setFont(new Font("경기천년바탕 Regular", Font.PLAIN, 16));
		lblNowUser.setBounds(560, 12, 209, 34);
		contentPane.add(lblNowUser);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 236, 781, 456);
		panel.setOpaque(false);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton btnSearch = new JButton("리스트 조회");
		
		btnSearch.setLocation(190, 227);
		btnSearch.setSize(400, 50);
		btnSearch.setBackground(new Color(255, 228, 225));
		btnSearch.setFont(new Font("경기천년바탕 Regular", Font.PLAIN, 40));
		
		panel.add(btnSearch);
		
		JButton btnTournament = new JButton("토너먼트");
		
		btnTournament.setLocation(190, 51);
		btnTournament.setSize(400, 50);
		btnTournament.setBackground(new Color(255, 228, 225));
		btnTournament.setFont(new Font("경기천년바탕 Regular", Font.PLAIN, 40));
		panel.add(btnTournament);
		
		JButton btnSetList = new JButton("리스트 설정");
		
		btnSetList.setLocation(190, 315);
		btnSetList.setSize(400, 50);
		btnSetList.setBackground(new Color(255, 228, 225));
		btnSetList.setFont(new Font("경기천년바탕 Regular", Font.PLAIN, 40));
		panel.add(btnSetList);
		
		JButton btnRandom = new JButton("랜덤 추천");
		
		btnRandom.setLocation(190, 139);
		btnRandom.setSize(400, 50);
		btnRandom.setBackground(new Color(255, 228, 225));
		btnRandom.setFont(new Font("경기천년바탕 Regular", Font.PLAIN, 40));
		panel.add(btnRandom);
		
		JButton btnSetting = new JButton("마이페이지");
		
		btnSetting.setFont(new Font("경기천년바탕 Regular", Font.PLAIN, 30));
		btnSetting.setBackground(new Color(255, 228, 225));
		btnSetting.setBounds(190, 403, 190, 40);
		panel.add(btnSetting);
		
		btnExit = new JButton("끝내기");
		
		btnExit.setFont(new Font("경기천년바탕 Regular", Font.PLAIN, 30));
		btnExit.setBackground(new Color(255, 228, 225));
		btnExit.setBounds(400, 403, 190, 40);
		panel.add(btnExit);
		
		JLabel lblTitle = new JLabel("오늘 뭐 먹지?");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setForeground(Color.BLACK);
		lblTitle.setFont(new Font("경기천년바탕 Bold", Font.BOLD, 55));
		lblTitle.setBounds(6, 0, 769, 226);
		contentPane.add(lblTitle);
		
		JLabel lblBackground = new JLabel("");
		lblBackground.setIcon(new ImageIcon("eatImages\\Main.jpg"));
		lblBackground.setBounds(0, 0, 781, 692);
		contentPane.add(lblBackground);
		
		setVisible(true);
		
		/*********************************************************************/
		//목록 조회
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Search(uVo);
			}
		});
		
		// 토너먼트
		btnTournament.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Tournament(uVo);
			}
		});
		
		// 리스트 설정
		btnSetList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new SetList(uVo);
			}
		});
		
		// 랜덤 추천
		btnRandom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Random(uVo);
			}
		});
		
		// 설정
		btnSetting.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Setting(uVo);
			}
		});
		
		// 끝내기
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				try {
//					Thread.sleep(1000);
//				} catch (InterruptedException e1) {}
//				System.exit(0);
				
				dispose();
				new Login();
			}
		});
	}
	
	public static void mainClose() {
		btnExit.doClick();
	}
}
