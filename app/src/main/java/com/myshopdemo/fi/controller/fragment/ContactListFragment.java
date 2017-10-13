package com.myshopdemo.fi.controller.fragment;

import android.view.View;

import com.hyphenate.easeui.ui.EaseContactListFragment;
import com.myshopdemo.fi.R;

/**
 * 创建者     yangyanfei
 * 创建时间   2017/10/13 0013 15:53
 * 作用	      联系人列表页面
 * <p/>
 * 版本       $$Rev$$
 * 更新者     $$Author$$
 * 更新时间   $$Date$$
 * 更新描述   ${TODO}
 */
public class ContactListFragment extends EaseContactListFragment {
    @Override
    protected void initView() {
        super.initView();

        //布局显示加号
        titleBar.setRightImageResource(R.drawable.em_add);

        //添加头布局
        View headerView=View.inflate(getActivity(),R.layout.header_fragment_contact,null);
        listView.addHeaderView(headerView);
    }
}
