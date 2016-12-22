package com.base.utils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class MessageUtils {
	
	public static String getTitle(int i){
		
		String str="";
		if(i==1){   //�û������ύ
			
			str="�������������ύ";
			
		}else if(i==2){  //����Ա���"ͬ������"
			
			str="����������˳ɹ�";
			
		}else if(i==3){  //����Ա���"�ܾ�����"
			
			str="�����������ʧ��";
			
		}else if(i==4){  //���ѹ���
			
			str="�������޽ɷ��У�����ʧ��";
			
		}else if(i==5){//����Ա���"ȡ������"
			
			str="�������޽ɷ��У����˻�";
			
		}else if(i==6){  //����Ա���"���ѳɹ�"
			
			str="�ɷѳɹ�";
			
		}else if(i==7){  //�û��Լ�����
			str="���볷��";
		}	
		
		return str;
	}
	
	
	public static String getContent(int i,String bname){

		String content="";
		
         if(i==1){   //�û������ύ
			
			content=bname+"���������ύ�ɹ�";
			
		}else if(i==2){  //����Ա���"ͬ������"
			
			content=bname+"���ͨ��";
			
		}else if(i==3){  //����Ա���"�ܾ�����"
			
			content=bname+"����Ա�˻��������";
			
		}else if(i==4){  //���ѹ���
			
			content=bname+"�ɷѹ���";
			
		}else if(i==5){//����Ա���"ȡ������"
			
			content=bname+"������Ա�˻�";
			
		}else if(i==6){  //����Ա���"���ѳɹ�"
			
			content=bname+"�������޳ɹ�";
			
		}else if(i==7){
			content=bname+"�������������ѳ���";
			}	
         
         return content;
      }
	
	public static String getInsertStr(String infoStr,int tag){
		
	  String insertStr="";
      Calendar c = Calendar.getInstance();//���Զ�ÿ��ʱ���򵥶��޸�
		
    	int year = c.get(Calendar.YEAR);  
    	int month=c.get(Calendar.MONTH)+1;
        int date=c.get(Calendar.DATE);
        String month1=(month>=10?""+month:"0"+month);
        String date1=(date>=10?""+date:"0"+date);
       
        String dateStr=""+year+"-"+month1+"-"+date1;
       
    	String msg="";
    	
		JSONArray obj = JSONArray.fromObject(infoStr);
		for (int i = 0; i < obj.size(); i++) {
			JSONObject temp = obj.getJSONObject(i);
			msg=temp.getString("msg");			
			insertStr=insertStr+"('"+MessageUtils.getTitle(tag)+"','"+MessageUtils.getContent(tag,msg)+"','"+dateStr+"',"+0+",'"+temp.getString("userid")+"'";
			if(i==obj.size()-1){
				insertStr+=")";
			}else{
				insertStr+="),";
			}
			
		}
		insertStr="insert into baseweb.message(title,content,time,isRead,userid) values"+insertStr;
		System.out.println(insertStr);
		return insertStr;
	}
	
	/**
	  * ������ʾ��ǰ��ҳ����ʼҳ
	  * @param pageNum ��ǰҳ��
	  * @param pageCount ��ҳ��
	  * @param sideNum ��ҳϵ��  ��ҳ������ʾ��������ҳ�롣
	  * ��ʾ����ҳ����� = 2 * sideNum + 1
	  */
	 public static List calcPage(int pageNum,int pageCount,int sideNum){            
	   		 
		 int startNum = 0;
	     int endNum = 0;

	     if(pageCount<=sideNum){
	         endNum = pageCount;
	     }else{
	         if((sideNum+pageNum)>=pageCount){
	             endNum = pageCount;
	         }else{
	             endNum = sideNum+pageNum;
	             if((sideNum+pageNum)<=(2*sideNum+1)){                  
	                 if((2*sideNum+1)>=pageCount){
	                     endNum = pageCount;
	                 }else{
	                     endNum = 2*sideNum+1;
	                 }
	             }else{
	                 endNum = sideNum + pageNum;
	             }
	         }
	     }

	     if(pageNum<=sideNum){
	         startNum = 1;
	     }else{         
	         if((pageNum+sideNum)>=pageCount){
	             if((2*sideNum+1)>=pageCount){
	                 if((pageCount - 2*sideNum)>=1){
	                     startNum = pageCount - 2*sideNum;
	                 }else{
	                     startNum = 1;
	                 }
	             }else{
	                 startNum = pageCount - 2*sideNum;
	             }              
	         }else{
	             if((pageNum-sideNum)>=1){
	                 startNum = pageNum - sideNum;
	             }else{
	                 startNum = 1;
	             }              
	         }
	     }  
	     
	     List list = new ArrayList();
	     list.add(startNum);
	     list.add(endNum);
	     
	     return list;
}
	
}