package com.xlm.mmwave.controller;

import com.xlm.mmwave.utils.Res;
import com.xlm.mmwave.vo.BrDataRequest;
import com.xlm.mmwave.vo.BrDataResponse;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author xlm
 * 2024/12/12 下午3:31
 */
@RestController
@RequestMapping("/api")
public class TestController {
    @GetMapping("/arr/getWaveform/uid/{id}")
    public Res getWaveform(@PathVariable("id") String id) {
        // 生成假数据
        List<Integer> scgWaveform = generateWaveform(1000, -2000, 2000);
        // 构造返回结果
        Map<String, Object> response = new HashMap<>();
        response.put("uid", id);
        response.put("isArrhythmia", true); // 假设返回值为 true
        response.put("scg_waveform", scgWaveform);
        return Res.ok(response);
    }

    @GetMapping("/hr/getOneWave/uid/{uid}")
    public Res getOneWave(@PathVariable("uid") String uid) {
        // 假数据
        Random  random = new Random();
        int i = random.nextInt();
        Map<String, Object> response = new HashMap<>();
        response.put("uid", uid);
        response.put("heart_rate", random.nextInt(100) + 30);
        response.put("timestamp", System.currentTimeMillis() / 1000);

        return Res.ok(response);
    }

    @GetMapping("/hr/getWaveform/uid/{uid}")
    public Res getWaveform2(@PathVariable("uid") String uid) {
        // 假数据
        List<Integer> heartWaveform = new ArrayList<>();
        List<Long> timeStamps = new ArrayList<>();
        long currentTime = System.currentTimeMillis() / 1000 - 500; // 当前时间戳（秒级）

        // 生成心电波形数据 (heart_waveform) 和时间戳 (time_stamp)
        for (int i = 0; i < 100; i++) {
            heartWaveform.add((int) (Math.random() * 100) + 30); // 随机生成 0-200 之间的心电波形数据
            timeStamps.add(currentTime + i * 5); // 时间戳每5秒递增
        }

        Map<String, Object> response = new HashMap<>();
        response.put("uid", uid);
        response.put("heart_rate", 70); // 假定心率为70
        response.put("heart_waveform", heartWaveform); // 心电波形数据
        response.put("time_stamp", timeStamps); // 时间戳列表

        return Res.ok(response);
    }

    @PostMapping("/history/br/getBrData")
    public Res getBrData(@RequestBody BrDataRequest requestData) {
        // 从请求中获取uid、start_time、end_time
        int uid = requestData.getUid();
        String startTime = requestData.getStart_time();
        String endTime = requestData.getEnd_time();

        // 假数据
        Random random = new Random();
        int dataLength = 100;  // 假设数据长度是100

        List<Integer> humMoveCnt = new ArrayList<>();
        List<Long> timeStamp = new ArrayList<>();

        // 生成假数据
        for (int i = 0; i < dataLength; i++) {
            humMoveCnt.add(random.nextInt(10));  // 随机生成一个0到9之间的数
            timeStamp.add(System.currentTimeMillis() / 1000 + i);  // 模拟一个时间戳
        }



        BrDataResponse data = new BrDataResponse();
        data.setUid(uid);
        data.setHum_move_cnt(humMoveCnt);
        data.setTime_stamp(timeStamp);

        return Res.ok(data);
    }


    // 生成随机波形数据
    private List<Integer> generateWaveform(int size, int min, int max) {
        Random random = new Random();
        List<Integer> waveform = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            int value = random.nextInt(max - min + 1) + min; // 生成范围内的随机值
            waveform.add(value);
        }
        return waveform;
    }

    @GetMapping("/br/getWaveform/uid/{uid}")
    public Res getBreathWaveform(@PathVariable("uid") String uid) {
        // 假数据
        Random random = new Random();
        int waveformLength = 100;  // 假设波形数据的长度是100
        List<Integer> breathWaveform = new ArrayList<>();

        // 生成假数据
        for (int i = 0; i < waveformLength; i++) {
            breathWaveform.add(random.nextInt(11) - 5); // 随机生成一个-5到5之间的数
        }

        Map<String, Object> response = new HashMap<>();
        response.put("uid", uid);
        response.put("breath_waveform", breathWaveform);
        return Res.ok(response);
    }

    @GetMapping("/br/getRing/uid/{uid}")
    public Res getBreathRing(@PathVariable("uid") String uid) {
        // 假数据
        Random random = new Random();
        int ringLength = 100;  // 假设环形数据的长度是100
        List<Integer> breathRingX = new ArrayList<>();
        List<Integer> breathRingY = new ArrayList<>();

        // 生成假数据
        for (int i = 0; i < ringLength; i++) {
            breathRingX.add(random.nextInt(11) - 5); // 随机生成一个-5到5之间的数
            breathRingY.add(random.nextInt(11) - 5); // 随机生成一个-5到5之间的数
        }

        Map<String, Object> response = new HashMap<>();
        response.put("uid", uid);
        response.put("breath_ring_x", breathRingX);
        response.put("breath_ring_y", breathRingY);
        return Res.ok(response);
    }


    private int statusCounter = 0; // 用于轮询 status 值

    @GetMapping("/humov/getStatus/uid/{uid}")
    public Res getStatus(@PathVariable("uid") String uid) {
        // 轮询状态值
        statusCounter = (statusCounter % 4) + 1; // 循环 1, 2, 3, 4
        Map<String, Object> response = new HashMap<>();
        response.put("uid", uid);
        response.put("status", statusCounter); // 返回当前状态值

        response.put("isWarning", statusCounter == 4); // 固定为 True

        if (Integer.parseInt(uid) == 1) {
            response.put("waringInfo", "用户离床过久！");
        } else {
            response.put("waringInfo", "用户离床过久！" + uid);
        }

        response.put("WarningId", 41);

        return Res.ok(response);
    }

    @GetMapping("/humov/getTrack/uid/{uid}")
    public Res getTrack(@PathVariable("uid") String uid) {
        // 随机生成坐标值
        Random random = new Random();
        double x = -2 + random.nextDouble() * 4; // [-2, 2]
        double y = random.nextDouble() * 6;      // [0, 6]
        double z = random.nextDouble() * 2.5;    // [0, 2.5]

        Map<String, Object> response = new HashMap<>();
        response.put("uid", uid);
        response.put("pos", new double[]{x, y, z}); // 返回坐标值

        return Res.ok(response);
    }
}
