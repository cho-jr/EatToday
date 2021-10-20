package eatToday;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class Tournament extends JFrame {
	DAO dao = new DAO();
	MenuVO mVo = new MenuVO();
	ArrayList<MenuVO> mVos = new ArrayList<MenuVO>();
	MenuVO selectedMenu1 = null;
	MenuVO selectedMenu2 = null;
	ArrayList<Integer> numberList = new ArrayList<Integer>();
	ArrayList<MenuVO> vData = new ArrayList<MenuVO>();
	int cnt = 0;
	
	private JPanel contentPane;
	int round = 1;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	
	public Tournament(UserVO uVo) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 733, 502);
		setLocationRelativeTo(null);
		setTitle("오늘 뭐먹지 - 토너먼트");
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(230, 230, 250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel pnTop = new JPanel();
		pnTop.setBackground(new Color(230, 230, 250));
		pnTop.setBounds(0, 0, 717, 83);
		contentPane.add(pnTop);
		pnTop.setLayout(null);
		
		JComboBox<String> comboList = new JComboBox<String>();
		comboList.addItem("AllMenu");
		comboList.addItem(uVo.getId() + "_1");
		comboList.addItem(uVo.getId() + "_2");
		comboList.setFont(new Font("경기천년바탕 Regular", Font.BOLD, 19));
		comboList.setBackground(new Color(255, 228, 225));
		
		comboList.setBounds(9, 21, 190, 40);
		pnTop.add(comboList);
		
		JButton btnStart = new JButton("시 작");
		
		btnStart.setFont(new Font("경기천년바탕 Regular", Font.BOLD, 19));
		btnStart.setBackground(new Color(255, 228, 225));
		btnStart.setBounds(208, 21, 160, 40);
		pnTop.add(btnStart);
		
		JButton btnQuikSelect = new JButton("한 번에 선택");
		
		btnQuikSelect.setFont(new Font("경기천년바탕 Regular", Font.BOLD, 19));
		btnQuikSelect.setBackground(new Color(255, 228, 225));
		btnQuikSelect.setBounds(377, 21, 160, 40);
		pnTop.add(btnQuikSelect);
		
		JButton btnExit = new JButton("종 료");
		
		btnExit.setFont(new Font("경기천년바탕 Regular", Font.BOLD, 19));
		btnExit.setBackground(new Color(255, 228, 225));
		btnExit.setBounds(546, 21, 160, 40);
		pnTop.add(btnExit);
		
		JPanel pnContent = new JPanel();
		pnContent.setBackground(new Color(230, 230, 250));
		pnContent.setBounds(0, 83, 717, 380);
		contentPane.add(pnContent);
		pnContent.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("VS");
		lblNewLabel.setFont(new Font("경기천년바탕 Regular", Font.BOLD, 40));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(312, 165, 90, 50);
		pnContent.add(lblNewLabel);
		
		JPanel pnLeft = new JPanel();
		pnLeft.setBounds(6, 80, 300, 220);
		pnContent.add(pnLeft);
		pnLeft.setLayout(null);
		
		JLabel lblLeftShop = new JLabel("상호");
		lblLeftShop.setHorizontalAlignment(SwingConstants.CENTER);
		lblLeftShop.setFont(new Font("경기천년바탕 Regular", Font.BOLD, 30));
		lblLeftShop.setBounds(12, 17, 276, 50);
		pnLeft.add(lblLeftShop);
		
		JLabel lblLeftMenu = new JLabel("메뉴");
		lblLeftMenu.setHorizontalAlignment(SwingConstants.CENTER);
		lblLeftMenu.setFont(new Font("경기천년바탕 Regular", Font.BOLD, 30));
		lblLeftMenu.setBounds(12, 84, 276, 50);
		pnLeft.add(lblLeftMenu);
		
		JLabel lblLeftLocate = new JLabel("위치");
		lblLeftLocate.setHorizontalAlignment(SwingConstants.CENTER);
		lblLeftLocate.setFont(new Font("경기천년바탕 Regular", Font.BOLD, 30));
		lblLeftLocate.setBounds(12, 151, 276, 50);
		pnLeft.add(lblLeftLocate);
		
		JPanel pnRight = new JPanel();
		pnRight.setBounds(408, 80, 300, 220);
		pnContent.add(pnRight);
		pnRight.setLayout(null);
		
		JLabel lblRightShop = new JLabel("상호");
		lblRightShop.setHorizontalAlignment(SwingConstants.CENTER);
		lblRightShop.setFont(new Font("경기천년바탕 Regular", Font.BOLD, 30));
		lblRightShop.setBounds(12, 17, 276, 50);
		pnRight.add(lblRightShop);
		
		JLabel lblRightMenu = new JLabel("메뉴");
		lblRightMenu.setHorizontalAlignment(SwingConstants.CENTER);
		lblRightMenu.setFont(new Font("경기천년바탕 Regular", Font.BOLD, 30));
		lblRightMenu.setBounds(12, 84, 276, 50);
		pnRight.add(lblRightMenu);
		
		JLabel lblRightLocate = new JLabel("위치");
		lblRightLocate.setHorizontalAlignment(SwingConstants.CENTER);
		lblRightLocate.setFont(new Font("경기천년바탕 Regular", Font.BOLD, 30));
		lblRightLocate.setBounds(12, 151, 276, 50);
		pnRight.add(lblRightLocate);
		
		JRadioButton rdbtnLeft = new JRadioButton("선택");
		buttonGroup.add(rdbtnLeft);
		rdbtnLeft.setFont(new Font("경기천년바탕 Regular", Font.BOLD, 19));
		rdbtnLeft.setBounds(100, 318, 122, 26);
		pnContent.add(rdbtnLeft);
		
		JRadioButton rdbtnRight = new JRadioButton("선택");
		buttonGroup.add(rdbtnRight);
		rdbtnRight.setFont(new Font("경기천년바탕 Regular", Font.BOLD, 19));
		rdbtnRight.setBounds(501, 318, 122, 26);
		pnContent.add(rdbtnRight);
		
		JButton btnNext = new JButton("다  음");
		btnNext.setBackground(new Color(178, 203, 225));
		
		btnNext.setFont(new Font("경기천년바탕 Regular", Font.BOLD, 30));
		btnNext.setBounds(294, 312, 141, 51);
		pnContent.add(btnNext);
		
		JButton btnPass = new JButton("");
		btnPass.setBackground(new Color(178, 203, 225));
		
		btnPass.setEnabled(false);
		btnPass.setFont(new Font("경기천년바탕 Regular", Font.BOLD, 30));
		btnPass.setBounds(294, 17, 141, 51);
		pnContent.add(btnPass);
		
		setVisible(true);
		/*********************************************************************/
		// 시작 ///~~~~~~~~~~~~~~~~~
		
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vData = dao.getListMVO(uVo, comboList.getSelectedItem().toString(), "menu");
				btnNext.setText("다  음");
				btnPass.setText(vData.size() + "강");
				for(int i = 0; i < vData.size(); i++) {
					numberList.add(i);
				}
				int rand = (int) (Math.random()*numberList.size()); 
				int selectedNum1 = numberList.get(rand);
				numberList.remove(rand);
				rand = (int) (Math.random()*numberList.size());
				int selectedNum2 = numberList.get(rand);
				numberList.remove(rand);
				
				selectedMenu1 = vData.get(selectedNum1);
				selectedMenu2 = vData.get(selectedNum2);
				
				lblLeftShop.setText(selectedMenu1.getShop());
				lblLeftMenu.setText(selectedMenu1.getMenu());
				lblLeftLocate.setText(selectedMenu1.getLocate());
				
				lblRightShop.setText(selectedMenu2.getShop());
				lblRightMenu.setText(selectedMenu2.getMenu());
				lblRightLocate.setText(selectedMenu2.getLocate());
			}
		});
		
		// 다음
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(vData.size() == 2) {
					// 최종 선택
					if(rdbtnLeft.isSelected() == true) {
						mVos.add(selectedMenu1);
					} else if(rdbtnRight.isSelected() == true) {
						mVos.add(selectedMenu2);
					} else {
						return;
					}
					dao.chooseMenu(uVo, mVos.get(0));
					JOptionPane.showMessageDialog(null, uVo.getName()+"님의 선택은\n" + mVos.get(0).getShop() + "에서 " + mVos.get(0).getMenu() + " 즐기기 ^~^");
					dispose();
				}

				if(numberList.size() == 1) {
					if(rdbtnLeft.isSelected() == true) {
						mVos.add(selectedMenu1);
					} else if(rdbtnRight.isSelected() == true) {
						mVos.add(selectedMenu2);
					} else {
						return;
					}
					
					lblLeftShop.setText(vData.get(numberList.get(0)).getShop());
					lblLeftMenu.setText(vData.get(numberList.get(0)).getMenu());
					lblLeftLocate.setText(vData.get(numberList.get(0)).getLocate());
					
					lblRightShop.setText("부 전 승");
					lblRightMenu.setText("");
					lblRightLocate.setText("");
					
					mVos.add(vData.get(numberList.get(0)));

					btnNext.setEnabled(false);
					btnPass.setEnabled(true);
				} else if(numberList.size()>=0) {
					if(rdbtnLeft.isSelected() == true) {
						mVos.add(selectedMenu1);
					} else if(rdbtnRight.isSelected() == true) {
						mVos.add(selectedMenu2);
					} else {
						return;
					}
				
					if(numberList.size()==0){
						btnNext.setEnabled(false);
						btnPass.setEnabled(true);
						
					} else {
						int rand = (int) (Math.random()*numberList.size()); 
						int selectedNum1 = numberList.get(rand);
						numberList.remove(rand);
						rand = (int) (Math.random()*numberList.size());
						int selectedNum2 = numberList.get(rand);
						numberList.remove(rand);
						
						selectedMenu1 = vData.get(selectedNum1);
						selectedMenu2 = vData.get(selectedNum2);
						
						lblLeftShop.setText(selectedMenu1.getShop());
						lblLeftMenu.setText(selectedMenu1.getMenu());
						lblLeftLocate.setText(selectedMenu1.getLocate());
						
						lblRightShop.setText(selectedMenu2.getShop());
						lblRightMenu.setText(selectedMenu2.getMenu());
						lblRightLocate.setText(selectedMenu2.getLocate());
					}
				} 
			}
		});
		
		// 강
		btnPass.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vData.clear();
				vData.addAll(mVos);
				mVos.clear();
				if(vData.size()>2) {
					btnPass.setText(vData.size() + "강");
				} else if(vData.size() == 2) {
					btnPass.setText("결승");
					btnPass.setEnabled(false);
					btnNext.setText("최종 선택!");
				}
				
				numberList.clear();

				for(int i = 0; i < vData.size(); i++) {
					numberList.add(i);
				}

				int rand = (int) (Math.random()*numberList.size()); 
				int selectedNum1 = numberList.get(rand);
				numberList.remove(rand);
				rand = (int) (Math.random()*numberList.size());
				int selectedNum2 = numberList.get(rand);
				numberList.remove(rand);
				
				selectedMenu1 = vData.get(selectedNum1);
				selectedMenu2 = vData.get(selectedNum2);
				
				lblLeftShop.setText(selectedMenu1.getShop());
				lblLeftMenu.setText(selectedMenu1.getMenu());
				lblLeftLocate.setText(selectedMenu1.getLocate());
				
				lblRightShop.setText(selectedMenu2.getShop());
				lblRightMenu.setText(selectedMenu2.getMenu());
				lblRightLocate.setText(selectedMenu2.getLocate());
				
				btnNext.setEnabled(true);
				btnPass.setEnabled(false);
			}
		});
		// 한 번에 선택
		btnQuikSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String restMenu= "";
				int cnt = 1;
				for(MenuVO mv: vData) {
					String shop = mv.getShop();
					String menu = mv.getMenu();
					
					restMenu += cnt +". " + shop + "에서 " + menu+ "\n";
					cnt++;
				}
				
				String number = "";
				number = JOptionPane.showInputDialog(null, "번호를 입력해주세요.\n" + restMenu, "토너먼트 - 한번에 선택하기", JOptionPane.PLAIN_MESSAGE);
				if((number != null)) {
					if(!number.equals("")) {
						int num = Integer.parseInt(number) - 1;
						dao.chooseMenu(uVo, vData.get(num));
						JOptionPane.showMessageDialog(null, uVo.getName()+"님의 선택은\n" + vData.get(num).getShop() + "에서 " + vData.get(num).getMenu() + " 즐기기 ^~^");
						dispose();
					}
				}
			}
		});
		
		// 종료
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
	}
}
