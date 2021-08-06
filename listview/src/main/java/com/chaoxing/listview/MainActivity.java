package com.chaoxing.listview;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {


    public static int i=3;

    public static long time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView listView = (ListView)findViewById(R.id.lv_listview);



        String[] objects={"张三","李四","王五","赵六","刘七"};


        //三个参数，只能传字符串，param2只能是有一个TextView的layout
        ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<>(this, R.layout.item5_onlytextview, objects);

        //
        stringArrayAdapter=  new ArrayAdapter<String>(this,R.layout.item2,R.id.tv_title2,objects);

        List<Map<String,String>> simpleList=new ArrayList<Map<String, String>>();
        
        String[] from={"title","content","image"};
        int[] to=new int[]{R.id.tv_title,R.id.tv_content,R.id.iv_icon};

        HashMap<String, String> item1 = new HashMap<>();
        item1.put("title","中国足球再次冲击世界杯");
        item1.put("image","https://foruse.oss-cn-beijing.aliyuncs.com/%E7%B4%A0%E6%9D%90/%E5%AD%A6%E6%A0%A1%E7%85%A7%E7%89%87/ej_banner.png");
        item1.put("content","中国足球再次冲击世界杯,9月与伊朗在上海1:0");

        HashMap<String, String> item2 = new HashMap<>();
        item2.put("title","英国足球再次冲击世界杯");
        item2.put("content","英国足球再次冲击世界杯,9月与伊朗在上海1:0");

        HashMap<String, String> item3 = new HashMap<>();
        item3.put("title","LPL赛区");
        item3.put("content","LPL赛区,9月与伊朗在上海1:0");

        simpleList.add(item1);
        simpleList.add(item2);
        simpleList.add(item3);

        

        SimpleAdapter simpleAdapter = new SimpleAdapter(this, simpleList, R.layout.item, from, to);


//        stringArrayAdapter=new ArrayAdapter<>()

        listView.setAdapter(new MyAdapter());
    }


    private class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {

            return i;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @SuppressLint("ResourceType")
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            /**
             * param1 上下文
             * param2 item布局的资源id
             * param3 ViewGroup (比如 LinearLayout)
             * 现在只想把xml文件转换成view对象 ，不需要放到viewGroup中，param3传null
             */



            System.out.println("调用getView"+position);

            View itemView=null;
            if (convertView==null){
//               itemView = findViewById(R.layout.item);

                /**
                 * param1 上下文
                 * param2 item布局的资源id
                 * param3 ViewGroup (比如 LinearLayout)
                 * 现在只想把xml文件转换成view对象 ，不需要放到viewGroup中，param3传null
                 */
                 itemView = View.inflate(MainActivity.this, R.layout.item, null);


                 //inflater
                //n. 增压泵；充气者
//                LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
//                View inflate = inflater.inflate(R.layout.item, null);

            }else{
//                System.out.println("重用旧的对象"+position);
                itemView=convertView;

            }


            return itemView;
        }
    }
}