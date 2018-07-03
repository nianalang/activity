package com.zhaowoba.adapter;
import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zhaowoba.R;
import com.zhaowoba.entity.HttpResult;
import com.zhaowoba.entity.PostDataDelivery;
import com.zhaowoba.entity.ResultData;
import com.zhaowoba.utils.PublishDialog;
import java.io.IOException;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
public class NormalRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public String urlLoginPath = "http://39.105.138.38:8080/zhaowoba/postDataDelivery/postDeliveryMessage";
    private final LayoutInflater mLayoutInflater;
    private final Context mContext;
    /**
     * 任务
     */
    public static final int TASK = 0;

    /**
     * 消息
     */
    public static final int MESSAGE = 1;
    /**
     * 当前类型
     */
    public int currentType = TASK;
    public NormalRecyclerViewAdapter(Context context) {
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
    }
    //用于创建viewHolder实例
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TASK) {
            return new TaskViewHolder(mLayoutInflater.inflate(R.layout.task_item, parent, false), mContext);
        } else if (viewType == MESSAGE) {
            return new MessageViewHolder(mLayoutInflater.inflate(R.layout.message_item, parent, false), mContext);
        }
        return null;
    }
    /**
     * 绑定数据
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == TASK) {
            TaskViewHolder bannerViewHolder = (TaskViewHolder) holder;
            //设置监听//TODO
        } else if (getItemViewType(position) == MESSAGE) {
            MessageViewHolder recommendViewHolder = (MessageViewHolder) holder;
            RecyclerView recycleView = recommendViewHolder.list_view;
            Context recyleContex = recommendViewHolder.mContext;//上下文
            //对RecycerView在进行设置
            recycleView.setLayoutManager(new LinearLayoutManager(recyleContex));
            //TODO添加分割线
            //recycleView.addItemDecoration(new DividerItemDecoration(recyleContex,DividerItemDecoration.HORIZONTAL));
            //设置adapter
            recycleView.setAdapter(new MessageRecyclerViewAdapter(recyleContex));
        }
    }
    /**
     * 根据位置得到类型-系统调用
     *
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {
        switch (position) {
            case TASK:
                currentType = TASK;
                break;
            case MESSAGE:
                currentType = MESSAGE;
                break;
        }
        return currentType;
    }
    /**
     * 返回总条数，共2种类型
     *
     * @return
     */
    @Override
    public int getItemCount() {
        return 2;
    }
    class MessageViewHolder extends RecyclerView.ViewHolder {
        public RecyclerView list_view;
        public Context mContext;
        public MessageViewHolder(View itemView, Context mContext) {
            super(itemView);
            list_view = itemView.findViewById(R.id.list_view);
            this.mContext = mContext;
        }
    }
    class TaskViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public ImageView publishTask;//发布任务
        public ImageView CheckTask;//查看任务
        public ImageView noAccomplishTask;//未完成订单
        public ImageView orderTask;//已完成订单
        public Context mContext;
        public TaskViewHolder(View itemView, Context context) {
            super(itemView);
            publishTask = (ImageView) itemView.findViewById(R.id.iv_publish_task);
            CheckTask = (ImageView) itemView.findViewById(R.id.iv_check_task);
            noAccomplishTask = (ImageView) itemView.findViewById(R.id.iv_no_accomplish_task);
            orderTask = (ImageView) itemView.findViewById(R.id.iv_order_task);
            noAccomplishTask.setOnClickListener(this);
            CheckTask.setOnClickListener(this);
            publishTask.setOnClickListener(this);
            orderTask.setOnClickListener(this);
            this.mContext = context;
        }
        //数据处理
        public void getData() {
            final PublishDialog dialog = new PublishDialog(mContext);
            dialog.setPublishOnclickListener(new PublishDialog.onPublishOnclickListener() {

                @Override
                public void onPublishClick() {
                    //点击发布后
                    //去后台将数据存储起来，并跳到发现界面
                    PostDataDelivery postDataDelivery = new PostDataDelivery();
                    /*
                     * 收件人电话
                     */
                    String post_data_users_phone = PublishDialog.et_dialog_phone.getText().toString();
                    /*
                     * 收件人姓名
                     */
                    String post_data_delivery_name = PublishDialog.et_dialog_name.getText().toString();
                    /*
                     * 快递信息
                     */
                    String post_data_delivery_message = PublishDialog.et_dialog_info.getText().toString();
                    /*
                     * 送达的地点
                     */
                    String post_data_delivery_address = PublishDialog.et_dialog_locate.getText().toString();
                    /*
                     *代取价格
                     */
                    String post_data_replace_price = PublishDialog.et_dialog_price.getText().toString();
                    //得到用户发布的数据
                    postDataDelivery.setPost_data_delivery_address(post_data_delivery_address);
                    postDataDelivery.setPost_data_delivery_message(post_data_delivery_message);
                    postDataDelivery.setPost_data_delivery_name(post_data_delivery_name);
                    postDataDelivery.setPost_data_replace_price(post_data_replace_price);
                    postDataDelivery.setPost_data_users_phone(post_data_users_phone);
                    //获取数据
                    ResultData resultData = getDataServer(postDataDelivery);
                    if (resultData != null) {
                        if (7 == resultData.getState()) { //7代表发布成功
                            dialog.dismiss();
                            //调到发现fragment里
                            Toast.makeText(mContext, resultData.getStateInfo(), Toast.LENGTH_SHORT).show();
                        } else {//登陆失败
                            Toast.makeText(mContext, resultData.getStateInfo(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            });
            dialog.setCancelOnclickListener(new PublishDialog.onCancelOnclickListener() {

                @Override
                public void onCancelClick() {
                    //点击取消后
                    dialog.dismiss();
                }
            });
            dialog.show();
        }
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.iv_publish_task://发布任务
                    getData();
                    break;
                case R.id.iv_check_task://查看任务
                    break;
                case R.id.iv_no_accomplish_task://未完成订单
                    break;
                case R.id.iv_order_task://已完成订单
                    break;
            }
        }
    }
    ResultData resultData = null;
    public ResultData getDataServer(PostDataDelivery postDataDelivery) {
        //新建一个对象
        OkHttpClient client = new OkHttpClient();
        client.connectTimeoutMillis();
        RequestBody body = new FormBody.Builder()
                .add("post_data_delivery_address", postDataDelivery.getPost_data_delivery_address())//请求的参数单
                .add("post_data_delivery_message", postDataDelivery.getPost_data_delivery_message())
                .add("post_data_delivery_name", postDataDelivery.getPost_data_delivery_name())
                .add("post_data_replace_price", postDataDelivery.getPost_data_replace_price())
                .add("post_data_users_phone", postDataDelivery.getPost_data_users_phone())
                .add("post_data_person_phone", "")
                .build();
        Request request = new Request.Builder().url(urlLoginPath).post(body).build(); //构建一个request对象
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("LoginActivity", "发布失败");
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseData = response.body().string();
                Gson gson = new Gson();//使用son进行解析
                HttpResult<ResultData> httpResult = gson.fromJson(responseData, new TypeToken<HttpResult<ResultData>>() {
                }.getType());
                resultData = httpResult.getData();
            }
        });
        return resultData;
    }
}