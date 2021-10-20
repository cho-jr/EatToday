package eatToday;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class Search extends JFrame {
	DAO dao = new DAO();
	MenuVO mVo = new MenuVO();
	
	private JPanel contentPane;
	private JTable tblContent;
	private JTextField txtSearch;
	
	private DefaultTableModel defaultTableModel;
	private Vector<String> headings;
	@SuppressWarnings("rawtypes")
	private Vector vData;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Search(UserVO uVo) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 685, 548);
		setLocationRelativeTo(null);
		setTitle("오늘 뭐먹지 - 목록 조회");
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
		scrollPane.setBounds(0, 0, 669, 437);
		
		scrollPane.setViewportView(tblContent);
		pnContent.add(scrollPane);
		
		setVisible(true);
		/****************************************************/
		
		// 조건 검색
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sortBy = comboSearch.getSelectedItem().toString();
				String list = comboList.getSelectedItem().toString();
				String strSearch = txtSearch.getText();
				
				if(list.contains("_1")) {
					list = "user_1";
				}else if(list.contains("_2")) {
					list = "user_2";
				}
				// switch 로 변경?
				if(sortBy.equals("메뉴")) {
					vData = dao.getSearch(uVo, list, "menu", strSearch);
				} else if(sortBy.equals("선호도")) {
					vData = dao.getSearch(uVo, list, "prefer", strSearch);
				} else if(sortBy.equals("위치")) {
					vData = dao.getSearch(uVo, list, "locate", strSearch);
				} else if(sortBy.equals("분류")) {
					vData = dao.getSearch(uVo, list, "classi", strSearch);
				} else if(sortBy.equals("가게 이름")) {
					vData = dao.getSearch(uVo, list, "shop", strSearch);
				} 
				
				if(vData.size() == 0) {
					JOptionPane.showMessageDialog(null, "검색된 자료가 없습니다.");
				} else {
					defaultTableModel.setDataVector(vData, headings);
				}
			}
		});
		
		// 전체 검색
		btnList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sortBy = comboSearch.getSelectedItem().toString();
				// switch 로 변경?
				if(sortBy.equals("메뉴")) {
					vData = dao.getList(uVo, comboList.getSelectedItem().toString(), "menu");
				} else if(sortBy.equals("선호도")) {
					vData = dao.getList(uVo, comboList.getSelectedItem().toString(), "prefer");
				} else if(sortBy.equals("위치")) {
					vData = dao.getList(uVo, comboList.getSelectedItem().toString(), "locate");
				} else if(sortBy.equals("분류")) {
					vData = dao.getList(uVo, comboList.getSelectedItem().toString(), "classi");
				} else if(sortBy.equals("가게 이름")) {
					vData = dao.getList(uVo, comboList.getSelectedItem().toString(), "shop");
				} 
				
				if(vData.size() == 0) {
					JOptionPane.showMessageDialog(null, "검색된 자료가 없습니다.");
				}else {
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
		
		// 엔터키
		comboList.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(java.awt.event.KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					btnList.doClick();
				}
			}
		});
		
		txtSearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(java.awt.event.KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					btnSearch.doClick();
				}
			}
		});
	}
}
