package kr.kgaons.itemutility;

import java.io.IOException;

public class Config {

    private Config(){}

    public static void save(){
        try {
            ItemUtility.getInstance().messages.save(ItemUtility.getInstance().messagesfile);
            ItemUtility.getInstance().items.save(ItemUtility.getInstance().itemsfile);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
