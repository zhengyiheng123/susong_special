package com.xyd.susong.address;

import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.xyd.susong.R;
import com.xyd.susong.api.AddressApi;
import com.xyd.susong.base.BaseActivity;
import com.xyd.susong.base.BaseApi;
import com.xyd.susong.base.BaseModel;
import com.xyd.susong.base.BaseObserver;
import com.xyd.susong.base.EmptyModel;
import com.xyd.susong.base.RxSchedulers;
import com.xyd.susong.promptdialog.PromptDialog;
import com.yby.areaselector.SelectAreaDialog;

import butterknife.Bind;

/**
 * @author: zhaoxiaolei
 * @date: 2017/7/25
 * @time: 15:02
 * @description:
 */

public class AddressEditActivity extends BaseActivity {
    @Bind(R.id.base_title_back)
    TextView baseTitleBack;
    @Bind(R.id.base_title_title)
    TextView baseTitleTitle;
    @Bind(R.id.base_title_menu)
    ImageView baseTitleMenu;
    @Bind(R.id.address_edit_name)
    EditText addressEditName;
    @Bind(R.id.address_edit_phone)
    EditText addressEditPhone;
    @Bind(R.id.address_edit_qu)
    EditText addressEditQu;
    @Bind(R.id.address_edit_jie)
    EditText addressEditJie;
    @Bind(R.id.address_edit_detail)
    EditText addressEditDetail;
    @Bind(R.id.address_edit_moren)
    TextView addressEditMoren;
    @Bind(R.id.address_edit_moren1)
    CheckBox addressEditMoren1;
    @Bind(R.id.address_edit_del)
    TextView addressEditDel;
    @Bind(R.id.address_edit_save)
    TextView addressEditSave;
    @Bind(R.id.address_edit_area)
    TextView address_edit_area;
    private PromptDialog dialog;
    private String a_id;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_address_edit;
    }

    @Override
    protected void initView() {
        a_id = getIntent().getStringExtra("a_id");
        String a_name = getIntent().getStringExtra("a_name");
        String a_area = getIntent().getStringExtra("a_area");
        String a_address = getIntent().getStringExtra("a_address");
        String link_phone = getIntent().getStringExtra("link_phone");
        String is_default = getIntent().getStringExtra("is_default");
        addressEditName.setText(a_name);
        addressEditPhone.setText(link_phone);
        addressEditQu.setText(a_area);
        addressEditJie.setText(a_address);
        if (a_id.equals(""))
            baseTitleTitle.setText("添加地址");
        else
            baseTitleTitle.setText("修改地址");
        baseTitleMenu.setVisibility(View.INVISIBLE);
        dialog = new PromptDialog(this);


    }

    @Override
    protected void initEvent() {
        baseTitleBack.setOnClickListener(this);
        addressEditMoren.setOnClickListener(this);
        addressEditDel.setOnClickListener(this);
        addressEditSave.setOnClickListener(this);
        address_edit_area.setOnClickListener(this);
    }

    @Override
    public void widgetClick(View v) {
        switch (v.getId()) {
            case R.id.base_title_back:
                finish();
                break;
            case R.id.address_edit_moren:
                if (addressEditMoren1.isChecked())
                    addressEditMoren1.setChecked(false);
                else
                    addressEditMoren1.setChecked(true);
                break;
            case R.id.address_edit_del:

                break;
            case R.id.address_edit_save:
                if (TextUtils.isEmpty(addressEditName.getText().toString()))
                    showToast("请填写收货人");
                else if (TextUtils.isEmpty(addressEditPhone.getText().toString()))
                    showToast("请填写收货人电话");
                else if (TextUtils.isEmpty(addressEditJie.getText().toString()))
                    showToast("请填写收货人详细地址");
                else if (TextUtils.isEmpty(address_edit_area.getText().toString()))
                    showToast("请选择地区");
                else if (TextUtils.isEmpty(a_id))
                    addAddress(addressEditName.getText().toString(),
                            addressEditQu.getText().toString(),
                            address_edit_area.getText().toString()+" "+addressEditJie.getText().toString(),
                            addressEditPhone.getText().toString());
                else
                    editAddress(addressEditName.getText().toString(),
                            addressEditQu.getText().toString(),
                            address_edit_area.getText().toString()+" "+addressEditJie.getText().toString(),
                            addressEditPhone.getText().toString());
                break;
            case R.id.address_edit_area:
                SelectAreaDialog selectAreaDialog = new SelectAreaDialog(this);
                selectAreaDialog.setOnConfirmListener(new SelectAreaDialog.OnConfirmListener() {
                    @Override
                    public void getData(String provice, String city, String district) {
//                        Toast.makeText(getApplicationContext(), provice + city + district, Toast.LENGTH_SHORT).show();
                            address_edit_area.setText(provice +" "+ city +" "+ district);
                    }
                });
                selectAreaDialog.show();
                break;
        }

    }

    private void editAddress(String name, String area, String address, String phone) {
        dialog.showLoading("");
        BaseApi.getRetrofit()
                .create(AddressApi.class)
                .editAddress(a_id, name, area, address, phone)
                .compose(RxSchedulers.<BaseModel<EmptyModel>>compose())
                .subscribe(new BaseObserver<EmptyModel>() {
                    @Override
                    protected void onHandleSuccess(EmptyModel emptyModel, String msg, int code) {
                        dialog.dismissImmediately();
                        finish();
                    }

                    @Override
                    protected void onHandleError(String msg) {
                        showToast(msg);
                    }
                });

    }

    private void addAddress(String name, String area, String address, String phone) {
        dialog.showLoading("");
        BaseApi.getRetrofit()
                .create(AddressApi.class)
                .addAddress(name, area, address, phone)
                .compose(RxSchedulers.<BaseModel<EmptyModel>>compose())
                .subscribe(new BaseObserver<EmptyModel>() {
                    @Override
                    protected void onHandleSuccess(EmptyModel emptyModel, String msg, int code) {
                        dialog.dismissImmediately();
                        finish();
                    }

                    @Override
                    protected void onHandleError(String msg) {
                        dialog.dismissImmediately();
                        showToast(msg);
                    }
                });
    }


}
