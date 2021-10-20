package eatToday;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class Random extends JFrame {
	static MenuVO mVo = new MenuVO();
	DAO dao = new DAO();
	private JPanel contentPane;

	public Random(UserVO uVo) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(500, 300, 502, 502);
		setLocationRelativeTo(null);
		setTitle("오늘 뭐먹지 - 랜덤 추천");
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel pnTop = new JPanel();
		pnTop.setLayout(null);
		pnTop.setBackground(new Color(230, 230, 250));
		pnTop.setBounds(0, 0, 496, 83);
		contentPane.add(pnTop);
		
		JComboBox<String> comboList = new JComboBox<String>();
		comboList.setFont(new Font("경기천년바탕 Regular", Font.BOLD, 19));
		comboList.addItem("AllMenu");
		comboList.addItem(uVo.getId() + "_1");
		comboList.addItem(uVo.getId() + "_2");
		comboList.setBackground(new Color(255, 228, 225));
		comboList.setBounds(14, 21, 106, 40);
		pnTop.add(comboList);
		
		JButton btnStart = new JButton("시 작");
		
		btnStart.setFont(new Font("경기천년바탕 Regular", Font.BOLD, 19));
		btnStart.setBackground(new Color(255, 228, 225));
		btnStart.setBounds(134, 21, 106, 40);
		pnTop.add(btnStart);
		
		JButton btnSelect = new JButton("선택");
		
		btnSelect.setFont(new Font("경기천년바탕 Regular", Font.BOLD, 19));
		btnSelect.setBackground(new Color(255, 228, 225));
		btnSelect.setBounds(254, 21, 106, 40);
		pnTop.add(btnSelect);
		
		JButton btnExit = new JButton("종 료");
		
		btnExit.setFont(new Font("경기천년바탕 Regular", Font.BOLD, 19));
		btnExit.setBackground(new Color(255, 228, 225));
		btnExit.setBounds(374, 21, 106, 40);
		pnTop.add(btnExit);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(230, 230, 250));
		panel.setBounds(0, 82, 496, 391);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblshop = new JLabel("상호");
		lblshop.setFont(new Font("경기천년바탕 Regular", Font.BOLD, 30));
		lblshop.setHorizontalAlignment(SwingConstants.CENTER);
		lblshop.setBounds(56, 52, 400, 60);
		panel.add(lblshop);
		
		JLabel lblMenu = new JLabel("메뉴");
		lblMenu.setFont(new Font("경기천년바탕 Regular", Font.BOLD, 30));
		lblMenu.setHorizontalAlignment(SwingConstants.CENTER);
		lblMenu.setBounds(56, 164, 400, 60);
		panel.add(lblMenu);
		
		JLabel lblLocate = new JLabel("위치");
		lblLocate.setFont(new Font("경기천년바탕 Regular", Font.BOLD, 30));
		lblLocate.setHorizontalAlignment(SwingConstants.CENTER);
		lblLocate.setBounds(56, 276, 400, 60);
		panel.add(lblLocate);
		
		setVisible(true);
		/*********************************************************************/
		
		// 시작		///~~~~
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<MenuVO> vData = dao.getListMVO(uVo, comboList.getSelectedItem().toString(), "menu");
				int dataSize = vData.size();
				// 랜덤 수 발생 int rand -> 0 < rand <리스트에 있는 메뉴 수
				int rand = (int) (Math.random()*dataSize);
				
				//해당 리스트[rand 번째 항목] 출력
				mVo = vData.get(rand);
				lblshop.setText(mVo.getShop());
				lblMenu.setText(mVo.getMenu());
				lblLocate.setText(mVo.getLocate());
				
			}
		});
		
		// 선택///~~~~
		btnSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!lblshop.getText().equals("상호")) {
					JOptionPane.showMessageDialog(null, uVo.getName() + "님의 선택은\n" + lblshop.getText() + "에서 " + lblMenu.getText() + " 즐기기. \n맛있게 드세요 *^^*");	////
					// 해당 메뉴 선택 회수 +1
					dao.chooseMenu(uVo, mVo);
					btnExit.doClick();
				}
			}
		});
		
		// 종료
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		// 엔터키
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					btnStart.doClick(); 
				}
			}
		});
		
		
	}
}
