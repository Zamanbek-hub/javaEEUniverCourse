package com.example.demo.controller;

import com.example.demo.entities.*;
import com.example.demo.entities.helpers.ItemBasket;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.util.*;

@Controller(value = "/")
public class HomeController {

    @Autowired
    private ItemService itemService;

    @Autowired
    private PictureService pictureService;

    @Autowired
    private HttpSession httpSession;

//    @Autowired
//    private BrandService brandService;

    @Autowired
    private UserService userService;

//    @Autowired
//    private UserRepository userRepository;

//    @Autowired
//    private RoleService roleService;

    @GetMapping(value = "/")
    public String index(Model model, @RequestParam(name = "category_id", defaultValue = "-1", required = false) Long category_id){
        List<Item> items;
        Category cat = itemService.getCategory(category_id);

        if(category_id > 0 && cat != null)
            items = itemService.findAllByCategoriesContains(cat);

        else
            items = itemService.getAllItems();

        model.addAttribute("currentUser", getUserData());
        model.addAttribute("items", items);
        model.addAttribute("categories", itemService.getAllCategories());
        model.addAttribute("brands", itemService.getAllBrands());
        return "user/index";
    }


    @GetMapping(value = "/search")
    public String advancedSearch(Model model,
                                 @RequestParam(name = "name", defaultValue = "all", required = false) String name,
                                 @RequestParam(name = "brand_id", defaultValue = "-1", required = false) Long brand_id,
                                 @RequestParam(name = "price_from", defaultValue = "0", required = false) int price_from,
                                 @RequestParam(name = "price_to", defaultValue = "1000000000", required = false) int price_to,
                                 @RequestParam(name = "order", defaultValue = "asc", required = false) String order){

        List<Item> items;

        if (order.equals("asc"))
            items = itemService.findAllByNameContainsAndPriceBetweenAndBrandEqualsOrderByPriceAsc(name, price_from, price_to, itemService.getBrand(brand_id));
        else
            items = itemService.findAllByNameContainsAndPriceBetweenAndBrandEqualsOrderByPriceDesc(name, price_from, price_to, itemService.getBrand(brand_id));


        System.out.println("Check to null = " + items);
        System.out.println("price_from = " + price_from);
        System.out.println("price_to = " + price_to);
        if(items.size() == 0 && price_from == 0 && price_to == 1000000000) {
            items = itemService.findAllByNameContains(name);
            System.out.println("Name = " + items);

        }

        if (! items.isEmpty()) {
            brand_id = items.get(0).getBrand().getId();
        }

//        if(items == null)
//            items = itemService.getAllItems();


        model.addAttribute("items", items);
        model.addAttribute("categories", itemService.getAllCategories());
        model.addAttribute("brands", itemService.getAllBrands());
        model.addAttribute("brand_id", brand_id);
        return "user/advancedSearch";
    }


    @GetMapping(value = "/result")
    public String searchResult(Model model,
                               @RequestParam(name = "word", defaultValue = "all", required = false) String word,
                               @RequestParam(name = "orderBy", defaultValue = "asc", required = false) String orderBy,
                               @RequestParam(name = "price_from", defaultValue = "0", required = false) int priceFrom,
                               @RequestParam(name = "price_to", defaultValue = "0", required = false) int priceTo){
        if(word.equals("all"))
            return "redirect:/";

        List<Item> items;

        if(priceTo == 0)
            priceTo = 100000000;

        System.out.println("price_from = " + priceFrom);
        System.out.println("price_to = " + priceTo);


        if(orderBy.equals("desc"))
            items = itemService.findAllByNameContainsAndPriceBetweenOrderByPriceDesc(word, priceFrom, priceTo);

        else
            items = itemService.findAllByNameContainsAndPriceBetweenOrderByPriceAsc(word, priceFrom, priceTo);



        model.addAttribute("items", items);
        model.addAttribute("word", word);
        return "user/searchResult";
    }


    @GetMapping(value = "/item")
    public String itemDetail(HttpServletRequest request, Model model, @RequestParam(name = "id", defaultValue = "1", required = true) Long id){
        Item item = itemService.getItem(id);
        if(item != null) {
            Users currentUser = getUserData();
            assert currentUser != null;
            boolean isAdmin = false;

            if (request.isUserInRole("ROLE_ADMIN")) {
                isAdmin = true;
            }

//            List<Roles> roles = currentUser.getRoles();
//            for(Roles r: roles)
//                if (r.g   etName().equals("ROLE_ADMIN"))
//                    isAdmin = true;

            List<Picture> pictures = pictureService.findPicturesByItem(item);
            List<Comment> comments = itemService.getAllCommentsByItem(item);
            model.addAttribute("pictures", pictures);
            model.addAttribute("item", item);
            model.addAttribute("categories", itemService.getAllCategories());
            model.addAttribute("brands", itemService.getAllBrands());
            model.addAttribute("comments", comments);
            model.addAttribute("currentUser", currentUser);
            model.addAttribute("isAdmin", isAdmin);
            return "user/itemDetail";
        }

        return "error";
    }


    @GetMapping(value = "/403")
    public String accessDenied(Model model){
        return "403";
    }


    @GetMapping(value = "/login")
    public String login(Model model,
                        @RequestParam(name = "error", defaultValue = "1", required = false) String error,
                        @RequestParam(name = "email", defaultValue = "", required = false) String email){
        model.addAttribute("email", email);
        return "login";
    }


    @GetMapping(value = "/register")
    public String register(Model model, @RequestParam(name = "error", required = false) String error){
        model.addAttribute("currentUser", getUserData());
        return "register";
    }


    @PostMapping(value = "/register")
    public String toRegister(@RequestParam(name = "email") String email,
                             @RequestParam(name = "password") String password,
                             @RequestParam(name = "retype_password") String re_password,
                             @RequestParam(name = "full_name") String full_name){

        if(password.equals(re_password)){
            String defaultAva = "https://cdn.icon-icons.com/icons2/1378/PNG/512/avatardefault_92824.png";
            Users user = new Users(email, password, full_name, defaultAva);
            if(userService.createUser(user) != null){
                return "redirect:/login?email="+email;

            }
        }
        return "redirect:/register?error";
    }


    @GetMapping(value="/profile")
    @PreAuthorize("isAuthenticated()")
    public String profile(Model model){
        model.addAttribute("currentUser", getUserData());
        System.out.println("User = " + getUserData());
        return "user/profile";
    }


    private Users getUserData(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(!(authentication instanceof AnonymousAuthenticationToken)){
            User secUser = (User)authentication.getPrincipal();
            Users myUser = userService.getUserByEmail(secUser.getUsername());
            return myUser;
        }

        return null;
    }

    @GetMapping(value="/basket_view")
    public String viewBasket(Model model, HttpServletRequest request){
        Map<Long, Long> basket_items = (HashMap) httpSession.getAttribute("basket");
        Map<Item, Long> itemBackets = new HashMap<>();

        double[] totalPrice = {0.0};

        try{
            assert basket_items!=null;
            basket_items.forEach((k,v)->itemBackets.put(itemService.getItem(k), v));
            basket_items.forEach((k,v)-> totalPrice[0] += (v *(itemService.getItem(k).getPrice())));
        }catch (Exception e){
            e.printStackTrace();
        }

        System.out.println(totalPrice[0]);
        model.addAttribute("totalPrice", totalPrice[0]);
        model.addAttribute("itemBackets", itemBackets);
        model.addAttribute("currentUser", getUserData());
        return "basket_view";
    }


    @PostMapping(value = "/basket/clean")
    public String cleanBasket(){
        httpSession.setAttribute("basket", null);
        return "redirect:/basket_view";
    }


    @PostMapping(value = "/basket/item_plus")
    public String plusBasket(HttpServletRequest request, HttpServletResponse response, @RequestParam(name = "item_id") Long item_id){
        Map<Long, Long> basket_items = (Map<Long, Long>) httpSession.getAttribute("basket");
        if (basket_items!=null){
            Long amount = basket_items.get(item_id);
            amount++;
            basket_items.put(item_id, amount);
            httpSession.setAttribute("basket", basket_items);
        }
        return "redirect:/basket_view";
    }

    @PostMapping(value = "/basket/item_minus")
    public String minusBasket(HttpServletRequest request, HttpServletResponse response, @RequestParam(name = "item_id") Long item_id) {
        Map<Long, Long> basket_items = (Map<Long, Long>) httpSession.getAttribute("basket");
        if (basket_items!=null){
            Long amount = basket_items.get(item_id);
            if (amount>1){
                amount--;
                basket_items.put(item_id, amount);
            }else {
                basket_items.remove(item_id);
            }
            httpSession.setAttribute("basket", basket_items);
        }
        return "redirect:/basket_view";
    }


//    @GetMapping(value="/basket_view")
//    public String basketView(Model model, HttpServletRequest request){
//
//        List<ItemBasket> itemBaskets = new ArrayList<>();
//        Cookie[] cookies = request.getCookies();
//        List<Long> ids = new ArrayList<>();
//
//        System.out.println("STEP 1 in basket_view");
//        if (cookies != null) {
//            for (Cookie c : cookies) {
//                System.out.println("c.getnamt = " + c.getName());
//                if (c.getName().contains("item_")) {
//                    String[] cooks = c.getName().split("_");
//
//                    Long cooks_id = Long.parseLong(cooks[1]);
//                    System.out.println("cooks_id = " + cooks_id);
//                    ids.add(cooks_id);
//                }
//            }
//
//            System.out.println("ids =" + ids);
//            List<Item> items = itemService.findItemsById(ids);
//            for (Item item: items) {
//                for (Cookie c : cookies) {
//                    if (c.getName().equals("item_"+item.getId())) {
//                        itemBaskets.add(new ItemBasket(item, Integer.parseInt(c.getValue())));
//                    }
//                }
//            }
//        }
//        System.out.println("STEP 3 in basket_view");
//        model.addAttribute("currentUser", getUserData());
//        model.addAttribute("categories", itemService.getAllCategories());
//        model.addAttribute("brands", itemService.getAllBrands());
//        model.addAttribute("itemBackets", itemBaskets);
//        return "basket_view";
//    }


//    @PostMapping(value = "/basket/add")
//    public String savePicture(HttpServletRequest request,
//                              HttpServletResponse response,
//                              @RequestParam(name = "item_id") Long item_id){
//        try {
//            Item item = itemService.getItem(item_id);
//            System.out.println("Step 1");
//            String cookie_name = "item_" + item.getId();
//            int count = 0;
//            Cookie[] cookies = request.getCookies();
//            if (cookies != null) {
//                for (Cookie c : cookies) {
//                    if(c.getName().contains("item_")) {
//                        String[] cooks = c.getName().split("_");
//                        Long cooks_id = Long.parseLong(cooks[1]);
//                        if (cooks_id.equals(item.getId())) {
//                            count = Integer.parseInt(c.getValue()) + 1;
//                        }
//                    }
//                }
//            }
//
//            System.out.println("cookie_name = " + cookie_name);
//            System.out.println("count = " + count);
//            Cookie cookie = new Cookie(cookie_name, String.valueOf(count));
//            cookie.setMaxAge(7 * 24 * 60 * 60);
//            response.addCookie(cookie);
//            return "redirect:/basket_view";
//        } catch (Exception e){
//            e.printStackTrace();
//        }
//
//        return "redirect:/item?id="+item_id;
//    }



    @PostMapping(value = "/basket/add")
    public String addBasket(Model model,HttpServletRequest request, HttpServletResponse response, @RequestParam(name = "item_id") Long item_id){
        Map<Long, Long> basket_items = (HashMap) httpSession.getAttribute("basket");
        System.out.println("_______________");
        System.out.println(basket_items);

        if (basket_items != null){
            Long amount = basket_items.get(item_id);
            if (amount!=null){
                amount++;
                basket_items.put(item_id, amount);
            }else{
                basket_items.put(item_id, 1L);
            }
        }
        else {
            basket_items = new HashMap<>();
            basket_items.put(item_id, 1L);
        }

        model.addAttribute("currentUser", getUserData());
        httpSession.setAttribute("basket", basket_items);
        return "redirect:/item?id=" + item_id;
    }




    /*************************** Comments ***************************/

    @PostMapping(value = "comment_save")
    public String saveComment(@RequestParam(name = "item_id") Long item_id,
                              @RequestParam(name = "comment_text") String comment_text){
        Item item = itemService.getItem(item_id);

        if (item != null) {
            long millis = System.currentTimeMillis();
            Date date = new java.sql.Date(millis);
            Comment comment = new Comment(comment_text, date, item, getUserData());
            itemService.saveComment(comment);
        }
        return "redirect:/item?id="+item_id;
    }


    @PostMapping(value = "comment_update")
    public String updateComment(@RequestParam(name = "item_id") Long item_id,
                              @RequestParam(name = "comment_id") Long comment_id,
                              @RequestParam(name = "comment_text") String comment_text){
        Comment comment = itemService.getComment(comment_id);

        if (comment != null) {
            long millis = System.currentTimeMillis();
            Date date = new java.sql.Date(millis);
            comment.setComment(comment_text);
            comment.setAddedDate(date);
            itemService.saveComment(comment);
        }
        return "redirect:/item?id="+item_id;
    }


    @PostMapping(value = "comment_delete")
    public String updateComment(@RequestParam(name = "item_id") Long item_id,
                                @RequestParam(name = "comment_id") Long comment_id){
        Comment comment = itemService.getComment(comment_id);

        if (comment != null) {
            itemService.deleteComment(comment.getId());
        }
        return "redirect:/item?id="+item_id;
    }

    /*************************** END Comments ***************************/

}
