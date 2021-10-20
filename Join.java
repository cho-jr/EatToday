package eatToday;

import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;

@SuppressWarnings("serial")
public class Join extends JFrame{
	private JPanel contentPane;
	private JTextField txtName;
	private JPasswordField txtPassword;
	private JPasswordField txtPwdChk;
	
	UserVO uVo = new UserVO();
	MenuVO mVo = new MenuVO();
	DAO dao = new DAO();
	private JTextField txtID;
	
	boolean idChk = false;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Join() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(550, 300, 502, 487);
		setLocationRelativeTo(null);
		setTitle("오늘 뭐먹지 - 회원 가입");
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTitle = new JLabel("회원 가입");
		lblTitle.setIcon(null);
		lblTitle.setBackground(new Color(255, 204, 204));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("경기천년바탕 Bold", Font.PLAIN, 30));
		lblTitle.setBounds(0, 0, 484, 93);
		contentPane.add(lblTitle);
		
		JLabel lblName = new JLabel("성           명");
		lblName.setHorizontalAlignment(SwingConstants.CENTER);
		lblName.setFont(new Font("경기천년바탕 Regular", Font.BOLD, 19));
		lblName.setBounds(10, 120, 130, 40);
		contentPane.add(lblName);
		
		JLabel lblPwd = new JLabel("비  밀  번  호");
		lblPwd.setHorizontalAlignment(SwingConstants.CENTER);
		lblPwd.setFont(new Font("경기천년바탕 Regular", Font.BOLD, 19));
		lblPwd.setBounds(10, 200, 130, 40);
		contentPane.add(lblPwd);
		
		JLabel lblPwdChk = new JLabel("비밀번호확인");
		lblPwdChk.setHorizontalAlignment(SwingConstants.CENTER);
		lblPwdChk.setFont(new Font("경기천년바탕 Regular", Font.BOLD, 19));
		lblPwdChk.setBounds(10, 240, 130, 40);
		contentPane.add(lblPwdChk);
		
		txtName = new JTextField();
		txtName.setBackground(new Color(230, 230, 250));
		txtName.setFont(new Font("경기천년바탕 Regular", Font.PLAIN, 19));
		txtName.setBounds(160, 120, 260, 40);
		contentPane.add(txtName);
		txtName.setColumns(10);
		
		txtPassword = new JPasswordField();
		txtPassword.setBackground(new Color(230, 230, 250));
		txtPassword.setFont(new Font("Dialog", Font.PLAIN, 19));
		txtPassword.setColumns(10);
		txtPassword.setBounds(160, 200, 260, 40);
		contentPane.add(txtPassword);
		
		txtPwdChk = new JPasswordField();
		txtPwdChk.setBackground(new Color(230, 230, 250));
		txtPwdChk.setFont(new Font("Dialog", Font.PLAIN, 19));
		txtPwdChk.setColumns(10);
		txtPwdChk.setBounds(160, 240, 260, 40);
		contentPane.add(txtPwdChk);
		
		JButton btnJoin = new JButton("회원가입");
		btnJoin.setBackground(new Color(255, 228, 225));
		btnJoin.setFont(new Font("경기천년바탕 Regular", Font.PLAIN, 19));
		btnJoin.setBounds(40, 380, 130, 40);
		contentPane.add(btnJoin);
		
		JButton btnReset = new JButton("취소");
		btnReset.setBackground(new Color(255, 228, 225));
		btnReset.setFont(new Font("경기천년바탕 Regular", Font.PLAIN, 19));
		btnReset.setBounds(190, 380, 130, 40);
		contentPane.add(btnReset);
		
		JButton btnExit = new JButton("종료");
		btnExit.setBackground(new Color(255, 228, 225));
		btnExit.setFont(new Font("경기천년바탕 Regular", Font.PLAIN, 19));
		btnExit.setBounds(340, 380, 130, 40);
		contentPane.add(btnExit);
		
		JLabel lblID = new JLabel("아    이    디");
		lblID.setHorizontalAlignment(SwingConstants.CENTER);
		lblID.setFont(new Font("경기천년바탕 Regular", Font.BOLD, 19));
		lblID.setBounds(10, 160, 130, 40);
		contentPane.add(lblID);
		
		txtID = new JTextField();
		txtID.setBackground(new Color(230, 230, 250));
		txtID.setFont(new Font("경기천년바탕 Regular", Font.PLAIN, 19));
		txtID.setColumns(10);
		txtID.setBounds(160, 160, 200, 40);
		contentPane.add(txtID);
		
		JButton btnIdChk = new JButton("중복확인");
		btnIdChk.setBackground(new Color(230, 230, 250));
		btnIdChk.setFont(new Font("경기천년바탕 Regular", Font.PLAIN, 13));
		btnIdChk.setMargin(new Insets(3, 3, 3, 3));
		btnIdChk.setBounds(360, 160, 60, 40);
		contentPane.add(btnIdChk);
		
		JLabel lblBirth = new JLabel("생  년  월  일");
		lblBirth.setHorizontalAlignment(SwingConstants.CENTER);
		lblBirth.setFont(new Font("경기천년바탕 Regular", Font.BOLD, 19));
		lblBirth.setBounds(10, 280, 130, 40);
		contentPane.add(lblBirth);
		
		JComboBox comboYear = new JComboBox();
		comboYear.setBackground(new Color(230, 230, 250));
		comboYear.setBounds(160, 290, 90, 25);
		for(int i = 1920; i < 2007; i++) {
			comboYear.addItem(i);
		}
		contentPane.add(comboYear);
		
		JComboBox comboMonth = new JComboBox();
		comboMonth.setBackground(new Color(230, 230, 250));
		comboMonth.setBounds(260, 290, 75, 25);
		for(int i = 1; i < 13; i++) {
			comboMonth.addItem(i);
		}
		contentPane.add(comboMonth);
		
		JComboBox comboDate = new JComboBox();
		comboDate.setBackground(new Color(230, 230, 250));
		comboDate.setBounds(345, 290, 75, 25);
		for(int i = 1; i < 31; i++) {
			comboDate.addItem(i);
		}
		contentPane.add(comboDate);
		
		JLabel lblBackground = new JLabel("");
		lblBackground.setIcon(new ImageIcon("eatImages\\Join.jpg"));
		lblBackground.setBounds(0, 0, 484, 448);
		contentPane.add(lblBackground);

		setVisible(true);
		
		/*-----------------------------------------------------------------------------------------------*/
		
		// 회원 가입
		btnJoin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = txtName.getText();
				String id = txtID.getText();
				String pw = String.valueOf(txtPassword.getPassword());
				String pwChk = String.valueOf(txtPwdChk.getPassword());
				String birthDay = comboYear.getSelectedItem().toString() + "-" + comboMonth.getSelectedItem().toString() + "-" + comboDate.getSelectedItem().toString();
				
				if(!Pattern.matches("^[가-힣]*$", name.trim())) {
					JOptionPane.showMessageDialog(null, "이름을 입력하세요. \n 이름은 한글 2~19 자리만 가능합니다. ");
					txtName.requestFocus();
				} else if(name.trim().length()>20 ||name.trim().length()<2) {
					JOptionPane.showMessageDialog(null, "이름은 2~19자리만 가능합니다. ");
					txtID.requestFocus();
				} else if(!Pattern.matches("^[0-9a-zA-Z]*$", id.trim())) {
					JOptionPane.showMessageDialog(null, " 아이디를 입력하세요. \n 아이디는 4~20자리 영문자와 숫자만 가능합니다. ");
					txtID.requestFocus();
				} else if(id.trim().length()>20 ||id.trim().length()<4) {
					JOptionPane.showMessageDialog(null, "아이디는 4~20자리 영문자와 숫자만 가능합니다. ");
					txtID.requestFocus();
				} else if(pw.trim().length()>20 ||pw.trim().length()<4) {
					JOptionPane.showMessageDialog(null, "비밀번호는 4~20자리만 가능합니다. ");
					txtPassword.requestFocus();
				} else if(pwChk.trim().equals("")) {
					JOptionPane.showMessageDialog(null, "비밀번호 확인을 입력하세요");
					txtPwdChk.requestFocus();
				} else {
					if(!idChk) {
						JOptionPane.showMessageDialog(null, "아이디 중복 확인 필요. ");
						return;
					}
					if(pw.equals(pwChk)) {
						uVo.setPassword(pw);
					} else {
						JOptionPane.showMessageDialog(null, "비밀번호가 일치하지 않습니다. ");
						txtPwdChk.setText("");
						return;
					}
					uVo.setName(txtName.getText());
					uVo.setId(txtID.getText());
					uVo.setBirthDay(birthDay);
					
					dao.Join(uVo);
					dao.newTable(uVo);
					
					JOptionPane.showMessageDialog(null, "회원 가입 완료");
					
					txtName.setText("");
					txtID.setText("");
					txtPassword.setText("");
					txtPwdChk.setText("");
					dispose();
				}
			}
		});
		// 아이디 중복 체크
		btnIdChk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = txtID.getText();
				
				if(!Pattern.matches("^[0-9a-zA-Z]*$", id.trim())) {
					JOptionPane.showMessageDialog(null, "아이디는 영문자와 숫자만 가능합니다. ");
					txtID.requestFocus();
				}  else if(id.trim().length()>20 ||id.trim().length()<4) {
					JOptionPane.showMessageDialog(null, "아이디는 4~20자리만 가능합니다. ");
					txtID.requestFocus();
				} else {
					if(dao.IdChk(id).equals("")) {
						int ans = JOptionPane.showConfirmDialog(null, "사용 가능한 아이디 입니다. \n이 아이디를 사용하시겠습니까?", "아이디 중복 확인", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE);
						if(ans == 0) {
							txtID.setEnabled(false);
							idChk = true;
							txtPassword.requestFocus();
						}
					} else {
						JOptionPane.showMessageDialog(null, "같은 아이디가 존재합니다. ");
						txtID.setText("");
					}
				}
			}
		});
		
		// 가입 취소
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtName.setText("");
				txtID.setText("");
				txtPassword.setText("");
				txtPwdChk.setText("");
				txtID.setEnabled(true);
				idChk = false;
			}
		});
		
		// 종료
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		// 엔터키
		txtName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(java.awt.event.KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					txtID.requestFocus();
				}
			}
		});
		
		txtID.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(java.awt.event.KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					btnIdChk.doClick();
				}
			}
		});

		btnIdChk.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					btnIdChk.doClick(); 
				}
			}
		});
		
		txtPassword.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					txtPwdChk.requestFocus();
				}
			}
		});
		
		txtPwdChk.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					comboYear.requestFocus();
				}
			}
		});
		
		comboDate.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					btnJoin.doClick(); 
				}
			}
		});
	}
}
