package com.chaoxing.sendsms;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;

public class ReplyActivity  extends Activity {

    private String[] smss={"您真的觉得贵？这就对了，价格高代表品质高！\n" ,
            
            "您想不想省钱呢？阿姨，最好的省钱的方法就是一次买对。\n" ,
            
            "阿姨，您希望是什么样的价格呢？市场上还有很多价格低，品质一般的产品，您会买吗？\n" ,
            
            " 您是拿什么产品和我们比的呢？是······还是······（略带开玩笑的口气）\n" ,
            
            "阿姨，您想不想赚钱呢？（您身体好了，是不是可以多拿几年国家的退休金呢？）\n" ,
            
            "其实我觉得价格并不是最主要的，关键是对您有没有用？有没有效果？\n" ,
            
            " 好东西，我们在掏钱的那一刻是难受的，但是以后越用越顺心，质量差的东西，我们只有在掏钱的那一刻很开心，以后越用越难受，所以说“便宜无好货，好货不便宜”。\n" ,
            
            "（装作不理解的心情）阿姨，您怎么会这么想呢？您这样身份的人怎么会听不明白呢？\n" ,
            
            "阿姨，如果我给您配上最优惠的政策呢，您还嫌贵吗？\n" ,
            
            "您愿意多花点钱买高品质的呢，还是愿意少花点钱没效果得不到保证的呢？阿姨啊，这可是吃的东西。\n" ,
            
            "不错，我们的产品是贵了点，因为我们要考虑到长远的售后服务，您希望有完善的售后服务吗？\n" ,
            
            "谢谢您对我们价格的如此关注，这正是我们产品能胜人一筹的地方，您最在乎的是钱，还是为您带来的好处的？\n" ,
            
            "您想不想购买产品后有很多的麻烦呢？（我们有雄厚的实力和完善的售后服务）\n" ,
            
            "这不是钱的问题，钱等得了，但是健康不能等啊，您的身体这样耗下去结果会是怎么样呢?\n" ,
            
            "我太理解您现在的想法了，在没接触这个产品之前我也是这么想的，但是您知道我后来发现什么了吗？（讲述产品的价值)\n" ,
            
            "我太理解您现在的想法了，在没接触这个产品之前，我这有个xx叔叔也是这样想的，可是您知道他服用了三天后发生了什么情况吗？\n" ,
            
            "  谢谢您给我讲实话，要是我解决价格问题，您会购买吗？\n" ,
            
            "要是我证明这个价格是公道的，您会买吗？\n" ,
            
            "我们先抛开价格的问题，您喜欢我们的产品吗？对于喜欢的东西多付出一点您觉得值吗？\n" ,
            
            "客户拒绝的攻单话术"};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reply);

        ListView lv_reply = findViewById(R.id.lv_reply);

        ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<>(getApplicationContext(), R.layout.item_reply, smss);
        lv_reply.setAdapter(stringArrayAdapter);

        lv_reply.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {




                Intent intent = new Intent();
                intent.putExtra("reply", smss[position]);
                setResult(2,intent);

                finish();
            }
        });

    }
}
