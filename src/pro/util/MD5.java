package pro.util;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
/*
 * 
 * 
 */
public class MD5 {

	//全局变量
	private final static String[] strDigits={"0","1","2","3","4","5","6","7","8","9",
			"a","b","c","d","e","f"};
	
	public MD5(){
		
	}
	
	//返回形式为数字跟字符串
	private static String byteToArrayString(byte bByte){
		int iRet=bByte;//一个字节有256个编码
		//System.out.println("iRet="+iRet);
		if(iRet<0){
			iRet+=256;//一个字节是8位，一位是0/1，共2的8次方
		}
		int iD1=iRet/16;
		int iD2=iRet%16;
		return strDigits[iD1]+strDigits[iD2];	//strDigits是什么意思？
	}
	
	//返回形式只为数字
	@SuppressWarnings("unused")	//屏蔽java编译中的一些警告信息（一些变量未使用的警告）。
	private static String byteToNum(byte bByte){
		int iRet=bByte;
		System.out.println("iRet1="+iRet);
		if(iRet<0){
			iRet+=256;
		}
		return String.valueOf(iRet);
	}
	
	//转换字节数组为16进制子串
	private static String byteToString(byte[] bByte){
		StringBuffer sBuffer=new StringBuffer();
		//String类是字符串常量，是不可更改的常量。
		//而StringBuffer是字符串变量，它的对象是可以扩充和修改的。
		for(int i=0;i<bByte.length;i++){
			sBuffer.append(byteToArrayString(bByte[i]));//append（）为字符串的连接
		}
		return sBuffer.toString();
	}
	
	//得到最终的字符串
	public static String GetMD5Code(String strObj){
		String resultString=null;
		System.out.println("strObj:"+strObj);
		try{
			resultString=new String(strObj);
			MessageDigest md=MessageDigest.getInstance("MD5");//MeessageDigest类为应用程序提供信息摘要算法的功能
			//md.digest() 该函数返回值为存放哈希值结果的byte数组
			resultString =byteToString(md.digest(strObj.getBytes()));
		}catch(NoSuchAlgorithmException ex){
			ex.printStackTrace();
		}
		return resultString;
	}
}
