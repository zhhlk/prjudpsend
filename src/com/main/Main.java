package com.main;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println("正在准备发送数据,请稍后......");
        while(true){
            try {

                /***************发送数据的方式**************************/
                //创建发送的报文套接字对象
                DatagramSocket dataSendSocket=new DatagramSocket();

                Scanner input=new Scanner(System.in);
                System.out.println("请输入要发送给服务器端的消息:");
                String msg=input.next();

                //创建发送的报文包对象DatagramPacket对象
                DatagramPacket senddataPacket=new DatagramPacket(msg.getBytes(),msg.length(), InetAddress.getByName("172.16.0.172"),8081);
                //使用dataSendSocket发送数据报
                dataSendSocket.send(senddataPacket);
                dataSendSocket.close();
                System.out.println("发送完成......");
                /*****************************************************/

                /*******接受数据的方式************/
                //创建接收数据的套接字
                DatagramSocket reciveSocket=new DatagramSocket(8090);
                //创建接收的报文包
                byte[] b=new byte[1024];
                DatagramPacket dprecivePacket=new DatagramPacket(b,b.length);

                //开始接收数据
                reciveSocket.receive(dprecivePacket);

                //从dprecivePacket中获取接收到的字节数组数据
                b=dprecivePacket.getData();
                String s=new String(b);
                System.out.println("服务器端发送的消息为:"+s);
                reciveSocket.close();
                System.out.println("接收完成......");
                /********************************/
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
