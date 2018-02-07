package kr.skylightqp.itemutility;

public class PluginUtil {

    private PluginUtil(){
        throw new UnsupportedOperationException("This is PluginUtil class.");
    }

    public static boolean isDuplicated(String itemname){
        return (ItemUtility.getConfiguration().items.get("items." + itemname) != null);
    }

    public static int toTypeID(String s){
        return Integer.parseInt(s.split(":")[0]);
    }

    public static int toData(String s){
        return Integer.parseInt(s.split(":")[1]);
    }
}
