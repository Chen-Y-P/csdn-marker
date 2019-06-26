package com.chen;

import com.xuxueli.crawler.XxlCrawler;
import com.xuxueli.crawler.annotation.PageFieldSelect;
import com.xuxueli.crawler.annotation.PageSelect;
import com.xuxueli.crawler.conf.XxlCrawlerConf;
import com.xuxueli.crawler.parser.PageParser;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

public class Main {
    @PageSelect(cssQuery = ".blog-content-box")
    public static class PageVo {

        @PageFieldSelect(cssQuery = ".title-article")
        private String repository;

        @PageFieldSelect(cssQuery = "#content_views")
        private String text;

        @Override
        public String toString() {
            return "标题:'" + repository + '\'';
        }
    }


    public static Map<String, String> strtomap(String c) {
        String str = "uuid_tt_dd=10_17808972580-1549851812152-651106; UN=chen18677338530; smidV2=2019021311195243461a95cccf90cf84fba7ded5ccaf7d0020341d41b2705e0; UM_distinctid=168e555571134-043d8de6cb6ad9-33504275-1fa400-168e5555712114; _ga=GA1.2.702298106.1550796645; Hm_ct_6bcd52f51e9b3dce32bec4a3997715ac=5744*1*chen18677338530!1788*1*PC_VC!6525*1*10_17808972580-1549851812152-651106; UserName=chen18677338530; UserInfo=1232241b6d7947758de179fb0c84e3d6; UserToken=1232241b6d7947758de179fb0c84e3d6; UserNick=%E8%B0%81%E4%B8%80%E7%9B%B4%E9%94%99%E8%BF%87%E7%81%AC; AU=B60; BT=1559813563970; p_uid=U000000; __yadk_uid=2fZrXUTOzDBaNXSv9qeTL9ZhMHgxKX8l; dc_session_id=10_1561519347254.375278; firstDie=1; TINGYUN_DATA=%7B%22id%22%3A%22-sf2Cni530g%23HL5wvli0FZI%22%2C%22n%22%3A%22WebAction%2FCI%2FpostList%252Flist%22%2C%22tid%22%3A%22af99de2dd46642%22%2C%22q%22%3A0%2C%22a%22%3A107%7D; Hm_lvt_6bcd52f51e9b3dce32bec4a3997715ac=1561519431,1561519460,1561528263,1561533983; dc_tos=ptp4vk; Hm_lpvt_6bcd52f51e9b3dce32bec4a3997715ac=1561535984";

        str = c;
        Map<String, String> result = new HashMap<>();

        String[] s = str.split("; ");
        for (String res : s) {
            String[] a = res.split("=");
            result.put(a[0], a[1]);
        }
        return result;
    }


    public static void main(String[] args) throws Exception {
        File file = new File("d://1.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        String a = br.readLine();
        String c = br.readLine();


        for (int i = 0; i < 1000; i++) {

            XxlCrawler crawler = new XxlCrawler.Builder()
                    .setCookieMap(strtomap(c))
                    .setUrls(a)
                    .setWhiteUrlRegexs("https://blog.csdn.net/"+strtomap(c).get("UserName")+"/article/details/\\d+")
                    .setPageParser(new PageParser<PageVo>() {
                        @Override
                        public void parse(Document html, Element pageVoElement, PageVo pageVo) {
                            String pageUrl = html.baseUri();
                            System.out.println(pageUrl + "：" + pageVo.toString());
                        }
                    })
                    .build();

            System.out.println("=======");
            crawler.start(true);
            System.out.println("=======");

            Thread.sleep(100);

        }


    }
}
