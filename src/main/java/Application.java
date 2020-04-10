import entity.Parking;
import exception.InvalidTicketException;
import service.ParkingServiceFoolish;
import service.ParkingServiceBright;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Application {
    private static ParkingServiceFoolish parkingServiceFoolish = new ParkingServiceFoolish();
    private static ParkingServiceBright parkingServiceBright = new ParkingServiceBright();

    public static void main(String[] args) {
        operateParking();
    }

    public static void operateParking() {
        while (true) {
            System.out.println("1. 初始化停车场数据\n2. 停车\n3. 取车\n4. 退出\n请输入你的选择(1~4)：");
            Scanner printItem = new Scanner(System.in);
            String choice = printItem.next();
            if (choice.equals("4")) {
                System.out.println("系统已退出");
                break;
            }
            try {
                handle(choice);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        parkingServiceFoolish.closeConnection();
    }

    private static void handle(String choice) {
        Scanner scanner = new Scanner(System.in);
        if (choice.equals("1")) {
            System.out.println("请输入初始化数据\n格式为\"停车场编号1：车位数,停车场编号2：车位数\" 如 \"A:8,B:9\"：");
            String initInfo = scanner.next();
            init(initInfo);
        } else if (choice.equals("2")) {
            System.out.println("请输入车牌号\n格式为\"车牌号\" 如: \"A12098\"：");
            String carInfo = scanner.next();
            String ticket = park(carInfo);
            String[] ticketDetails = ticket.split(",");
            System.out.format("已将您的车牌号为%s的车辆停到%s停车场%s号车位，停车券为：%s，请您妥善保存。\n", ticketDetails[2], ticketDetails[0], ticketDetails[1], ticket);

            String brightTicket = brightPark(carInfo);
            String[] brightTicketDetails = ticket.split(",");
            System.out.format("已将您的车牌号为%s的车辆停到%s停车场%s号车位，停车券为：%s，请您妥善保存。\n", brightTicketDetails[2], brightTicketDetails[0], brightTicketDetails[1], brightTicket);
        } else if (choice.equals("3")) {
            System.out.println("请输入停车券信息\n格式为\"停车场编号1,车位编号,车牌号\" 如 \"A,1,8\"：");
            String ticket = scanner.next();
            String car = fetch(ticket);
            int time = generateRandom();
            int spead = spead(6);
            System.out.format("已为您取到车牌号为%s的车辆,您的停车时间为%d小时，停车费用为%d元,很高兴为您服务，祝您生活愉快!\n", car, time, spead);
        }
    }

    public static int generateRandom() {
        return (int) (1 + Math.random() * (24));
    }

    public static int spead(int time) {
        if (time <= 2) {
            return 0;
        } else if (time < 5) {
            return (time - 2) * 5;
        } else {
            return 3 * 5 + (time - 5) * 10;
        }
    }

    public static void init(String initInfo) {
        Pattern pattern = Pattern.compile("^A:(\\d+),B:(\\d+)$");
        Matcher matcher = pattern.matcher(initInfo);
        if (matcher.find()) {
            int aParkingCount = Integer.parseInt(matcher.group(1));
            int bParkingCount = Integer.parseInt(matcher.group(2));
            parkingServiceFoolish.insertParkingInfo(aParkingCount, bParkingCount);
            System.out.println("初始化成功");
            return;
        }
        System.out.println("输入错误，请重新输入");
    }


    public static String park(String carNumber) {
        return parkingServiceFoolish.generateParkingTicket(carNumber);
    }

    public static String brightPark(String carNumber) {
        return parkingServiceBright.generateParkingTicket(carNumber);
    }

    public static String fetch(String ticket) {
        String[] ticketDetails = ticket.split(",");
        try {
            return parkingServiceFoolish.fetchInfo(new Parking(ticketDetails[0], Integer.parseInt(ticketDetails[1]), ticketDetails[2]));
        } catch (Exception e) {
            throw new InvalidTicketException("很抱歉，无法通过您提供的停车券为您找到相应的车辆，请您再次核对停车券是否有效！ ");
        }
    }

}
