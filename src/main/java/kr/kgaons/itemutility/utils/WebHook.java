package kr.kgaons.itemutility.utils;

import org.bukkit.Bukkit;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class WebHook {
    public static List<String> getVersion(String url){
        try
        {
            URL updateurl = new URL(url);
            HttpURLConnection huc = (HttpURLConnection)updateurl.openConnection();
            huc.setDoOutput(true);
            BufferedReader br = new BufferedReader(new InputStreamReader(huc.getInputStream()));
            String inputline;
            List<String> list = new ArrayList<>();
            while ((inputline = br.readLine()) != null)
            {
                list.add(inputline);
            }
            return list;
        }
        catch (Exception e)
        {
            Bukkit.getConsoleSender().sendMessage("§f[§6!§f] §c버전 로드에 실패하였습니다.");
            Bukkit.getConsoleSender().sendMessage("§f[§6!§f] §c관리자에게 문의해주세요.");
        }
        return null;
    }
    public static boolean checkVersion(String url, String version) {
        if (getVersion(url).get(0).contains(version)) {
            Util.sendConsoleMessage("최신버전입니다.");
            return true;
        } else {
            Util.sendConsoleMessage("§c버전이 올바르지 않거나 구버전입니다.");
            Util.sendConsoleMessage("§c최신버전을 다운로드 해주세요!");
            Util.sendConsoleMessage("§chttp://blog.kgaons.kr");
            return false;
        }
    }
    public static boolean checkUtilVersion(String version){
        String url = "https://raw.githubusercontent.com/SkyLightQP/skylightqp.github.io/master/MCPluginUtil.txt";
        if (getVersion(url).get(0).contains(version)) {
            Util.sendConsoleMessage("플러그인 구성요소가 최신버전입니다.");
            return true;
        } else {
            Util.sendConsoleMessage("§c구성요소 버전이 올바르지 않거나 구버전입니다.");
            Util.sendConsoleMessage("§c최신버전을 다운로드 하거나 제작자에게 문의해주세요!");
            Util.sendConsoleMessage("§chttp://blog.kgaons.kr");
            return false;
        }
    }
}