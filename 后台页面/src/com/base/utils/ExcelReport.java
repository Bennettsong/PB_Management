package com.base.utils;

import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WriteException;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import com.base.po.LandApply_view;
import com.base.po.RentMaintain;


public class ExcelReport{

	/*
	 * һ:��������ά����Excel�ļ����� (non-Javadoc)
	 * 
	 * @see com.base.service.ExcelReportService#landRentPreserveReport()
	 */	
	public void landRentPreserveReport(List<RentMaintain> list) { // ��ͷ��Ϣ
		String[] col_title = { "���", "��ʼ����", "��������", "������", "���ر��", "������",
				"�걨����", "�����ô���", "��������", "�������", "���˴�������", "�ƻ���������",
				"���޷���", "��������" };// 15����ͷ
		@SuppressWarnings("resource")
		XSSFWorkbook workbook = new XSSFWorkbook();// ����һ��Excel�ĵ�����
		Sheet sheet = workbook.createSheet();// ��������ĵ��д���Excel��
		// �����еĿ��
		sheet.setColumnWidth(0, 1300);
		sheet.setColumnWidth(1, 3000);
		sheet.setColumnWidth(2, 3000);
		sheet.setColumnWidth(3, 3000);
		sheet.setColumnWidth(4, 3000);
		sheet.setColumnWidth(5, 2800);
		sheet.setColumnWidth(6, 4500);
		sheet.setColumnWidth(7, 2000);
		sheet.setColumnWidth(8, 4500);
		sheet.setColumnWidth(9, 4500);
		sheet.setColumnWidth(10, 2500);
		sheet.setColumnWidth(11, 4000);
		sheet.setColumnWidth(12, 4000);
		sheet.setColumnWidth(13, 2500);
		sheet.setColumnWidth(14, 3000);
		sheet.setColumnWidth(15, 2000);

		/*
		 * ������ʽ
		 */
		// 1����������// ���ñ�������
		Font font_title = workbook.createFont();
		font_title.setFontName("����");
		font_title.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);// ���������ϸ
		font_title.setFontHeightInPoints((short) 18);// ���������С
		// ������ͷ����
		Font font1 = workbook.createFont();
		font1.setFontName("����");
		font1.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);// ���������ϸ
		font1.setFontHeightInPoints((short) 11);// ���������С
		// ������������1
		Font font2 = workbook.createFont();
		font2.setFontName("����");
		font2.setBoldweight(XSSFFont.BOLDWEIGHT_NORMAL);// ���������ϸ
		font2.setFontHeightInPoints((short) 11);// ���������С
		// 2��������ʽ
		// ���ñ�����ʽ
		XSSFCellStyle cs_title = workbook.createCellStyle();
		cs_title.setAlignment(XSSFCellStyle.ALIGN_CENTER);// ��������ˮƽ����
		cs_title.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);// �������崹ֱ����
		// �������õס����ҡ��ϱ߿�
		cs_title.setBorderBottom(XSSFCellStyle.BORDER_THIN);
		cs_title.setBorderLeft(XSSFCellStyle.BORDER_THIN);
		cs_title.setBorderRight(XSSFCellStyle.BORDER_THIN);
		cs_title.setBorderTop(XSSFCellStyle.BORDER_THIN);
		cs_title.setWrapText(true);// ����ʹ�õ�Ԫ������ְ��յ�Ԫ����п����Զ������У���ʾ
		cs_title.setFont(font_title);
		// ������ͷ��ʽ
		XSSFCellStyle cs1 = workbook.createCellStyle();
		cs1.setAlignment(XSSFCellStyle.ALIGN_CENTER);// ��������ˮƽ����
		cs1.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);// �������崹ֱ����
		// �������õס����ҡ��ϱ߿�
		cs1.setBorderBottom(XSSFCellStyle.BORDER_THIN);
		cs1.setBorderLeft(XSSFCellStyle.BORDER_THIN);
		cs1.setBorderRight(XSSFCellStyle.BORDER_THIN);
		cs1.setBorderTop(XSSFCellStyle.BORDER_THIN);
		cs1.setWrapText(true);// ���ÿ������Ӧ
		cs1.setFont(font1);
		// �������ľ�����ʽ
		XSSFCellStyle cs2 = workbook.createCellStyle();
		cs2.setAlignment(XSSFCellStyle.ALIGN_CENTER);// ��������ˮƽ����
		cs2.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);// �������崹ֱ����
		// �������õס����ҡ��ϱ߿�
		cs2.setBorderBottom(XSSFCellStyle.BORDER_THIN);
		cs2.setBorderLeft(XSSFCellStyle.BORDER_THIN);
		cs2.setBorderRight(XSSFCellStyle.BORDER_THIN);
		cs2.setBorderTop(XSSFCellStyle.BORDER_THIN);
		cs2.setWrapText(true);// ���ÿ������Ӧ
		cs2.setFont(font2);
		// �ϲ��� ����һ�У����һ�У���һ�У����һ�У�
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 14));// �ϲ���-����

		/*
		 * �������ݲ���
		 */
		// ��һ�ű���
		Row row1 = sheet.createRow(0);
		row1.setHeight((short) 1000);// �����еĸ߶�
		Cell cell_title = row1.createCell(0);
		cell_title.setAsActiveCell();
		cell_title.setCellValue("����ũҵ��ѧ����������Ϣ��");
		cell_title.setCellStyle(cs_title);
		// �ڶ�������Ӧ��ͷ��
		Row row2 = sheet.createRow(1);
		for (int i = 0; i < col_title.length; i++) {
			Cell cell_line = row2.createCell(i);
			cell_line.setCellValue(col_title[i]);
			cell_line.setCellStyle(cs1);
		}
		// ����֮��
		/*
		 * !!!!!!���ע����������Ҫ�������޵�����list�������ͷ�������Ȼ����滹��ÿ�������л�ȡ������ֵ�ķ���!!!!!!!!!!!!!!!!
		 * !!!!!!
		 */
		// �û�ID���Ժ�Ҫ�Ӻ�̨��ȡ�ģ�������

		/*
		 * ����list��ȡ�õ�����
		 */
		for (int i = 0; i < list.size(); i++) {
			RentMaintain line_data = list.get(i);// ȡ��list������һ������
			int line = i + 2;// ������
			Row row_line = sheet.createRow(line);// ������line��
			// ��һ����� �Զ�����1
			Cell cell0 = row_line.createCell(0);
			cell0.setCellValue(i + 1);
			cell0.setCellStyle(cs2);
			// �ڶ��п�ʼ����
			Cell cell1 = row_line.createCell(1);
			// !!!!!!!!!!�������д����line_data.getStartTime()������ȡ��ֵ��Ҫ��Ӧ������˳��Ĺ���ʣ�
			// !!!!!!!!!!!Ȼ�����ÿ�ж���ͬ�˸���!!!!!!!!!!!!!!!
			cell1.setCellValue(line_data.getStartTime());
			cell1.setCellStyle(cs2);
			// �����н�������
			Cell cell2 = row_line.createCell(2);
			cell2.setCellValue(line_data.getEndTime());
			cell2.setCellStyle(cs2);
			// �����л�����
			Cell cell3 = row_line.createCell(3);
			cell3.setCellValue(line_data.getBname());
			cell3.setCellStyle(cs2);
			// ���������ر��
			Cell cell4 = row_line.createCell(4);
			cell4.setCellValue(line_data.getLid());
			cell4.setCellStyle(cs2);
			// ������������
			Cell cell5 = row_line.createCell(5);
			cell5.setCellValue(line_data.getName());
			cell5.setCellStyle(cs2);
			// �������걨����
			Cell cell6 = row_line.createCell(6);
			cell6.setCellValue(line_data.getDeptName());
			cell6.setCellStyle(cs2);
			// �ڰ��������ô���
			Cell cell7 = row_line.createCell(7);
			cell7.setCellValue(line_data.getTimes());
			cell7.setCellStyle(cs2);
			
			/*// �ھ�����ֲ����
			Cell cell8 = row_line.createCell(8);
			cell8.setCellValue(line_data.getPlanting());
			cell8.setCellStyle(cs2);*/
			
			// ��ʮ����������
			Cell cell9 = row_line.createCell(9);
			cell9.setCellValue(line_data.getLandname());
			cell9.setCellStyle(cs2);
			// ��ʮһ���������
			Cell cell10 = row_line.createCell(10);
			cell10.setCellValue(line_data.getLandArea());
			cell10.setCellStyle(cs2);
			// ��ʮ�������˴�������
			Cell cell11 = row_line.createCell(11);
			cell11.setCellValue(line_data.getAptplanting());
			cell11.setCellStyle(cs2);
			// ��ʮ���мƻ���������
			Cell cell12 = row_line.createCell(12);
			cell12.setCellValue(line_data.getPlanting());
			cell12.setCellStyle(cs2);
			// ��ʮ�������޷���
			Cell cell13 = row_line.createCell(13);
			cell13.setCellValue(line_data.getRentMoney());
			cell13.setCellStyle(cs2);
			// ��ʮ���н�������
			Cell cell14 = row_line.createCell(14);
			cell14.setCellValue(line_data.getChargeDate());
			cell14.setCellStyle(cs2);
		}
		// ����Excel�ļ�
		// !!!!!!!!!��ʰ�~~�����·���ǽ����ɵ��ļ��ŵ���������·��!!!!!!!!!!!!!!!!!!!
		File file = new File("E://landRentPreserveReport.xlsx");
		try {
			file.createNewFile();
			FileOutputStream stream = FileUtils.openOutputStream(file);
			workbook.write(stream);
			stream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/*
	 * ��:ʵϰ����ά����Excel�ļ����� (non-Javadoc)
	 * 
	 * @see com.base.service.ExcelReportService#workLandPreserveReport()
	 */
	/*@Override
	public void workLandPreserveReport() {
		// ��ͷ��Ϣ����������������Ұ���������Ū�ģ������Ҫ��ʲô�͸ģ�������������
		String[] col_title = { "���", "��������", "��������", "�걨����", "һ���Կɳе�ʵϰѧ������",
				"�������/Ķ", "�������/ƽ����", "������" };// 8����ͷ
		@SuppressWarnings("resource")
		XSSFWorkbook workbook = new XSSFWorkbook();// ����һ��Excel�ĵ�����
		Sheet sheet = workbook.createSheet();// ��������ĵ��д���Excel��
		// �����еĿ��
		sheet.setColumnWidth(0, 1000);
		sheet.setColumnWidth(1, 3500);
		sheet.setColumnWidth(2, 3000);
		sheet.setColumnWidth(3, 3500);
		sheet.setColumnWidth(4, 4000);
		sheet.setColumnWidth(5, 4000);
		sheet.setColumnWidth(6, 4500);
		sheet.setColumnWidth(7, 3000);
		
		 * ������ʽ
		 
		// 1����������// ���ñ�������
		Font font_title = workbook.createFont();
		font_title.setFontName("����");
		font_title.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);// ���������ϸ
		font_title.setFontHeightInPoints((short) 18);// ���������С
		// ������ͷ����
		Font font1 = workbook.createFont();
		font1.setFontName("����");
		font1.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);// ���������ϸ
		font1.setFontHeightInPoints((short) 11);// ���������С
		// ������������1
		Font font2 = workbook.createFont();
		font2.setFontName("����");
		font2.setBoldweight(XSSFFont.BOLDWEIGHT_NORMAL);// ���������ϸ
		font2.setFontHeightInPoints((short) 11);// ���������С
		// 2��������ʽ
		// ���ñ�����ʽ
		XSSFCellStyle cs_title = workbook.createCellStyle();
		cs_title.setAlignment(XSSFCellStyle.ALIGN_CENTER);// ��������ˮƽ����
		cs_title.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);// �������崹ֱ����
		// �������õס����ҡ��ϱ߿�
		cs_title.setBorderBottom(XSSFCellStyle.BORDER_THIN);
		cs_title.setBorderLeft(XSSFCellStyle.BORDER_THIN);
		cs_title.setBorderRight(XSSFCellStyle.BORDER_THIN);
		cs_title.setBorderTop(XSSFCellStyle.BORDER_THIN);
		cs_title.setWrapText(true);// ����ʹ�õ�Ԫ������ְ��յ�Ԫ����п����Զ������У���ʾ
		cs_title.setFont(font_title);
		// ������ͷ��ʽ
		XSSFCellStyle cs1 = workbook.createCellStyle();
		cs1.setAlignment(XSSFCellStyle.ALIGN_CENTER);// ��������ˮƽ����
		cs1.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);// �������崹ֱ����
		// �������õס����ҡ��ϱ߿�
		cs1.setBorderBottom(XSSFCellStyle.BORDER_THIN);
		cs1.setBorderLeft(XSSFCellStyle.BORDER_THIN);
		cs1.setBorderRight(XSSFCellStyle.BORDER_THIN);
		cs1.setBorderTop(XSSFCellStyle.BORDER_THIN);
		cs1.setWrapText(true);// ���ÿ������Ӧ
		cs1.setFont(font1);
		// �������ľ�����ʽ
		XSSFCellStyle cs2 = workbook.createCellStyle();
		cs2.setAlignment(XSSFCellStyle.ALIGN_CENTER);// ��������ˮƽ����
		cs2.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);// �������崹ֱ����
		// �������õס����ҡ��ϱ߿�
		cs2.setBorderBottom(XSSFCellStyle.BORDER_THIN);
		cs2.setBorderLeft(XSSFCellStyle.BORDER_THIN);
		cs2.setBorderRight(XSSFCellStyle.BORDER_THIN);
		cs2.setBorderTop(XSSFCellStyle.BORDER_THIN);
		cs2.setWrapText(true);// ���ÿ������Ӧ
		cs2.setFont(font2);
		// �ϲ��� ����һ�У����һ�У���һ�У����һ�У�
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 7));// �ϲ���-����
		
		 * �������ݲ���
		 
		// ��������һ��ռ���У�
		Row row1 = sheet.createRow(0);
		row1.setHeight((short) 1000);
		Cell cell_title = row1.createCell(0);
		cell_title.setAsActiveCell();
		cell_title.setCellValue("����ũҵ��ѧʵϰ������Ϣ��");
		cell_title.setCellStyle(cs_title);
		// �ڶ�������Ӧ��ͷ��
		Row row2 = sheet.createRow(1);
		for (int i = 0; i < col_title.length; i++) {
			Cell cell_line = row2.createCell(i);
			cell_line.setCellValue(col_title[i]);
			cell_line.setCellStyle(cs1);
		}
		// ����֮��
		
		 * !!!!!!���ע����������Ҫ�������޵�����list�������ͷ�������Ȼ����滹��ÿ�������л�ȡ������ֵ�ķ���!!!!!!!!!!!!!!!!
		 * !!!!!!
		 
		// �û�ID���Ժ�Ҫ�Ӻ�̨��ȡ�ģ�������
		@SuppressWarnings("rawtypes")
		String applicantId = "201440509";
		@SuppressWarnings("unchecked")
		List<LandApply_view> list = landApplyServiceImpl
				.getselfApply(applicantId);
		
		 * ����list��ȡ�õ�����
		 
		for (int i = 0; i < list.size(); i++) {
			LandApply_view line_data = list.get(i);// ȡ��list������һ������
			int line = i + 2;// ������
			Row row_line = sheet.createRow(line);// ������line��
			// ��һ������Զ�����1
			Cell cell0 = row_line.createCell(0);
			cell0.setCellValue(i + 1);
			cell0.setCellStyle(cs2);
			// String[] col_title = { "���","��������","��������","�걨����","һ���Կɳе�ʵϰѧ������",
			// "�������/Ķ","�������/ƽ����","������"};// 8����ͷ
			// �ڶ��л�������
			Cell cell1 = row_line.createCell(1);
			// !!!!!!!!!!�������д����line_data.getStartTime()������ȡ��ֵ��Ҫ��Ӧ������˳��Ĺ���ʣ�
			// !!!!!!!!!!!Ȼ�����ÿ�ж���ͬ�˸���!!!!!!!!!!!!!!!
			cell1.setCellValue(line_data.getStartTime());
			cell1.setCellStyle(cs2);
			// �����л�������
			Cell cell2 = row_line.createCell(2);
			cell2.setCellValue(line_data.getEndTime());
			cell2.setCellStyle(cs2);
			// �������걨����
			Cell cell3 = row_line.createCell(3);
			cell3.setCellValue(line_data.getBname());
			cell3.setCellStyle(cs2);
			// ������һ���Կɳе�ʵϰѧ������
			Cell cell4 = row_line.createCell(4);
			cell4.setCellValue(line_data.getLid());
			cell4.setCellStyle(cs2);
			// �������������/Ķ
			Cell cell5 = row_line.createCell(5);
			cell5.setCellValue(line_data.getName());
			cell5.setCellStyle(cs2);
			// �����н������/ƽ����
			Cell cell6 = row_line.createCell(6);
			cell6.setCellValue(line_data.getCollege());
			cell6.setCellStyle(cs2);
			// �ڰ���������
			Cell cell7 = row_line.createCell(7);
			cell7.setCellValue(line_data.getMajor_oriented());
			cell7.setCellStyle(cs2);
		}
		// ����Excel�ļ�
		// !!!!!!!!!��ʰ�~~�����·���ǽ����ɵ��ļ��ŵ���������·��!!!!!!!!!!!!!!!!!!!
		File file = new File("E://workLandPreserveReport.xlsx");
		try {
			file.createNewFile();
			FileOutputStream stream = FileUtils.openOutputStream(file);
			workbook.write(stream);
			stream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	
	 * ��:ʵϰ�ƻ�ά����Excel�ļ����� (non-Javadoc)
	 * 
	 * @see com.base.service.ExcelReportService#workPlanPreserveReport()
	 
	@Override
	public void workPlanPreserveReport() {// ��ͷ��Ϣ
		String[] col_title = { "���", "ʵϰ����", "��ϵ�绰", "ʵϰ��ʼʱ��", "ʵϰ����ʱ��",
				"ʵϰ����", "ʵϰѧԺ", "ʵϰ�ص�", "ʵϰָ����ʦ", "ʵϰ��Ŀ����", "�꼶רҵ�༶", "ʵϰ����",
				"ʵϰ����ժҪ�����̰���" };
		// 13��
		// 14����ͷ
		@SuppressWarnings("resource")
		XSSFWorkbook workbook = new XSSFWorkbook();// ����һ��Excel�ĵ�����
		Sheet sheet = workbook.createSheet();// ��������ĵ��д���Excel��
		// �����еĿ��
		sheet.setColumnWidth(0, 1500);
		sheet.setColumnWidth(1, 4500);
		sheet.setColumnWidth(2, 4000);
		sheet.setColumnWidth(3, 3500);
		sheet.setColumnWidth(4, 3500);
		sheet.setColumnWidth(5, 3000);
		sheet.setColumnWidth(6, 4500);
		sheet.setColumnWidth(7, 4500);
		sheet.setColumnWidth(8, 3500);
		sheet.setColumnWidth(9, 4500);
		sheet.setColumnWidth(10, 4500);
		sheet.setColumnWidth(11, 2500);
		sheet.setColumnWidth(12, 7000);
		
		 * ������ʽ
		 
		// 1����������// ���ñ�������
		Font font_title = workbook.createFont();
		font_title.setFontName("����");
		font_title.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);// ���������ϸ
		font_title.setFontHeightInPoints((short) 18);// ���������С
		// ������ͷ����
		Font font1 = workbook.createFont();
		font1.setFontName("����");
		font1.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);// ���������ϸ
		font1.setFontHeightInPoints((short) 11);// ���������С
		// ������������1
		Font font2 = workbook.createFont();
		font2.setFontName("����");
		font2.setBoldweight(XSSFFont.BOLDWEIGHT_NORMAL);// ���������ϸ
		font2.setFontHeightInPoints((short) 11);// ���������С
		// 2��������ʽ
		// ���ñ�����ʽ
		XSSFCellStyle cs_title = workbook.createCellStyle();
		cs_title.setAlignment(XSSFCellStyle.ALIGN_CENTER);// ��������ˮƽ����
		cs_title.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);// �������崹ֱ����
		// �������õס����ҡ��ϱ߿�
		cs_title.setBorderBottom(XSSFCellStyle.BORDER_THIN);
		cs_title.setBorderLeft(XSSFCellStyle.BORDER_THIN);
		cs_title.setBorderRight(XSSFCellStyle.BORDER_THIN);
		cs_title.setBorderTop(XSSFCellStyle.BORDER_THIN);
		cs_title.setWrapText(true);// ����ʹ�õ�Ԫ������ְ��յ�Ԫ����п����Զ������У���ʾ
		cs_title.setFont(font_title);
		// ������ͷ��ʽ
		XSSFCellStyle cs1 = workbook.createCellStyle();
		cs1.setAlignment(XSSFCellStyle.ALIGN_CENTER);// ��������ˮƽ����
		cs1.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);// �������崹ֱ����
		// �������õס����ҡ��ϱ߿�
		cs1.setBorderBottom(XSSFCellStyle.BORDER_THIN);
		cs1.setBorderLeft(XSSFCellStyle.BORDER_THIN);
		cs1.setBorderRight(XSSFCellStyle.BORDER_THIN);
		cs1.setBorderTop(XSSFCellStyle.BORDER_THIN);
		cs1.setWrapText(true);// ���ÿ������Ӧ
		cs1.setFont(font1);
		// �������ľ�����ʽ
		XSSFCellStyle cs2 = workbook.createCellStyle();
		cs2.setAlignment(XSSFCellStyle.ALIGN_CENTER);// ��������ˮƽ����
		cs2.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);// �������崹ֱ����
		// �������õס����ҡ��ϱ߿�
		cs2.setBorderBottom(XSSFCellStyle.BORDER_THIN);
		cs2.setBorderLeft(XSSFCellStyle.BORDER_THIN);
		cs2.setBorderRight(XSSFCellStyle.BORDER_THIN);
		cs2.setBorderTop(XSSFCellStyle.BORDER_THIN);
		cs2.setWrapText(true);// ���ÿ������Ӧ
		cs2.setFont(font2);
		// �ϲ��� ����һ�У����һ�У���һ�У����һ�У�
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 12));// �ϲ���-����
		
		 * �������ݲ���
		 
		// ��������һ��ռ���У�
		Row row1 = sheet.createRow(0);
		row1.setHeight((short) 1000);
		Cell cell_title = row1.createCell(0);
		cell_title.setAsActiveCell();
		cell_title.setCellValue("����ũҵ��ѧʵϰ�ƻ���Ϣ��");
		cell_title.setCellStyle(cs_title);
		// �ڶ�������Ӧ��ͷ��
		Row row2 = sheet.createRow(1);
		for (int i = 0; i < col_title.length; i++) {
			Cell cell_line = row2.createCell(i);
			cell_line.setCellValue(col_title[i]);
			cell_line.setCellStyle(cs1);
		}
		// ����֮��
		
		 * !!!!!!���ע����������Ҫ�������޵�����list�������ͷ�������Ȼ����滹��ÿ�������л�ȡ������ֵ�ķ���!!!!!!!!!!!!!!!!
		 * !!!!!!
		 
		// �û�ID���Ժ�Ҫ�Ӻ�̨��ȡ�ģ�������
		@SuppressWarnings("rawtypes")
		String applicantId = "201440509";
		@SuppressWarnings("unchecked")
		List<LandApply_view> list = landApplyServiceImpl
				.getselfApply(applicantId);
		
		 * ����list��ȡ�õ�����
		 
		for (int i = 0; i < list.size(); i++) {
			LandApply_view line_data = list.get(i);// ȡ��list������һ������
			int line = i + 2;// ������
			Row row_line = sheet.createRow(line);// ������line��
			// ��һ������Զ�����1
			Cell cell0 = row_line.createCell(0);
			cell0.setCellValue(i + 1);
			cell0.setCellStyle(cs2);
			// String[] col_title = { "���", "ʵϰ����", "��ϵ�绰", "ʵϰ��ʼʱ��", "ʵϰ����ʱ��",
			// "ʵϰ����", "ʵϰѧԺ", "ʵϰ�ص�", "ʵϰָ����ʦ", "ʵϰ��Ŀ����",
			// "�꼶רҵ�༶", "ʵϰ����","ʵϰ����ժҪ�����̰���" };
			// // 13����ͷ
			// �ڶ���ʵϰ����
			Cell cell1 = row_line.createCell(1);
			// !!!!!!!!!!�������д����line_data.getStartTime()������ȡ��ֵ��Ҫ��Ӧ������˳��Ĺ���ʣ�
			// !!!!!!!!!!!Ȼ�����ÿ�ж���ͬ�˸���!!!!!!!!!!!!!!!
			cell1.setCellValue(line_data.getStartTime());
			cell1.setCellStyle(cs2);
			// ��������ϵ�绰
			Cell cell2 = row_line.createCell(2);
			cell2.setCellValue(line_data.getEndTime());
			cell2.setCellStyle(cs2);
			// ����ʵϰ��ʼʱ��
			Cell cell3 = row_line.createCell(3);
			cell3.setCellValue(line_data.getBname());
			cell3.setCellStyle(cs2);
			// ������ʵϰ����ʱ��
			Cell cell4 = row_line.createCell(4);
			cell4.setCellValue(line_data.getLid());
			cell4.setCellStyle(cs2);
			// ������ʵϰ����
			Cell cell5 = row_line.createCell(5);
			cell5.setCellValue(line_data.getName());
			cell5.setCellStyle(cs2);
			// ������ʵϰѧԺ
			Cell cell6 = row_line.createCell(6);
			cell6.setCellValue(line_data.getCollege());
			cell6.setCellStyle(cs2);
			// �ڰ���ʵϰ�ص�
			Cell cell7 = row_line.createCell(7);
			cell7.setCellValue(line_data.getMajor_oriented());
			cell7.setCellStyle(cs2);
			// �ھ���ʵϰָ����ʦ
			Cell cell8 = row_line.createCell(8);
			cell8.setCellValue(line_data.getBuildingArea());
			cell8.setCellStyle(cs2);
			// ��ʮ��ʵϰ��Ŀ����
			Cell cell9 = row_line.createCell(9);
			cell9.setCellValue(line_data.getLandArea());
			cell9.setCellStyle(cs2);
			// ��ʮһ���꼶רҵ�༶
			Cell cell10 = row_line.createCell(10);
			cell10.setCellValue(line_data.getAptPlanting());
			cell10.setCellStyle(cs2);
			// ��ʮ����ʵϰ����
			Cell cell11 = row_line.createCell(11);
			cell11.setCellValue(line_data.getAfford());
			cell11.setCellStyle(cs2);
			// ��ʮ����ʵϰ����ժҪ�����̰���
			Cell cell12 = row_line.createCell(12);
			cell12.setCellValue(line_data.getTenancy());
			cell12.setCellStyle(cs2);
		}
		// ����Excel�ļ�
		// !!!!!!!!!��ʰ�~~�����·���ǽ����ɵ��ļ��ŵ���������·��!!!!!!!!!!!!!!!!!!!
		File file = new File("E://workPlanPreserveReport.xlsx");
		try {
			file.createNewFile();
			FileOutputStream stream = FileUtils.openOutputStream(file);
			workbook.write(stream);
			stream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	
	 * ��:ϵͳ�û�ά����Excel�ļ����� (non-Javadoc)
	 * 
	 * @see com.base.service.ExcelReportService#systemUsersPreserveReport()
	 
	@Override
	public void systemUsersPreserveReport() {
		// ��ͷ��Ϣ
		String[] col_title = { "���", "Ա�����", "�������", "����", "�Ա�", "Ա������",
				"��������", "���֤����", "��ϵ�绰", "����", "ԭ������λ", "��У����ʱ��", "�μӹ���ʱ��",
				"�ù���ʽ", "��������", "����" };// 16
		@SuppressWarnings("resource")
		XSSFWorkbook workbook = new XSSFWorkbook();// ����һ��Excel�ĵ�����
		Sheet sheet = workbook.createSheet();// ��������ĵ��д���Excel��
		// �����еĿ��
		sheet.setColumnWidth(0, 1500);
		sheet.setColumnWidth(1, 2500);
		sheet.setColumnWidth(2, 3000);
		sheet.setColumnWidth(3, 2500);
		sheet.setColumnWidth(4, 1500);
		sheet.setColumnWidth(5, 3000);
		sheet.setColumnWidth(6, 3000);
		sheet.setColumnWidth(7, 5500);
		sheet.setColumnWidth(8, 4000);
		sheet.setColumnWidth(9, 4000);
		sheet.setColumnWidth(10, 4500);
		sheet.setColumnWidth(11, 3000);
		sheet.setColumnWidth(12, 3000);
		sheet.setColumnWidth(13, 3000);
		sheet.setColumnWidth(14, 3000);
		sheet.setColumnWidth(15, 3000);
		
		 * ������ʽ
		 
		// 1����������// ���ñ�������
		Font font_title = workbook.createFont();
		font_title.setFontName("����");
		font_title.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);// ���������ϸ
		font_title.setFontHeightInPoints((short) 18);// ���������С
		// ������ͷ����
		Font font1 = workbook.createFont();
		font1.setFontName("����");
		font1.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);// ���������ϸ
		font1.setFontHeightInPoints((short) 11);// ���������С
		// ������������1
		Font font2 = workbook.createFont();
		font2.setFontName("����");
		font2.setBoldweight(XSSFFont.BOLDWEIGHT_NORMAL);// ���������ϸ
		font2.setFontHeightInPoints((short) 11);// ���������С
		// 2��������ʽ
		// ���ñ�����ʽ
		XSSFCellStyle cs_title = workbook.createCellStyle();
		cs_title.setAlignment(XSSFCellStyle.ALIGN_CENTER);// ��������ˮƽ����
		cs_title.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);// �������崹ֱ����
		// �������õס����ҡ��ϱ߿�
		cs_title.setBorderBottom(XSSFCellStyle.BORDER_THIN);
		cs_title.setBorderLeft(XSSFCellStyle.BORDER_THIN);
		cs_title.setBorderRight(XSSFCellStyle.BORDER_THIN);
		cs_title.setBorderTop(XSSFCellStyle.BORDER_THIN);
		cs_title.setWrapText(true);// ����ʹ�õ�Ԫ������ְ��յ�Ԫ����п����Զ������У���ʾ
		cs_title.setFont(font_title);
		// ������ͷ��ʽ
		XSSFCellStyle cs1 = workbook.createCellStyle();
		cs1.setAlignment(XSSFCellStyle.ALIGN_CENTER);// ��������ˮƽ����
		cs1.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);// �������崹ֱ����
		// �������õס����ҡ��ϱ߿�
		cs1.setBorderBottom(XSSFCellStyle.BORDER_THIN);
		cs1.setBorderLeft(XSSFCellStyle.BORDER_THIN);
		cs1.setBorderRight(XSSFCellStyle.BORDER_THIN);
		cs1.setBorderTop(XSSFCellStyle.BORDER_THIN);
		cs1.setWrapText(true);// ���ÿ������Ӧ
		cs1.setFont(font1);
		// �������ľ�����ʽ
		XSSFCellStyle cs2 = workbook.createCellStyle();
		cs2.setAlignment(XSSFCellStyle.ALIGN_CENTER);// ��������ˮƽ����
		cs2.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);// �������崹ֱ����
		// �������õס����ҡ��ϱ߿�
		cs2.setBorderBottom(XSSFCellStyle.BORDER_THIN);
		cs2.setBorderLeft(XSSFCellStyle.BORDER_THIN);
		cs2.setBorderRight(XSSFCellStyle.BORDER_THIN);
		cs2.setBorderTop(XSSFCellStyle.BORDER_THIN);
		cs2.setWrapText(true);// ���ÿ������Ӧ
		cs2.setFont(font2);
		// �ϲ��� ����һ�У����һ�У���һ�У����һ�У�
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 15));// �ϲ���-����
		
		 * �������ݲ���
		 
		// ��������һ��ռ���У�
		Row row1 = sheet.createRow(0);
		row1.setHeight((short) 1000);
		Cell cell_title = row1.createCell(0);
		cell_title.setAsActiveCell();
		cell_title.setCellValue("����ũҵ��ѧϵͳ�û���Ϣ��");
		cell_title.setCellStyle(cs_title);
		// �ڶ�������Ӧ��ͷ��
		Row row2 = sheet.createRow(1);
		for (int i = 0; i < col_title.length; i++) {
			Cell cell_line = row2.createCell(i);
			cell_line.setCellValue(col_title[i]);
			cell_line.setCellStyle(cs1);
		}
		// ����֮��
		
		 * !!!!!!���ע����������Ҫ�������޵�����list�������ͷ�������Ȼ����滹��ÿ�������л�ȡ������ֵ�ķ���!!!!!!!!!!!!!!!!
		 * !!!!!!
		 
		// �û�ID���Ժ�Ҫ�Ӻ�̨��ȡ�ģ�������
		@SuppressWarnings("rawtypes")
		String applicantId = "201440509";
		@SuppressWarnings("unchecked")
		List<LandApply_view> list = landApplyServiceImpl
				.getselfApply(applicantId);
		
		 * ����list��ȡ�õ�����
		 
		for (int i = 0; i < list.size(); i++) {
			LandApply_view line_data = list.get(i);// ȡ��list������һ������
			int line = i + 2;// ������
			Row row_line = sheet.createRow(line);// ������line��
			// ��һ������Զ�����1
			Cell cell0 = row_line.createCell(0);
			cell0.setCellValue(i + 1);
			cell0.setCellStyle(cs2);
			// String[] col_title = { "���","Ա�����","�������","����","�Ա�","Ա������",
			// "��������","���֤����","��ϵ�绰","����","ԭ������λ",
			// "��У����ʱ��","�μӹ���ʱ��","�ù���ʽ","��������","����"};//16
			// �ڶ���Ա�����
			Cell cell1 = row_line.createCell(1);
			// !!!!!!!!!!�������д����line_data.getStartTime()������ȡ��ֵ��Ҫ��Ӧ������˳��Ĺ���ʣ�
			// !!!!!!!!!!!Ȼ�����ÿ�ж���ͬ�˸���!!!!!!!!!!!!!!!
			cell1.setCellValue(line_data.getStartTime());
			cell1.setCellStyle(cs2);
			// �������������
			Cell cell2 = row_line.createCell(2);
			cell2.setCellValue(line_data.getEndTime());
			cell2.setCellStyle(cs2);
			// ����������
			Cell cell3 = row_line.createCell(3);
			cell3.setCellValue(line_data.getBname());
			cell3.setCellStyle(cs2);
			// �������Ա�
			Cell cell4 = row_line.createCell(4);
			cell4.setCellValue(line_data.getLid());
			cell4.setCellStyle(cs2);
			// ������Ա������
			Cell cell5 = row_line.createCell(5);
			cell5.setCellValue(line_data.getName());
			cell5.setCellStyle(cs2);
			// �����г�������
			Cell cell6 = row_line.createCell(6);
			cell6.setCellValue(line_data.getCollege());
			cell6.setCellStyle(cs2);
			// �ڰ������֤����
			Cell cell7 = row_line.createCell(7);
			cell7.setCellValue(line_data.getMajor_oriented());
			cell7.setCellStyle(cs2);
			// �ھ�����ϵ�绰
			Cell cell8 = row_line.createCell(8);
			cell8.setCellValue(line_data.getBuildingArea());
			cell8.setCellStyle(cs2);
			// ��ʮ�в���
			Cell cell9 = row_line.createCell(9);
			cell9.setCellValue(line_data.getLandArea());
			cell9.setCellStyle(cs2);
			// ��ʮһ��ԭ������λ
			Cell cell10 = row_line.createCell(10);
			cell10.setCellValue(line_data.getAptPlanting());
			cell10.setCellStyle(cs2);
			// ��ʮ������У����ʱ��
			Cell cell11 = row_line.createCell(11);
			cell11.setCellValue(line_data.getAfford());
			cell11.setCellStyle(cs2);
			// ��ʮ���вμӹ���ʱ��
			Cell cell12 = row_line.createCell(12);
			cell12.setCellValue(line_data.getDescp());
			cell12.setCellStyle(cs2);
			// ��ʮ�����ù���ʽ
			Cell cell13 = row_line.createCell(13);
			cell13.setCellValue(line_data.getTenancy());
			cell13.setCellStyle(cs2);
			// ��ʮ���л�������
			Cell cell14 = row_line.createCell(14);
			cell14.setCellValue(line_data.getPlanting());
			cell14.setCellStyle(cs2);
			// ��ʮ��������
			Cell cell15 = row_line.createCell(15);
			cell15.setCellValue(line_data.getPlanting());
			cell15.setCellStyle(cs2);
		}
		// ����Excel�ļ�
		// !!!!!!!!!��ʰ�~~�����·���ǽ����ɵ��ļ��ŵ���������·��!!!!!!!!!!!!!!!!!!!
		File file = new File("E://systemUsersPreserveReport.xlsx");
		try {
			file.createNewFile();
			FileOutputStream stream = FileUtils.openOutputStream(file);
			workbook.write(stream);
			stream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}*/

}
