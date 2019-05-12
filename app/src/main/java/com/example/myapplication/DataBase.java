package com.example.myapplication;

import java.util.ArrayList;
import java.util.List;

public class DataBase {
    public  static List<Student> students = getStudents();
    public static List<Student> row = getStudents();
    public static List<Student> getStudents() {
        List<Student> students = new ArrayList<>();
        students.add(new Student("1405030107","蒋鹏",R.raw.jiangpeng));
        students.add(new Student("1505030120","史崧成", R.raw.shisongcheng));
        students.add(new Student("1505030121","孙永", R.raw.sunyong));
        students.add(new Student("1602010329","赵浩宇",R.raw.zhaohaoyu));
        students.add(new Student("1605030102","曹梦晴",R.raw.caomengqing));
        students.add(new Student("1605030103","陈鹏", R.raw.chenpeng));
        students.add(new Student("1605030104","丁姗姗", R.raw.dingshanshan));
        students.add(new Student("1605030105","董喜来", R.raw.dongxilai));
        students.add(new Student("1605030106","付聪", R.raw.fucong));
        students.add(new Student("1605030107","付彦鑫",R.raw.fuyanxin ));
        students.add(new Student("1605030108","郭武", R.raw.guowu));
        students.add(new Student("1605030109","胡宇轩",R.raw.huyuxuan ));
        students.add(new Student("1605030110","贾思程", R.raw.jiasicheng));
        students.add(new Student("1605030111","姜天",R.raw.jiangtian ));
        students.add(new Student("1605030112","姜月", R.raw.jiangyue));
        students.add(new Student("1605030113","李升达", R.raw.lishengda));
        students.add(new Student("1605030114","欧叶",R.raw.ouye ));
        students.add(new Student("1605030115","任文迪", R.raw.renwendi));
        students.add(new Student("1605030116","宋红岳", R.raw.songhongyue));
        students.add(new Student("1605030117","宋耀辉", R.raw.songyaohui));
        students.add(new Student("1605030118","孙天也", R.raw.suntianye));
        students.add(new Student("1605030119","王炯", R.raw.wangjiong));
        students.add(new Student("1605030120","王曦",R.raw.wangxi ));
        students.add(new Student("1605030121","王宇", R.raw.wangyu));
        students.add(new Student("1605030122","王禹琦",R.raw.wangyuqi ));
        students.add(new Student("1605030123","夏德旭", R.raw.xiadexu));
        students.add(new Student("1605030125","杨堃", R.raw.yangkun));
        students.add(new Student("1605030126","叶柏宏",R.raw.yebaihong ));
        students.add(new Student("1605030127","张鹏", R.raw.zhangpeng));
        students.add(new Student("1605030128","张庆禹", R.raw.zhangqingyu));
        students.add(new Student("1605030129","张玉顺", R.raw.zhangyushun));
        students.add(new Student("1605030130","张裕森",R.raw.zhangyusen ));
        students.add(new Student("1605030131","周文博", R.raw.zhouwenbo));
        students.add(new Student("1605030132","朱春阳", R.raw.zhuchunyang));
        return students;
    }


}
