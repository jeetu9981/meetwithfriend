package com.meetnewfriend;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;

import javax.imageio.ImageIO;

import cn.apiclub.captcha.Captcha;
import cn.apiclub.captcha.backgrounds.GradiatedBackgroundProducer;
import cn.apiclub.captcha.noise.CurvedLineNoiseProducer;
import cn.apiclub.captcha.text.producer.DefaultTextProducer;

public class CreateCaptcha {
	public static Captcha createCaptch(int width,int height) {
		Captcha cap=new Captcha.Builder(width,height)
		.addBackground(new GradiatedBackgroundProducer())
		.addText(new DefaultTextProducer())
		.addNoise(new CurvedLineNoiseProducer())
		.build();
		return cap;
	}
	
	public static String createImage(Captcha captch) throws IOException {
		String imageData=null;
		ByteArrayOutputStream os=new ByteArrayOutputStream();
		ImageIO.write(captch.getImage(),"png",os);
		byte[] arr=Base64.getEncoder().encode(os.toByteArray());
		imageData=new String(arr); 
//		FileOutputStream file=new FileOutputStream("C:\\Users\\DELL\\Pictures\\Saved Pictures\\cap.png");
//		file.write(os.toByteArray());
//		file.flush();
//		file.close();
		return imageData;
		
	}
}
