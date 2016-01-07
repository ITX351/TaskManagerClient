package com.example.itx351.taskmanagerclient;
//
//import java.awt.AWTException;
//import java.awt.Rectangle;
//import java.awt.Robot;
//import java.awt.Toolkit;
//import java.awt.image.BufferedImage;
//import java.io.ByteArrayInputStream;
//import java.io.ByteArrayOutputStream;
//import java.io.IOException;
//import java.io.Serializable;
//
//import javax.imageio.ImageIO;
//
//public class Screenshot implements Serializable{
//    private static final long serialVersionUID = 1L;
//    transient public BufferedImage capture;
//    byte[] imageInByte;
//
//    void capture() throws AWTException{
//        Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
//        this.capture = new Robot().createScreenCapture(screenRect);
//    }
//    public void imageToByte(){
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        try{
//            ImageIO.write(this.capture, "jpg", baos);
//            this.imageInByte = baos.toByteArray();
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }
//    public void byteToImage(){
//        try{
//            ByteArrayInputStream bais = new ByteArrayInputStream(this.imageInByte);
//            this.capture = ImageIO.read(bais);
//        }catch(IOException e){
//            e.printStackTrace();
//        }
//    }
//}
