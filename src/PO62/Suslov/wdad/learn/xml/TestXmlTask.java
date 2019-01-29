package PO62.Suslov.wdad.learn.xml;

import PO62.Suslov.wdad.data.managers.PreferencesManager;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class TestXmlTask {
    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
        XmlTask test = new XmlTask("src/PO62/Suslov/wdad/learn/xml/test.xml");

        User user = new User("test@yandex.ru", "test");

        test.updateNote(user, "Еда", "Сделать покушать 2.0");

        System.out.println(test.getNoteText(user, "Еда"));

        XmlTask test2 = new XmlTask("src/PO62/Suslov/wdad/learn/xml/test2.xml");

        User user2 = new User("user1@yandex.ru", "user");

        test2.setPrivileges(user2, "Лабы", 3);

        System.out.println(test2.getNoteText(user2, "Лабы"));

        System.out.println(PreferencesManager.getInstance().getRegistryAddress());
    }
}
