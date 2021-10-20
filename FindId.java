package eatToday;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class FindId extends JFrame {
	DAO dao = new DAO();
	UserVO uVo = new UserVO();
	private JPanel contentPane;
	private final JPanel panel = new JPanel();
	private JLabel lblName;
	private JLabel lblBirth;
	private JTextField txtName;

	public FindId() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 502,300);
		setLocationRelativeTo(null);
		setTitle("오늘 뭐먹지 - 아이디 찾기");
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		panel.setBackground(new Color(178, 203, 225));
		panel.setBounds(0, 0, 486, 261);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblTitle = new JLabel("아이디, 잊어도 괜찮아");
		lblTitle.setBounds(0, 0, 486, 69);
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("경기천년바탕 Bold", Font.PLAIN, 30));
		lblTitle.setBackground(new Color(255, 204, 204));
		panel.add(lblTitle);
		
		lblName = new JLabel("이           름");
		lblName.setHorizontalAlignment(SwingConstants.CENTER);
		lblName.setFont(new Font("경기천년바탕 Regular", Font.BOLD, 19));
		lblName.setBounds(32, 89, 130, 40);
		panel.add(lblName);
		
		lblBirth = new JLabel("생  년  월  일");
		lblBirth.setHorizontalAlignment(SwingConstants.CENTER);
		lblBirth.setFont(new Font("경기천년바탕 Regular", Font.BOLD, 19));
		lblBirth.setBounds(32, 150, 130, 40);
		panel.add(lblBirth);
		
		txtName = new JTextField();
		txtName.setBackground(new Color(255, 228, 225));
		txtName.setFont(new Font("경기천년바탕 Regular", Font.PLAIN, 19));
		txtName.setColumns(10);
		txtName.setBounds(180, 89, 260, 40);
		panel.add(txtName);
		
		JComboBox<Integer> comboYear = new JComboBox<Integer>();
		for(int i = 1920; i < 2007; i++) {
			comboYear.addItem(i);
		}
		comboYear.setBackground(new Color(255, 228, 225));
		comboYear.setBounds(180, 158, 90, 25);
		panel.add(comboYear);
		
		JComboBox<Integer> comboMonth = new JComboBox<Integer>();
		for(int i = 1; i < 13; i++) {
			comboMonth.addItem(i);
		}
		comboMonth.setBackground(new Color(255, 228, 225));
		comboMonth.setBounds(289, 158, 75, 25);
		panel.add(comboMonth);
		
		JComboBox<Integer> comboDate = new JComboBox<Integer>();
		for(int i = 1; i < 32; i++) {
			comboDate.addItem(i);
		}
		comboDate.setBackground(new Color(255, 228, 225));
		comboDate.setBounds(383, 158, 75, 25);
		panel.add(comboDate);
		
		JButton btnSearch = new JButton("조  회");
		btnSearch.setBackground(new Color(230, 230, 250));
		
		btnSearch.setFont(new Font("경기천년바탕 Regular", Font.PLAIN, 19));
		btnSearch.setBounds(126, 200, 130, 40);
		panel.add(btnSearch);
		
		JButton btnCancle = new JButton("나 가 기");
		
		btnCancle.setBackground(new Color(230, 230, 250));
		btnCancle.setFont(new Font("경기천년바탕 Regular", Font.PLAIN, 19));
		btnCancle.setBounds(286, 200, 130, 40);
		panel.add(btnCancle);
		
		setVisible(true);
		
		/*********************************************************/
		
		// 아이디 조회
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = txtName.getText();
				String birthDay = comboYear.getSelectedItem().toString() + "-" + comboMonth.getSelectedItem().toString() + "-" + comboDate.getSelectedItem().toString();
				String id = dao.FindId(name, birthDay);
				
				if((id.equals("")) ||(id == null)) {
					JOptionPane.showMessageDialog(null, "일치하는 정보가 없습니다. ");
				} else {
					JOptionPane.showMessageDialog(null, "당신의 아이디는 '" + id +"'입니다. \n 이젠 잊지 않기로 약속해요.");
				}
			}
		});
		
		// 취소
		btnCancle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		// 엔터키 작업
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
