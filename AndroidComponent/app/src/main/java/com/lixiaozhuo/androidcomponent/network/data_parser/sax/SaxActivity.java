package com.lixiaozhuo.androidcomponent.network.data_parser.sax;

import android.app.Activity;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.lixiaozhuo.androidcomponent.R;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * SAX解析
 */
public class SaxActivity extends Activity implements View.OnClickListener{
    //显示控件
    private TextView responseText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sax);
        //初始化控件并绑定事件
        responseText =  findViewById(R.id.response_text);
        Button sendRequest = findViewById(R.id.send_request);
        Button parseRequest =findViewById(R.id.parse_request);
        Button parseLocal = findViewById(R.id.parse_local_XML) ;
        sendRequest.setOnClickListener(this);
        parseRequest.setOnClickListener(this);
        parseLocal.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        //向服务器发送请求
        if (v.getId() == R.id.send_request) {
            sendRequestWithHttpURLConnection();
            Toast.makeText(this, "正在发送请求,等待响应", Toast.LENGTH_SHORT).show();
        }
        //解析接收到的XML文档
        if (v.getId() == R.id.parse_request) {
            parseXMLWithSAX(responseText.getText().toString());
        }
        //解析本地文档
        if (v.getId() == R.id.parse_local_XML) {
            parseLocalXMLWithSAX();
            Toast.makeText(this, "正在解析", Toast.LENGTH_SHORT).show();
        }
    }
    //向服务器发送请求
    private void sendRequestWithHttpURLConnection() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection = null;
                BufferedReader reader = null;
                try {
                    URL url = new URL("https://hbu.github.io/xmlTest.xml");
                    connection = (HttpURLConnection) url.openConnection();
                    //设置请求所用的方法
                    connection.setRequestMethod("GET");
                    //设置连接超时，读取的毫秒数
                    connection.setConnectTimeout(8000);
                    connection.setReadTimeout(8000);
                    //获取服务器的返回的输入流
                    InputStream in = connection.getInputStream();
                    // 对获取到的输入流进行读取
                    reader = new BufferedReader(new InputStreamReader(in));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }
                    //显示响应数据
                    showResponse(response.toString());

                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (reader != null) {
                        try {
                            reader.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (connection != null) {
                        //关闭连接
                        connection.disconnect();
                    }
                }
            }
        }).start();
    }
    //解析接收到的XML文档
    private void parseXMLWithSAX(String xmlData) {
        try {
            //创建解析器工厂
            SAXParserFactory factory = SAXParserFactory.newInstance();
            //获取SAXParser解析器，并赋值给事件源对象XMLReader
            XMLReader xmlReader = factory.newSAXParser().getXMLReader();
            //初始化适配器
            ContentHandler handler = new ContentHandler();
            //设置适配器
            xmlReader.setContentHandler(handler);
            //解析xml数据
            xmlReader.parse(new InputSource(new StringReader(xmlData)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //解析本地文档
    private void parseLocalXMLWithSAX() {
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        try {
            //获取解析器
            SAXParser saxParser = saxParserFactory.newSAXParser();
            //获取asset管理器
            AssetManager assetManager = getAssets();
            //将asset文件转换为文件流
            InputStream inputStream = assetManager.open("xmlTest.xml");
            //解析文件流
            saxParser.parse(inputStream,new ContentHandler());
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 内容适配器
     */
    public class ContentHandler extends DefaultHandler {
        //节点名称
        private String nodeName;
        //id
        private StringBuilder id;
        //名称
        private StringBuilder name;
        //版本号
        private StringBuilder version;
        //存储显示数据
        private String result="";

        /**
         * 处理文档解析开始事件
         * @throws SAXException
         */
        @Override
        public void startDocument() throws SAXException {
            id = new StringBuilder();
            name = new StringBuilder();
            version = new StringBuilder();
            result = "";
        }

        /**
         * 处理元素开始事件
         * @param uri
         * @param localName
         * @param qName
         * @param attributes
         * @throws SAXException
         */
        @Override//
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            //记录当前结点名
             nodeName = localName;
        }

        /**
         * 处理元素内容事件。根据当前的结点名判断将内容添加到哪一个StringBuilder对象中。
         * @param ch
         * @param start
         * @param length
         * @throws SAXException
         */
        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            if ("id".equals(nodeName)) {
                id.append(ch, start, length);
            } else if ("name".equals(nodeName)) {
                name.append(ch, start, length);
            } else if ("version".equals(nodeName)) {
                version.append(ch, start, length);
            }
        }

        /**
         * 处理元素结束事件
         * @param uri
         * @param localName
         * @param qName
         * @throws SAXException
         */
        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            if ("app".equals(localName)) {
                //保存解析结果
                result += "\nid = " + id.toString().trim()+", name = " + name.toString().trim()+", version = " + version.toString().trim();
                // 将StringBuilder清空
                id.setLength(0);
                name.setLength(0);
                version.setLength(0);
            }
        }

        /**
         * 处理文档解析结束事件
         * @throws SAXException
         */
        @Override
        public void endDocument() throws SAXException {
            super.endDocument();
            //展示解析结果
            showResponse(result);
        }

    }

    //显示数据
    private void showResponse(final String response) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                responseText.setText(response);
            }
        });
    }


}
