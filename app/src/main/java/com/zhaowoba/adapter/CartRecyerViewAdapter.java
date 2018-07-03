package com.zhaowoba.adapter;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.zhaowoba.R;
public class CartRecyerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private LayoutInflater mLayoutInflater;
    private Context mContext;
    public static final int CONTENT = 1;
    /**
     * 当前类型
     */
    public int currentType = CONTENT;

    public CartRecyerViewAdapter(Context context) {
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == CONTENT) {
            return new CartViewHolder(mLayoutInflater.inflate(R.layout.fragment_cart_order_item, parent, false), mContext);
        }
        return null;
    }
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == CONTENT) {
            CartViewHolder cartNoViewHolder = (CartViewHolder) holder;
        }
    }
    @Override
    public int getItemCount() {
        return 5;
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
            case CONTENT:
                currentType = CONTENT;
                break;
        }
        return currentType;
    }
    class CartViewHolder extends RecyclerView.ViewHolder {
        public Context mContext;
        public CartViewHolder(View itemView, Context mContext) {
            super(itemView);
            this.mContext = mContext;
        }
    }
}
