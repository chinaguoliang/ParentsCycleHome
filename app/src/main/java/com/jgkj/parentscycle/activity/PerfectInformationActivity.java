package com.jgkj.parentscycle.activity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jgkj.parentscycle.R;
import com.jgkj.parentscycle.adapter.PerfectInformationAdapter;
import com.jgkj.parentscycle.bean.GetSevenCowTokenInfo;
import com.jgkj.parentscycle.bean.PerfectInfoInfo;
import com.jgkj.parentscycle.global.BgGlobal;
import com.jgkj.parentscycle.json.GetSevenCowTokenPaser;
import com.jgkj.parentscycle.json.GetVerifyPhoneNumPaser;
import com.jgkj.parentscycle.json.PerfectInfoPaser;
import com.jgkj.parentscycle.net.NetBeanSuper;
import com.jgkj.parentscycle.net.NetListener;
import com.jgkj.parentscycle.net.NetRequest;
import com.jgkj.parentscycle.user.UserInfo;
import com.jgkj.parentscycle.utils.LogUtil;
import com.jgkj.parentscycle.utils.QiNiuUploadImgManager;
import com.jgkj.parentscycle.utils.ToastUtil;
import com.jgkj.parentscycle.utils.UtilTools;
import com.qiniu.android.common.Zone;
import com.qiniu.android.http.ResponseInfo;
import com.qiniu.android.storage.Configuration;
import com.qiniu.android.storage.KeyGenerator;
import com.qiniu.android.storage.Recorder;
import com.qiniu.android.storage.UpCancellationSignal;
import com.qiniu.android.storage.UpCompletionHandler;
import com.qiniu.android.storage.UpProgressHandler;
import com.qiniu.android.storage.UploadManager;
import com.qiniu.android.storage.UploadOptions;
import com.qiniu.android.storage.persistent.FileRecorder;
import com.qiniu.android.utils.UrlSafeBase64;

import org.json.JSONObject;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by chen on 16/7/24.
 */
public class PerfectInformationActivity extends BaseActivity implements View.OnClickListener,NetListener{
    private static final String TAG = "PerfectInformationActivity";

    @Bind(R.id.perfect_information_activity_lv)
    ListView mListView;

    @Bind(R.id.baby_document_activity_back_iv)
    ImageView backIv;

    @Bind(R.id.baby_document_activity_title)
    TextView titleTv;

    @Bind(R.id.baby_document_right_title_tv)
    TextView rightTitleTv;

    @Bind(R.id.title_bar_layout_rel)
    RelativeLayout mWrapTitleRel;

    @Bind(R.id.perfect_information_activity_save_btn)
    Button saveBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.perfect_information_activity);
        ButterKnife.bind(this);
        mPerfectInformationAdapter = new PerfectInformationAdapter(this, getContentData());
        mListView.setAdapter(mPerfectInformationAdapter);
        titleTv.setText("完善资料");
        rightTitleTv.setVisibility(View.GONE);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    //点击了用户头像
                    showChangePhotoDialog();
                }
            }
        });
    }

    private List<String> getContentData() {
        ArrayList<String> data = new ArrayList<String>();
        data.add("头像_ ");
        data.add("昵称_ ");
        data.add("帐号_ ");
        data.add("地区_ ");
        data.add("性别_ ");
        data.add("家庭角色_ ");
        data.add("宝宝名字_ ");
        data.add("宝宝性别_ ");
        data.add("宝宝年龄_ ");
        return data;
    }

    @OnClick({R.id.baby_document_activity_back_iv,R.id.perfect_information_activity_save_btn})
    @Override
    public void onClick(View v) {
        if (v == backIv) {
            finish();
        } else if (v == saveBtn) {
            showProgressDialog();
            requestSave();
        }
    }



    public void requestSave() {
        HashMap<String, String> requestData = new HashMap<String, String>();
        HashMap<Integer,String> data = mPerfectInformationAdapter.getData();
        requestData.put("analysis","1");
        requestData.put("birthdate","1987-11-11");
        requestData.put("classid","1,2,3");
        if (TextUtils.isEmpty(uploadImgKeyStr)) {
            requestData.put("headportrait","");
        } else {
            requestData.put("headportrait",BgGlobal.IMG_SERVER_PRE_URL + uploadImgKeyStr);
        }
        requestData.put("kbwx","1"); //1: 是  0：否
        requestData.put("kbqq","1");
        requestData.put("nationality","1");
        requestData.put("nickname",data.get(1));
        requestData.put("onthejob","1"); // 1:在职  0： 离职
        requestData.put("permissions","1");
        requestData.put("phone",UserInfo.loginInfo.getInfo().getPhone());
        requestData.put("schoolname","橙子班");
        requestData.put("teacherid","1");
        requestData.put("teachername","哈哈");
        requestData.put("teachersex","0");
        requestData.put("tmpinfoid", UserInfo.loginInfo.getRole().getId());
        requestData.put("schoolid", "1");  //暂时传1

//        requestData.put("schoolid","1");
//        requestData.put("babyname",data.get(6));
//        requestData.put("familyrole",data.get(5));
//        requestData.put("babyage",data.get(8));
//
//        requestData.put("babysex",data.get(7));
//        requestData.put("sex",data.get(4));
//        requestData.put("region",data.get(3));
//        requestData.put("account",data.get(2));
//        requestData.put("sex","1");
        PerfectInfoPaser lp = new PerfectInfoPaser();
        NetRequest.getInstance().request(mQueue, this, BgGlobal.TEACHER_INFO_SAVE, requestData, lp);
    }

    @Override
    public void requestResponse(Object obj) {
        NetBeanSuper nbs = (NetBeanSuper)obj;
        hideProgressDialog();
        if (nbs.obj instanceof PerfectInfoInfo) {
            if (nbs.isSuccess()) {
                finish();
            }

            ToastUtil.showToast(this, nbs.getMsg(), Toast.LENGTH_SHORT);
        }

    }


}
