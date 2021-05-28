import org.json.JSONObject;
import org.json.XML;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class ParserJsonToXml {
    public static void main(String[] args) {
    }

    public static void getxml() {

    try(
    BufferedReader br = new BufferedReader(new FileReader("C:\\test\\files\\JSON_file.json")))

    {
        String line;
        FileWriter fw = new FileWriter("C:\\test\\files\\XML_file.xml");
        while ((line = br.readLine()) != null) {
            JSONObject json = new JSONObject(line);
            fw.append("<user>").append(XML.toString(json)).append("</user>").append("\n");
        }
        fw.close();
    } catch(
    IOException e)

    {
        e.printStackTrace();
    }
    }
}


