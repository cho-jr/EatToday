package eatToday;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class FindPw extends JFrame {
	DAO dao = new DAO();
	UserVO uVo = new UserVO();
	private JPanel contentPane;
	private final JPanel panel = new JPanel();
	private JLabel lblId;
	private JLabel lblBirth;
	private JTextField txtId;
	private JTextField txtName;

	public FindPw() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 502,300);
		setLocationRelativeTo(null);
		setTitle("오늘 뭐먹지 - 비밀번호 찾기");
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		panel.setBackground(new Color(178, 203, 225));
		panel.setBounds(0, 0, 486, 261);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblTitle = new JLabel("비밀번호, 잊어도 괜찮아");
		lblTitle.setBounds(0, 0, 486, 69);
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("경기천년바탕 Bold", Font.PLAIN, 30));
		lblTitle.setBackground(new Color(255, 204, 204));
		panel.add(lblTitle);
		
		lblId = new JLabel("아     이     디");
		lblId.setHorizontalAlignment(SwingConstants.CENTER);
		lblId.setFont(new Font("경기천년바탕 Regular", Font.BOLD, 19));
		lblId.setBounds(32, 70, 130, 40);
		panel.add(lblId);
		
		lblBirth = new JLabel("생  년   월  일");
		lblBirth.setHorizontalAlignment(SwingConstants.CENTER);
		lblBirth.setFont(new Font("경기천년바탕 Regular", Font.BOLD, 19));
		lblBirth.setBounds(32, 170, 130, 40);
		panel.add(lblBirth);
		
		txtId = new JTextField();
		txtId.setBackground(new Color(230, 230, 250));
		txtId.setFont(new Font("경기천년바탕 Regular", Font.PLAIN, 19));
		txtId.setColumns(10);
		txtId.setBounds(180, 70, 260, 40);
		panel.add(txtId);
		
		JComboBox<Integer> comboYear = new JComboBox<Integer>();
		comboYear.setBackground(new Color(230, 230, 250));
		for(int i = 1920; i < 2007; i++) {
			comboYear.addItem(i);
		}
		comboYear.setBounds(180, 178, 90, 25);
		panel.add(comboYear);
		
		JComboBox<Integer> comboMonth = new JComboBox<Integer>();
		comboMonth.setBackground(new Color(230, 230, 250));
		for(int i = 1; i < 13; i++) {
			comboMonth.addItem(i);
		}
		comboMonth.setBounds(289, 178, 75, 25);
		panel.add(comboMonth);
		
		JComboBox<Integer> comboDate = new JComboBox<Integer>();
		comboDate.setBackground(new Color(230, 230, 250));
		for(int i = 1; i < 32; i++) {
			comboDate.addItem(i);
		}
		comboDate.setBounds(383, 178, 75, 25);
		panel.add(comboDate);
		
		JButton btnSearch = new JButton("조회");
		btnSearch.setBackground(new Color(211, 211, 211));
		
		btnSearch.setFont(new Font("경기천년바탕 Regular", Font.PLAIN, 19));
		btnSearch.setBounds(289, 219, 90, 30);
		panel.add(btnSearch);
		
		JButton btnCancle = new JButton("나가기");
		btnCancle.setBackground(new Color(211, 211, 211));
		btnCancle.setFont(new Font("경기천년바탕 Regular", Font.PLAIN, 19));
		btnCancle.setBounds(388, 219, 90, 30);
		panel.add(btnCancle);
		
		JLabel lblName = new JLabel("이             름");
		lblName.setHorizontalAlignment(SwingConstants.CENTER);
		lblName.setFont(new Font("경기천년바탕 Regular", Font.BOLD, 19));
		lblName.setBounds(32, 120, 130, 40);
		panel.add(lblName);
		
		txtName = new JTextField();
		txtName.setBackground(new Color(230, 230, 250));
		txtName.setFont(new Font("경기천년바탕 Regular", Font.PLAIN, 19));
		txtName.setColumns(10);
		txtName.setBounds(180, 120, 260, 40);
		panel.add(txtName);
		
		setVisible(true);
		
		/*********************************************************/
		
		// 비밀번호 조회
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = txtId.getText();
				String name = txtName.getText();
				String birthDay = comboYear.getSelectedItem().toString() + "-" + comboMonth.getSelectedItem().toString() + "-" + comboDate.getSelectedItem().toString();
				
				String pw = dao.FindPw(id, name, birthDay);
				
				if((pw.equals("")) ||(pw == null)) {
					JOptionPane.showMessageDialog(null, "일치하는 정보가 없습니다. ");
				} else {
					JOptionPane.showMessageDialog(null, "당신의 비밀번호는 '" + pw +"'입니다. \n 이젠 잊지 않기로 약속해요.");
				}
			}
		});
		
		// 취소
		btnCancle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		// 엔터키
		txtId.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(java.awt.event.KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					txtName.requestFocus();
				}
			}
		});
		
		txtName.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(java.awt.event.KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					comboYear.requestFocus();
				}
			}
		});
		
		comboDate.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(java.awt.event.KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					btnSearch.doClick();
				}
			}
		});
	}
}
