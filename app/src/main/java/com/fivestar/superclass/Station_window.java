package com.fivestar.superclass;

import android.content.Intent;

import android.os.Bundle;

import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Lenovo on 2017/10/26.
 */

public class Station_window extends ListFragment {


    int imageArr [] ={R.drawable.head1,R.drawable.head3,R.drawable.head4,R.drawable.head5,
            R.drawable.head6,R.drawable.head7,R.drawable.head8,R.drawable.head9,R.drawable.head10,R.drawable.head11,
            R.drawable.head12,R.drawable.head13,R.drawable.head14,R.drawable.head15,R.drawable.head16,};
    private ArrayList<String> list_title ;
    private ArrayList<String> list_content;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.station_window, null);
        initData();

//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, list);
//        setListAdapter(adapter);
        setListAdapter(new MyAdapter());

        return view;

    }

    public void initData(){
        list_title = new ArrayList<String>();
        String s1 = "人民大学学霸女生获吴玉章奖学金 保研清华大学";
        String s2 = "教育部决定撤销陈小武“长江学者”称号，停发并追回已发奖金";
        String s3 = "中国大学生家庭出身调查研究：寒门难出贵子？";
        String s4 = "这才是秀女儿的最高境界！把女儿画进画里，写进诗里，美翻了";
        String s5 = "国科大校长回应落选一流大学建设高校：不会有什么大的影响";
        String s6 = "高校人才争夺加剧，学者身价飙涨！有人开百万年薪+800万房补";
        String s7 = "家长挖坑，教师点收红包后被处分，对教师的道德绑架何时休";
        String s8 = "13岁杭州男孩进澳洲奥数国家队，11岁获美国奥数金牌";
        String s9 = "从北外到耶鲁MBA，她的家庭阐述了：榜样比严厉管教更有效";
        list_title.add(s1);
        list_title.add(s2);
        list_title.add(s3);
        list_title.add(s4);
        list_title.add(s5);
        list_title.add(s6);
        list_title.add(s7);
        list_title.add(s8);
        list_title.add(s9);

        list_content = new ArrayList<String>();

        String s11 = "   \t新闻-国际政治实验班14级本科生，现任校学生会常务副主席、" +
                "新闻学院本科联合党支部书记。2017年吴玉章奖学金获得者。";
        String s22 = "   \t针对近日高校教师性骚扰学生事件，教育部高度关注。教育部" +
                "1月14日回复澎湃新闻（www.thepaper.cn)表示，已要求有关高校迅速核查，如果属实要依法依规严肃处理。";

        String s33 = "   \t李中清缓慢地走向讲台。他有着典型的“美式身材”和一张中国" +
                "人的面孔。笑起来眼睛眯成一条缝，脸颊两侧微微鼓起，那种与年龄无关的少年感，和他的父亲李政道一模一样。";
        String s44 = "   \t父母对孩子的爱" + "　　是源自生命深处的，" + "" + "　　是纯粹挚诚的。";
        String s55 = "    \t2017年11月初，中国科学院大学（以下简称“国科大”）计算机与控制学院大四学生谈清扬，" +
                "以第一作者身份撰写的论文";
        String s66 = "   \t1月5日，教育部发布了2017年长江学者公示名单，共有463名建议人选，其中特聘教授148名，" +
                "讲座教授51名，青年学者264人。";

        String s77 = "   \t学生们扮演《西游记》中的角色，丰富的表情，夸张的动作，引得同学们发出阵阵欢笑。";
        String s88 = "   \t当大部分孩子和家长都被奥数折磨得“欲仙欲死”时，一年半" +
                "前，杭州学军小学的一名五年级学生就凭此引起了鹿姐姐的关注";
        String s99 = "   \t每年的12月中下旬，是美国大学发放ED offer的密集期。美国名校尤其是“藤校”offer的发放，";
        list_content.add(s11);
        list_content.add(s22);
        list_content.add(s33);
        list_content.add(s44);
        list_content.add(s55);
        list_content.add(s66);
        list_content.add(s77);
        list_content.add(s88);
        list_content.add(s99);


    }


    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Toast.makeText(getActivity(),"正在加载,请稍等...", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getActivity(),Station_window_item_Des.class);
        intent.putExtra("position", position);
        //数据集合传输
        intent.putStringArrayListExtra("list_title",list_title);
        intent.putStringArrayListExtra("list_content",list_content);
        startActivity(intent);
    }


    private class MyAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return list_title.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view;
            if(convertView == null){
                view = View.inflate(getActivity(),R.layout.station_item,null);
            }else{
                view = convertView;
            }
//
//            //设置奇行偶行不同颜色
//            if(position %2 == 0){
//                view.setBackgroundResource(R.color.color_itemBack);
//            }else{
//                view.setBackgroundResource(R.color.color_Theme);
//            }
            view.setBackgroundResource(R.color.color_White);


            Item item = new Item();
            //动态设置Item里面的内容，一个iamge，一个title，一个content
            item.imageView_item_iamge = (ImageView) view.findViewById(R.id.iv_item_iamge);
            item.textView_item_title = (TextView) view.findViewById(R.id.tv_item_title);
            item.textView_item_content = (TextView) view.findViewById(R.id.tv_item_content);
            item.imageView_item_iamge.setImageResource(imageArr[position]);
            item.textView_item_title.setText(list_title.get(position));
            item.textView_item_content.setText(list_content.get(position));

            return view;
        }
    }

    //封装一个Item类，进行管理里面的控件

    public class Item{
        public ImageView imageView_item_iamge;
        public TextView textView_item_title;
        public TextView textView_item_content;
    }

}
