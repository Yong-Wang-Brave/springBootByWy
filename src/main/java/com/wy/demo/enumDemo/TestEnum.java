package com.wy.demo.enumDemo;


import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
/*判断传入数值的等级*/
public class TestEnum {

    private static final List<String> FASTING_POINT = Arrays.asList("MorningRise");
    private static final List<String> AFTER_MEAL_POINT = Arrays.asList("AfterBreakFast");

    //根据传入的测量时间点  与对应的测量值 判断餐前还是餐后
    //根据传入的值进行分组

    public static void main(String[] args) {
        List<List<String>> points = Arrays.asList(FASTING_POINT, AFTER_MEAL_POINT);
        List<BloodSugerValue> bloodSugerValues = Arrays.asList(new BloodSugerValue(2d), new BloodSugerValue(3.5d));
        BloodSugerType type;

        for (List<String> point : points) {
            if (point.contains("MorningRise")) {
                 type = BloodSugerType.Fasting;
            }else{
                 type = BloodSugerType.AfterMeal;
            }


            BloodSugerType finalType = type;
            Map<MyEnum, List<Double>> collect = bloodSugerValues.stream().map(BloodSugerValue::getValue).collect(Collectors.groupingBy(value -> MyEnum.judgeLevel(value, finalType),
                    Collectors.toList()));
            System.out.println(collect);

        }

    /*        {L1=[2.0], L2=[3.5]}
        {L0=[2.0, 3.5]}*/


        }
    }







