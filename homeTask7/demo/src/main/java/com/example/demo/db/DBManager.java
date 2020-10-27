package com.example.demo.db;

import java.util.ArrayList;

public class DBManager {

    private static ArrayList<ShopItem> items = new ArrayList<>();
    private static Long id = 16L;
    private static String desc = "The iPhone 12 mini display has rounded corners.";

    private static String laptopUrl = "https://www.notebookcheck-ru.com/uploads/tx_nbc2/MicrosoftSurfaceLaptop3-15__1_.JPG";
    private static String phoneUrl =  "https://www.ixbt.com/img/n1/news/2020/9/1/12_large_0_large_large.png";
    private static String watchUrl = "https://cdn57.androidauthority.net/wp-content/uploads/2019/09/Diesel-On-Axial-Wear-OS-smartwatch-1.jpg";
    private static String tvUrl = "https://lh3.googleusercontent.com/proxy/QeNCVVlqofyta1wnHrL3HLSuej0fi83Ro9bOZji9uKAQvLTyd9Lhm8f_Jtn02SAlL5PSawqZqMYp_9H8MMRAXg1gJbhBQ6eaUy1XvA";
    private static String consoleURL = "https://cdn.vox-cdn.com/thumbor/Y_PzDNQ_5tVJOfGqjS5HhEUpKMM=/1400x1400/filters:format(jpeg)/cdn.vox-cdn.com/uploads/chorus_asset/file/20031517/PS5DigitalEdition_02.jpg";

    static{
        items.add(new ShopItem(1L, "Mack book", desc, "laptop", 7000000, 7, 4, laptopUrl));
        items.add(new ShopItem(2L, "Asus", desc, "laptop", 3000000, 7, 3, laptopUrl));
        items.add(new ShopItem(3L, "Acer", desc, "laptop", 3000000, 7, 5, laptopUrl));
        items.add(new ShopItem(4L, "Iphone", desc, "phone", 4000000, 8, 4, phoneUrl));
        items.add(new ShopItem(5L, "Samsung", desc, "phone", 4000000, 8, 3, phoneUrl));
        items.add(new ShopItem(6L, "Xiaomi", desc, "phone", 4000000, 8, 2, phoneUrl));
        items.add(new ShopItem(7L, "Apple", desc, "watch", 3000000, 10, 4, watchUrl));
        items.add(new ShopItem(8L, "Google", desc, "watch", 3000000, 10, 5, watchUrl));
        items.add(new ShopItem(9L, "Samsung", desc, "watch", 3000000, 10, 3, watchUrl));
        items.add(new ShopItem(10L, "LG", desc, "tv", 5000000, 9, 3, tvUrl));
        items.add(new ShopItem(11L, "Samsung", desc, "tv", 5000000, 9, 4, tvUrl));
        items.add(new ShopItem(12L, "Sony", desc, "tv", 5000000, 9, 5, tvUrl));
        items.add(new ShopItem(13L, "PS5", desc, "console", 2000000, 9, 5, consoleURL));
        items.add(new ShopItem(14L, "Xbox", desc, "console", 2000000, 9, 4, consoleURL));
        items.add(new ShopItem(15L, "Nintendo", desc, "console", 2000000, 9, 3, consoleURL));
    }

    public static ArrayList<ShopItem> getAllItems(){
        for(ShopItem item: items){
            ArrayList<Boolean> startArr = new ArrayList();
            for(int i = 1; i < 6; i++) {
                if (item.getStars() >= i)
                    startArr.add(true);
                else startArr.add(false);
            }

            item.setStartArr(startArr);
        }
        return items;
    }

    public static void addItem(ShopItem item){
        item.setId(id);
        items.add(item);
        id++;
    }


}
