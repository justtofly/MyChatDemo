package com.myshopdemo.fi.controller.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.myshopdemo.fi.R;
import com.myshopdemo.fi.model.Model;
import com.myshopdemo.fi.model.bean.UserInfo;

//添加联系人页面
public class AddContactActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView       tvAddcontactSearch;
    private ImageView      ivAddcontactIcon;
    private TextView       tvAddcontactName;
    private Button         btnAddcontactAdd;
    private EditText       et_addcontact_name;
    private RelativeLayout rl_addcontact;

    /**
     * Find the Views in the layout<br />
     * <br />
     * Auto-created on 2017-10-13 22:22:30 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    private void findViews() {
        tvAddcontactSearch = (TextView) findViewById(R.id.tv_addcontact_search);
        ivAddcontactIcon = (ImageView) findViewById(R.id.iv_addcontact_icon);
        tvAddcontactName = (TextView) findViewById(R.id.tv_addcontact_name);
        btnAddcontactAdd = (Button) findViewById(R.id.btn_addcontact_add);
        et_addcontact_name = (EditText)findViewById( R.id.et_addcontact_name );
        rl_addcontact = (RelativeLayout)findViewById( R.id.rl_addcontact );

        btnAddcontactAdd.setOnClickListener( this );
        tvAddcontactSearch.setOnClickListener( this );
    }

    @Override
    public void onClick(View v) {
        if ( v == btnAddcontactAdd ) {//添加按钮
            add();
        }else if (v==tvAddcontactSearch){//查找按钮
            search();
        }
    }

    /**
     * 查找按钮的事件处理
     */
    private void search() {
        //获取输入的名称
        final String name=et_addcontact_name.getText().toString();

        //检验输入的名称
        if(TextUtils.isEmpty(name)){
            Toast.makeText(AddContactActivity.this,"您输入的用户名称不能为空,请重新输入！",Toast.LENGTH_SHORT).show();
        }
        //去服务器判断当前用户是否存在
        Model.getInstance().getGlobalThreadPool().execute(new Runnable() {
            @Override
            public void run() {
                final UserInfo userInfo=new UserInfo(name);

                //更新UI显示
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        rl_addcontact.setVisibility(View.VISIBLE);
                        tvAddcontactName.setText(userInfo.getName());
                    }
                });
            }
        });

    }

    private void add() {

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        //初始化View
        findViews();

    }
}
