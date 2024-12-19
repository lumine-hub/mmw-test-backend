package com.xlm.mmwave.controller;

import com.xlm.mmwave.dto.OnlineUserCountDto;
import com.xlm.mmwave.utils.Res;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import com.xlm.mmwave.controller.User;
/**
 * @author xlm
 * 2024/12/9 下午4:29
 */
@RestController
@RequestMapping("/api/usr")
public class UsrController {
    private static final List<Integer> WARNING_TYPE_IDS = Arrays.asList(11, 21, 12, 22, 3, 41, 42);

    @GetMapping("/getOnlineUsrCnt")
    public Res hello(){
        Map<String, Object> response = new HashMap<>();
        response.put("online_usr_cnt", 2190);
        response.put("delta_online_usr_cnt", 190);
        response.put("normal_online_usr_cnt", 3001);
        response.put("warning_online_usr_cnt", 108);

        // 返回封装好的数据
        return Res.ok(response);
    }


    @GetMapping("/getUsrWarning/{count}")
    public Res getUsrWarning(@PathVariable("count") int count) {
        List<Map<String, Object>> warningList = new ArrayList<>();
        Random random = new Random();

        for (int i = 1; i <= count; i++) {
            Map<String, Object> warningItem = new HashMap<>();
            warningItem.put("usr_desp", "北京市海淀区刘**" + i);
            warningItem.put("warning_timestamp", System.currentTimeMillis() / 1000 - i * 1000); // 时间戳为当前时间减去一定间隔
            warningItem.put("warning_type_id", WARNING_TYPE_IDS.get(random.nextInt(WARNING_TYPE_IDS.size()))); // 从固定列表中随机选择
            warningItem.put("patientId", i); // 病人 ID 为 1~count
            warningList.add(warningItem);
        }

        Map<String, Object> response = new HashMap<>();
        response.put("warning_cnt", warningList.size());
        response.put("warning_list", warningList);

        return Res.ok(response);
    }

    @GetMapping("/getWarningCnt")
    public Res getWarningCnt() {
        List<Map<String, Object>> warningCntList = new ArrayList<>();
        List<Integer> shuffledWarningTypeIds = new ArrayList<>(WARNING_TYPE_IDS);

        // 打乱列表顺序
        Collections.shuffle(shuffledWarningTypeIds);

        for (int i = 0; i < 5; i++) {
            Map<String, Object> warning = new HashMap<>();
            warning.put("warning_type_id", shuffledWarningTypeIds.get(i)); // 顺序取一个值
            warning.put("warning_type_cnt", (i + 1) * 10); // 假设警告类型的数量递增
            warningCntList.add(warning);
        }

        Map<String, Object> response = new HashMap<>();
        response.put("warning_cnt_list", warningCntList);

        return Res.ok(response);
    }

    @GetMapping("/getUsrCntPerCity")
    public Res getUsrCntPerCity() {
        List<Map<String, Object>> usrAndCityList = new ArrayList<>();
        String[] cities = {"北京", "上海", "广州", "深圳", "杭州"};
        int[] counts = {2005, 1500, 1800, 1700, 1600};

        for (int i = 0; i < cities.length; i++) {
            Map<String, Object> cityData = new HashMap<>();
            cityData.put("city_name", cities[i]); // 城市名称
            cityData.put("usr_cnt_in_city", counts[i]); // 城市用户数
            usrAndCityList.add(cityData);
        }

        Map<String, Object> response = new HashMap<>();
        response.put("usr_cnt", 120899); // 总用户数
        response.put("delta_usr_cnt", 248); // 用户增量
        response.put("usr_and_city_list", usrAndCityList);

        return Res.ok(response);
    }

    @GetMapping("/getUsrWarningCntPerDate")
    public Res getUsrWarningCntPerDate() {
        Map<String, Object> yearLevelResult = new HashMap<>();
        yearLevelResult.put("usr_cnt", 111);
        yearLevelResult.put("warning_cnt", 11);

        Map<String, Object> yearLevelResult2 = new HashMap<>();
        yearLevelResult2.put("usr_cnt", 222);
        yearLevelResult2.put("warning_cnt", 22);

        Map<String, Object> yearLevelResult3 = new HashMap<>();
        yearLevelResult3.put("usr_cnt", 333);
        yearLevelResult3.put("warning_cnt", 33);

        Map<String, Object> yearLevelResult4 = new HashMap<>();
        yearLevelResult4.put("usr_cnt", 444);
        yearLevelResult4.put("warning_cnt", 44);

        Map<String, Object> response = new HashMap<>();
        response.put("year_level_result", yearLevelResult);
        response.put("season_level_result", yearLevelResult2);
        response.put("month_level_result", yearLevelResult3);
        response.put("day_level_result", yearLevelResult4);

        return Res.ok(response);
    }

    @GetMapping("/getWarningCnt2")
    public Res getWarningCntDuplicate() {
        List<Map<String, Object>> warningCntList = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            Map<String, Object> warning = new HashMap<>();
            warning.put("warning_type_id", i);
            warning.put("warning_type_cnt", i * 15);
            warningCntList.add(warning);
        }

        Map<String, Object> response = new HashMap<>();
        response.put("warning_cnt_list", warningCntList);

        return Res.ok(response);
    }


    @GetMapping("/getAllUsr")
    public Res getAllUsr(@RequestParam int page, @RequestParam int pageSize) {
        // 模拟获取用户数据
        List<User> userList = getUserList();

        // 获取分页数据（这里只是简单的模拟，实际可以通过数据库查询分页）
        int start = (page - 1) * pageSize;
        int end = Math.min(start + pageSize, userList.size());
        List<User> pagedUserList = userList.subList(start, end);

        // 构建响应
        Map<String, Object> responseData = new HashMap<>();
        responseData.put("usr_list", pagedUserList);
        responseData.put("total", 13);

        return Res.ok(responseData);
    }

    private List<User> getUserList() {
        // 这里可以模拟从数据库或者其他地方获取用户数据
        List<User> userList = new ArrayList<>();

        // 添加用户
        userList.add(createUser(15, "庞博逸（卧姿）", "北京市朝阳区", 22, 0, true));
        userList.add(createUser(16, "吕锐（卧姿）", "上海市浦东新区", 23, 0, false));
        userList.add(createUser(17, "林麒（卧姿）", "广州市天河区", 24, 0, true));
        userList.add(createUser(19, "庞博逸（卧姿）", "深圳市南山区", 22, 0, false));
        userList.add(createUser(32, "赵朗程", "成都市武侯区", 25, 0, true));
        userList.add(createUser(34, "梁雨萌", "杭州市西湖区", 26, 0, false));
        userList.add(createUser(37, "刘一慧", "武汉市江汉区", 23, 1, true));
        userList.add(createUser(39, "鲍俊杰", "南京市鼓楼区", 22, 0, false));
        userList.add(createUser(40, "张峰霖", "西安市雁塔区", 24, 0, true));
        userList.add(createUser(41, "郑子新", "重庆市渝中区", 22, 1, false));
        userList.add(createUser(20, "康乐（卧姿）", "天津市和平区", 24, 0, true));
        userList.add(createUser(21, "患者1（术前）", "苏州市姑苏区", 61, 0, false));
        userList.add(createUser(23, "患者2", "青岛市市南区", 65, 1, true));

        return userList;
    }

    private User createUser(int usrId, String name, String address, int age, int gender, boolean isOn) {
        User user = new User();
        user.setUsrId(usrId);
        User.UserDetails usrDesp = new User.UserDetails();
        usrDesp.setName(name);
        usrDesp.setAddress(address);
        usrDesp.setAge(age);
        usrDesp.setGender(gender);
        usrDesp.setProvince("");
        usrDesp.setCity("");
        user.setUsrDesp(usrDesp);
        user.setOn(isOn);
        return user;
    }

}
