package javaCVTest;

import org.bytedeco.javacpp.opencv_core.Mat;

import com.rookiex.image.util.JavaCVUtil;
import com.rookiex.image.util.MyUtil;
/** 
 * ��ʾһ��ͼƬ 
 * @author rookiexu 
 * �޸�ʱ�䣺2016��12��17�� 
 * @see javavcCameraTest   
 * @since  javacv1.3
 */ 
public class TestShowPhoto {
	 public static void main(String[] args) { 
		 JavaCVUtil util = new JavaCVUtil();
			MyUtil myUtil = new MyUtil(util);
		    String filePath = "image/�¶ȼ���.bmp";  
		    //��ȡmat  
		    Mat mat = myUtil.imRead(filePath);  
		    //��ʾͼ��  
		    myUtil.imShow(mat, "��ԫ����");  
		    //����mat
		    myUtil.imWrite(mat, "image/��ԫ����-����.png");  
		    }  
}
