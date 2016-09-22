package com.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.Calendar;

import javax.servlet.http.HttpServletResponse;

public class FileManager {
	/**
	 * ���� �ٿ�ε� �޼ҵ�
	 * @param saveFilename ��������������ϸ�
	 * @param originalFilename Ŭ���̾�Ʈ�����ε������ϸ�
	 * @param pathname ����������Ȱ��
	 * @param resp HttpServletResponse ��ü
	 * @return �ٿ�ε强������
	 */
	public static boolean doFiledownload(String saveFilename, String originalFilename, String pathname, HttpServletResponse resp) {
		boolean flag=false;
		
		if(pathname==null || saveFilename==null || saveFilename.length()==0 ||
				originalFilename==null || originalFilename.length()==0) {
			return flag;
		}
		
		try {
			originalFilename=new String(
					originalFilename.getBytes("euc-kr"), "8859_1");
			pathname=pathname+File.separator+saveFilename;
			File f=new File(pathname);
			if(! f.exists()) {
				return flag;
			}
			
			// Ŭ���̾�Ʈ���� ������ ����Ÿ���� ��Ʈ���̶�� ����
			resp.setContentType("application/octet-stream");
			
			// ���ϸ��� �����
			resp.setHeader("Content-disposition",
					"attachment;filename="+originalFilename);
			
			// Ŭ���̾�Ʈ���� ������ ������ ����
			byte[] b=new byte[1024];
			BufferedInputStream bis=
					new BufferedInputStream(
							new FileInputStream(f));
			
			// Ŭ���̾�Ʈ���� ������ ��� ��Ʈ��
			OutputStream os=resp.getOutputStream();
			
			int n;
			while((n=bis.read(b, 0, b.length))!=-1) {
				os.write(b, 0, n);
			}
			os.flush();
			os.close();
			bis.close();
			
			flag=true;
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
		return flag;
	}
	
	/**
	 * ���� �̸� ����(����Ͻú��ʳ�����)
	 * @param pathname ����������� ���
	 * @param filename ������ ���ϸ�
	 * @return ���ο����ϸ�
	 */
	public static String doFilerename(String pathname, String filename) {
		String newname="";
		
    	String fileExt = filename.substring(
    			       filename.lastIndexOf("."));
    	String s = String.format(
    			"%1$tY%1$tm%1$td%1$tH%1$tM%1$tS", 
				 Calendar.getInstance());
    	s += System.nanoTime();
    	s += fileExt;
    	
    	try {
	    	File f1=new File(pathname+File.separator+filename);
	    	File f2=new File(pathname+File.separator+s);
	    	f1.renameTo(f2);
	    	
	    	newname = s;
    	}catch(Exception e) {
    	}
		
		return newname;
	}
	
	/**
	 * ���� ����
	 * @param pathname ������ ����� ���
	 * @param filename ������ ���ϸ�
	 * @return ���� ���� ���� ����
	 */
	public static boolean doFiledelete(String pathname, String filename) {
		String path=pathname+File.separator+filename;
		
		try {
			File f=new File(path);
			
			if(! f.exists()) // ������ ������
				return false;
			
			f.delete();
		} catch (Exception e) {
		}
		
		return true;
	}
}