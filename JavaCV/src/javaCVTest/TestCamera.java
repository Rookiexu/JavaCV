package javaCVTest;  
  
import javax.swing.JFrame;

import org.bytedeco.javacv.CanvasFrame;
import org.bytedeco.javacv.OpenCVFrameGrabber;  
 
  
/** 
 * ���ñ�������ͷ������Ƶ 
 * @author rookiexu 
 * �޸�ʱ�䣺2016��12��17�� 
 */  
  
public class TestCamera  
{  
public static void main(String[] args) throws Exception, InterruptedException  
{  
    OpenCVFrameGrabber grabber = new OpenCVFrameGrabber(0);    
    grabber.start();   //��ʼ��ȡ����ͷ����  
    CanvasFrame canvas = new CanvasFrame("����ͷ");//�½�һ������  
    canvas.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
    canvas.setAlwaysOnTop(true);  
      
    while(true)  
    {  
        if(!canvas.isDisplayable())  
        {//�����Ƿ�ر�  
            grabber.stop();//ֹͣץȡ  
            System.exit(2);//�˳�  
        }  
        canvas.showImage(grabber.grab());//��ȡ����ͷͼ�񲢷ŵ���������ʾ�� �����Frame frame=grabber.grab(); frame��һ֡��Ƶͼ��  
  
        Thread.sleep(50);//50����ˢ��һ��ͼ��  
    }  
}  
}  


