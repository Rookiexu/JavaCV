package javaCVTest2;



import org.bytedeco.javacpp.opencv_core.Mat;
import com.rookiex.image.util.JavaCVUtil;
import com.rookiex.image.util.MyUtil;
import com.rookiex.image.util.MyRoberts;


/**
 * @author RookieXu
 * @����     ʵ���Ӿ�����ʵ��2��ͼ���˲�����ǿ
 * @���ں�ʱ�� 2016��12��20�� ����12:41:30
 * @�汾 V0.2
 *
 */
public class Experiment3 {
	static int CV_BGR2GRAY = 6;

	public static void main(String[] args) {
		
		String filePath = "image/�¶ȼ���.bmp";  
		String filePath1 = "image/hui.jpg";
		
		String filePath2 = "image/��ԫ����.jpg";
		String filePath3 = "image/gaussian.jpg";
	
		Mat outGray = new Mat();

		JavaCVUtil util = new JavaCVUtil();
		MyUtil myUtil = new MyUtil(util);
		
	     //��ʾ�Ҷ�ͼԭͼ
		Mat src = myUtil.imRead(filePath2);  
		myUtil.imShow(src, "ԭͼ");
		//��ͼ���ֵ��
		org.bytedeco.javacpp.opencv_imgproc.cvtColor(src, outGray, CV_BGR2GRAY);		
		myUtil.imShow(outGray, "�Ҷ�ͼ");
		//Roberts���Ӵ���
		Mat outRoberts = new Mat();			
		myUtil.imShow(MyRoberts.roberts(outGray, outRoberts), "Robertsͼ");
		//soble����
		Mat outSobel = new Mat();	
		org.bytedeco.javacpp.opencv_imgproc.Sobel(outGray, outSobel, 0, 1, 1);
		myUtil.imShow(outSobel, "Sobelͼ");
		//canny���Ӵ���
		Mat outCanny = new Mat();	
		double threshold1 = 70;
		double threshold2 = 100;				
		org.bytedeco.javacpp.opencv_imgproc.Canny(outGray, outCanny, threshold1, threshold2);
		myUtil.imShow(outCanny, "Cannyͼ");
		
		
		/**
		Mat outRoberts = new Mat();			
		myUtil.imShow(MyRoberts.roberts(outGray, outRoberts), "Robertsͼ");
		util.AddGaussianNoise(out);
		
		myUtil.imShow(outGray, "�Ҷ�ͼ");
		 *
		Mat out2 = myRoberts.roberts(out, out1);
		
		myUtil.imShow(out2, "robertsͼ");
		
		 */
	}
}
