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
    private static int lastArrhythmiaState = -1; // 初始值

    @GetMapping("/arr/getWaveform/uid/{id}")
    public Res getWaveform(@PathVariable("id") String id) {
        // 生成假数据
        List<Integer> scgWaveform = generateWaveform(1000, -2000, 2000);

        // 计算当前 isArrhythmia 的值
        lastArrhythmiaState = (lastArrhythmiaState + 2) % 3 - 1; // 交替 0, 1, -1

        // 构造返回结果
        Map<String, Object> response = new HashMap<>();
        response.put("uid", id);
        response.put("isArrhythmia", lastArrhythmiaState);
        response.put("scg_waveform", scgWaveform);
        response.put("is_in_bed", true );

        return Res.ok(response);
    }

    @GetMapping("/hr/getOneWave/uid/{uid}")
    public Res getOneWave(@PathVariable("uid") String uid) {
        // 随机数生成器
        Random random = new Random();

        // 设置 heart_rate 值，70% 的概率为 -1
        int heartRate = random.nextDouble() < 0.7 ? -1 : random.nextInt(100) + 30;

        // 构造返回结果
        Map<String, Object> response = new HashMap<>();
        response.put("uid", uid);
        response.put("heart_rate", heartRate);
        response.put("timestamp", System.currentTimeMillis() / 1000);

        return Res.ok(response);
    }

    @GetMapping("/hr/getWaveform/uid/{uid}")
    public Res getWaveform2(@PathVariable("uid") String uid) {
        // 假数据
        List<Integer> heartWaveform = new ArrayList<>();
        List<Long> timeStamps = new ArrayList<>();
        long currentTime = System.currentTimeMillis() / 1000 - 300; // 当前时间戳（秒级），5分钟内数据

        // 生成心电波形数据 (heart_waveform) 和时间戳 (time_stamp)
        for (int i = 0; i < 60; i++) { // 60个点
            // 30%的概率值为 -1
//            if (Math.random() < 0.3) {
            if (Math.random() < 0.3) {
                heartWaveform.add(-1);
            } else {
                heartWaveform.add((int) (Math.random() * 100) + 30); // 随机生成 30-129 之间的心电波形数据
            }
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

    @GetMapping("/br/getWaveform/u2id/{uid}")
    public Res getBreathWavefor2m(@PathVariable("uid") String uid) {
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

    @GetMapping("/br/getRing/u2id/{uid}")
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
        // 轮询状态值：0 0 1 1 2 2 3 3 4 4
//        statusCounter = (statusCounter % 10) + 1; // 循环 1 到 10
//        int status = (statusCounter - 1) / 2; // 将计数转换为 0 0 1 1 2 2 3 3 4 4
        int status = (statusCounter++ % 4);

        Map<String, Object> response = new HashMap<>();
        response.put("uid", uid);
        response.put("status", status); // 返回当前状态值

        response.put("isWarning", status == 4); // 固定为 True 当 status 为 4

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

    @GetMapping("/br/getWaveform/uid/{uid}")
    public Res getBreathWaveform(@PathVariable("uid") String uid) {
        // 假数据
        Random random = new Random();
        int waveformLength = 100; // 假设波形数据的长度是100
        List<Integer> breathWaveform = new ArrayList<>();

        // 生成假数据
        for (int i = 0; i < waveformLength; i++) {
            breathWaveform.add(random.nextInt(11) - 5); // 随机生成一个-5到5之间的数
        }

        // 生成肺功能参数假数据
        Map<String, Object> breath_param = new HashMap<>();
        breath_param.put("rr", random.nextInt(16) + 10); // 呼吸速率，每分钟10-25次
        breath_param.put("ti", random.nextDouble() * 1.0 + 0.5); // 吸气时间，0.5-1.5秒
        breath_param.put("te", random.nextDouble() * 1.5 + 1.0); // 呼气时间，1.0-2.5秒
        breath_param.put("t_tot", random.nextDouble() * 3.0 + 2.0); // 呼吸周期时间，2.0-5.0秒
        breath_param.put("ti/te", random.nextDouble() * 1.5 + 0.5); // 吸呼气时间比，0.5-2.0
        breath_param.put("duty_cycle", random.nextDouble() * 0.4 + 0.3); // 吸气周期时间比，0.3-0.7
        breath_param.put("t_ptif/ti", random.nextDouble() * 0.5 + 0.2); // 吸气达峰时间比，0.2-0.7
        breath_param.put("t_ptef/te", random.nextDouble() * 0.5 + 0.2); // 呼气达峰时间比，0.2-0.7
        breath_param.put("t_ptif", random.nextDouble() * 0.5 + 0.2); // 吸气达峰时间，0.2-0.7秒
        breath_param.put("t_ptef", random.nextDouble() * 0.5 + 0.2); // 呼气达峰时间，0.2-0.7秒
        breath_param.put("ptif/ptef", random.nextDouble() * 2.0 + 0.5); // 吸气呼气达峰时间比，0.5-2.5
        breath_param.put("ptif", random.nextDouble() * 2.0 + 0.5); // 吸气峰流量，0.5-2.5 L/s
        breath_param.put("ptef", random.nextDouble() * 2.0 + 0.5); // 呼气峰流量，0.5-2.5 L/s
        breath_param.put("ie50", random.nextDouble() * 1.0 + 0.5); // 吸气呼气中期流量比，0.5-1.5
        breath_param.put("tef50", random.nextDouble() * 1.5 + 0.5); // 呼气中期流量，0.5-2.0 L/s
        breath_param.put("tif50", random.nextDouble() * 1.5 + 0.5); // 吸气中期流量，0.5-2.0 L/s

        // 构建响应
        Map<String, Object> response = new HashMap<>();
        response.put("uid", uid);
        response.put("breath_waveform", breathWaveform);
        response.put("breath_param", breath_param); // 添加肺功能参数数据
        return Res.ok(response);
    }
    @GetMapping("/br/getRing/uid/{uid}")
    public Res getBreathRing2(@PathVariable("uid") String uid) {
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
}
