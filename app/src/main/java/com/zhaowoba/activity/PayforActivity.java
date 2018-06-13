package com.zhaowoba.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.zhaowoba.R;
import com.zhaowoba.entity.PayResult;
import com.zhaowoba.utils.SignUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

/**
 * Created by 念阿郎 on 2018/5/26.
 */

public class PayforActivity extends Activity {

    public static final String PARTNER = "2017062807585767";

    // 商户收款账号
    public static final String SELLER = "";

    // 商户私钥，pkcs8格式
    public static final String RSA_PRIVATE = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCNYm+oveZOECAjwrH1E+RHznGxVqdAKI/teijarKYIV7RjpNyfMaEaI0ms8vd9aXtN6gEeSPvBQmWVunY1FWfLpAOkSYGJLJ8GJEgiNTAstCgkHw21DaojrD9LxoUZbvfBwWXiDLDAPUGiU6pnG7AkClJuzSETMCTWsrcB35Y9MMprnPaXgNG8+MJ6P2Z1xmN51uNQw4Z99iDrR27lrQH/OXNzLnRDzlj0rwoYFHDSPds58qmjVRTcBXCVpZoLmuf4OfSc8gplNGz/qs/rjOfKEOrcZQeKw1SCkG5U4ZHsMM5XmwbCGg20G9+BokYdHJNKFKu/+kwu69No1Mcy8RTfAgMBAAECggEAIXBCkFo5egT+VPbbN+d4ejMtWI/yBo6RW80klHN44Ug89cQsGcqXG6N07V6ZgiPMceUCVrNUN6UIeZ0cD/n8DoHACr8Hz/Wptr4mAVErD6ecRs7BYyzULJO0dKuDFzzThBPFkO0HcLAMMeQvzSsTQbLfRC1nwS4FyHGELwE+e0IQy3wug7jAid/X2crGC438pwxS7iCjZxsO44WCteCLTjIG/y2AR42wJXSRlPpsGQP6CVgUKa1ATEsoGBDoImDAitnPAyADyOvRMf3jqOcadWq8MtXKPM1KyfM1Sq+NgPawwXxdBHPXB4aDPHmoZm3qb8Nat1VkbTfnmnFNVNiGAQKBgQDGcR0xEI/oP/HRdhKQJCNguUN2dcXIfbfLj4ff9yMtQ+086W3BpJYO5rq6B8mXU66wg3crKJHwpaQ5a6CXb1U757y2J2qPccKdy3ZXed7z0bEkGxwPzwkAiNXM30KvHO9QxVFX3oILDca2qOk7h5vRrRCH9GHdZkYgf7F0WRFwnwKBgQC2ZKYOVPE881ek0SFHURuTN99M+MsciyLzJNeRpopXCBvViRV3rMvyzCRsciJEqQmZnQM7VDkqh3MtutEDnPv2Qux3Qlhk756Q8PdmS9hPl9WK8NGSSA6AQFGqrV16ngjYRm1h+fm6c6K9YFaoJXw/5qYF48X0hXRE39++TXSzwQKBgBnji/Fovb2JCh1PkCBp9ouZ3+lGeCUt8ZqHAS0A6v/uyraVpZILzN/ozheTCIPLkRDKNfPVeSSyF3i+R9c52R7VntMM1WQdbUx0zN2gsquQgdG6D7EoS35cW7g8sFB0L+yTsYcLKmASzgfqhXMUwAlc0LlL8rCVtTRsNFR/gjz1AoGAUiANmSRsHvqe+wpjRp5hoS8mL51Srz6C9SIgomdvoPJ4vfRkoyc+Ccwblmzpuyq1tOI640rwFpM4rF2S4WKdHOxTVvubm489QZwOeZQrCOOf9liqtIgXZ24Ol6BKF/zylJdZhyUsaeTJYSXwvvNp98fd94bwykIQ8TYwo5pyssECgYAZC+l1Ok0VJyisBLgOHoAuwYmWbFRC0RJAwQQoTs4/ozHiR+kFOgiHY6W7sjfgdMej+0U0gNifm2nn0lj1KRuOXiAzkzRBTkiwDChP0PAa2ns9GSbxApRVPJJzeM2NlRX4ptscjKUqWB3tgqPNWDTjW0d7iCYeFWkx0GfRgSwHaQ==";

    private static final int SDK_PAY_FLAG = 1;

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    PayResult payResult = new PayResult((String) msg.obj);
                    /**
                     * 同步返回的结果必须放置到服务端进行验证（验证的规则请看https://doc.open.alipay.com/doc2/
                     * detail.htm?spm=0.0.0.0.xdvAU6&treeId=59&articleId=103665&
                     * docType=1) 建议商户依赖异步通知
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息

                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为“9000”则代表支付成功，具体状态码代表含义可参考接口文档
                    if (TextUtils.equals(resultStatus, "9000")) {
                        Toast.makeText(PayforActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
                    } else {
                        // 判断resultStatus 为非"9000"则代表可能支付失败
                        // "8000"代表支付结果因为支付渠道原因或者系统原因还在等待支付结果确认，最终交易是否成功以服务端异步通知为准（小概率状态）
                        if (TextUtils.equals(resultStatus, "8000")) {
                            Toast.makeText(PayforActivity.this, "支付结果确认中", Toast.LENGTH_SHORT).show();

                        } else {
                            // 其他值就可以判断为支付失败，包括用户主动取消支付，或者系统返回的错误
                            Toast.makeText(PayforActivity.this, "支付失败", Toast.LENGTH_SHORT).show();

                        }
                    }
                    break;
                }
                default:
                    break;
            }
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);
    }

    public void testAlipay(View view) {
        if (TextUtils.isEmpty(PARTNER) || TextUtils.isEmpty(RSA_PRIVATE)) {
            new AlertDialog.Builder(this).setTitle("警告").setMessage("需要配置PARTNER | RSA_PRIVATE| SELLER")
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialoginterface, int i) {
                            //
                            finish();
                        }
                    }).show();
            return;
        }

        String orderInfo = getOrderInfo("测试的商品", "该测试商品的详细描述", "0.01");

/**
 * 特别注意，这里的签名逻辑需要放在服务端，切勿将私钥泄露在代码中！
 */
        String sign = sign(orderInfo);
        try {
            /**
             * 仅需对sign 做URL编码
             */
            sign = URLEncoder.encode(sign, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

/**
 * 完整的符合支付宝参数规范的订单信息
 */
        final String payInfo = orderInfo + "&sign=\"" + sign + "\"&" + getSignType();

        Runnable payRunnable = new Runnable() {

            @Override
            public void run() {
                // 构造PayTask 对象
                PayTask alipay = new PayTask(PayforActivity.this);
                // 调用支付接口，获取支付结果
                String result = alipay.pay(payInfo, true);

                Message msg = new Message();
                msg.what = SDK_PAY_FLAG;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };

// 必须异步调用
        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }


    /**
     * create the order info. 创建订单信息
     */
    private String getOrderInfo(String subject, String body, String price) {

        // 签约合作者身份ID
        String orderInfo = "partner=" + "\"" + PARTNER + "\"";

        // 签约卖家支付宝账号
        orderInfo += "&seller_id=" + "\"" + SELLER + "\"";

        // 商户网站唯一订单号
        orderInfo += "&out_trade_no=" + "\"" + getOutTradeNo() + "\"";

        // 商品名称
        orderInfo += "&subject=" + "\"" + subject + "\"";

        // 商品详情
        orderInfo += "&body=" + "\"" + body + "\"";

        // 商品金额
        orderInfo += "&total_fee=" + "\"" + price + "\"";

        // 服务器异步通知页面路径
        orderInfo += "&notify_url=" + "\"" + "http://notify.msp.hk/notify.htm" + "\"";

        // 服务接口名称， 固定值
        orderInfo += "&service=\"mobile.securitypay.pay\"";

        // 支付类型， 固定值
        orderInfo += "&payment_type=\"1\"";

        // 参数编码， 固定值
        orderInfo += "&_input_charset=\"utf-8\"";

        // 设置未付款交易的超时时间
        // 默认30分钟，一旦超时，该笔交易就会自动被关闭。
        // 取值范围：1m～15d。
        // m-分钟，h-小时，d-天，1c-当天（无论交易何时创建，都在0点关闭）。
        // 该参数数值不接受小数点，如1.5h，可转换为90m。
        orderInfo += "&it_b_pay=\"30m\"";

        // extern_token为经过快登授权获取到的alipay_open_id,带上此参数用户将使用授权的账户进行支付
        // orderInfo += "&extern_token=" + "\"" + extern_token + "\"";

        // 支付宝处理完请求后，当前页面跳转到商户指定页面的路径，可空
        orderInfo += "&return_url=\"m.alipay.com\"";

        // 调用银行卡支付，需配置此参数，参与签名， 固定值 （需要签约《无线银行卡快捷支付》才能使用）
        // orderInfo += "&paymethod=\"expressGateway\"";

        return orderInfo;
    }

    /**
     * sign the order info. 对订单信息进行签名
     *
     * @param content 待签名订单信息
     */
    private String sign(String content) {
        return SignUtils.sign(content, RSA_PRIVATE);
    }

    /**
     * get the sign type we use. 获取签名方式
     */
    private String getSignType() {
        return "sign_type=\"RSA\"";
    }

    /**
     * get the out_trade_no for an order. 生成商户订单号，该值在商户端应保持唯一（可自定义格式规范）
     */
    private String getOutTradeNo() {
        SimpleDateFormat format = new SimpleDateFormat("MMddHHmmss", Locale.getDefault());
        Date date = new Date();
        String key = format.format(date);

        Random r = new Random();
        key = key + r.nextInt();
        key = key.substring(0, 15);
        return key;
    }

}