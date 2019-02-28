package com.Util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MyUtil {
	/**
	 * @param rows			�� ȭ�鿡 ǥ���� ������ ��
	 * @param dataCount		��ü ������ ����
	 * @return				�� ������ ��
	 */
	public int pageCount(int rows, int dataCount) {
		if(dataCount<=0)
			return 0;
		
		return dataCount / rows + (dataCount % rows > 0 ? 1 : 0);
	}
	
	/**
	 * ����¡ ó��(GET ���)
	 * @param current_page		���� ������
	 * @param total_page		��ü ������
	 * @param list_url			��ũ�� ������ URL
	 * @return					������ ó�� ���
	 */
	public String paging(int current_page, int total_page, String list_url) {
		StringBuffer sb=new StringBuffer();
		
		int numPerBlock=10;
		int currentPageSetup;
		int n;
		int page;
		
		if(current_page<1 || total_page < 1 )
			return "";
		
		if(list_url.indexOf("?")!=-1)
			list_url+="&";
		else
			list_url+="?";
		
		currentPageSetup=(current_page/numPerBlock)*numPerBlock;
		if(current_page%numPerBlock==0)
			currentPageSetup=currentPageSetup-numPerBlock;
		
		sb.append("<div id='paginate'>");
		n=current_page-numPerBlock;
		if(total_page > numPerBlock && currentPageSetup > 0) {
			sb.append("<a href='"+list_url+"page=1'>ó��</a>");
			sb.append("<a href='"+list_url+"page="+n+"'>����</a>");
		}
		
		// �ٷΰ���
		page=currentPageSetup+1;
		while(page<=total_page && page <=(currentPageSetup+numPerBlock)) {
			if(page==current_page) {
				sb.append("<span class='curBox'>"+page+"</span>");
			} else {
				sb.append("<a href='"+list_url+"page="+page+"' class='numBox'>"+page+"</a>");
			}
			page++;
		}
		//����(10������ ��), ������ ������
		n=current_page+numPerBlock;
		if(n>total_page) n=total_page;
		if(total_page-currentPageSetup>numPerBlock) {
			sb.append("<a href='"+list_url+"page="+n+"'>����</a>");
			sb.append("<a href='"+list_url+"page="+total_page+"'>��</a>");
		}
		sb.append("</div>");
		
		return sb.toString();
	}
	
	/**
	 * Ư�����ڸ� html ���ڷ� ����, ���͸� <br>�� ����
	 * @param str 		������ ���ڿ� 
	 * @return			������ ���
	 */
	public String htmlSymbols(String str) {
		if(str==null || str.length()==0)
			return "";
		
		str=str.replaceAll("&", "&amp;");
		str=str.replaceAll("\"", "&quot;");
		str=str.replaceAll(">", "&gt;");
		str=str.replaceAll("<", "&lt;");

		str=str.replaceAll("\n", "<br>");
		str=str.replaceAll("\\s", "&nbsp;");  // \\s�� ���͵� �����ϹǷ� \n���� �ڿ�

		return str;
	}
	
	public String replaceAll(String str, String oldStr, String newStr) throws Exception {
		if(str == null)
			return "";
		
		Pattern p = Pattern.compile(oldStr);
		
		// �Է� ���ڿ��� �Բ� ���� Ŭ���� ����
		Matcher m = p.matcher(str);
		
		StringBuffer sb = new StringBuffer();
		//���ϰ� ��ġ�ϴ� ���ڿ��� newStr�� ��ü�ذ��鼭 ���ο� ���ڿ��� �����.
		while(m.find()) {
			m.appendReplacement(sb, newStr);
		}
		
		// ������ �κ��� ���ο� ���ڿ� ���� �����δ�.
		m.appendTail(sb);
		
		return sb.toString();
	}
	
	/**
	 * �̸����� ��ȿ���� �˻��ϴ� �Լ�
	 * @param email			�Էµ� �̸���
	 * @return				������ �����ϸ� true, �������� ���ϸ� false
	 */
	public boolean isValidEmail(String email) {
		if(email==null) return false;
		boolean b = Pattern.matches(
				"[\\w\\~\\-\\.]+@[\\w\\~\\-]+(\\.[\\w\\~\\-]+)+",
				email.trim());
		return b;
	}
}
