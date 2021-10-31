//package com.company.Minimax.Practice.Chess;
//
//import com.github.bhlangonijr.chesslib.Board;
//import com.github.bhlangonijr.chesslib.move.Move;
//import org.openqa.selenium.By;
//import org.openqa.selenium.JavascriptExecutor;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.remote.RemoteWebDriver;
//import org.openqa.selenium.*;
//
//import java.util.List;
//
//public class PlayComputer {
//    WebDriver driver ;
//
//    public PlayComputer() throws InterruptedException {
//        System.setProperty("webdriver.chrome.driver","/home/beter/Selenium_Java/Documents/chromedriver_linux64/chromedriver");
//        driver = new ChromeDriver();
//        driver.get("https://www.chess.com/play/computer");
//
//        // Play vs Computer
//        driver.findElement(By.xpath("//button[contains(.,'Choose')]")).click();
//        driver.findElement(By.xpath("//button[contains(.,'Play')]")).click();
//        // Get FEN board game chess
//        String FEN = getFenBoard();
//
//        Board board = new Board();
//        board.loadFromFen(FEN);
//        Chess_Minimax_Alpha_Beta minimax = new Chess_Minimax_Alpha_Beta(board);
//        Move move = minimax.getBestMove(1);
//        String smove = move.toString();
//        String from = smove.substring(0,1);
//        String to = smove.substring(2,3);
//
//        System.out.println("from: " + from + "to: " + to);
//
////        JavascriptExecutor js = null;
////        if (driver instanceof JavascriptExecutor) {
////            js = (JavascriptExecutor)driver;
////        }
////
////        js.executeScript("chessboard= document.getElementById('board-vs-personalities');" +
////                " element = document.createElement('div');" +
////                " element.setAttribute('class', 'piece ww square-23');" +
////                " chessboard.appendChild(element);");
////
//////        driver.
////
////        List<WebElement> pieces = driver.findElements(By.className("piece"));
////        System.out.println(pieces.size());
////
////        WebElement start=null,end=null;
////        for (WebElement i:pieces){
////            System.out.println(i.getAttribute("class"));
////            if (i.getAttribute("class").contains("22")){
////                start = i;
////            }
////            if (i.getAttribute("class").contains("23")){
////                end = i;
////            }
////            if (start!=null && end!=null){
////                break;
////            }
////        }
////
////        start.click();
////        end.click();
//
//        driver.close();
//    }
//
//    private String getFenBoard() {
//        // This function get game
//        // This is function get game
//        // Get FEN board game
//        driver.findElement(By.xpath("//div[4]/div[4]/div/button[2]/span")).click();
//        String s = "";
//
//        while (s.length()==0){
//            try{
//                s = driver.findElement(By.xpath("//section/div/div/input")).getAttribute("value");
//            }
//            catch (Exception e){
////                e.printStackTrace();
//            }
//        }
//
//        System.out.println(s);
//        driver.findElement(By.xpath("xpath=//div[2]/div/button/div")).click();
//        return s;
//    }
//
//    public static void main(String args[]) throws InterruptedException {
//        PlayComputer auto = new PlayComputer();
//    }
//}
