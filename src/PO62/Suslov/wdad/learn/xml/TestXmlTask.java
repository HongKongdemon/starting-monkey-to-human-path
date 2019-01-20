package PO62.Suslov.wdad.learn.xml;

import PO62.Suslov.wdad.data.managers.PreferencesManager;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class TestXmlTask {
    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
        User owner = new User();
        owner.setName("Имя");
        owner.setMail("Почта");
        owner.setRights("R");

        XmlTask xmlTask = new XmlTask();
        String answer = xmlTask.getNoteText(owner, "Заголовок" );
        System.out.println(answer);

        //xmlTask.updateNote(owner,"Заголовок", "Новый текст");

        User user = new User();
        user.setName("Имя пользователя1");
        user.setMail("Почта пользователя1");
        user.setRights("R");


        xmlTask.setPrivileges("Заголовок2",user,3);

        PreferencesManager manag = new PreferencesManager();

        String tx = manag.getClassProvider();
        System.out.println(tx);

        manag.setCreateRegistry("No");


    }
}