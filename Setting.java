package eatToday;

import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class Setting extends JFrame {
	static MenuVO mVo = new MenuVO();
	DAO dao = new DAO();
	JPanel contentPane;
	JTextField txtName;
	JTextField txtId;
	JTextField txtPw;
	JTextField txtBirthDay;
	JTextField txtBestMenu;
	JLabel lblTitle, lblName, lblId, lblPassword, lblBirthday, lblBestMenu;
	JButton btnExit, btnIdChk, btnGoodBye;
	
	public Setting(UserVO uVo) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 502, 502);
		setLocationRelativeTo(null);
		setTitle("오늘 뭐먹지 - 마이페이지");
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(178, 203, 225));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblTitle = new JLabel("소중한 개인정보");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("경기천년바탕 Bold", Font.PLAIN, 30));
		lblTitle.setBackground(new Color(255, 204, 204));
		lblTitle.setBounds(0, 0, 486, 69);
		contentPane.add(lblTitle);
		
		lblName = new JLabel("이           름");
		lblName.setHorizontalAlignment(SwingConstants.CENTER);
		lblName.setFont(new Font("경기천년바탕 Regular", Font.BOLD, 19));
		lblName.setBounds(30, 87, 130, 40);
		contentPane.add(lblName);
		
		lblId = new JLabel("아    이     디");
		lblId.setHorizontalAlignment(SwingConstants.CENTER);
		lblId.setFont(new Font("경기천년바탕 Regular", Font.BOLD, 19));
		lblId.setBounds(30, 146, 130, 40);
		contentPane.add(lblId);
		
		lblPassword = new JLabel("비  밀  번  호");
		lblPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblPassword.setFont(new Font("경기천년바탕 Regular", Font.BOLD, 19));
		lblPassword.setBounds(30, 205, 130, 40);
		contentPane.add(lblPassword);
		
		lblBirthday = new JLabel("생  년  월  일");
		lblBirthday.setHorizontalAlignment(SwingConstants.CENTER);
		lblBirthday.setFont(new Font("경기천년바탕 Regular", Font.BOLD, 19));
		lblBirthday.setBounds(30, 264, 130, 40);
		contentPane.add(lblBirthday);
		
		lblBestMenu = new JLabel("최  애  메  뉴");
		lblBestMenu.setHorizontalAlignment(SwingConstants.CENTER);
		lblBestMenu.setFont(new Font("경기천년바탕 Regular", Font.BOLD, 19));
		lblBestMenu.setBounds(30, 323, 130, 40);
		contentPane.add(lblBestMenu);
		
		btnExit = new JButton("나 가 기");
		
		btnExit.setFont(new Font("경기천년바탕 Regular", Font.PLAIN, 19));
		btnExit.setBackground(new Color(230, 230, 250));
		btnExit.setBounds(314, 402, 130, 40);
		contentPane.add(btnExit);
		
		txtName = new JTextField();
		txtName.setEditable(false);
		txtName.setFont(new Font("경기천년바탕 Regular", Font.PLAIN, 19));
		txtName.setColumns(10);
		txtName.setBackground(new Color(255, 228, 225));
		txtName.setBounds(184, 87, 260, 40);
		contentPane.add(txtName);
		
		txtId = new JTextField();
		txtId.setFont(new Font("경기천년바탕 Regular", Font.PLAIN, 19));
		txtId.setColumns(10);
		txtId.setBackground(new Color(255, 228, 225));
		txtId.setBounds(184, 146, 260, 40);
		contentPane.add(txtId);
		
		txtPw = new JTextField();
		txtPw.setText("알려드릴 수 없어요");
		txtPw.setEditable(false);
		txtPw.setFont(new Font("경기천년바탕 Regular", Font.PLAIN, 19));
		txtPw.setColumns(10);
		txtPw.setBackground(new Color(255, 228, 225));
		txtPw.setBounds(184, 205, 200, 40);
		contentPane.add(txtPw);
		
		txtBirthDay = new JTextField();
		txtBirthDay.setEditable(false);
		txtBirthDay.setFont(new Font("경기천년바탕 Regular", Font.PLAIN, 19));
		txtBirthDay.setColumns(10);
		txtBirthDay.setBackground(new Color(255, 228, 225));
		txtBirthDay.setBounds(184, 264, 260, 40);
		contentPane.add(txtBirthDay);
		
		txtBestMenu = new JTextField();
		txtBestMenu.setEditable(false);
		txtBestMenu.setFont(new Font("경기천년바탕 Regular", Font.PLAIN, 19));
		txtBestMenu.setColumns(10);
		txtBestMenu.setBackground(new Color(255, 228, 225));
		txtBestMenu.setBounds(184, 323, 260, 40);
		contentPane.add(txtBestMenu);
		
		btnIdChk = new JButton("변경하기");
		
		btnIdChk.setMargin(new Insets(3, 3, 3, 3));
		btnIdChk.setFont(new Font("경기천년바탕 Regular", Font.PLAIN, 13));
		btnIdChk.setBackground(new Color(230, 230, 250));
		btnIdChk.setBounds(385, 205, 60, 40);
		contentPane.add(btnIdChk);
		
		setVisible(true);
		/*********************************************************************/
		
		txtName.setText(uVo.getName());
		txtId.setText(uVo.getId());
		txtBirthDay.setText(uVo.getBirthDay());
		txtBestMenu.setText(dao.getBestMenu(uVo));		
		
		btnGoodBye = new JButton("회원탈퇴...");
		
		btnGoodBye.setFont(new Font("경기천년바탕 Regular", Font.PLAIN, 13));
		btnGoodBye.setBackground(new Color(230, 230, 250));
		btnGoodBye.setBounds(211, 418, 91, 24);
		contentPane.add(btnGoodBye);
		
		// 비밀번호 변경
		btnIdChk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ChangePassword();
			}
		});
		
		// 나가기
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "변경 사항을 저장했습니다. ");
				dispose();
			}
		});
		
		// 탈퇴, 계정, 테이블 삭제 /////////~~~~~~~~~~~~~~~~~~
		btnGoodBye.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int ans = JOptionPane.showConfirmDialog(null, "탈퇴하시면 모든 데이터가 지워집니다. \n정말 탈퇴하시겠습니까?", "회원 탈퇴", JOptionPane.YES_NO_OPTION);
				if(ans == 0) {
					dao.resetUser(uVo);
					JOptionPane.showMessageDialog(null, "계정이 삭제되었습니다. 함께해주셔서 감사했습니다. ", "안녕은 영원한 헤어짐은 아니겠지요..", JOptionPane.PLAIN_MESSAGE);
					dispose();
					Main.mainClose();
				}
			}
		});
	}
}
