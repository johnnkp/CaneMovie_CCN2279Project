package hkcc.ccn2279.gp.canemovie.xml;

import android.util.Log;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

/**
 * 使用Dom讀取Books.xml的資料
 *
 * @author Kevin Chen
 * InputStream可以是網路也可以是文件
 */
public class DomParseXML {
    private static List<Discussion> ReadPost(InputStream inStream) throws Exception {
        List<Discussion> discussionList = new ArrayList<Discussion>();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(inStream); // 以樹狀格式存於記憶體中﹐比較耗記憶體
        Element root = document.getDocumentElement();
        NodeList nodes = root.getElementsByTagName("discussion");

        for (int i = 0; i < nodes.getLength(); i++) {
            Element discussionElement = (Element) nodes.item(i);
            Discussion discussion = new Discussion();
            discussion.setDate(discussionElement.getAttribute("date"));
            discussion.setTitle(discussionElement.getAttribute("title"));
            discussion.setPostAuthor(discussionElement.getAttribute("publisher"));
            discussion.setPostMessage(discussionElement.getAttribute("post_message"));

            discussionList.add(discussion);
        }

        return discussionList;
    }

    public static List<Discussion> getPost(String myurl) {
        List<Discussion> discussions = new ArrayList<Discussion>();
        try {
            URL url = new URL(myurl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000 /* milliseconds */);
            conn.setConnectTimeout(15000 /* milliseconds */);
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            conn.connect();
            int response = conn.getResponseCode();
            Log.d(String.valueOf(Log.DEBUG), "The response is: " + response);

            InputStream inStream = conn.getInputStream();
            discussions = DomParseXML.ReadPost(inStream);
        } catch (Exception er) {
            er.printStackTrace();
        }
        return discussions;
    }
}
