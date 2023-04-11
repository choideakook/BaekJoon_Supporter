package com.huh.BaekJoonSupporter.board;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class JsoupController {

    public static void selenium() {

        // 크롬 드라이버 경로
        Path path = Paths.get("/Users/choedaegug/chromedriver_mac_arm64/chromedriver");

        // 크롬 드라이버 세팅
        System.setProperty("webdriver.chrome.driver", path.toString());
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("debuggerAddress", "localhost:9222");

        // 브라우저 선택
        ChromeDriver driver = new ChromeDriver(options);


        // 웹페이지 띄우기
        driver.get("https://solved.ac/profile/xiaowuc1");

        List<WebElement> elements = driver.findElements(By.cssSelector(".css-k7tvm0"));
        System.out.println(elements.size());
        System.out.println("된거맞야?");
    }





    public static void getWebData() {
        String id = "xiaowuc1";
        final String url = "https://solved.ac/profile/" + id;
        Connection con = Jsoup.connect(url);

        try {
            Document document = con.get();
            String thead = getRankingHeader(document);
//            System.out.println(document);
        } catch (IOException e) {
        }
    }

    //-- table head 조회 --//
    private static String getRankingHeader(Document document) {
        Elements elements = document.select("div#__next div.css-16xqaon");
        System.out.println(elements.size());

        return elements.text();
    }
}
