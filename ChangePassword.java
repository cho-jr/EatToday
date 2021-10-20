package eatToday;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class ChangePassword extends JFrame {
	UserVO uVo = new UserVO();
	DAO dao = new DAO();
	JPanel contentPane;
	JPasswordField txtPw;
	JPasswordField txtPwChk;
	JLabel lblTitle, lblPw, lblPwChk,lblTitle_1;
	JButton btnSet, btnCancle;

	public ChangePassword() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 300, 300);
		setLocationRelativeTo(null);
		setTitle("오늘 뭐먹지 - 비밀번호 변경");
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblTitle = new JLabel("비밀번호,");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("경기천년바탕 Bold", Font.PLAIN, 26));
		lblTitle.setBackground(new Color(255, 204, 204));
		lblTitle.setBounds(0, 0, 284, 46);
		contentPane.add(lblTitle);
		
		txtPw = new JPasswordField();
		txtPw.setBounds(142, 99, 130, 30);
		contentPane.add(txtPw);
		
		txtPwChk = new JPasswordField();
		txtPwChk.setBounds(142, 139, 130, 30);
		contentPane.add(txtPwChk);
		
		lblPw = new JLabel("비  밀  번  호");
		lblPw.setFont(new Font("경기천년바탕 Regular", Font.PLAIN, 19));
		lblPw.setHorizontalAlignment(SwingConstants.CENTER);
		lblPw.setBounds(10, 99, 120, 30);
		contentPane.add(lblPw);
		
		lblPwChk = new JLabel("비밀번호 확인");
		lblPwChk.setFont(new Font("경기천년바탕 Regular", Font.PLAIN, 19));
		lblPwChk.setHorizontalAlignment(SwingConstants.CENTER);
		lblPwChk.setBounds(10, 140, 120, 30);
		contentPane.add(lblPwChk);
		
		btnSet = new JButton("변  경");
		btnSet.setBackground(new Color(230, 230, 250));
		
		btnSet.setFont(new Font("경기천년바탕 Regular", Font.PLAIN, 19));
		btnSet.setBounds(34, 205, 90, 30);
		contentPane.add(btnSet);
		
		btnCancle = new JButton("취  소");
		btnCancle.setBackground(new Color(230, 230, 250));
		
		btnCancle.setFont(new Font("경기천년바탕 Regular", Font.PLAIN, 19));
		btnCancle.setBounds(158, 205, 90, 30);
		contentPane.add(btnCancle);
		
		lblTitle_1 = new JLabel("\n너도  바뀔 수 있어");
		lblTitle_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle_1.setFont(new Font("경기천년바탕 Bold", Font.PLAIN, 26));
		lblTitle_1.setBackground(new Color(255, 204, 204));
		lblTitle_1.setBounds(0, 40, 284, 46);
		contentPane.add(lblTitle_1);
		
		setVisible(true);
		/***********************************************************/
		
		// 변경 ///////~~~~~~~~
		btnSet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 같으면 변경사항 dao에 저장하고 창 닫기
				String pw = "";
				String pwc = "";
				for(char p: txtPw.getPassword()) {pw += p;}
				for(char p: txtPwChk.getPassword()) {pwc += p;}
				
				if(pw.equals(pwc)) {
					dao.changePassword(uVo, pw);	//~
					JOptionPane.showMessageDialog(null, "비밀번호가 변경되었습니다. ");
					dispose();
				} else {
					JOptionPane.showMessageDialog(null, "비밀번호가 일치하지 않습니다. 다시 확인해주세요. ");
				}
			}
		});
		
		// 취소
		btnCancle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		
	}
}
