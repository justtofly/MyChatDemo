package com.myshopdemo.fi.controller.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.myshopdemo.fi.R;
import com.myshopdemo.fi.model.bean.InvationInfo;
import com.myshopdemo.fi.model.bean.UserInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * 创建者     yangyanfei
 * 创建时间   2017/10/15 0015 19:32
 * 作用	      邀请信息列表页面的适配器
 * <p/>
 * 版本       $$Rev$$
 * 更新者     $$Author$$
 * 更新时间   $$Date$$
 * 更新描述   ${TODO}
 */
public class InviteAdapter extends BaseAdapter {
    //创建上下文对象
    private Context mContext;

    //实例化集合对象
    private List<InvationInfo> mInvationInfos = new ArrayList<>();

    

    //构造方法，传入上下文
    public InviteAdapter(Context context,OnInviteListener onInviteListener) {
        mContext = context;

        mOnInviteListener=onInviteListener;
    }

    //刷新数据的方法
    public void refresh(List<InvationInfo> invationInfos) {
        if (invationInfos != null && invationInfos.size() >= 0) {

            //每次进来，先把它清空,再添加
            mInvationInfos.clear();

            mInvationInfos.addAll(invationInfos);

            //刷新适配器
            notifyDataSetChanged();
        }
    }

    @Override
    public int getCount() {
        return mInvationInfos==null?0:mInvationInfos.size();
    }

    @Override
    public Object getItem(int position) {
        return mInvationInfos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //1.获取创建的ViewHolder
        ViewHolder viewHolder=null;
        if (convertView==null){
            viewHolder=new ViewHolder();

            convertView=View.inflate(mContext, R.layout.item_invite,null);

            viewHolder.name= (TextView) convertView.findViewById(R.id.tv_invite_name);
            viewHolder.reason= (TextView) convertView.findViewById(R.id.tv_invite_reason);
            viewHolder.accept= (Button) convertView.findViewById(R.id.btn_invite_accept);
            viewHolder.reject= (Button) convertView.findViewById(R.id.btn_invite_reject);

            //通过setTag方法，把viewHolder保存在convertview中
            convertView.setTag(viewHolder);
        }else {
            //如果convertView不为空，就把viewHolder取出来,减少findViewById的次数，提高性能
            viewHolder= (ViewHolder) convertView.getTag();
        }
        //2.获取当前item数据,拿到邀请信息对象
        final InvationInfo invationInfo = mInvationInfos.get(position);

        //3.显示当前item数据,获取联系人对象
        UserInfo userInfo=invationInfo.getUser();
        if (userInfo!=null){
            //名称的展示
            viewHolder.name.setText(invationInfo.getUser().getName());

            //接受和拒绝按钮默认不显示
            viewHolder.accept.setVisibility(View.GONE);
            viewHolder.reject.setVisibility(View.GONE);

            //原因
            if (invationInfo.getStatus()==InvationInfo.InvitationStatus.NEW_INVITE){
                //新的邀请
                if (invationInfo.getReason()==null){
                    viewHolder.reason.setText("我要添加你为好友");
                }else {
                    viewHolder.reason.setText(invationInfo.getReason());
                }

                viewHolder.accept.setVisibility(View.VISIBLE);
                viewHolder.reject.setVisibility(View.VISIBLE);
            }else if (invationInfo.getStatus()==InvationInfo.InvitationStatus.INVITE_ACCEPT){
                //接受邀请
                if (invationInfo.getReason()==null){
                    viewHolder.reason.setText("接受邀请");
                }else {
                    viewHolder.reason.setText(invationInfo.getReason());
                }

            }else if (invationInfo.getStatus()==InvationInfo.InvitationStatus.INVITE_ACCEPT_BY_PEEP){
                //邀请被接受
                if (invationInfo.getReason()==null){
                    viewHolder.reason.setText("邀请被接受");
                }else {
                    viewHolder.reason.setText(invationInfo.getReason());
                }
            }

            //按钮的处理
            viewHolder.accept.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnInviteListener.onAccept(invationInfo);
                }
            });
            viewHolder.reject.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnInviteListener.onReject(invationInfo);
                }
            });

        }else {
            //群组
        }

        //4.返回view
        return convertView;
    }
    class ViewHolder{
        private TextView name;
        private TextView reason;
        private Button accept;
        private Button reject;
    }

    //邀请信息监听的接口的对象的创建
    private OnInviteListener mOnInviteListener;

    //不希望所有的业务都在adapter中处理，定义一个接口,邀请信息监听的接口
    //谁来实现这个接口，谁注册了这个adapter,谁就传递过来一个接口，可以通过构造方法传进来，或者set,get方法
    public interface OnInviteListener{
        //联系人接受按钮的点击事件
        void onAccept(InvationInfo invationInfo);

        //联系人拒绝按钮的点击事件
        void onReject(InvationInfo invationInfo);
    }
}
