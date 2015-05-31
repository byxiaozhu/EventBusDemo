package com.zhuyongit.eventbusdemo;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.zhuyongit.eventbusdemo.bean.EventBean;

import de.greenrobot.event.EventBus;


public class MainActivity extends ActionBarActivity {

    private TextView textView ;
    private Button  btnEventBusClick ;
    private Button  btnStartActivity ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EventBus.getDefault().register(this);

        // 初始化UI
        initView();
    }

    /**
     * 初始化UI
     */
    private void initView() {
            textView = (TextView) findViewById(R.id.textView);
            btnEventBusClick = (Button) findViewById(R.id.btnPostEvent);
            btnEventBusClick.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    EventBean bean = new EventBean();
                    bean.setTag("0");
                    bean.setContent("我是事件0");
                    EventBus.getDefault().post(bean);
                }
            });

            btnStartActivity = (Button) findViewById(R.id.btnStartActivity);
            btnStartActivity.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(MainActivity.this,MainActivity2Activity.class));
                }
            });
    }

    public void onEvent(EventBean eventBean) {
        if (eventBean.getTag().equals("0")) {
            textView.setText(eventBean.getContent());
        }if (eventBean.getTag().equals("1")) {
            textView.setText("EventBus 发布订阅模式 ， 应该还蛮好用的 ！");
        }
    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
