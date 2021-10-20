package eatToday;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class Login extends JFrame {
	UserVO uVo = new UserVO();
	MenuVO mVo = new MenuVO();
	DAO dao = new DAO();
	
	
	private JPanel contentPane;
	private JTextField txtId;
	private JPasswordField txtPw;

	public static void main(String[] args) {
		new Login();
	}

	public Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(500, 200, 502, 525);
		setLocationRelativeTo(null);
		setTitle("오늘 뭐먹지 - 로그인");
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel pn = new JPanel();
		pn.setBounds(0, 0, 486, 486);
		contentPane.add(pn);
		pn.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("개발자 연락처 : +821056979193");
		lblNewLabel.setFont(new Font("경기천년바탕 Regular", Font.PLAIN, 13));
		lblNewLabel.setForeground(SystemColor.textInactiveText);
		lblNewLabel.setBounds(12, 455, 200, 18);
		pn.add(lblNewLabel);
		
		txtId = new JTextField();
		txtId.setFont(new Font("경기천년바탕 Regular", Font.PLAIN, 20));
		txtId.setBounds(82, 246, 180, 37);
		pn.add(txtId);
		txtId.setColumns(10);

		txtPw = new JPasswordField();
		txtPw.setColumns(10);
		txtPw.setBounds(82, 293, 180, 37);
		pn.add(txtPw);
		
		JLabel lblTitle = new JLabel("오늘 뭐 먹지?");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setForeground(new Color(0, 0, 0));
		lblTitle.setFont(new Font("경기천년바탕 Bold", Font.BOLD, 40));
		lblTitle.setBounds(45, 84, 396, 94);
		pn.add(lblTitle);
		
		JButton btnLogin = new JButton("로그인");
		
		btnLogin.setBackground(new Color(255, 228, 225));
		btnLogin.setFont(new Font("경기천년바탕 Bold", Font.PLAIN, 26));
		btnLogin.setBorderPainted(true);
		btnLogin.setBounds(274, 246, 106, 83);
		pn.add(btnLogin);
		
		JButton btnFindId = new JButton("아이디 찾기");
		
		btnFindId.setBackground(new Color(255, 228, 225));
		btnFindId.setFont(new Font("경기천년바탕 Regular", Font.BOLD, 13));
		btnFindId.setBounds(82, 374, 97, 23);
		pn.add(btnFindId);
		
		JButton btnFindPwd = new JButton("비번 찾기");
		
		btnFindPwd.setBackground(new Color(255, 228, 225));
		btnFindPwd.setFont(new Font("경기천년바탕 Regular", Font.BOLD, 13));
		btnFindPwd.setBounds(194, 374, 97, 23);
		pn.add(btnFindPwd);
		
		JButton btnJoin = new JButton("회원 가입");
		
		btnJoin.setBackground(new Color(255, 228, 225));
		btnJoin.setFont(new Font("경기천년바탕 Regular", Font.BOLD, 13));
		btnJoin.setBounds(306, 374, 97, 23);
		pn.add(btnJoin);
		
		JButton btnExit = new JButton("끝내기");
		
		btnExit.setBackground(SystemColor.activeCaptionBorder);
		btnExit.setFont(new Font("경기천년바탕 Regular", Font.BOLD, 13));
		btnExit.setBounds(377, 453, 97, 23);
		pn.add(btnExit);
		
		JLabel lblBackground = new JLabel("");
		lblBackground.setVerticalAlignment(SwingConstants.TOP);
		lblBackground.setIcon(new ImageIcon(".\\eatImages\\Login.jpg"));
		
		lblBackground.setBounds(-7, 0, 500, 486);
		pn.add(lblBackground);
		setVisible(true);
		
		/*******************************************************************************************/
		// 로그인 버튼에 마우스 올리면 아이콘 변하게 하고싶은뎅,,,
		
		// 로그인 버튼
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = txtId.getText();
				char[] pw = txtPw.getPassword();
				
				uVo = dao.LoginChk(id, pw);
				
				if(uVo == null) {
					JOptionPane.showMessageDialog(null, "로그인 정보가 올바르지 않습니다. ", "로그인 오류", JOptionPane.ERROR_MESSAGE);
				} else {
					dao.updateMenu(uVo);
					dispose();
					new Main(uVo);
				}
				
			}
		});
		
		// 회원가입 버튼
		btnJoin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Join();
			}
		});
		
		// 아이디 찾기
		btnFindId.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new FindId();
			}
		});
		
		// 비밀번호 찾기
		btnFindPwd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new FindPw();
			}
		});
		
		// 끝내기 버튼
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int ans = JOptionPane.showConfirmDialog(null, "정말 종료하시겠습니까? ", "진행창", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				
				if(ans == 0) {
					System.exit(0);
				} 
			}
		});
		
		// 엔터키 처리
		txtPw.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					btnLogin.doClick();
				}
			}
		});

		txtId.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					txtPw.requestFocus();
				}
			}
		});
	}
}
