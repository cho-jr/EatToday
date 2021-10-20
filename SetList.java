package eatToday;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Vector;
import java.util.regex.Pattern;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class SetList extends JFrame {
	DAO dao = new DAO();
	MenuVO mVo = new MenuVO();
	
	private JPanel contentPane;
	private JTable tblContent;
	private JTextField txtSearch;
	
	private DefaultTableModel defaultTableModel;
	private Vector<String> headings;
	@SuppressWarnings("rawtypes")
	private Vector vData;
	private JTextField txtClassi;
	private JTextField txtShop;
	private JTextField txtMenu;
	private JTextField txtLocate;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public SetList(UserVO uVo) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 685, 548);
		setLocationRelativeTo(null);
		setTitle("오늘 뭐먹지 - 리스트 설정");
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel pnTop = new JPanel();
		pnTop.setBounds(0, 0, 669, 70);
		contentPane.add(pnTop);
		pnTop.setLayout(null);
		
		txtSearch = new JTextField();
		txtSearch.setFont(new Font("경기천년바탕 Regular", Font.PLAIN, 19));
		txtSearch.setColumns(10);
		txtSearch.setBounds(195, 20, 130, 30);
		pnTop.add(txtSearch);
		
		JComboBox comboSearch = new JComboBox();
		comboSearch.setBackground(new Color(230, 230, 250));
		comboSearch.setModel(new DefaultComboBoxModel(new String[] {"메뉴", "선호도", "위치", "분류", "가게 이름"}));
		comboSearch.setFont(new Font("경기천년바탕 Regular", Font.PLAIN, 19));
		comboSearch.setBounds(100, 15, 90, 40);
		pnTop.add(comboSearch);
		
		JButton btnSearch = new JButton("조건검색");
		btnSearch.setBackground(new Color(230, 230, 250));
		
		btnSearch.setFont(new Font("경기천년바탕 Regular", Font.PLAIN, 19));
		btnSearch.setBounds(330, 15, 106, 40);
		pnTop.add(btnSearch);
		
		JButton btnList = new JButton("전체검색");
		btnList.setBackground(new Color(230, 230, 250));
		
		btnList.setFont(new Font("경기천년바탕 Regular", Font.PLAIN, 19));
		btnList.setBounds(441, 15, 106, 40);
		pnTop.add(btnList);
		
		JButton btnExit = new JButton("종 료");
		btnExit.setBackground(new Color(230, 230, 250));
		
		btnExit.setFont(new Font("경기천년바탕 Regular", Font.PLAIN, 19));
		btnExit.setBounds(552, 14, 106, 42);
		pnTop.add(btnExit);
		
		JComboBox<String> comboList = new JComboBox<String>();
		// DB에서 table 이름이 userId, userId_1, userId_2 인 것 들 가져오기
		
		comboList.addItem("AllMenu");
		comboList.addItem(uVo.getId() + "_1");
		comboList.addItem(uVo.getId() + "_2");
		comboList.setFont(new Font("경기천년바탕 Regular", Font.PLAIN, 19));
		comboList.setBackground(new Color(230, 230, 250));
		comboList.setBounds(5, 15, 90, 40);
		pnTop.add(comboList);
		
		JPanel pnContent = new JPanel();
		pnContent.setBounds(0, 72, 669, 437);
		contentPane.add(pnContent);
		pnContent.setLayout(null);
		
		headings = new Vector<String>();
		headings.add("번호");
		headings.add("분류");
		headings.add("가게 이름");
		headings.add("메뉴");
		headings.add("선호도");
		headings.add("위치");
		headings.add("선택 회수");
		
		// 데이터 전체 검색
		vData = dao.getList(uVo, comboList.getSelectedItem().toString(), "menu");
		
		defaultTableModel = new DefaultTableModel(vData, headings); 
		tblContent = new JTable(defaultTableModel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 669, 367);
		
		scrollPane.setViewportView(tblContent);
		pnContent.add(scrollPane);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 367, 669, 70);
		pnContent.add(panel);
		panel.setLayout(null);
		
		JSlider sliderPrefer = new JSlider(0, 10, 5);
		sliderPrefer.setBounds(475, 30, 110, 30);
		sliderPrefer.setMajorTickSpacing(2);
		sliderPrefer.setMinorTickSpacing(1);
		sliderPrefer.setPaintTicks(true);
		panel.add(sliderPrefer);
		
		txtClassi = new JTextField();
		txtClassi.setFont(new Font("경기천년바탕 Regular", Font.PLAIN, 19));
		txtClassi.setColumns(10);
		txtClassi.setBounds(7, 33, 110, 30);
		panel.add(txtClassi);
		
		txtShop = new JTextField();
		txtShop.setFont(new Font("경기천년바탕 Regular", Font.PLAIN, 19));
		txtShop.setColumns(10);
		txtShop.setBounds(124, 33, 110, 30);
		panel.add(txtShop);
		
		txtMenu = new JTextField();
		txtMenu.setFont(new Font("경기천년바탕 Regular", Font.PLAIN, 19));
		txtMenu.setColumns(10);
		txtMenu.setBounds(241, 33, 110, 30);
		panel.add(txtMenu);
		
		txtLocate = new JTextField();
		txtLocate.setFont(new Font("경기천년바탕 Regular", Font.PLAIN, 19));
		txtLocate.setColumns(10);
		txtLocate.setBounds(358, 33, 110, 30);
		panel.add(txtLocate);
		
		JButton btnAdd = new JButton("추가");
		btnAdd.setFont(new Font("경기천년바탕 Regular", Font.PLAIN, 19));
		
		btnAdd.setBackground(new Color(230, 230, 250));
		btnAdd.setBounds(592, 2, 70, 20);
		panel.add(btnAdd);
		
		JLabel lblClassi = new JLabel("분류");
		lblClassi.setHorizontalAlignment(SwingConstants.CENTER);
		lblClassi.setFont(new Font("경기천년바탕 Regular", Font.PLAIN, 19));
		lblClassi.setBounds(7, 3, 110, 30);
		panel.add(lblClassi);
		
		JLabel lblshop = new JLabel("가게 이름");
		lblshop.setHorizontalAlignment(SwingConstants.CENTER);
		lblshop.setFont(new Font("경기천년바탕 Regular", Font.PLAIN, 19));
		lblshop.setBounds(124, 3, 110, 30);
		panel.add(lblshop);
		
		JLabel lblMenu = new JLabel("메뉴명");
		lblMenu.setHorizontalAlignment(SwingConstants.CENTER);
		lblMenu.setFont(new Font("경기천년바탕 Regular", Font.PLAIN, 19));
		lblMenu.setBounds(241, 3, 110, 30);
		panel.add(lblMenu);
		
		JLabel lblLocate = new JLabel("위치");
		lblLocate.setHorizontalAlignment(SwingConstants.CENTER);
		lblLocate.setFont(new Font("경기천년바탕 Regular", Font.PLAIN, 19));
		lblLocate.setBounds(358, 3, 110, 30);
		panel.add(lblLocate);
		
		JLabel lblPrefer = new JLabel("선호도");
		lblPrefer.setHorizontalAlignment(SwingConstants.CENTER);
		lblPrefer.setOpaque(true);
		lblPrefer.setFont(new Font("경기천년바탕 Regular", Font.PLAIN, 19));
		lblPrefer.setBounds(475, 3, 110, 30);
		panel.add(lblPrefer);
		
		JButton btnUpdate = new JButton("수정");
		
		btnUpdate.setFont(new Font("경기천년바탕 Regular", Font.PLAIN, 19));
		btnUpdate.setBackground(new Color(230, 230, 250));
		btnUpdate.setBounds(592, 24, 70, 20);
		panel.add(btnUpdate);
		
		JButton btnDelete = new JButton("삭제");
		
		btnDelete.setFont(new Font("경기천년바탕 Regular", Font.PLAIN, 19));
		btnDelete.setBackground(new Color(230, 230, 250));
		btnDelete.setBounds(592, 46, 70, 20);
		panel.add(btnDelete);
		
		setVisible(true);
		/*********************************************************************/
		// 표에 체크박스를 넣어서 db를 수정하고 싶어요,, 
		
		// 조건 검색
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				if(headings.size()==7) {
					if(comboList.getSelectedItem().toString().contains("_1")) {
						headings.add("_1");
					} else if(comboList.getSelectedItem().toString().contains("_2")) {
						headings.add("_2");
					}
				} else {
					headings.remove(7);
					if(comboList.getSelectedItem().toString().contains("_1")) {
						headings.add("_1");
					} else if(comboList.getSelectedItem().toString().contains("_2")) {
						headings.add("_2");
					}
				}
				
				String sortBy = comboSearch.getSelectedItem().toString();
				String list = comboList.getSelectedItem().toString();
				String strSearch = txtSearch.getText();
				// switch 로 변경?
				if(sortBy.equals("메뉴")) {
					vData = dao.getSearchSet(uVo, list, "menu", strSearch);
				} else if(sortBy.equals("선호도")) {
					vData = dao.getSearchSet(uVo, list, "prefer", strSearch);
				} else if(sortBy.equals("위치")) {
					vData = dao.getSearchSet(uVo, list, "locate", strSearch);
				} else if(sortBy.equals("분류")) {
					vData = dao.getSearchSet(uVo, list, "classi", strSearch);
				} else if(sortBy.equals("가게 이름")) {
					vData = dao.getSearchSet(uVo, list, "shop", strSearch);
				} 
				
				if(vData.size() == 0) {
					JOptionPane.showMessageDialog(null, "검색된 자료가 없습니다.");
				}
				else {
					defaultTableModel.setDataVector(vData, headings);
				}
			}
		});
		
		// 전체 검색
		btnList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String sortBy = comboSearch.getSelectedItem().toString();
				
				if(headings.size()==7) {
					if(comboList.getSelectedItem().toString().contains("_1")) {
						headings.add("_1");
					} else if(comboList.getSelectedItem().toString().contains("_2")) {
						headings.add("_2");
					}
				} else {
					headings.remove(7);
					if(comboList.getSelectedItem().toString().contains("_1")) {
						headings.add("_1");
					} else if(comboList.getSelectedItem().toString().contains("_2")) {
						headings.add("_2");
					}
				}
				
				// switch 로 변경?
				if(sortBy.equals("메뉴")) {
					vData = dao.getListSet(uVo, comboList.getSelectedItem().toString(), "menu");
				} else if(sortBy.equals("선호도")) {
					vData = dao.getListSet(uVo, comboList.getSelectedItem().toString(), "prefer");
				} else if(sortBy.equals("위치")) {
					vData = dao.getListSet(uVo, comboList.getSelectedItem().toString(), "locate");
				} else if(sortBy.equals("분류")) {
					vData = dao.getListSet(uVo, comboList.getSelectedItem().toString(), "classi");
				} else if(sortBy.equals("가게 이름")) {
					vData = dao.getListSet(uVo, comboList.getSelectedItem().toString(), "shop");
				} 
				
				if(vData.size() == 0) {
					JOptionPane.showMessageDialog(null, "검색된 자료가 없습니다.");
				}
				else {
					defaultTableModel.setDataVector(vData, headings);
				}
			}
		});
		
		// 종료 버튼
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		// 추가		///~~ allMenu 에서는 신메뉴 추가
					/// user_1, user_2에서는 allMenu 중 없는거 추가
					//(가게 이름, 메뉴만 입력받고 인덱스로 바꿔서 user_1 을 1로 바꿈)
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!checkRegex()) return;
				String selectedList = comboList.getSelectedItem().toString();
				String classi = txtClassi.getText();
				String shop = txtShop.getText();
				String menu = txtMenu.getText();
				String locate = txtLocate.getText();
				int prefer = sliderPrefer.getValue();
				
				mVo.setClassi(classi);
				mVo.setShop(shop);
				mVo.setMenu(menu);
				mVo.setLocate(locate);
				mVo.setPrefer(prefer);
				if(selectedList.equals("AllMenu")) {
					// AllMenu 일 때		=> All menu에 새 메뉴 추가
					if(classi.equals("") || shop.equals("") || menu.equals("") || locate.equals("")) {
						JOptionPane.showMessageDialog(null, "정보를 모두 입력해주세요. 오늘 뭐먹지가 조금 더 편리해집니다.");
						txtClassi.requestFocus();
					} else {
						dao.addMenuData(uVo, classi, shop, menu, prefer, locate);		// 앗 mVo로 넘길껄..!! ㅜㅠ
						btnList.doClick();
						txtClassi.setText("");
						txtShop.setText("");
						txtMenu.setText("");
						txtLocate.setText("");
					}
				} else {
					// user_1, user_2 일 때 	=> 각각 테이블에 띄울 데이터 추가 (AllMenu 중에서)
					// 메뉴 인덱스 찾아서 user_1, _2 true로 바꿔줌
					int idx = dao.findMenuIndex(mVo);
					if(idx == 0) {
						JOptionPane.showMessageDialog(null, "해당 메뉴가 존재하지 않습니다. AllMenu에서 추가해주세요. ");
					} else {
						String list = "";
						if(selectedList.contains("_1")) {
							list = "user_1";
						} else if(selectedList.contains("_1")) {
							list = "user_2";
						}
						dao.addUserTable(uVo, idx, list, prefer);
						btnList.doClick();
						txtClassi.setText("");
						txtShop.setText("");
						txtMenu.setText("");
						txtLocate.setText("");
					}
				}
			}
		});
		
		// 수정
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!comboList.getSelectedItem().equals("AllMenu")) {
					JOptionPane.showMessageDialog(null, "수정은 'AllMenu'에서만 가능합니다. (선호도만 수정 가능)");
				} else {
					String classi = txtClassi.getText();
					String shop = txtShop.getText();
					String menu = txtMenu.getText();
					String locate = txtLocate.getText();
					int prefer = sliderPrefer.getValue();
					
					mVo.setClassi(classi);
					mVo.setShop(shop);
					mVo.setMenu(menu);
					mVo.setLocate(locate);
					
					// 해당 메뉴 인덱스 번호 찾기
					int idx = dao.findMenuIndex(mVo);
					if(idx == 0) {
						JOptionPane.showMessageDialog(null, "해당 메뉴가 존재하지 않습니다. AllMenu에서 추가해주세요. ");
					} else {
						// user 테이블의 prefer 변경
						dao.updateUserTable(uVo, idx, prefer);
						btnList.doClick();
					}
				}
			}
		});
		
		// 삭제
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String classi = txtClassi.getText();
				String shop = txtShop.getText();
				String menu = txtMenu.getText();
				String locate = txtLocate.getText();
				if(comboList.getSelectedItem().toString().equals("AllMenu")) {
					JOptionPane.showMessageDialog(null, "AllMenu는 삭제하실 수 없습니다. ");
				} else {
					if(classi.equals("") || shop.equals("") || menu.equals("") || locate.equals("")) {
						JOptionPane.showMessageDialog(null, "정보를 모두 입력해주세요. 오늘 뭐먹지가 조금 더 정확해집니다.");
						txtClassi.requestFocus();
					} else {
						// 메뉴 인덱스 찾아서 user_1, _2 true로 바꿔줌
						mVo.setClassi(classi);
						mVo.setShop(shop);
						mVo.setMenu(menu);
						mVo.setLocate(locate);
						int idx = dao.findMenuIndex(mVo);
						String col = ""; 
						if(comboList.getSelectedItem().toString().contains("_1")) {
							col = "user_1"; 
						} else if(comboList.getSelectedItem().toString().contains("_2")) {
							col = "user_2"; 
						}
						dao.deleteUserTable(uVo, col, idx);
						btnList.doClick();
						txtClassi.setText("");
						txtShop.setText("");
						txtMenu.setText("");
						txtLocate.setText("");
					}
				}
			}
		});
		
		// 엔터키
		comboList.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(java.awt.event.KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					btnList.doClick();
				}
			}
		});
		
		sliderPrefer.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()== KeyEvent.VK_ENTER) {
					btnAdd.doClick();
					txtClassi.requestFocus();
				}
			}
		});
		
		txtSearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()== KeyEvent.VK_ENTER) {
					btnSearch.doClick();
				}
			}
		});
	}

	protected boolean checkRegex() {
		String classi = txtClassi.getText().trim();
		String shop = txtShop.getText().trim();
		String menu = txtMenu.getText().trim();
		String locate = txtLocate.getText().trim();
		String regex = "^[가-힣 ]*$";
		boolean isRight = Pattern.matches(regex, classi) && Pattern.matches(regex, shop)
						&& Pattern.matches(regex, menu) && Pattern.matches(regex, locate);
		if(!isRight) {
			JOptionPane.showMessageDialog(null, "한글만 입력해주세요");
			return false;
		} else return true;
	}
}
